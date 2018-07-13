package com.sadak.bitcoin.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.sadak.bitcoin.R;

public class Security extends AppCompatActivity {

    LinearLayout changepassword,googlell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
        getSupportActionBar().hide();
        changepassword = (LinearLayout) findViewById(R.id.changepaasswordll);
        googlell = (LinearLayout) findViewById(R.id.googlell);
        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Security.this,ChangePassword.class);
                startActivity(i);
            }
        });

    }
}
