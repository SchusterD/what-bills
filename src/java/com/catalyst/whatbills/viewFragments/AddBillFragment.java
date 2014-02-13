package com.catalyst.whatbills.viewFragments;

import java.util.Date;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.catalyst.whatbills.R;
import com.catalyst.whatbills.entities.Bill;
import com.catalyst.whatbills.utilites.DatabaseHelper;
import com.catalyst.whatbills.utilites.FormValidator;

public class AddBillFragment extends Fragment{
	
	ViewGroup rootView;
	private DatabaseHelper db;
	private EditText billName;
	private EditText billAmount;
	private DatePicker datepicker;
	private Spinner recurrence;
	private Spinner category;
	private Button submitButton;
	
	public static AddBillFragment newInstance(Context context) {
		AddBillFragment addBill = new AddBillFragment();

        return addBill;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,Bundle savedInstanceState) {
    	db = DatabaseHelper.getDatabase(this.getActivity());
        rootView = (ViewGroup) inflater.inflate(R.layout.activity_add_bill, null);
        
        billName = (EditText)rootView.findViewById(R.id.form_billname_edittext);
		billAmount = (EditText)rootView.findViewById(R.id.form_amount_edittext);
		datepicker = (DatePicker)rootView.findViewById(R.id.datePicker1);
		recurrence = null;/*(Spinner)rootView.findViewById(R.id.spinner1);*/
		category = null;/*(Spinner)rootView.findViewById(R.id.spinner2);*/
		submitButton = (Button)rootView.findViewById(R.id.form_submitbutton);
        
        submitButton = (Button)rootView.findViewById(R.id.form_submitbutton);
        
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              submitForm();
            }
        });
                
        return rootView;
    }
    
    private void submitForm(){
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
			
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.popBackStack();
		}
		
    }
    
    private void saveBill(String name, String amt, String category,
			String date, String rec){
		
		Bill b = new Bill();
		
		b.setName(name);
		b.setAmount(Double.parseDouble(amt));
		b.setCategory(category);
		b.setRecurrence(rec);
		b.setDate(new Date(date));
		
		db.addBill(b);
	}
    
}
