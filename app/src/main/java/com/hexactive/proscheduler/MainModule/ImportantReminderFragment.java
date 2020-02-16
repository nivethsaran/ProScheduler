package com.hexactive.proscheduler.MainModule;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hexactive.proscheduler.R;
import com.hexactive.proscheduler.ReminderModule.ReminderAdapter;
import com.hexactive.proscheduler.ReminderModule.ReminderDetails;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImportantReminderFragment extends Fragment {

RecyclerView important_reminder_rv;
    ReminderAdapter reminderAdapter;
    FirebaseUser currentUser;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
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
        progressBar=view.findViewById(R.id.progressbar);

        mAuth=FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        important_reminder_rv.setLayoutManager(layoutManager);
        new ImportantReminderTask().execute();
        return view;
    }


    public class ImportantReminderTask extends AsyncTask<Void,Void,String>
    {

        ReminderDetails reminderDetails;
        List<ReminderDetails> list;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... voids) {
            String url,json=null;
            if(currentUser!=null)
            {
                Log.d("ImportantReminder","Check One");
                try {
                    url = "https://pro-scheduler-backend.herokuapp.com/getRemindertoday/uid/" + currentUser.getUid();
                    Log.d("ImportantReminder","URL:"+url);
                    json = Jsoup.connect(url).ignoreContentType(true).execute().body();
                    Log.d("ImportantReminder","JSON:"+json);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Log.d("ImportantReminder","Error");
                }
            }
            else
            {
                Log.d("ImportantReminder","Firebase Error");
            }

            return json;
        }

        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            try{
//                JSONObject data=new JSONObject(json);
                JSONArray dataArray=new JSONArray(json);
                list=new ArrayList<ReminderDetails>();
                for (int i=0;i<dataArray.length();i++)
                {
                    JSONObject temp=dataArray.getJSONObject(i);
                    reminderDetails=new ReminderDetails();
                    reminderDetails.note=temp.getString("note");
                    reminderDetails.priority=temp.getString("priority");
                    reminderDetails.notification=temp.getString("notification");
                    reminderDetails.r_date=temp.getString("r_date");
                    reminderDetails.r_time=temp.getString("r_time");
                    reminderDetails.uid=temp.getString("uid");
                    reminderDetails.title=temp.getString("title");
                    list.add(reminderDetails);
                }
                ImportantReminderAdapter reminderAdapter=new ImportantReminderAdapter(list);
                important_reminder_rv.setAdapter(reminderAdapter);
            }catch (Exception e)
            {
                e.printStackTrace();
                Log.d("ImportantReminder",e.getMessage());
            }
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

}
