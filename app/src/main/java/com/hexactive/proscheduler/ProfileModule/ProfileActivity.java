package com.hexactive.proscheduler.ProfileModule;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hexactive.proscheduler.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

public class ProfileActivity extends AppCompatActivity {
FirebaseAuth mAuth;
FirebaseUser firebaseUser;
ImageView edit_iv,history_iv;
TextView designationtv,nametv,emailtv,mobiletv,avatarurltv,resumepathtv,instatv,githubtv,linkedintv,websitetv;
ImageView avatar;
ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        history_iv=findViewById(R.id.history);
        edit_iv=findViewById(R.id.edit);
        designationtv=findViewById(R.id.designation);
        nametv=findViewById(R.id.name);
        emailtv=findViewById(R.id.email);
        mobiletv=findViewById(R.id.mobile);
        avatar=findViewById(R.id.profile);
        resumepathtv=findViewById(R.id.resume);
        instatv=findViewById(R.id.instagram);
        githubtv=findViewById(R.id.github);
        linkedintv=findViewById(R.id.linkedin);
        websitetv=findViewById(R.id.website);
        mAuth=FirebaseAuth.getInstance();
        firebaseUser=mAuth.getCurrentUser();
        String url1="https://pro-scheduler-backend.herokuapp.com/getsocial/uid/"+firebaseUser.getUid();
        String url2="https://pro-scheduler-backend.herokuapp.com/getprofiledata/uid/"+firebaseUser.getUid();
        dialog = new ProgressDialog(new ContextThemeWrapper(ProfileActivity.this, R.style.MyProgressDialog));
        new ProfileRetrievalTask().execute(url1,url2);


        history_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProfileActivity.this,PreviousActivityModule.class);
                startActivity(intent);
            }
        });

        edit_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProfileActivity.this,EditProfileActivity.class);
                String n[]=nametv.getText().toString().split(" ");
                intent.putExtra("fname",n[0]);
                intent.putExtra("lname",n[1]);
                intent.putExtra("rpath",resumepathtv.getText().toString());
                intent.putExtra("email",emailtv.getText().toString());
                intent.putExtra("mobile",mobiletv.getText().toString());
                startActivity(intent);
            }
        });

    }

    class ProfileRetrievalTask extends AsyncTask<String,Void,String[]>
    {String URL1,URL2,json1="",json2="";
        String insta="Nil",github="Nil",linkedin="Nil",website="Nil";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Collecting data from server");
            dialog.show();
        }

        @Override
        protected String[] doInBackground(String... voids) {
            try {
                URL1 = voids[0];
                URL2 = voids[1];
//                Log.d("Login","URL:"+url);
                    json1 = Jsoup.connect(URL1).ignoreContentType(true).execute().body();
                    json2 = Jsoup.connect(URL2).ignoreContentType(true).execute().body();
//                Log.d("Login","JSON:"+json);
            }
            catch (Exception e)
            {
//                Log.d("Login","Error");
            }
            return new String[]{json1,json2};
        }

        @Override
        protected void onPostExecute(String[] s) {
            super.onPostExecute(s);
            try {
                JSONArray dataArray1=new JSONArray(s[0]);
                JSONArray dataArray2=new JSONArray(s[1]);
                JSONObject jsonObject1=dataArray1.getJSONObject(0);
                nametv.setText(jsonObject1.getString("fname")+" "+jsonObject1.getString("rname"));
                designationtv.setText(jsonObject1.getString("design"));
                emailtv.setText(jsonObject1.getString("email"));
                mobiletv.setText(jsonObject1.getString("mobile"));
                resumepathtv.setText(jsonObject1.getString("rpath"));
                Picasso.get().load(jsonObject1.getString("avatarurl")).into(avatar);
                for (int i=0;i<dataArray2.length();i++)
                {
                    JSONObject jsonObjectTemp=dataArray2.getJSONObject(i);
                    if(jsonObjectTemp.getString("platformname").equals("instagram"))
                    {
                        insta=jsonObjectTemp.getString("link");
                    }
                    if(jsonObjectTemp.getString("platformname").equals("github"))
                    {
                        github=jsonObjectTemp.getString("link");
                    }
                    if(jsonObjectTemp.getString("platformname").equals("linkedin"))
                    {
                        linkedin=jsonObjectTemp.getString("link");
                    }
                    if(jsonObjectTemp.getString("platformname").equals("website"))
                    {
                        website=jsonObjectTemp.getString("link");
                    }
                }
                instatv.setText(insta);
                githubtv.setText(github);
                linkedintv.setText(linkedin);
                websitetv.setText(website);

            }
            catch (Exception e)
            {
                Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_SHORT).show();
            }
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }
}
