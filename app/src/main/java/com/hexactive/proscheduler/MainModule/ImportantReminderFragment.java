package com.hexactive.proscheduler.MainModule;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hexactive.proscheduler.R;
import com.hexactive.proscheduler.ReminderModule.ReminderDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImportantReminderFragment extends Fragment {

RecyclerView important_reminder_rv;
    List<ReminderDetails> list;
    public ImportantReminderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_important_reminder, container, false);
        important_reminder_rv=view.findViewById(R.id.important_reminder_rv);
        ReminderDetails temp=new ReminderDetails();
        temp.note="Hhahaha";
        temp.priority="Hhahaha";
        temp.notification="Hhahaha";
        temp.r_date="Hhahaha";
        temp.r_time="Hhahaha";
        temp.uid="Hhahaha";
        temp.title="Hhahaha";
        list=new ArrayList<ReminderDetails>();
        list.add(temp);
        list.add(temp);
        list.add(temp);
        ImportantReminderAdapter adapter=new ImportantReminderAdapter(list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        important_reminder_rv.setLayoutManager(layoutManager);
        important_reminder_rv.setAdapter(adapter);


        return view;
    }


    public class ImportantReminderTask extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

}
