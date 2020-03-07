package com.hexactive.proscheduler.ProfileModule;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hexactive.proscheduler.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

public class ProfileActivity extends AppCompatActivity {
FirebaseAuth mAuth;
FirebaseUser firebaseUser;
ImageView edit_iv;
TextView designationtv,nametv,emailtv,mobiletv,avatarurltv,resumepathtv,instatv,githubtv,linkedintv,websitetv;
ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        edit_iv=findViewById(R.id.edit);
        designationtv=findViewById(R.id.designation);
        nametv=findViewById(R.id.name);
        emailtv=findViewById(R.id.email);
        mobiletv=findViewById(R.id.mobile);
//        avatarurltv=findViewById(R.id.i)
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

        edit_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProfileActivity.this,EditProfileActivity.class);
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
                e.printStackTrace();
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
                nametv.setText(jsonObject1.getString("f_name")+" "+jsonObject1.getString("r_name"));
                designationtv.setText(jsonObject1.getString("designation"));
                emailtv.setText("Email:"+jsonObject1.getString("email"));
                mobiletv.setText(jsonObject1.getString("mobile"));
                resumepathtv.setText("Resume:"+jsonObject1.getString("resumepath"));
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

            }
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }
}
