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

	public static void createMessage(String name) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();

		checkStringInput(name, "Message name");

		// check if the message name already exists in the system
		if (Message.hasWithName(name)) {
			throw new InvalidInputException("The message name already exists");
		}

		smss.addMessage(name);
	}

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

	public static String getSenderClass() {
		return SmssApplication.getSmss().getSenderClass();
	}

	public static String getSenderName() {
		return SmssApplication.getSmss().getSenderName();
	}

	public static String getMethodName() {
		return SmssApplication.getSmss().getMethodName();
	}

	public static List<String> getRecieverTypes() {
		SMSS smss = SmssApplication.getSmss();

		return smss.getReceiverClasses().stream().map(type -> type.getName()).collect(Collectors.toList());
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
		createFragment(FragmentType.Alternative);
	}

	public static void createParFragment() throws InvalidInputException {
		createFragment(FragmentType.Parallel);
	}
	
	public static void finishFragment() throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		int size = smss.getBlocks().size();
		
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

	public static List<String> getMessages() {
		return SmssApplication.getSmss().getMessages().stream().map(m -> m.getName()).collect(Collectors.toList());
	}
	
	public static boolean isInAltFragment() {
		SMSS smss = SmssApplication.getSmss();
		int size = smss.getBlocks().size();
		
		return size !=  0 && (smss.getBlock(size - 1) instanceof AlternativeFragment) && !((Fragment) smss.getBlock(size - 1)).getIsFinished();
	}
	
	public static boolean isInParFragment() {
		SMSS smss = SmssApplication.getSmss();
		int size = smss.getBlocks().size();
		
		return size !=  0 && (smss.getBlock(size - 1) instanceof ParallelFragment) && !((Fragment) smss.getBlock(size - 1)).getIsFinished();
	}
	
	private static enum FragmentType{Parallel, Alternative}
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

	private static void checkStringInput(String value, String name) throws InvalidInputException {
		if (value == null || value.trim().length() == 0) {
			throw new InvalidInputException(name + " cannot be null or empty");
		}
	}

}
