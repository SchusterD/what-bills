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
	/*private DatePicker datepicker;
	private Spinner recurrence;
	private Spinner category;*/
	private Button submitButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_bill);
				
		billName = (EditText)findViewById(R.id.form_billname_edittext);
		billAmount = (EditText)findViewById(R.id.form_amount_edittext);
		
		submitButton = (Button)findViewById(R.id.form_submitbutton);
		
		submitButton.setOnClickListener(new View.OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				boolean allFilled = FormValidator.validate(billName.getText().toString(), billAmount.getText().toString());
				Log.w("Form valid:", String.valueOf(allFilled));
				
				if(allFilled == true){
					saveBill(billName.getText().toString(), billAmount.getText().toString());
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
	
	public void saveBill(String name, String amt){
		
		Bill b = new Bill(); //call default constructor will all set to null
		
		b.setName(name);
		b.setAmount(Double.parseDouble(amt));
		
		db.addBill(b);
	}

}
