package com.hexactive.proscheduler.MainModule;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hexactive.proscheduler.CalendarModule.CalendarActivity;
import com.hexactive.proscheduler.LoginModule.LoginActivity;
import com.hexactive.proscheduler.ProfileModule.ProfileActivity;
import com.hexactive.proscheduler.R;
import com.hexactive.proscheduler.ReminderModule.ReminderActivity;
import com.hexactive.proscheduler.SettingsModule.SettingsActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
private CalendarView calendarView;
private Spinner main_lang_spinner;
private String language;
private Button changeLan_btn;
private Fragment mDrawer;
    boolean doubleBackToExitPressedOnce = false;
    ImportantReminderFragment importantReminderFragment;
    private ActionBarDrawerToggle mDrawerToggle;
private Locale locale;
FirebaseAuth firebaseAuth;
FirebaseUser firebaseUser;
ImageButton calendar_btn,reminder_btn,settings_btn,profile_btn;

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sp=getSharedPreferences("mycredentials",Context.MODE_PRIVATE);
        language=sp.getString("langauge","en");
        firebaseAuth=FirebaseAuth.getInstance();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setTheme(R.style.AppThemeLight);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar_btn=findViewById(R.id.calendar_btn);
        reminder_btn=findViewById(R.id.alarm_btn);
        settings_btn=findViewById(R.id.settings_btn);
        profile_btn=findViewById(R.id.profile_btn);
        main_lang_spinner=findViewById(R.id.spinner_login_language);
        changeLan_btn=findViewById(R.id.change_lan_btn);

        importantReminderFragment = new ImportantReminderFragment();
        getSupportFragmentManager().beginTransaction().detach(importantReminderFragment);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.important_reminder_container, importantReminderFragment).commit();

        ArrayAdapter<String> priorityAdapter=new ArrayAdapter<String>(getBaseContext(),R.layout.priority_spinner_item,new String[]{"English","Spanish"});
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        main_lang_spinner.setAdapter(priorityAdapter);

        main_lang_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0)
                {
                    language="en";
                    SharedPreferences preferences = getSharedPreferences("mycredentials", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("language", "en");
                    editor.commit();
                }
                else if(i==1)
                {
                    language="es";
                    SharedPreferences preferences = getSharedPreferences("mycredentials",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("language", "es");
                    editor.commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        changeLan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locale = new Locale(language);
                Locale.setDefault(locale);
                SharedPreferences preferences = getSharedPreferences("mycredentials",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("language", language);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                refresh();

            }
        });




        calendar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        reminder_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ReminderActivity.class);
                startActivity(intent);
            }
        });

        settings_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });




    }


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        Configuration config = getBaseContext().getResources().getConfiguration();
        // refresh your views here
        Locale.setDefault(locale);
        config.locale = locale;
        super.onConfigurationChanged(newConfig);

    }

    private void refresh() {
        finish();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.logout)
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Do you really want to logout?").setTitle("Logout");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    firebaseAuth.signOut();
                    Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            })
            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            }).setCancelable(true);
            builder.show();
        }
        else if(item.getItemId()==R.id.osl)
        {
            String url = "https://choosealicense.com/licenses/mit/";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
        else if(item.getItemId()==R.id.tandc)
        {
            String url = "https://www.termsandconditionsgenerator.com/live.php?token=YXkJtIc6gZs38KDWSMhHQwI3VJ9PKhlM";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            firebaseAuth.signOut();
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


}
