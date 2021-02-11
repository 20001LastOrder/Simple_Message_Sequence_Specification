package ca.mcgill.ecse.smss.view;

import ca.mcgill.ecse.smss.controller.SmssController;

public final class SmssViewFormatUtil {
	/**
	 * return formatted String of the method body using the controller
	 * @return formatted Stirng of the method body
	 */
	public static String createFormattedMethodContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(SmssController.getSenderClass()).append(" ")
			.append(SmssController.getMethodName()).append("\n");
		
		// TODO: iterate the blocks to create visualization of the method body
		
		return sb.toString();
	}
}
