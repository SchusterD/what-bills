package com.catalyst.whatbills.utilites;

public class FormValidator {
	
	
	public static boolean validate(String name, String amt, String category,
			String date, String rec){
		boolean allFilled = false;
		
		if(name.length() > 0 && amt.length() > 0
				/*&& category.length() > 0*/ && date.length() > 0
				/*&& rec.length() > 0*/){
			allFilled = true;
		}
		
		return allFilled;
	}
}
