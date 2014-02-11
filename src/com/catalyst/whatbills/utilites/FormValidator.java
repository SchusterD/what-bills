package com.catalyst.whatbills.utilites;

public class FormValidator {
	
	
	public static boolean validate(String name, String amt){
		boolean allFilled = false;
		
		if(name.length() > 0 && Double.parseDouble(amt) > 0){
			allFilled = true;
		}		
		return allFilled;
	}
}
