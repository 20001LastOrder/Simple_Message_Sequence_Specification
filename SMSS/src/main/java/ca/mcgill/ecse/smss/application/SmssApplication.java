package ca.mcgill.ecse.smss.application;

import ca.mcgill.ecse.smss.model.SMSS;
import ca.mcgill.ecse.smss.view.SmssViewApplication;
import javafx.application.Application;

public class SmssApplication {
	private static SMSS aSMSS;
	
	public static void main(String[] args) {
		Application.launch(SmssViewApplication.class, args);
	}
	
	public static SMSS getSmss() {
		if (aSMSS == null) {
			aSMSS = new SMSS();
		}
		
		return aSMSS;
	}
}
