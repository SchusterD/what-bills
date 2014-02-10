package com.catalyst.whatbills.viewFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.catalyst.whatbills.R;

public class AddBillFragment extends Fragment{
	
	ViewGroup root;
	
	public static AddBillFragment newInstance(Context context) {
		AddBillFragment addBill = new AddBillFragment();

        return addBill;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.activity_add_bill, null);
                
        return root;
    }
}
