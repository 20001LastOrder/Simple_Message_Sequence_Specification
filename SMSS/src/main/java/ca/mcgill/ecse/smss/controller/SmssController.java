package ca.mcgill.ecse.smss.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import ca.mcgill.ecse.smss.application.SmssApplication;
import ca.mcgill.ecse.smss.model.AlternativeFragment;
import ca.mcgill.ecse.smss.model.Block;
import ca.mcgill.ecse.smss.model.Fragment;
import ca.mcgill.ecse.smss.model.Message;
import ca.mcgill.ecse.smss.model.MessageExchange;
import ca.mcgill.ecse.smss.model.Operand;
import ca.mcgill.ecse.smss.model.ParallelFragment;
import ca.mcgill.ecse.smss.model.ReceiverClass;
import ca.mcgill.ecse.smss.model.ReceiverObject;
import ca.mcgill.ecse.smss.model.SMSS;

public class SmssController {

	/**
	 * method to create a message
	 * @param name
	 * @throws InvalidInputException
	 */
	public static void createMessage(String name) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();

		checkStringInput(name, "Message name");

		// check if the message name already exists in the system
		if (Message.hasWithName(name)) {
			throw new InvalidInputException("The message name already exists");
		}

		smss.addMessage(name);
	}

	/**
	 * Update the information for the sender in the application 
	 * @param className
	 * @param objectName
	 * @param methodName
	 * @throws InvalidInputException
	 */
	public static void updateSenderInfo(String className, String objectName, String methodName)
			throws InvalidInputException {
		checkStringInput(className, "Class name");
		checkStringInput(objectName, "Object name");
		checkStringInput(methodName, "Method name");

		SMSS smss = SmssApplication.getSmss();
		smss.setSenderClass(className);
		smss.setSenderName(objectName);
		smss.setMethodName(methodName);

	}
	
	/**
	 * fetching the sender class
	 * @return the send class
	 */
	public static String getSenderClass() {
		return SmssApplication.getSmss().getSenderClass();
	}
	
	/**
	 * Fetching the sender name
	 * @return the sender name
	 */
	public static String getSenderName() {
		return SmssApplication.getSmss().getSenderName();
	}

	/**
	 * Fetch the method name
	 * @return the method name
	 */
	public static String getMethodName() {
		return SmssApplication.getSmss().getMethodName();
	}
	
	/**
	 * Fetch the receiver types
	 * @return the receiver type
	 */
	public static List<String> getRecieverTypes() {
		SMSS smss = SmssApplication.getSmss();

		return smss.getReceiverClasses().stream().map(type -> type.getName()).collect(Collectors.toList());
	}
    
	/**
	 * Create reciever class
	 * @param receiverType
	 * @throws InvalidInputException
	 */
	public static void createRecieverType(String receiverType) throws InvalidInputException {
		//Validation on reciever type
		checkStringInput(receiverType, "Receiver type");
		//If the similar receiver already exist throw an exception
		if (ReceiverClass.hasWithName(receiverType)) {
			throw new InvalidInputException("Receiver type already exists");
		}

		SmssApplication.getSmss().addReceiverClass(receiverType);
	}
	/**
	 * create a receiver with type receiverType and name receiverName
	 * @param receiver class name
	 * @param receiver Name
	 * @throws if the receiver class doesn't exist, throw an exception
	 */
	public static void createReceiver(String receiverType, String receiverName) throws InvalidInputException {
		checkStringInput(receiverType, "Receiver Type");
		checkStringInput(receiverName, "Receiver Name");
		//If the receiver class doesn't exist, throw an exception 
		if (!ReceiverClass.hasWithName(receiverType)) {
			throw new InvalidInputException("Receiver type does not exist");
		}
		//If the given name already exist, throw an exception 
		if (ReceiverObject.hasWithName(receiverName)) {
			throw new InvalidInputException("Receiver name already exist");
		}

		ReceiverClass.getWithName(receiverType).addObject(receiverName);
	}
	
	//Create ALT fragment
	public static void createAltFragment() throws InvalidInputException {
		createFragment(FragmentType.Alternative);
	}
	
	//Create PAR fragment
	public static void createParFragment() throws InvalidInputException {
		createFragment(FragmentType.Parallel);
	}
	
	/**
	 * mark the current fragment as finished
	 * @throws exception when there are no fragments, last block is not a fragment
	 * , has less than 2 fragments in a block or no message in the last operand
	 */
	public static void finishFragment() throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		int size = smss.getBlocks().size();
		//If no fragment exist, throw an exception 
		if (size == 0) {
			throw new InvalidInputException("There are no fragments");	
		} else if (!(smss.getBlocks().get(size - 1) instanceof Fragment)) {
			throw new InvalidInputException("Last block is not a Fragment");
		}
		
		Fragment fragment = (Fragment) smss.getBlock(size - 1);
		
		if (fragment.getOperands().size() < 2) {
			throw new InvalidInputException("The fragment has less then 2 operands");
		}
		
		if (fragment.getOperand(fragment.getOperands().size() - 1).getBlocks().size() < 1) {
			throw new InvalidInputException("The last operand of the fragment has no messages");
		}
		
		fragment.setIsFinished(true);
	}
	/**
	 * create a new operand in the last block (needs to be a fragment)
	 * @param provide condition for the operand
	 * @throws exception when there are no fragments or last block is not a fragment
	 * or fragment has already ended or fragment doesn't have message.
	 */
	public static void createOperand(String condition) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		int size = smss.getBlocks().size();

		if (size == 0) {
			throw new InvalidInputException("There are no fragments");
		} else if (!(smss.getBlocks().get(size - 1) instanceof Fragment)) {
			throw new InvalidInputException("Last block is not a Fragment");
		}

		Fragment fragment = (Fragment) smss.getBlock(size - 1);

		if (fragment.getIsFinished()) {
			throw new InvalidInputException("The fragment is already finished!");
		}

		int numOfOperands = fragment.getOperands().size();
		if (numOfOperands != 0 && fragment.getOperand(numOfOperands - 1).getBlocks().size() == 0) {
			throw new InvalidInputException("The last operand of the fragment doesn't have any message");
		}

		// if the fragment is alternative, then there needs to be a condition
		if (fragment instanceof AlternativeFragment) {
			checkStringInput(condition, "Condition in alternative operand");
		}

		fragment.addOperand(condition);
	}
	/**
	 * delete the last message in the system or in an operand,
	 * if a operand has only one message, the operand will also be deleted
	 * if a fragment has only one operand, the fragment will also be deleted
	 * @throws exception if there are no messages to delete
	 */
	public static void deleteLastMessage() throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		int size = smss.getBlocks().size();
		
		if (size == 0) {
			throw new InvalidInputException("No message to delete!");
		}
		
		if (smss.getBlock(size-1) instanceof Fragment)
		{
			Fragment fragment = (Fragment) smss.getBlock(size -1);
			// the fragment is not finished anymore
			fragment.setIsFinished(false);
			// if the fragment does not have any operand, delete it
			if (fragment.getOperands().size() == 0) {
				fragment.delete();
				return;
			}
			
			Operand lastOperand = fragment.getOperand(fragment.getOperands().size() - 1);
			if (lastOperand.getBlocks().size()  > 0) {
				lastOperand.getBlock(lastOperand.getBlocks().size() - 1).delete();
			}
			// if the operand does not have any messages, delete the operand
			if (lastOperand.getBlocks().size() == 0)
			{
				lastOperand.delete();
				// if the fragment does not have any operands, delete the fragment
				if (fragment.getOperands().size() == 0)
				{
					fragment.delete();
				}
			}
		}
		else 
		{
			smss.getBlock(size-1).delete();
		}
	}
	/**
	 * add a message sending to the method
	 * @param Name for the message, name for the receiver
	 * @throws Exception if message and receiver objects are null
	 */
	public static void assignMessage(String messageName, String receiverName) throws InvalidInputException {
		// assign message to receiver using message exchange
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
			// if the block is a fragment, and it is not yet finished, we append the message
			// exchange to it's end
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
	/**
	 * get the receivers with a specific type
	 * @param Receiver class
	 * @return list of receivers
	 */
	public static List<String> getRecieverbyType(String type) {

		if (type == null || !ReceiverClass.hasWithName(type)) {
			return Collections.emptyList();
		}

		ReceiverClass receiverClass = ReceiverClass.getWithName(type);
		return receiverClass.getObjects().stream().map(obj -> obj.getName()).collect(Collectors.toList());
	}
	/**
	 * 
	 * @return Structured methodbody information (message exchange)
	 */
	public static String getMethodBody() {
		SMSS smss = SmssApplication.getSmss();
		StringBuilder sb = new StringBuilder();

		for (Block block : smss.getBlocks()) {
			sb.append(block.toString()).append("\n");
		}

		return sb.toString();
	}
	/**
	 * 
	 * @return: List of messages in the application 
	 */
	public static List<String> getMessages() {
		return SmssApplication.getSmss().getMessages().stream().map(m -> m.getName()).collect(Collectors.toList());
	}
	/**
	 * 
	 * @return: True if block is an instance of ALT fragment
	 */
	public static boolean isInAltFragment() {
		SMSS smss = SmssApplication.getSmss();
		int size = smss.getBlocks().size();
		
		return size !=  0 && (smss.getBlock(size - 1) instanceof AlternativeFragment) && !((Fragment) smss.getBlock(size - 1)).getIsFinished();
	}
	/**
	 * 
	 * @return: True if the block is an instance of PAR fragment
	 */
	public static boolean isInParFragment() {
		SMSS smss = SmssApplication.getSmss();
		int size = smss.getBlocks().size();
		
		return size !=  0 && (smss.getBlock(size - 1) instanceof ParallelFragment) && !((Fragment) smss.getBlock(size - 1)).getIsFinished();
	}

	private static enum FragmentType{Parallel, Alternative}
	/**
	 * Create a specific fragment
	 * @param Fragment type(ALT, PAR)
	 * @throws Exception if user try to add a new fragment while the last fragment is still unfinished
	 */
	private static void createFragment(FragmentType type) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		int size = smss.getBlocks().size();
		if (size != 0 && (smss.getBlock(size - 1) instanceof Fragment) && 
			!((Fragment) smss.getBlock(size - 1)).getIsFinished()) {
			throw new InvalidInputException("Cannot add new fragment. The last fragment is not finished yet!");
		}
		
		if (type == FragmentType.Parallel) {
			smss.addBlock(new ParallelFragment());
		} else {
			smss.addBlock(new AlternativeFragment());
		}
	}
	
	/**
	 * Validation for string input: make sure it is not null or empty
	 * @param value
	 * @param name
	 * @throws InvalidInputException
	 */
	private static void checkStringInput(String value, String name) throws InvalidInputException {
		if (value == null || value.trim().length() == 0) {
			throw new InvalidInputException(name + " cannot be null or empty");
		}
	}

}