package ca.mcgill.ecse.smss.controller;

import java.sql.Date;
import java.util.List;

import ca.mcgill.ecse.smss.application.SmssApplication;
import ca.mcgill.ecse.smss.model.SMSS;

public class SmssController {
	
	public static void createMessage(String name) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		try {
			smss.addMessage(name);
			SmssApplication.save();
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	public static void updateSenderInfo(String aclass, String name) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		try {
			smss.setSenderClass(aclass);
			smss.setSenderName(name);
			SmssApplication.save();
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	public static List <RecieverType> getRecieverType() {
		SMSS smss = SmssApplication.getSmss();

		return	smss.getReceiverClasses();
	}
	
	public static void createRecieverType() throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		//Add reciever name
		//Add reciever class
	}
		
	
	public static void createAltFragment() throws InvalidInputException {
		//create the fragment
		//assiging it to the smss 

	}

	public static void createPalFragment() throws InvalidInputException {
		//add a block
		//add an operand (no condition)
		//add a fragment 
	}
	
	// verify if the last item was a fragment 
	public static void createOperand(String condition) throws InvalidInputException {
		//Validation to check of the last block is the fragment
		//If the last block is alter then check for the condition
		//call the constructor 

	}
	
	public static void deleteLastMessage() throws InvalidInputException {
		//get the last message
		// delete it using delete()

	}
	
	public static void assignMessage() throws InvalidInputException {
		//assign message to receiver using message exchange
	}
	

	public static List <String> getRecieverbyType() throws InvalidInputException {

	}
	
	public static List <String> getMethodBody() throws InvalidInputException {
//get operand
		SMSS smss = SmssApplication.getSmss();

		return	smss.getBlocks();
		
	}
	
	public static List <String>  getMessages() throws InvalidInputException {
	}
	
}
