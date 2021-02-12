package ca.mcgill.ecse.smss.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import ca.mcgill.ecse.smss.application.SmssApplication;
import ca.mcgill.ecse.smss.model.Block;
import ca.mcgill.ecse.smss.model.Fragment;
import ca.mcgill.ecse.smss.model.Message;
import ca.mcgill.ecse.smss.model.MessageExchange;
import ca.mcgill.ecse.smss.model.Operand;
import ca.mcgill.ecse.smss.model.ReceiverClass;
import ca.mcgill.ecse.smss.model.ReceiverObject;
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
	
	public static List<String> getReceiverbyType(String type) throws InvalidInputException {
		ReceiverClass receiverClass = ReceiverClass.getWithName(type);
		if (receiverClass == null) {
			throw new InvalidInputException("No classfound");
		}
		return receiverClass.getObjects().stream().map(obj-> obj.getName()).collect(Collectors.toList());
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
	
	public static void createReceiverType(String name, String objName) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		ReceiverClass receiverClass = new ReceiverClass(name, smss);
		receiverClass.setName(name);
		receiverClass.setSMSS(smss);
		ReceiverObject receiverObject = new ReceiverObject(objName, receiverClass);
		receiverObject.setName(objName);
		receiverClass.addObject(receiverObject);
		//Add validation here
	}		
	
	// verify if the last item was a fragment 
	public static void createOperand(String condition) throws InvalidInputException {
		//Validation to check of the last block is the fragment
		SMSS smss = SmssApplication.getSmss();
		if(smss.getBlocks().size() == 0)
		{
			throw new InvalidInputException("There are no blocks");
		}
		else if (!(smss.getBlocks().get(smss.getBlocks().size()-1) instanceof Fragment)) 
		{
			throw new InvalidInputException("Last block is not a Fragment");
		}
		//If the last block is alterFragment
		else if (smss.getBlocks().get(smss.getBlocks().size()-1).getOperand().getCondition() == null ) 
		{
			//Parllel fragment
			Operand operand = new Operand(condition, null);
		}
		else 
		{
			//Alt fragment
		}

		throw new UnsupportedOperationException("Not Implemented Yet");
	}
	
	public static void assignMessage(ReceiverObject aObject, Message aMessage) throws InvalidInputException {
		//assign message to receiver using message exchange
		MessageExchange messageExchange = new MessageExchange(aObject, aMessage);
		messageExchange.setReceiver(aObject);
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
	public static void deleteLastMessage() throws InvalidInputException {
		//get the last message
		// delete it using delete()
		//Condition 1: If the message is standalone 
		//condition2: if the message belong to a frag
		//if yes{
	//		
	//	}
		 
		throw new UnsupportedOperationException("Not Implemented Yet");
	}


	public static List<String> getMethodBody() throws InvalidInputException {
		//get operand
				SMSS smss = SmssApplication.getSmss();
				smss.getMethodName();
				// Declare a has map
				
	}
		
	
}
