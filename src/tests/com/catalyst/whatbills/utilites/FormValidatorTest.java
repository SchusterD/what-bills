package com.catalyst.whatbills.utilites;

import junit.framework.TestCase;

public class FormValidatorTest extends TestCase {

	private FormValidator testFormValidator = new FormValidator();

	private String testName = "test";
	private String testAmt = "7357";
	private String testCategory = "TEST";
	private String testDate = "00/00/00";
	private String testRec = "4";

	public void testValidateAllFilled() {
		assertTrue(FormValidator.validate(testName, testAmt, testCategory, testDate, testRec));
	}

	public void testValidateNoneFilled() {
		assertFalse(FormValidator.validate("", "", "", "", ""));
	}

	public void testValidateNameFilledOnly() {
		assertFalse(FormValidator.validate(testName, "", "", "", ""));
	}

	public void testValidateAmtFilledOnly() {
		assertFalse(FormValidator.validate("", testAmt, "", "", ""));
	}

	public void testValidateCategoryFilledOnly() {
		assertFalse(FormValidator.validate("", "", testCategory, "", ""));
	}

	public void testValidateDateFilledOnly() {
		assertFalse(FormValidator.validate("", "", "", testDate, ""));
	}

	public void testValidateRecFilledOnly() {
		assertFalse(FormValidator.validate("", "", "", "", testRec));
	}
}
