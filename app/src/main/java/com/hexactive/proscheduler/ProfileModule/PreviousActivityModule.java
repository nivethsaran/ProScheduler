package com.hexactive.proscheduler.ProfileModule;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hexactive.proscheduler.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PreviousActivityModule extends AppCompatActivity {
FirebaseAuth auth;
FirebaseUser user;
ListView lv;
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    protected void onStart() {
        super.onStart();
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_module);
        lv=findViewById(R.id.lv);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        new PreviousTask().execute();
    }

    class PreviousTask extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            String json="";
            try {
                Log.d("Login","http://pro-scheduler-backend.herokuapp.com/gethistory/uid/"+user.getUid());
                json = Jsoup.connect("http://pro-scheduler-backend.herokuapp.com/gethistory/uid/"+user.getUid()).ignoreContentType(true).execute().body();
                Log.d("Login","JSON:"+json);
            }
            catch (Exception e)
            {
                Log.d("Login",e.getMessage());
            }
            return json;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                if(!s.equals("Error"))
                {
                    JSONArray dataArray=new JSONArray(s);
                    List<String> list=new ArrayList<String>();
                    for (int i=0;i<dataArray.length();i++)
                    {
                        JSONObject temp=dataArray.getJSONObject(i);
                        list.add("-->"+formatter.format(new Date(Long.parseLong(temp.getString("timestamp")))));

                    }

                    ArrayAdapter <String> arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.listitemprev,list);

                    lv.setAdapter(arrayAdapter);



                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {

            }

        }
    }
}
