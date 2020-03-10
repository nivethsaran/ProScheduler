package com.hexactive.proscheduler.MiscModules;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;

import com.hexactive.proscheduler.LoginModule.LoginActivity;
import com.hexactive.proscheduler.R;

import static java.lang.Thread.sleep;

public class LaunchActivity extends AppCompatActivity {


    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        progressBar=findViewById(R.id.progress_bar_circular);
        progressBar.setVisibility(View.VISIBLE);
        new LaunchTask().execute();

    }

    @Override
    protected void onResume() {
        super.onResume();
        new LaunchTask().execute();
    }

    class LaunchTask extends AsyncTask<Void, Void, Boolean> {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(isNetworkAvailable())
            {
                return true;
            }
            else
            {
                return false;
            }

        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        protected void onPostExecute(Boolean netAvl) {
            super.onPostExecute(netAvl);
            progressBar.setVisibility(View.INVISIBLE);
            if(netAvl)
            {
                Intent intent=new Intent(LaunchActivity.this, LoginActivity.class);
                startActivity(intent);
            }
            else {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(LaunchActivity.this, R.style.MyProgressDialog));
                alertDialog.setCancelable(false);
                alertDialog.setMessage("Turn on Internet or Close the Appplication!");
                alertDialog.setPositiveButton("RETRY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new LaunchTask().execute();
                    }
                });
                alertDialog.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                alertDialog.setTitle("No Internet Connection");
                alertDialog.show();
            }

        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}