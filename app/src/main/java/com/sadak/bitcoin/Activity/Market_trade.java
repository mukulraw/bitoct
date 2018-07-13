package com.sadak.bitcoin.Activity;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sadak.bitcoin.R;
import com.sadak.bitcoin.fragment.Market;

public class Market_trade extends AppCompatActivity {
    FragmentManager fm;
    FragmentTransaction ft;
    SharedPreferences sptodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_trade);
        getSupportActionBar().hide();
        fm = getSupportFragmentManager();
        sptodo = getSharedPreferences("Toopen",MODE_PRIVATE);
        SharedPreferences.Editor editor = sptodo.edit();
        editor.putString("todo", "Trade");
        editor.commit();

        Market m = new Market();
        ft = fm.beginTransaction();
        ft.replace(R.id.markettrade,m);
        ft.commit();
    }
}
