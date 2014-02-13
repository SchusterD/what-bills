package com.catalyst.whatbills.viewFragments;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.catalyst.whatbills.R;

public class HomeScreenFragment extends Fragment{
	
	ViewGroup root;
	
	public static HomeScreenFragment newInstance(Context context) {
		HomeScreenFragment homeScreen = new HomeScreenFragment();

        return homeScreen;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.activity_main_fragment, null);
                
        return root;
    }
    
}
