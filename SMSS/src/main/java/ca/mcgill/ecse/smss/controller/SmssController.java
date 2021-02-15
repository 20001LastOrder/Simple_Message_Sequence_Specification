package ca.mcgill.ecse.smss.controller;

import java.util.Collections;
import java.util.List;
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
		
		checkStringInput(name, "Message name");
		
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
		checkStringInput(receiverType, "Receiver type");
		
		if (ReceiverClass.hasWithName(receiverType)) {
			throw new InvalidInputException("Receiver type already exists");
		}
		
		SmssApplication.getSmss().addReceiverClass(receiverType);
	}
	
	public static void createReceiver(String receiverType, String receiverName) throws InvalidInputException {
		checkStringInput(receiverType, "Receiver Type");
		checkStringInput(receiverName, "Receiver Name");
		
		if (!ReceiverClass.hasWithName(receiverType)) {
			throw new InvalidInputException("Receiver type does not exist");
		}
		
		if (ReceiverObject.hasWithName(receiverName)) {
			throw new InvalidInputException("Receiver name already exist");
		}
		
		ReceiverClass.getWithName(receiverType).addObject(receiverName);
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
		SMSS smss = SmssApplication.getSmss();
		int size = smss.getBlocks().size();
		
		if (smss.getBlock(size-1) instanceof Fragment)
		{
			//check how many messages are there in the operand
			Fragment fragment = (Fragment) smss.getBlock(size - 1);
			Operand lastOperand = fragment.getOperand(fragment.getOperands().size() - 1);
			lastOperand.getBlock(lastOperand.getBlock().size() - 1).delete();
			if (lastOperand.getBlock().size() == 0){
				lastOperand.delete();
				if (fragment.getOperands().size() == 0) {
					fragment.delete();
				}
			}
			// if not last message then del the message
			//else if last message then - del the message +  del operand
			//Keep checking if the fragment does have operands if no del fragment
		}
		else 
		{
			smss.getBlock(size-1).delete();
		}
	}

	
	public static void assignMessage(String messageName, String receiverName) throws InvalidInputException {
		//assign message to receiver using message exchange
		checkStringInput(messageName, "Message Name");
		checkStringInput(receiverName, "Receiver Name");	
		
		Message message = Message.getWithName(messageName);
		ReceiverObject receiver = ReceiverObject.getWithName(receiverName);
		if (message == null || receiver == null) {
			throw new InvalidInputException("Message and receiver cannot be null.");
		}
		
		SMSS smss = SmssApplication.getSmss();
		int size = smss.getBlocks().size();
		
		if (size == 0) {
			smss.addBlock(new MessageExchange(receiver, message));
			return;
		}
		
		Block block = smss.getBlock(size - 1);
		if (block instanceof Fragment && !((Fragment) block).getIsFinished()) {
			// if the block is a fragment, and it is not yet finished, we append the message exchange to it's end
			Fragment fragment = (Fragment) block;
			int operandsSize = fragment.getOperands().size(); 
			if (operandsSize == 0) {
				throw new InvalidInputException("The fragment does not contain any operand!");
			}
			
			fragment.getOperand(operandsSize - 1).addBlock(new MessageExchange(receiver, message));
		} else {
			// else append the message exchange directly to the system
			smss.addBlock(new MessageExchange(receiver, message));
		}
	}
	

	public static List<String> getRecieverbyType(String type) {
		
		if (type == null || !ReceiverClass.hasWithName(type)) {
			return Collections.emptyList();
		}
		
		ReceiverClass receiverClass = ReceiverClass.getWithName(type);		
		return receiverClass.getObjects().stream().map(obj -> obj.getName()).collect(Collectors.toList());
	}
	
	public static String getMethodBody() {
		SMSS smss = SmssApplication.getSmss();
		StringBuilder sb = new StringBuilder();
		
		for (Block block : smss.getBlocks()) {
			sb.append(block.toString()).append("\n");
		}
		
		return sb.toString();
	}
	
	public static List<String>  getMessages() {
		return SmssApplication.getSmss().getMessages().stream().map(m -> m.getName()).collect(Collectors.toList());
		// throw new UnsupportedOperationException("Not Implemented Yet");
	}
	
	private static void checkStringInput(String value, String name) throws InvalidInputException {
		if (value == null || value.trim().length() == 0) {
			throw new InvalidInputException(name + " cannot be null or empty");
		}
	}
	
}
