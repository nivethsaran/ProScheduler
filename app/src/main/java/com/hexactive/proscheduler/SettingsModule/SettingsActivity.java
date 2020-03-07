package com.hexactive.proscheduler.SettingsModule;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hexactive.proscheduler.BuildConfig;
import com.hexactive.proscheduler.R;

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
        updatebtn=findViewById(R.id.update_btn);
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
                Toast.makeText(getApplicationContext(), BuildConfig.VERSION_NAME,Toast.LENGTH_SHORT).show();
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
}
