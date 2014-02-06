package com.catalyst.whatbills.entities;

public class Category {
	int category_id;
	String category_text;
	
	public Category(){
		
	}
	
	public Category(int id, String text){
		this.category_id = id;
		this.category_text = text;
	}
	
	public int getId(){
		return this.category_id;
	}
	
	public void setId(int id){
		this.category_id = id;
	}
	
	public String getText(){
		return this.category_text;
	}
	
	public void setText(String text){
		this.category_text = text;
	}
	
}
