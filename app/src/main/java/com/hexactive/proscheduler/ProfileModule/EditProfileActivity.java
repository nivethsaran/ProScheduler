package com.hexactive.proscheduler.ProfileModule;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.hexactive.proscheduler.R;

public class EditProfileActivity extends AppCompatActivity {

EditText fname_tv,lname_tv,mobile_tv,email_tv,design_tv;
ImageView fname_iv,lname_iv,mobile_iv,email_iv,design_iv;

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
    }
}
