package com.sadak.bitcoin.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.sadak.bitcoin.R;
import com.sadak.bitcoin.adapter.Fund_recycler_view;
import com.sadak.bitcoin.model.fund.Datum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Deposit extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Datum> list = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    String click_string = "2";
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        getSupportActionBar().hide();
        recyclerView = (RecyclerView) findViewById(R.id.redeposite);
        textView = (TextView) findViewById(R.id.txt);
        textView.setText("Deposit");
        list = (List<Datum>) getIntent().getSerializableExtra("coinlist");
        Fund_recycler_view f = new Fund_recycler_view(Deposit.this,list,click_string);
        linearLayoutManager=new LinearLayoutManager(Deposit.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(f);
    }
}
