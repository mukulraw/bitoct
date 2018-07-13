package com.sadak.bitcoin.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.sadak.bitcoin.MainActivity;
import com.sadak.bitcoin.R;

import java.io.IOException;
import java.io.InputStream;

public class Splash extends AppCompatActivity {
    SharedPreferences sp;
    String s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    sp = getSharedPreferences("Bitoct_user",MODE_PRIVATE);
                    s = sp.getString("user_id", "");
                    if (s.equals("")) {
                        Intent i = new Intent(Splash.this, Login.class);
                        startActivity(i);
                        finish();

                    }
                    else {
                        Intent i = new Intent(Splash.this, MainActivity.class);
                        MainActivity.market_name =  "BSS/BTC";
                        startActivity(i);
                        finish();
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

}


