package com.hexactive.proscheduler.ProfileModule;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hexactive.proscheduler.R;

import org.jsoup.Jsoup;

public class EditProfileActivity extends AppCompatActivity {

EditText fname_tv,lname_tv,mobile_tv,email_tv,design_tv;
ImageView fname_iv,lname_iv,mobile_iv,email_iv,design_iv;
Button update;
FirebaseAuth auth;
FirebaseUser currentuser;

    @Override
    protected void onStart() {
        super.onStart();
        auth=FirebaseAuth.getInstance();
        currentuser=auth.getCurrentUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        fname_tv=findViewById(R.id.fname_tv);
        lname_tv=findViewById(R.id.lname_tv);
        mobile_tv=findViewById(R.id.mobile_tv);
        email_tv=findViewById(R.id.email_tv);
        design_tv=findViewById(R.id.design_tv);
        fname_iv=findViewById(R.id.fname_iv);
        lname_iv=findViewById(R.id.lname_iv);
        mobile_iv=findViewById(R.id.mobile_iv);
        email_iv=findViewById(R.id.email_iv);
        design_iv=findViewById(R.id.design_iv);
        update=findViewById(R.id.updateProfile);
        Intent intent=getIntent();
        fname_tv.setText(intent.getStringExtra("fname"));
        lname_tv.setText(intent.getStringExtra("lname"));
        design_tv.setText(intent.getStringExtra("rpath"));
        email_tv.setText(intent.getStringExtra("email"));
        mobile_tv.setText(intent.getStringExtra("mobile"));

        fname_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fname_tv.isEnabled())
                {
                    fname_tv.setEnabled(false);
                    fname_iv.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.editbig));
                }
                else
                {
                    fname_tv.setEnabled(true);
                    fname_iv.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.done));
                }

            }
        });

        lname_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lname_tv.isEnabled())
                {
                    lname_tv.setEnabled(false);
                    lname_iv.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.editbig));
                }
                else
                {
                    lname_tv.setEnabled(true);
                    lname_iv.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.done));
                }

            }
        });

        mobile_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mobile_tv.isEnabled())
                {
                    mobile_tv.setEnabled(false);
                    mobile_iv.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.editbig));
                }
                else
                {
                    mobile_tv.setEnabled(true);
                    mobile_iv.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.done));
                }

            }
        });

        email_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email_tv.isEnabled())
                {
                    email_tv.setEnabled(false);
                    email_iv.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.editbig));
                }
                else
                {
                    email_tv.setEnabled(true);
                    email_iv.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.done));
                }

            }
        });

        design_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(design_tv.isEnabled())
                {
                    design_tv.setEnabled(false);
                    design_iv.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.editbig));
                }
                else
                {
                    design_tv.setEnabled(true);
                    design_iv.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.done));
                }

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(fname_tv.getText().toString().equals("")||lname_tv.getText().toString().equals("")||email_tv.getText().toString().equals("")||design_tv.getText().toString().equals("")||mobile_tv.getText().toString().equals("")))
                {
                    String url="https://pro-scheduler-backend.herokuapp.com/updateprofile/?uid="+currentuser.getUid()+"&fname="+fname_tv.getText().toString()+"&lname="+lname_tv.getText().toString()+"&email="+email_tv.getText().toString()+"&mobile="+mobile_tv.getText().toString()+"&rpath="+design_tv.getText().toString();
                    Log.d("EditProfile",url);
                    new UpdateProfileTask().execute(url);
                }
            }
        });

    }

    class UpdateProfileTask extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            try {

                String json1 = Jsoup.connect(strings[0]).ignoreContentType(true).execute().body();
                String json2 = Jsoup.connect("https://pro-scheduler-backend.herokuapp.com/inserthistory/uid/"+currentuser.getUid()+"/time/"+Long.toString(System.currentTimeMillis())).ignoreContentType(true).execute().body();
                Log.d("EditProfile","JSON:"+json1);
                return "Working";
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return "Error";
//                Log.d("Login","Error");
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("EditProfile",s);
        }
    }
}
