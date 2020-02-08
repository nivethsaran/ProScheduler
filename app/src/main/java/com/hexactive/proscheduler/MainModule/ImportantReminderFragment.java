package com.hexactive.proscheduler.MainModule;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hexactive.proscheduler.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImportantReminderFragment extends Fragment {


    public ImportantReminderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_important_reminder, container, false);

        return view;
    }

}
