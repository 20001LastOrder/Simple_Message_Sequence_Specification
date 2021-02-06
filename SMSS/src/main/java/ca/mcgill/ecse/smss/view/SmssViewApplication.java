package ca.mcgill.ecse.smss.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
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
	
	@Override
	public void start(Stage stage) throws Exception {
		this.mainStage = stage;
		
		Parent root = makeUI();
		  
		//Creating a scene object 
		Scene scene = new Scene(root);  
		  
		//Setting title to the Stage 
		stage.setTitle("SMSS"); 
		     
		//Adding scene to the stage 
		stage.setScene(scene);  
		stage.show(); 
	}
	
	private Parent makeUI() {
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
		gridPane.add(makeMethodVisualizationSection(), 2, 4);
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
		
		// TODO: set action of the update sender button
		updateSenderButton.setOnAction(a -> {
			makePopupWindow("to be implemented!");
		});;
		
		// create section container
		HBox senderSection = new HBox();
		senderSection.setSpacing(10);
		senderSection.getChildren().addAll(senderTypeText, senderTypeField,
										   methodNameText, methodNameField,
										   senderNameText, senderNameField,
										   updateSenderButton);
		return senderSection;
	}
	
	private Node makeReceiverTypeSection() {
		// create UI elements
		Label receiverTypeSectionLabel = new Label("Add Receiver Type");
		Text receiverTypeText = new Text("Receiver Type: ");
		TextField receiverTypeField = new TextField();
		Button addReceiverTypeButton = new Button("Add");
		
		// TODO set action of the add button
		addReceiverTypeButton.setOnAction(a -> {
			makePopupWindow("to be implemented!");
		});
		
		// create section container
		GridPane receiverTypeSection = new GridPane();
		receiverTypeSection.setPadding(new Insets(10, 10, 10, 10)); 
		receiverTypeSection.setVgap(5); 
		receiverTypeSection.setHgap(10);
		
		// add elements to the container
		receiverTypeSection.add(receiverTypeSectionLabel, 0, 0, 3, 1);
		receiverTypeSection.add(receiverTypeText, 0, 1);
		receiverTypeSection.add(receiverTypeField, 0, 2);
		receiverTypeSection.add(addReceiverTypeButton, 1, 2);
		
		return receiverTypeSection;
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
		GridPane receiverSection = new GridPane();
		receiverSection.setPadding(new Insets(10, 10, 10, 10)); 
		receiverSection.setVgap(5); 
		receiverSection.setHgap(10);
		
		// add elements to the container
		receiverSection.add(receiverSectionLabel, 0, 0, 3, 1);
		receiverSection.add(receiverTypeText, 0, 1);
		receiverSection.add(receiverNameText, 1, 1);
		receiverSection.add(receiverTypeChoice, 0, 2);
		receiverSection.add(receiverNameField, 1, 2);
		receiverSection.add(addReceiverButton, 2, 2);
		
		return receiverSection;
	}
	
	private Node makeMethodVisualizationSection() {
		ScrollPane methodVisualizationSection = new ScrollPane();
		Text methodText = new Text("asdasdadasd\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n \n\n\n\n\n\n\n\nasedsd");
		
		// TODO Add action listener to retrieve the text content
		
		methodVisualizationSection.setContent(methodText);
		
		return methodVisualizationSection;
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
		Scene dialogScene = new Scene(dialogPane, 200, 100);
		dialog.setScene(dialogScene);
		dialog.setTitle("Dialog"); 
		dialog.show();
	}
}
