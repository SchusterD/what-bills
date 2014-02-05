package com.catalyst.whatbills.sql;

public class DatabaseScripts {
	
	public static final String createTables = "CREATE TABLE bills "
			+ "(bill_id integer primary key autoincrement, "
			+ "bill_amount real, bill_category text, "
			+ "bill_duedate text, bill_name text, bill_recurrence text)";
	
	//may not need these
	public static final String addBill = "";
	public static final String editBill = "";
	public static final String removeBill = "";
	public static final String getAllBills = "";
	public static final String getOneBill = "";
	public static final String removeAllBills = "";
	
	
	//TODO for reference - remove later
	private static final String BILL_ID = "bill_id";
	private static final String BILL_NAME = "bill_name";
	private static final String BILL_AMOUNT = "bill_amount";
	private static final String BILL_CATEGORY = "bill_category";
	private static final String BILL_RECURRENCE = "bill_recurrence";
	private static final String BILL_DUEDATE = "bill_duedate";
	
	public DatabaseScripts(){
	
	}
	
}
