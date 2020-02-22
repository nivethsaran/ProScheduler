package com.hexactive.proscheduler.ReminderModule;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hexactive.proscheduler.R;

import org.jsoup.Jsoup;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class AddEditReminderActivity extends AppCompatActivity {
    EditText date_et,time_et;
    TextInputEditText title_et,note_et;
    String date,time,title,note,notification="0",priority="L",uid,rid="";
    private int mYear, mMonth, mDay, mHour, mMinute;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    Spinner priority_spinner;
    FirebaseAuth mAuth;
    Button button,update;
    CheckBox notification_cb;
    Calendar reminderNotification;
    FirebaseUser currentUser;
    ProgressDialog dialog;
    boolean FLAG=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_reminder);

        date_et=findViewById(R.id.edittext_date);
        time_et=findViewById(R.id.edittext_time);
        title_et=findViewById(R.id.edittext_title);
        note_et=findViewById(R.id.edittext_Note);
        notification_cb=findViewById(R.id.notification_check);
        priority_spinner=findViewById(R.id.spinner_priority);
        date="";time="";note="";title="";notification="";priority="";uid="";
        button=findViewById(R.id.submit_btn);
        update=findViewById(R.id.update_btn);
        reminderNotification=Calendar.getInstance();

        dialog = new ProgressDialog(new ContextThemeWrapper(AddEditReminderActivity.this, R.style.MyProgressDialog));
        final Intent intent=getIntent();
        String type=intent.getStringExtra("type");
        if(type.equals("ADD"))
        {
            Log.d("INTENT","ADD");
            time_et.setText("");
            title_et.setText("");
            note_et.setText("");
            date_et.setText("");
            notification_cb.setChecked(false);
            button.setEnabled(true);
            update.setEnabled(false);
        }
        else if(type.equals("VIEW"))
        {
            Log.d("INTENT","VIEW");
            time_et.setEnabled(false);
            date_et.setEnabled(false);
            note_et.setEnabled(false);
            title_et.setEnabled(false);
            notification_cb.setEnabled(false);
            priority_spinner.setEnabled(false);
            ReminderDetails intentData= (ReminderDetails) intent.getSerializableExtra("data");
            time_et.setText(intentData.r_time.substring(0,5));
            title_et.setText(intentData.title.replace('_',' '));
            note_et.setText(intentData.note.replace('_',' '));
            date_et.setText(intentData.r_date.substring(0,10));
            if(intentData.notification.equals("1"))
            notification_cb.setChecked(true);
            priority=intentData.priority;
            button.setEnabled(false);
            update.setEnabled(false);
        }
        else if(type.equals("EDIT"))
        {
            Log.d("INTENT","EDIT");
            title_et.setEnabled(false);
            notification_cb.setEnabled(true);
            priority_spinner.setEnabled(true);
            ReminderDetails intentData= (ReminderDetails) intent.getSerializableExtra("data");
            time_et.setText(intentData.r_time.substring(0,5));
            title_et.setText(intentData.title.replace('_',' '));
            note_et.setText(intentData.note.replace('_',' '));
            date_et.setText(intentData.r_date.substring(0,10));
            if(intentData.notification.equals("1"))
                notification_cb.setChecked(true);
            priority=intentData.priority;
            button.setEnabled(false);
            update.setEnabled(true);
            date=intentData.r_date.substring(0,10);
            time=intentData.r_time.substring(0,5);
            note=intentData.note.replace('_',' ');
            title=intentData.title.replace('_',' ');
            notification=intentData.notification;
            rid=intentData.rid;
            priority=intentData.priority;
        }

        mAuth=FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();
        uid=currentUser.getUid();



        notification_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    notification="1";
                else
                    notification="0";
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(date_et.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter date",Toast.LENGTH_SHORT).show();
                }
                if(time_et.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter time",Toast.LENGTH_SHORT).show();
                }
                if(title_et.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter title",Toast.LENGTH_SHORT).show();
                }
                if(note_et.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter note",Toast.LENGTH_SHORT).show();
                }

                note=note_et.getText().toString();
                title=title_et.getText().toString();
                if(!(note.equals("")||priority.equals("")||date.equals("")||time.equals("")||notification.equals("")||title.equals("")||uid.equals("")))
                {
                    rid=uid+System.currentTimeMillis();
                    String url="http://pro-scheduler-backend.herokuapp.com/insertReminder/date/"+date+"/time/"+time+"/uid/"+uid+"/note/"+note.replace(' ','_')+"/title/"+title.replace(' ','_')+"/notify/"+notification+"/pri/"+priority+"/rid/"+rid;
                    Log.d("URL",url);

                    String myDate = date+" "+time+":00";
                    LocalDateTime localDateTime = LocalDateTime.parse(myDate,
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") );
                    long millis = localDateTime
                            .atZone(ZoneId.systemDefault())
                            .toInstant().toEpochMilli();

                    Intent myIntent = new Intent(AddEditReminderActivity.this , ReminderNotificationService.class);
                    myIntent.putExtra("title",title);
                    myIntent.putExtra("uid",uid);
                    myIntent.putExtra("date",date);
                    myIntent.putExtra("time",time);
                    myIntent.putExtra("notification",notification);
                    myIntent.putExtra("note",note);
                    myIntent.putExtra("priority",priority);
                    myIntent.putExtra("rid",rid);
                    AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                    PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 0, myIntent, 0);
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP,millis,pendingIntent);

                    new AddReminderTask().execute(url);
                }
                else{
                    Toast.makeText(getApplicationContext(),"All fields are mandatory",Toast.LENGTH_SHORT).show();
                }

            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(date_et.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter date",Toast.LENGTH_SHORT).show();
                }
                if(time_et.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter time",Toast.LENGTH_SHORT).show();
                }
                if(title_et.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter title",Toast.LENGTH_SHORT).show();
                }
                if(note_et.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter note",Toast.LENGTH_SHORT).show();
                }

                note=note_et.getText().toString();
                title=title_et.getText().toString();
                if(!(note.equals("")||priority.equals("")||date.equals("")||time.equals("")||notification.equals("")||title.equals("")||uid.equals("")))
                {
                    String url="http://pro-scheduler-backend.herokuapp.com/updateReminder/date/"+date+"/time/"+time+"/uid/"+uid+"/note/"+note.replace(' ','_')+"/title/"+title.replace(' ','_')+"/notify/"+notification+"/pri/"+priority+"/rid/"+rid;
                    Log.d("URL",url);
                    new UpdateReminderTask().execute(url);
                }
                else{
                    Toast.makeText(getApplicationContext(),"All fields are mandatory",Toast.LENGTH_SHORT).show();
                }

            }
        });
        ArrayAdapter<String> priorityAdapter=new ArrayAdapter<String>(getBaseContext(),R.layout.priority_spinner_item,new String[]{"Priority:Low","Priority:Medium","Priority:High"});
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        priority_spinner.setAdapter(priorityAdapter);

        priority_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(i==0)
                {
                    priority="L";
                }
                else if(i==1)
                {
                    priority="M";
                }
                else if(i==2)
                {
                    priority="H";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour= c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);



        date_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog =new DatePickerDialog(AddEditReminderActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        if((i1+1)<10)
                        {
                            date=i+"-0"+(i1+1)+"-"+i2;
                        }
                        else
                        {
                            date=i+"-"+(i1+1)+"-"+i2;
                        }

                        date_et.setText(date);
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });

        time_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog= new TimePickerDialog(AddEditReminderActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        if(i1>=10)
                        {
                            time=i+":"+i1;
                            time_et.setText(time);

                        }
                        else
                        {
                            time=i+":0"+i1;
                            time_et.setText(time);
                        }

                    }
                },mHour,mMinute,true);
                timePickerDialog.show();
            }
        });

    }

    public class AddReminderTask extends AsyncTask<String,Void,String>
    {
        String URL,resp;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Collecting data from server");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            try{
                URL=strings[0];
                resp = Jsoup.connect(URL).ignoreContentType(true).execute().body();
            }
            catch (Exception e)
            {
                Log.d("Error","Error");
            }
            return resp;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            if(s.equals("Success"))
            {
                Toast.makeText(getApplicationContext(),"Added Reminder Successfully",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Server error,try again later",Toast.LENGTH_SHORT).show();
            }
        }
    }


    public class UpdateReminderTask extends AsyncTask<String,Void,String>
    {
        String URL,resp;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Collecting data from server");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            try{
                URL=strings[0];
                resp = Jsoup.connect(URL).ignoreContentType(true).execute().body();
            }
            catch (Exception e)
            {
                Log.d("Error",e.getMessage());
            }
            return resp;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            if(s.equals("Success"))
            {
                Toast.makeText(getApplicationContext(),"Added Reminder Successfully",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Server error,try again later",Toast.LENGTH_SHORT).show();
            }

        }
    }
}
