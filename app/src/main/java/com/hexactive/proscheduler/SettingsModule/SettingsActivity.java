package com.hexactive.proscheduler.SettingsModule;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hexactive.proscheduler.BuildConfig;
import com.hexactive.proscheduler.LoginModule.LoginActivity;
import com.hexactive.proscheduler.R;

import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {
SharedPreferences sp;
Switch rememberpass,automaticlogin;
Button updatebtn,logout,clickhere;
FirebaseAuth auth;
FirebaseUser user;
    private Locale locale;
    String language;
    @Override
    protected void onStart() {
        super.onStart();
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        sp=getSharedPreferences("mycredentials", Context.MODE_PRIVATE);
        language=sp.getString("langauge","en");
        locale = new Locale(language);

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
        logout=findViewById(R.id.logout_btn);
        clickhere=findViewById(R.id.clickhere);
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), BuildConfig.VERSION_NAME,Toast.LENGTH_SHORT).show();
                new UpdateTask().execute();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent i = new Intent(SettingsActivity.this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        clickhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.sendPasswordResetEmail(user.getEmail()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(SettingsActivity.this, "Mail Sent", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SettingsActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
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
                String url = "https://www.termsandconditionsgenerator.com/live.php?token=YXkJtIc6gZs38KDWSMhHQwI3VJ9PKhlM";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Update Availabale", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"No Update Available",Toast.LENGTH_SHORT).show();

            }
        }
    }
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        Configuration config = getBaseContext().getResources().getConfiguration();
        // refresh your views here
        Locale.setDefault(locale);
        config.locale = locale;
        super.onConfigurationChanged(newConfig);

    }
}
