package com.hexactive.proscheduler.SettingsModule;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hexactive.proscheduler.BuildConfig;
import com.hexactive.proscheduler.R;

import org.json.JSONObject;
import org.jsoup.Jsoup;

public class SettingsActivity extends AppCompatActivity {
SharedPreferences sp;
Switch rememberpass,automaticlogin;
Button updatebtn;
    @Override
    protected void onStart() {
        super.onStart();
        sp=getSharedPreferences("mycredentials", Context.MODE_PRIVATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        updatebtn=findViewById(R.id.updatecheck_btn);
        sp=getSharedPreferences("mycredentials", Context.MODE_PRIVATE);
        rememberpass=findViewById(R.id.rememberpass);
        automaticlogin=findViewById(R.id.automaticlogin);
        Boolean ir=sp.getBoolean("remembermestatus",false);
        Boolean ia=sp.getBoolean("automatic",false);
        rememberpass.setChecked(ir);
        automaticlogin.setChecked(ia);

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), BuildConfig.VERSION_NAME,Toast.LENGTH_SHORT).show();
                new UpdateTask().execute();
            }
        });

        rememberpass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    SharedPreferences preferences = getSharedPreferences("mycredentials",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("remembermestatus",true);
                    editor.commit();
                }
                else
                {
                    SharedPreferences preferences = getSharedPreferences("mycredentials",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("remembermestatus",false);
                    editor.commit();
                }

            }
        });

        automaticlogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    SharedPreferences preferences = getSharedPreferences("mycredentials",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("automatic",true);
                    editor.commit();
                }
                else
                {
                    SharedPreferences preferences = getSharedPreferences("mycredentials",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("automatic",false);
                    editor.commit();
                }
            }
        });

    }

    class UpdateTask extends AsyncTask<Void,Void,Boolean>
    {

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                String url = "https://pro-scheduler-backend.herokuapp.com/getversion";
//                Log.d("ImportantReminder","URL:"+url);
                String json = Jsoup.connect(url).ignoreContentType(true).execute().body();
                JSONObject jsonObject=new JSONObject(json);
                if(jsonObject.get("version").equals(BuildConfig.VERSION_NAME))
                {
                    return false;
                }
                else
                {
                    return true;
                }
//                    Log.d("ImportantReminder","JSON:"+json);
            }
            catch (Exception e)
            {
                return false;
//                e.printStackTrace();
//                Log.d("ImportantReminder","Error");
            }

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean) {
                Toast.makeText(getApplicationContext(), "Update Availabale", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"No Update Available",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
