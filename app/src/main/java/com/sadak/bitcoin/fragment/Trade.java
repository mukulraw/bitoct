package com.sadak.bitcoin.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
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
import com.sadak.bitcoin.fragment.Trade_all.Swipe_two_sell;
import com.sadak.bitcoin.fragment.Trade_all.Trade_buy_sell;

public class Trade extends Fragment {

    Spinner spinner;
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView markettext, orderhistory;
    SharedPreferences sptodo, sharedPreferencesMarketid;
    String marketid, todo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.trade, container, false);
        //spinner = (Spinner) v.findViewById(R.id.spinnertradefirst);
        tabLayout = (TabLayout) v.findViewById(R.id.tablayouttrade);
        viewPager = (ViewPager) v.findViewById(R.id.viewpagertrade);
        orderhistory = (TextView) v.findViewById(R.id.order_history);
        markettext = v.findViewById(R.id.markettext);


        tabLayout.addTab(tabLayout.newTab().setText("Buy"));
        tabLayout.addTab(tabLayout.newTab().setText("Sell"));
        tabLayout.addTab(tabLayout.newTab().setText("Open Order"));

        PagerAdapter adapter = new PagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("Buy");
        tabLayout.getTabAt(1).setText("Sell");
        tabLayout.getTabAt(2).setText("Open Order");


        markettext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), Market_trade.class);
                intent.putExtra("Market", "T");
                startActivity(intent);
                getActivity().finish();


            }
        });

        orderhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), History.class);
                startActivity(intent);
            }
        });


        return v;
    }


    class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new Trade_buy_sell();
            } else if (position == 1) {
                return new Swipe_two_sell();
            } else {
                return new OrderFrag();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }


    @Override
    public void onResume() {
        super.onResume();

        sptodo = getContext().getSharedPreferences("Toopen", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sptodo.edit();
        editor.putString("todo", "");
        editor.commit();


        markettext.setText("" + MainActivity.market_name);

    }
}