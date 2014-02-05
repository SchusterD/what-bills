package com.catalyst.whatbills.entities;

import java.util.Date;

public class Bill {
	private int billId;
	private String billName;
	private double amount;
	private Date dueDate; //TODO look into android/sqlite date objects
	private String recurrence;
	private String category;
	
	
	public Bill(){
		
	}
	
	public Bill(String name, double amt, Date date, 
			String recurrence, String category){
		this.billName = name;
		this.amount = amt;
		this.dueDate = date;
		this.recurrence = recurrence;
		this.category = category;
	}
	
	public int getId(){
		return this.billId;
	}
	
	public void setId(int id){
		this.billId = id;
	}
	
	public String getName(){
		return this.billName;
	}
	
	public void setName(String name){
		this.billName = name;
	}
	
	public double getAmount(){
		return this.amount;
	}
	
	public void setAmount(double d){
		this.amount = d;
	}
	
	public Date getDate(){
		return this.dueDate;
	}
	
	public void setDate(Date d){
		this.dueDate = d;
	}
	
	public String getRecurrence(){
		return this.recurrence;
	}
	
	public void setRecurrence(String rec){
		this.recurrence = rec;
	}
	
	public String getCategory(){
		return this.category;
	}
	
	public void setCategory(String cat){
		this.category = cat;
	}
}
