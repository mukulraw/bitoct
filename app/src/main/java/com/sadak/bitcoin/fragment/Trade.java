package com.sadak.bitcoin.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.sadak.bitcoin.Activity.History;
import com.sadak.bitcoin.Activity.Market_trade;
import com.sadak.bitcoin.MainActivity;
import com.sadak.bitcoin.R;
import com.sadak.bitcoin.adapter.Market_viewpager_adapter;
import com.sadak.bitcoin.adapter.Trade_adapter;

public class Trade extends Fragment {

    Spinner spinner;
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView markettext,orderhistory;
    SharedPreferences sptodo,sharedPreferencesMarketid;
    String marketid,todo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.trade,container,false);
        //spinner = (Spinner) v.findViewById(R.id.spinnertradefirst);
        tabLayout = (TabLayout) v.findViewById(R.id.tablayouttrade);
        viewPager = (ViewPager) v.findViewById(R.id.viewpagertrade);
        orderhistory=(TextView)v.findViewById(R.id.order_history);
        markettext = v.findViewById(R.id.markettext);
        viewPager.setAdapter(new Trade_adapter(getFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        sptodo = getContext().getSharedPreferences("Toopen", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sptodo.edit();
        editor.putString("todo","");
        editor.commit();


        markettext.setText(""+ MainActivity.market_name);

        markettext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), Market_trade.class);
                intent.putExtra("Market","T");
                startActivity(intent);
                getActivity().finish();


            }
        });

        orderhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),History.class);
                startActivity(intent);
            }
        });



        return v;
    }




}