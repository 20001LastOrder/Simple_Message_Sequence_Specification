package ca.mcgill.ecse.smss.view;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import ca.mcgill.ecse.smss.controller.InvalidInputException;
import ca.mcgill.ecse.smss.controller.SmssController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SmssViewApplication extends Application {
	Stage mainStage;
	GridPane root;
	Event refreshUIEvent;
	EventType<Event> refreshUIEventType;
	List<Node> refreshableNodes;
	
	
	@Override
	public void start(Stage stage) throws Exception {
		this.mainStage = stage;
		refreshableNodes = new ArrayList<>();
		
		refreshUIEventType = new EventType<>("REFRESH");
		
		root = makeUI();
		//Creating a scene object 
		Scene scene = new Scene(root);  
		//Setting title to the Stage 
		stage.setTitle("SMSS"); 
		//Adding scene to the stage 
		stage.setScene(scene);  
		stage.show(); 
		refreshUI();
	}
	
	private GridPane makeUI() {
	     //Creating a Grid Pane 
		GridPane gridPane = new GridPane();    
	  
		//Setting size for the pane  
//		gridPane.setMinSize(400, 200); 
	   
		//Setting the padding  
		gridPane.setPadding(new Insets(10, 10, 10, 10)); 
	  
		//Setting the vertical and horizontal gaps between the columns 
		gridPane.setVgap(5); 
		gridPane.setHgap(5);       
  
		//Setting the Grid alignment 
		gridPane.setAlignment(Pos.CENTER); 
		
		// set grid pane elements
		gridPane.add(makeSenderSection(), 0, 0, 3, 1);
		gridPane.add(new Separator(Orientation.HORIZONTAL), 0, 1, 3, 1);
		gridPane.add(makeReceiverTypeSection(), 0, 2);
		gridPane.add(new Separator(Orientation.VERTICAL), 1, 2);
		gridPane.add(makeReceiverSection(), 2, 2);
		gridPane.add(new Separator(Orientation.HORIZONTAL), 0, 3, 3, 1);
		gridPane.add(makeMessageSection(), 0, 4);
		gridPane.add(new Separator(Orientation.HORIZONTAL), 0, 5);
		gridPane.add(makeAddToMethodSection(), 0, 6);
		gridPane.add(new Separator(Orientation.HORIZONTAL), 0, 7);
		gridPane.add(makeFragmentSection(), 0, 8);
		gridPane.add(new Separator(Orientation.HORIZONTAL), 0, 9);
		gridPane.add(makeDeletionSection(), 0, 10);
		gridPane.add(new Separator(Orientation.VERTICAL), 1, 4, 1, 7);
		gridPane.add(makeMethodVisualizationSection(), 2, 4, 1, 7);

		return gridPane;
}
	
	private Node makeSenderSection() {
		// create sender related UI elements
		Text senderTypeText = new Text("Sender Type: ");
		TextField senderTypeField = new TextField();
		Text methodNameText = new Text("Method Name: ");
		TextField methodNameField = new TextField();
		Text senderNameText = new Text("Sender Name: ");
		TextField senderNameField = new TextField();
		Button updateSenderButton = new Button("Update");
		
		updateSenderButton.setOnAction(a -> {
			if (senderTypeField.getText().trim().length() == 0 ||
				methodNameField.getText().trim().length() == 0 ||
				senderNameField.getText().trim().length() == 0) {
				makePopupWindow("Type, sender name and method name need to be specified");
				return;
			}
			
			try {
				SmssController.updateSenderInfo(senderTypeField.getText(),
						senderNameField.getText(), methodNameField.getText());
				senderTypeField.clear();
				senderNameField.clear();
				methodNameField.clear();
				refreshUI();
			} catch (InvalidInputException e) {
				makePopupWindow(e.getMessage());
			}
		});
		
		// create section container
		HBox senderSection = new HBox();
		senderSection.setSpacing(10);
		senderSection.getChildren().addAll(senderTypeText, senderTypeField,
										   methodNameText, methodNameField,
										   senderNameText, senderNameField,
										   updateSenderButton);
		return senderSection;
	}

	private Node makeReceiverSection() {
		// create UI elements
		Label receiverSectionLabel = new Label("Add Receiver");
		Text receiverTypeText = new Text("Receiver Type");
		ChoiceBox<String> receiverTypeChoice = new ChoiceBox<String>();
		Text receiverNameText = new Text("Receiver Name");
		TextField receiverNameField = new TextField();
		Button addReceiverButton = new Button("Add");
		
		// TODO add event handling on refreshing for receiverTypeChoice
		receiverTypeChoice.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		GridPane.setHgrow(receiverTypeChoice, Priority.ALWAYS);
		
		// TODO set action for add button
		addReceiverButton.setOnAction(a -> {
			makePopupWindow("to be implemented!");
		});
		
		// create section container
		GridPane receiverSection = createGridPane();
		
		// add elements to the container
		receiverSection.add(receiverSectionLabel, 0, 0, 3, 1);
		receiverSection.add(receiverTypeText, 0, 1);
		receiverSection.add(receiverNameText, 1, 1);
		receiverSection.add(receiverTypeChoice, 0, 2);
		receiverSection.add(receiverNameField, 1, 2);
		receiverSection.add(addReceiverButton, 2, 2);
		
		return receiverSection;
	}
	
	private Node makeReceiverTypeSection() {		
		return makeSingleTextFieldSection("Add Receiver Type", "Receiver Type: ", "Add", t -> {
			try {
				SmssController.createRecieverType(t);
				refreshUI();
			} catch (InvalidInputException e) {
				// pop up warning to the user
				makePopupWindow(e.getMessage());
			}
		});
	}
	
	private Node makeMessageSection() {
		return makeSingleTextFieldSection("Create Message", "Message Name: ", "Add", t -> {
			try {
				SmssController.createMessage(t);
				refreshUI();
			} catch (InvalidInputException e) {
				// pop up warning to the user
				makePopupWindow(e.getMessage());
			}
		});
	}
	
	private Node makeAddToMethodSection() {
		// create UI element
		Label addToMethodSectionLabel = new Label("Add to Method / Fragment");
		Text messageNameText = new Text("Message Name:");
		ChoiceBox<String> messageChoice = new ChoiceBox<>();
		Text receiverTypeText = new Text("Receiver Type:");
		ChoiceBox<String> receiverTypeChoice = new ChoiceBox<>();
		Text receiverNameText = new Text("Receiver Name:");
		ChoiceBox<String> receiverNameChoice = new ChoiceBox<>();
		Button addButton = new Button("Add");
		
		// create section container
		GridPane addToMethodSection = createGridPane();
		
		// TODO add event handling for different choice boxes
		
		// Maximize sizes of choice boxes and button
		messageChoice.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		GridPane.setHgrow(messageChoice, Priority.ALWAYS);
		receiverTypeChoice.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		GridPane.setHgrow(receiverTypeChoice, Priority.ALWAYS);
		receiverNameChoice.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		GridPane.setHgrow(receiverNameChoice, Priority.ALWAYS);
		addButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		GridPane.setHgrow(addButton, Priority.ALWAYS);
		
		// TODO: Add handling for button
		addButton.setOnAction(a -> {
			makePopupWindow("to be implemented!");
		});
		
		// add UI elements to the container
		addToMethodSection.add(addToMethodSectionLabel, 0, 0);
		addToMethodSection.add(messageNameText, 0, 1);
		addToMethodSection.add(receiverTypeText, 1, 1);
		addToMethodSection.add(receiverNameText, 2, 1);
		addToMethodSection.add(messageChoice, 0, 2);
		addToMethodSection.add(receiverTypeChoice, 1, 2);
		addToMethodSection.add(receiverNameChoice, 2, 2);
		addToMethodSection.add(addButton, 2, 3);
		
		// add on refresh event for the section
		addToMethodSection.addEventHandler(refreshUIEventType, e -> {
			messageChoice.setItems(FXCollections.observableList(SmssController.getMessages()));
			// TODO set items for other choice boxes
		});
		refreshableNodes.add(addToMethodSection);

		return addToMethodSection;
	}
	
	enum FragmentType {
		ALT,
		PAR
	}
	
	private Node makeFragmentSection() {
		// define UI elements
		Label fragmentSecitonLabel = new Label("Fragment Type: ");
		Text fragmentTypeText = new Text("Fragment Name:");
		ChoiceBox<FragmentType> fragmentTypeChoice = new ChoiceBox<>();
		Button createFragmentButton = new Button("Create");
		
		// create section container
		GridPane section = createGridPane();
		
		// define fragment type choices, default choice and size
		fragmentTypeChoice.getItems().addAll(FragmentType.values());
		fragmentTypeChoice.setValue(fragmentTypeChoice.getItems().get(0));
		fragmentTypeChoice.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		GridPane.setHgrow(fragmentTypeChoice, Priority.ALWAYS);
		
		
		// TODO: Add handling for button
		createFragmentButton.setOnAction(a -> {
			makePopupWindow("to be implemented!");
			
			FragmentType selectedType = fragmentTypeChoice.getValue();
			root.add(makeOperandSection(selectedType), 
					GridPane.getColumnIndex(section), 
					GridPane.getRowIndex(section));
			root.getChildren().remove(section);
		});
		
		// Add UI elements to the container
		section.add(fragmentSecitonLabel, 0, 0);
		section.add(fragmentTypeText, 0, 1);
		section.add(fragmentTypeChoice, 0, 2);
		section.add(createFragmentButton, 1, 2);
		
		return section;
	}
	
	private Node makeOperandSection(FragmentType fragmentType) {
		// define UI elements
		Label operandSectionLabel = new Label("Define Operand");
		Text conditionText = new Text("Condition: ");
		TextField conditionField = new TextField();
		Button addNewOperandButton = new Button("Add New Operand");
		Button endFragmentButton = new Button("End Fragment");
		
		// create section container
		GridPane section = createGridPane();
		
		// set UI element size
		GridPane.setHgrow(conditionText, Priority.ALWAYS);
		addNewOperandButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		GridPane.setHgrow(addNewOperandButton, Priority.ALWAYS);
		endFragmentButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		GridPane.setHgrow(endFragmentButton, Priority.ALWAYS);
		
		// TODO: add event handling for buttons
		addNewOperandButton.setOnAction(a -> {
			makePopupWindow("to be implemented!");
		});
		
		endFragmentButton.setOnAction(a -> {
			// TODO: define condition for this button action
			
			root.add(makeFragmentSection(), 
					GridPane.getColumnIndex(section), 
					GridPane.getRowIndex(section));
			root.getChildren().remove(section);
		});
		
		// add UI elements to the container
		int rowIndex = 0;
		int columnIndex = 0;
		section.add(operandSectionLabel, columnIndex, rowIndex++);
		if (fragmentType == FragmentType.ALT) {
			section.add(conditionText, columnIndex, rowIndex++);
			section.add(conditionField, columnIndex++, rowIndex);
		}
		section.add(addNewOperandButton, columnIndex, rowIndex++);
		section.add(endFragmentButton, columnIndex, rowIndex);
		
		return section;
	}
	
	private Node makeDeletionSection() {
		// create UI elements
		Button deleteLastMessageButton = new Button("Delete the Last Message");
		
		// create section container
		GridPane section = createGridPane();
		section.setAlignment(Pos.CENTER);

		// set button size and action
		deleteLastMessageButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		GridPane.setHgrow(deleteLastMessageButton, Priority.ALWAYS);
		// TODO: add event handling for buttons
		deleteLastMessageButton.setOnAction(a -> {
			makePopupWindow("to be implemented!");
		});
		
		// add UI elements to the container
		section.getChildren().add(deleteLastMessageButton);
		
		return section;
	}
	
	private Node makeMethodVisualizationSection() {
		ScrollPane methodVisualizationSection = new ScrollPane();
		
		// TODO Remove placeholder
		Text methodText = new Text("asdasdadasd\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n \n\n\n\n\n\n\n\nasedse");
		// TODO Add action listener to retrieve the text content
		methodVisualizationSection.addEventHandler(refreshUIEventType, e -> {
			methodText.setText(SmssViewFormatUtil.createFormattedMethodContent());
			e.consume();
		});
		refreshableNodes.add(methodVisualizationSection);
		
		methodVisualizationSection.setMaxHeight(400);;
		methodVisualizationSection.setContent(methodText);
		
		return methodVisualizationSection;
	}
	
	private GridPane createGridPane() {
		GridPane section = new GridPane();
		section.setPadding(new Insets(10, 10, 10, 10)); 
		section.setVgap(5); 
		section.setHgap(10);
		
		return section;
	}
	
	private void makePopupWindow(String message) {
		Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(mainStage);
		VBox dialogPane = new VBox();
		
		// create UI elements
		Text text = new Text(message);
		Button okButton = new Button("OK");
		okButton.setOnAction((a) -> {
			dialog.close();
		});
		
		// Create scene and add UI elements to the scene
		dialogPane.setSpacing(10);
		dialogPane.setAlignment(Pos.CENTER);
		dialogPane.setPadding(new Insets(10, 10, 10, 10)); 
		dialogPane.getChildren().addAll(text, okButton);
		Scene dialogScene = new Scene(dialogPane, 400, 100);
		dialog.setScene(dialogScene);
		dialog.setTitle("Dialog"); 
		dialog.show();
	}
	
	private Node makeSingleTextFieldSection(String sectionName, String labelName, 
											String buttonName, Consumer<String> textConsumer) {
		// create UI elements
		Label sectionLabel = new Label(sectionName);
		Text text = new Text(labelName);
		TextField field = new TextField();
		Button button = new Button(buttonName);
		
		// TODO set action of the add button
		button.setOnAction(a -> {
			textConsumer.accept(field.getText());
			field.clear();
		});
		
		// create section container
		GridPane section = new GridPane();
		section.setPadding(new Insets(10, 10, 10, 10)); 
		section.setVgap(5); 
		section.setHgap(10);
		
		// add elements to the container
		section.add(sectionLabel, 0, 0, 3, 1);
		section.add(text, 0, 1);
		section.add(field, 0, 2);
		section.add(button, 1, 2);
		
		return section;
	}
	
	public void refreshUI() {
		for (Node node : refreshableNodes) {
			node.fireEvent(new Event(refreshUIEventType));
		}
	}
}
