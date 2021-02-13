package ca.mcgill.ecse.smss.controller;

import java.util.List;
import java.util.stream.Collectors;

import ca.mcgill.ecse.smss.application.SmssApplication;
import ca.mcgill.ecse.smss.model.Fragment;
import ca.mcgill.ecse.smss.model.Message;
import ca.mcgill.ecse.smss.model.ReceiverClass;
import ca.mcgill.ecse.smss.model.SMSS;

public class SmssController {
	
	public static void createMessage(String name) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		
		if (name == null || name.trim().length() == 0) {
			throw new InvalidInputException("The message name cannot be null or empty");
		}
		
		// check if the message name already exists in the system
		if (Message.hasWithName(name)) {
			throw new InvalidInputException("The message name already exists");
		}
		
		smss.addMessage(name);
	}

	public static void updateSenderInfo(String className, String objectName, String methodName) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		try {
			smss.setSenderClass(className);
			smss.setSenderName(objectName);
			smss.setMethodName(methodName);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public static String getSenderClass() {
		return SmssApplication.getSmss().getSenderClass();
	}
	
	public static String getSenderName() {
		return SmssApplication.getSmss().getSenderName();
	}
	
	public static String getMethodName() {
		return SmssApplication.getSmss().getMethodName();
	}

	public static List <String> getRecieverTypes() {
		SMSS smss = SmssApplication.getSmss();

		return	smss.getReceiverClasses().stream().map(type -> type.getName()).collect(Collectors.toList());
	}
	
	public static void createRecieverType(String receiverType) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		//Add reciever name
		//Add reciever class
		throw new UnsupportedOperationException("Not Implemented Yet");
	}
		
	
	public static void createAltFragment() throws InvalidInputException {
		//create the fragment
		//assiging it to the smss 
		throw new UnsupportedOperationException("Not Implemented Yet");
	}

	public static void createPalFragment() throws InvalidInputException {
		//add a block
		//add an operand (no condition)
		//add a fragment 
		throw new UnsupportedOperationException("Not Implemented Yet");
	}
	
	// verify if the last item was a fragment 
	public static void createOperand(String condition) throws InvalidInputException {
		//Validation to check of the last block is the fragment
		 SMSS smss = SmssApplication.getSmss();
		 if (smss.getBlocks().size() == 0 || !(smss.getBlocks().get(smss.getBlocks().size() - 1) instanceof Fragment)) {
			 throw new InvalidInputException("");
		 }
		 
		//If the last block is alter then check for the condition
		//call the constructor 
		throw new UnsupportedOperationException("Not Implemented Yet");
	}
	
	public static void deleteLastMessage() throws InvalidInputException {
		//get the last message
		// delete it using delete()
		throw new UnsupportedOperationException("Not Implemented Yet");
	}
	
	public static void assignMessage() throws InvalidInputException {
		//assign message to receiver using message exchange
		throw new UnsupportedOperationException("Not Implemented Yet");
	}
	

	public static List<String> getRecieverbyType(String type) throws InvalidInputException {
		ReceiverClass receiverClass = ReceiverClass.getWithName(type);
		
		if (receiverClass == null) {
			throw new InvalidInputException("The input receiver type does not exist");
		}
		
		return receiverClass.getObjects().stream().map(obj -> obj.getName()).collect(Collectors.toList());
	}
	
	public static List<String> getMethodBody() throws InvalidInputException {
//get operand
		SMSS smss = SmssApplication.getSmss();

		throw new UnsupportedOperationException("Not Implemented Yet");
		
	}
	
	public static List<String>  getMessages() {
		return SmssApplication.getSmss().getMessages().stream().map(m -> m.getName()).collect(Collectors.toList());
		// throw new UnsupportedOperationException("Not Implemented Yet");
	}
	
}
