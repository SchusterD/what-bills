package com.catalyst.whatbills.utilites;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.catalyst.whatbills.entities.Bill;
import com.catalyst.whatbills.sql.DatabaseScripts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	//constants
	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "whatbills";
	private static final String BILL_TABLE = "bills";
	//table info
	private static final String BILL_ID = "bill_id";
	private static final String BILL_NAME = "bill_name";
	private static final String BILL_AMOUNT = "bill_amount";
	private static final String BILL_CATEGORY = "bill_category_text";
	private static final String BILL_RECURRENCE = "bill_recurrence";
	private static final String BILL_DUEDATE = "bill_duedate";
	
	
	private static DatabaseHelper database;
	private Context context;
	private Cursor cursor;
	
	

	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		this.context = context;
	}

	public static DatabaseHelper getDatabase(Context context){
		if(database == null){
			database = new DatabaseHelper(context);
		}		
		return database;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		//create db and its tables
		try{
			db.execSQL(DatabaseScripts.createCategoriesTable);
			db.execSQL(DatabaseScripts.createBillsTable);
			db.execSQL(DatabaseScripts.populateCategories);
			
			//TODO may need additional statements once we
			//decide what should be in the db
			
		}catch(SQLException e){
			Log.w("Error creating database", e);
		}
	}
	

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//can you update tables rather than drop completely??
		//removing all data with each upgrade/update seems irritating
		
		//or just re-insert the data into the tables if stored on Google Drive
		//maybe a CSV so can avoid ugly parsing
	}
	
	/*
	 * found online
	 * 
	 * public void onUpgrade(
        final SQLiteDatabase db, final int oldVersion,
        final int newVersion)
    {
        int upgradeTo = oldVersion + 1;
        while (upgradeTo <= newVersion)
        {
            switch (upgradeTo)
            {
                case 5:
                    db.execSQL(SQLiteSet.V5_ADD_LAST_CARD);
                    db.execSQL(SQLiteCard.V5_ADD_FAILED);
                    break;
                case 6:
                    db.execSQL(SQLiteSet.V6_ADD_IMPORT_TYPE);
                    break;
                case 7:
                    db.execSQL(SQLiteSet.V7_ADD_SHORT_FNAME);
                    break;
            }
            upgradeTo++;
        }
    }
	 */
	
	@Override
	public void onOpen(SQLiteDatabase db){
		//is this needed?
	}
	
	public void addBill(Bill b){
		//do we want to check db to see if duplicate first 
		//or just make user delete manually
		SQLiteDatabase db = getWritableDatabase();
		ContentValues content = new ContentValues();
		
		try {
			String date = Long.toString(b.getDate().getTime());
			
			content.put(BILL_NAME, b.getName());
			content.put(BILL_AMOUNT, b.getAmount());
			
			//FIXME 
			content.put(BILL_CATEGORY, b.getCategory());
			
			
			content.put(BILL_DUEDATE, date);
			content.put(BILL_RECURRENCE, b.getRecurrence());
			content.put(BILL_ID, b.getId());
			
			db.insert(DatabaseHelper.BILL_TABLE, null, content);	
		} 
		catch (SQLException e) {
			Log.w("Error adding bill", e);
		}
		finally{
			db.close();
		}
	}
	
	public void editBill(Bill b){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues content = new ContentValues();
		
		//update anything different 
		//go in with id
		
		try {
			db.update(BILL_TABLE, content, "bill_id=?", 
					new String[] { String.valueOf(b.getId()) });
		} 
		catch (SQLException e) {
			Log.w("Error editing bill", e);
		}
		finally{
			db.close();
		}
	}
	
	public void removeBill(){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues content = new ContentValues();
		
		try{
			db.rawQuery(DatabaseScripts.removeBill, null);
		}
		catch(SQLException e){
			Log.w("Error removing bill", e);
		}
		finally{
			db.close();
		}
	}
	
	public void getAllBills(){
		SQLiteDatabase db = getReadableDatabase();
		List<Bill> allBills = new ArrayList<Bill>();
		
		try{
			cursor = db.rawQuery(DatabaseScripts.getAllBills, null);
		}
		catch(SQLException e){
			Log.w("Error getting all bills", e);
		}
		finally{
			db.close();
		}
		
		if(cursor != null && cursor.moveToFirst()){
			int nameIndex = 0;
			int amountIndex = 0;
			int categoryIndex = 0;
			int dateIndex = 0;
			int idIndex = 0;
			int recurrenceIndex = 0;
			
			
			
			do {
				try {
					Bill b = new Bill(); // can use other constructor

					b.setAmount(cursor.getInt(amountIndex));
					b.setCategory(cursor.getString(categoryIndex));
				
				
				
					// FIXME sqlite
					String date = cursor.getString(dateIndex);
					
					b.setDate(new Date(Long.parseLong(date)));

					b.setId(cursor.getInt(idIndex));
					b.setName(cursor.getString(nameIndex));
					b.setRecurrence(cursor.getString(recurrenceIndex));
					
					allBills.add(b);
				} catch (SQLException e) {
					Log.w("Error assigning bill fields", e);
				}
			} while (cursor.moveToNext());
		}
	}
	
	public void getOneBill(){
		SQLiteDatabase db = getReadableDatabase();
		ContentValues content = new ContentValues();
		
		try{
			db.rawQuery(DatabaseScripts.getOneBill, null);
		}
		catch(SQLException e){
			Log.w("Error getting one bill", e);
		}
		finally{
			db.close();
		}
	}
	
	public void removeAllBills(){
		SQLiteDatabase db = getWritableDatabase();
		
		try{
			db.rawQuery(DatabaseScripts.removeAllBills, null);
		}
		catch(SQLException e){
			Log.w("Error removing all bills", e);
		}
		finally{
			db.close();
		}
	}

}
