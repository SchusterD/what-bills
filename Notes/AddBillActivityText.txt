package com.catalyst.whatbills.activities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.catalyst.whatbills.R;
import com.catalyst.whatbills.entities.Bill;
import com.catalyst.whatbills.entities.Category;
import com.catalyst.whatbills.utilites.DatabaseHelper;
import com.catalyst.whatbills.utilites.FormValidator;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class AddBillActivity extends Activity {
	private DatabaseHelper db = DatabaseHelper.getDatabase(this);
	
	private EditText billName;
	private EditText billAmount;
	private DatePicker datepicker;
	private Spinner recurrence;
	private Spinner category;
	private Button submitButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_bill);
				
		billName = (EditText)findViewById(R.id.form_billname_edittext);
		billAmount = (EditText)findViewById(R.id.form_amount_edittext);
		datepicker = (DatePicker)findViewById(R.id.datePicker1);
		recurrence = null;/*(Spinner)findViewById(R.id.spinner1);*/
		category = (Spinner)findViewById(R.id.spinner2);
		
		ArrayAdapter<CharSequence> adapter = setupCategorySpinner();
		category.setAdapter((SpinnerAdapter) adapter);
		
		submitButton = (Button)findViewById(R.id.form_submitbutton);
		
		submitButton.setOnClickListener(new View.OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean allFilled = FormValidator.validate(billName.getText().toString(), billAmount.getText().toString(), 
						null, 
						new Date(datepicker.getYear() - 1900, datepicker.getMonth(), datepicker.getDayOfMonth()).toString(), 
						null);
				Log.w("Form valid:", String.valueOf(allFilled));
				
				if(allFilled == true){
					saveBill(billName.getText().toString(), billAmount.getText().toString(), 
							/*category.getSelectedItem().toString()*/ null, 
							new Date(datepicker.getYear() - 1900, datepicker.getMonth(), datepicker.getDayOfMonth()).toString(), 
							/*recurrence.getSelectedItem().toString()*/ null);
				}
				
				finish();
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_bill, menu);
		return true;
	}
	
	public void saveBill(String name, String amt, String category,
			String date, String rec){
		
		Bill b = new Bill();
		
		b.setName(name);
		b.setAmount(Double.parseDouble(amt));
		b.setCategory(category);
		b.setRecurrence(rec);
		b.setDate(new Date(date));
		
		db.addBill(b);
	}
	
	public ArrayAdapter<CharSequence> setupCategorySpinner(){
		//ArrayAdapter categories = ArrayAdapter.createFromResource(this, textArrayResId, textViewResId)
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 0, null);
		ArrayAdapter <CharSequence> adapter = null;
		
		try {
			Cursor c = db.getAllCategories();
			
			int categoryTextIndex = c.getColumnIndex("category_text");
			
			adapter = new ArrayAdapter<CharSequence>(this, 
							android.R.layout.simple_spinner_item );
			
			if(c != null && c.moveToFirst()){
				do{
					adapter.add(c.getString(categoryTextIndex)); 
				}while(c.moveToNext());
			}
		} catch (SQLException e) {
			Log.w("Error setting up category spinner", e);
		}
		
		return adapter;
	}

}
