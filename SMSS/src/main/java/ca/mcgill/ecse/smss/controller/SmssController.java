package ca.mcgill.ecse.smss.controller;

import java.util.List;

import ca.mcgill.ecse.smss.application.SmssApplication;
import ca.mcgill.ecse.smss.model.ReceiverClass;
import ca.mcgill.ecse.smss.model.SMSS;

public class SmssController {
	
	public static void createMessage(String name) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		try {
			smss.addMessage(name);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
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

	public static List <ReceiverClass> getRecieverType() {
		SMSS smss = SmssApplication.getSmss();

		return	smss.getReceiverClasses();
	}
	
	public static void createRecieverType() throws InvalidInputException {
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
	

	public static List<String> getRecieverbyType() throws InvalidInputException {
		throw new UnsupportedOperationException("Not Implemented Yet");
	}
	
	public static List<String> getMethodBody() throws InvalidInputException {
//get operand
		SMSS smss = SmssApplication.getSmss();

		throw new UnsupportedOperationException("Not Implemented Yet");
		
	}
	
	public static List<String>  getMessages() throws InvalidInputException {
		throw new UnsupportedOperationException("Not Implemented Yet");
	}
	
}
