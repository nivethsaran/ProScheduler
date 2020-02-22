package com.hexactive.proscheduler.ReminderModule;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hexactive.proscheduler.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;

public class ReminderActivity extends AppCompatActivity {
RecyclerView reminder_rv;
ReminderAdapter reminderAdapter;
    ProgressDialog dialog;
FirebaseUser currentUser;
FirebaseAuth mAuth;
SwipeRefreshLayout swipeRefreshLayout;
FloatingActionButton addReminder;
    @Override
    protected void onStart() {
        super.onStart();
        new ReminderTask().execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        reminder_rv=findViewById(R.id.reminder_rv);
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout);
        dialog = new ProgressDialog(new ContextThemeWrapper(ReminderActivity.this, R.style.MyProgressDialog));

        mAuth=FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();
        addReminder=findViewById(R.id.addReminder_Btn);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new ReminderTask().execute();
            }
        });


        addReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ReminderActivity.this, AddEditReminderActivity.class);
                intent.putExtra("type","ADD");

                startActivity(intent);
            }
        });




        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        reminder_rv.setLayoutManager(layoutManager);


        Log.d("Login","https://pro-scheduler-backend.herokuapp.com/getReminderall/uid/" + currentUser.getUid());
    }


    public class ReminderTask extends AsyncTask<Void,Void,String>
    {
        ReminderDetails reminderDetails;
        List<ReminderDetails> list;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Collecting data from server");
            dialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String url,json=null;
            if(currentUser!=null)
            {
                Log.d("Login","Check One");
                try {
                    url = "https://pro-scheduler-backend.herokuapp.com/getReminderall/uid/" + currentUser.getUid();
                    Log.d("Login","URL:"+url);
                    json = Jsoup.connect(url).ignoreContentType(true).execute().body();
                    Log.d("Login","JSON:"+json);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Log.d("Login","Error");
                }
            }
            else
            {
                Log.d("Login","Firebase Error");
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

                reminderAdapter=new ReminderAdapter(list, new ReminderAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(ReminderDetails reminderDetails) {
                        Toast.makeText(getApplicationContext(),reminderDetails.title,Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(ReminderActivity.this,AddEditReminderActivity.class);
                        intent.putExtra("type","VIEW");
                        intent.putExtra("data",reminderDetails);
                        startActivity(intent);
                    }
                },getApplicationContext());



                reminder_rv.setAdapter(reminderAdapter);
            }catch (Exception e)
            {
                e.printStackTrace();
                Log.d("Login",e.getMessage());
            }
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            swipeRefreshLayout.setRefreshing(false);
        }
    }


}
