package com.catalyst.whatbills.utilites;

import java.util.ArrayList;
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
	private static final String DB_NAME = "TBD";
	
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
			db.execSQL(DatabaseScripts.createDatabase);
			
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
	
	public void addBill(){
		//do we want to check db to see if duplicate first 
		//or just make user delete manually
		SQLiteDatabase db = getWritableDatabase();
		ContentValues content = new ContentValues();
		
		try {
			cursor = db.rawQuery(DatabaseScripts.addBill, null);
		} 
		catch (SQLException e) {
			Log.w("Error cadding bill", e);
		}
		finally{
			db.close();
		}
	}
	
	public void editBill(){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues content = new ContentValues();
		
		try {
			cursor = db.rawQuery(DatabaseScripts.editBill, null);
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
