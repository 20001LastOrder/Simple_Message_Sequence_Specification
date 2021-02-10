package ca.mcgill.ecse.smss.application;

import ca.mcgill.ecse.smss.model.SMSS;

public class SmssApplication {
	private static SMSS aSMSS;
	
	public static void main(String[] args) {
		
	}
	
	public static SMSS getSmss() {
		if (aSMSS == null) {
			aSMSS = new SMSS();
		}
		
		return aSMSS;
	}


	public static void save() {
		// implementation of persistence removed on purpose
	}
}