package ca.mcgill.ecse.smss.view;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SmssPage extends JFrame {
	private static final long serialVersionUID = -4426310869335015542L;
	
	// UI elements
	// sender
	private JLabel senderSectionLabel;
	private JLabel senderTypeLabel;
	private JTextField senderTypeField;
	private JLabel methodNameLabel;
	private JTextField methodNameField;
	private JLabel senderNameLabel;
	private JTextField senderNameField;
	private JButton updateSenderButton;
	
	public SmssPage() {
		prepareUI();
		refreshData();
	}
	
	private void prepareUI() {
		// sender
		senderSectionLabel = new JLabel("Sender");
		senderTypeLabel = new JLabel("Sender Type: ");
		senderTypeField = new JTextField();
		methodNameLabel = new JLabel("Method Name: ");
		methodNameField = new JTextField();
		senderNameLabel = new JLabel("SenderName");
		senderNameField = new JTextField();
		updateSenderButton = new JButton("update");
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		
		// line
		JSeparator horizontalLineTop = new JSeparator();
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
			.addGroup(
				layout.createParallelGroup()
				.addComponent(senderSectionLabel)
				.addGroup(
					layout.createSequentialGroup()
					.addComponent(senderTypeLabel)
					.addComponent(senderTypeField)
					.addComponent(methodNameLabel)
					.addComponent(methodNameField)
					.addComponent(senderNameLabel)
					.addComponent(senderNameField)
					.addComponent(updateSenderButton)
				).addComponent(horizontalLineTop)
			)
		);
		
		layout.linkSize(SwingConstants.HORIZONTAL, 
				new java.awt.Component[] {
						senderNameField, methodNameField, 
						senderTypeField, senderTypeLabel
		});
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
			.addComponent(senderSectionLabel)
			.addGroup(
				layout.createParallelGroup()
				.addComponent(senderTypeLabel)
				.addComponent(senderTypeField)
				.addComponent(methodNameLabel)
				.addComponent(methodNameField)
				.addComponent(senderNameLabel)
				.addComponent(senderNameField)
				.addComponent(updateSenderButton)
			)
			.addComponent(horizontalLineTop)
		);

		pack();
	}
	
	private void refreshData() {
		
	}
	
	
}
