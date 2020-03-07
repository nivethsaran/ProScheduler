package com.hexactive.proscheduler.ReminderModule;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hexactive.proscheduler.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReminderActivity extends AppCompatActivity {
RecyclerView reminder_rv;
ReminderAdapter reminderAdapter;
    ProgressDialog dialog;
FirebaseUser currentUser;
LinearLayout reminderActivityLayout;
FirebaseAuth mAuth;
    private int mYear, mMonth, mDay;
    DatePickerDialog datePickerDialog;
SwipeRefreshLayout swipeRefreshLayout;
FloatingActionButton addReminder;
String startdate,enddate;
EditText startdate_ed,enddate_ed;
Button query_rem_btn;
String tempUrl="",sortbyChoice="Nil";
Spinner sortby_spinner;
String sortby[]={"Random","Priority","Time"};
    @Override
    protected void onStart() {
        super.onStart();
        sortbyChoice="Random";
//        tempUrl="https://pro-scheduler-backend.herokuapp.com/getReminderall/uid/" + currentUser.getUid();
//        new ReminderTask().execute(tempUrl,sortbyChoice);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setTheme(R.style.AppThemeLight);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        reminder_rv=findViewById(R.id.reminder_rv);
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout);
        dialog = new ProgressDialog(new ContextThemeWrapper(ReminderActivity.this, R.style.MyProgressDialog));

        mAuth=FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();
        addReminder=findViewById(R.id.addReminder_Btn);
        startdate_ed=findViewById(R.id.startdate_ed);
        enddate_ed=findViewById(R.id.enddate_ed);
        query_rem_btn=findViewById(R.id.query_rem_btn);
        reminderActivityLayout=findViewById(R.id.reminderactivity);
        sortby_spinner=findViewById(R.id.sortby_spinner);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.priority_spinner_item,sortby);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        sortby_spinner.setAdapter(arrayAdapter);

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        startdate_ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog =new DatePickerDialog(ReminderActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        if((i1+1)<10)
                        {
                            startdate=i+"-0"+(i1+1)+"-"+i2;
                        }
                        else
                        {
                            startdate=i+"-"+(i1+1)+"-"+i2;
                        }

                        startdate_ed.setText(startdate);
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });

        enddate_ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog =new DatePickerDialog(ReminderActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        if((i1+1)<10)
                        {
                            enddate=i+"-0"+(i1+1)+"-"+i2;
                        }
                        else
                        {
                            enddate=i+"-"+(i1+1)+"-"+i2;
                        }

                        enddate_ed.setText(enddate);
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });

        query_rem_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(startdate_ed.getText().toString().equals("")||enddate_ed.getText().toString().equals(""))
                {
                    Snackbar.make(reminderActivityLayout,"Enter Query Parameters",Snackbar.LENGTH_SHORT).show();
                }
                else
                {
                    startdate=startdate_ed.getText().toString();
                    enddate=enddate_ed.getText().toString();
                    tempUrl="https://pro-scheduler-backend.herokuapp.com/getReminderBn/startdate/"+startdate+"/enddate/"+enddate+"/uid/"+currentUser.getUid();
                    startdate_ed.setText("");
                    enddate_ed.setText("");
                    Log.d("Reminder",tempUrl);
                    new ReminderTask().execute(tempUrl,sortbyChoice);
                }

            }
        });


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                startdate_ed.setText("");
                enddate_ed.setText("");
//                tempUrl="https://pro-scheduler-backend.herokuapp.com/getReminderall/uid/" + currentUser.getUid();
                new ReminderTask().execute(tempUrl,sortbyChoice);
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


        sortby_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sortbyChoice=sortby[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        reminder_rv.setLayoutManager(layoutManager);
        tempUrl="https://pro-scheduler-backend.herokuapp.com/getReminderall/uid/" + currentUser.getUid();
        new ReminderTask().execute("https://pro-scheduler-backend.herokuapp.com/getReminderall/uid/" + currentUser.getUid(),sortbyChoice);
        Log.d("Login","https://pro-scheduler-backend.herokuapp.com/getReminderall/uid/" + currentUser.getUid());
    }


    public class ReminderTask extends AsyncTask<String,Void,String>
    {
        ReminderDetails reminderDetails;
        List<ReminderDetails> list;
        String choice="";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Collecting data from server");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... voids) {
            String url,json=null;
            choice=voids[1];
            if(currentUser!=null)
            {
                Log.d("Login","Check One");
                try {
                    url=voids[0];
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



                List<ReminderDetails> finallist=getFinalList(list,sortbyChoice);

                reminderAdapter=new ReminderAdapter(finallist, new ReminderAdapter.OnItemClickListener() {
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

        private List<ReminderDetails> getFinalList(List<ReminderDetails> list, String sortbyChoice) {
            List<ReminderDetails> templist=new ArrayList<ReminderDetails>();
            if(sortbyChoice.equals("Random"))
                return list;
            else if(sortbyChoice.equals("Time")) {
                return list;
            }
            else if(sortbyChoice.equals("Priority"))
                return list;
            return templist;
        }
    }


}
