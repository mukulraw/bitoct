package com.sadak.bitcoin.fragment.Trade_all;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sadak.bitcoin.Activity.Market_trade;
import com.sadak.bitcoin.CustomViewPager;
import com.sadak.bitcoin.NetworkConnection.Apidata;
import com.sadak.bitcoin.NetworkConnection.RetrofitInstance;
import com.sadak.bitcoin.R;
import com.sadak.bitcoin.VerticalViewPager;
import com.sadak.bitcoin.adapter.Trade.Recycler_Sell_Order;
import com.sadak.bitcoin.adapter.Trade.Trade_history;
import com.sadak.bitcoin.fragment.Gainer;
import com.sadak.bitcoin.fragment.swipe_one;
import com.sadak.bitcoin.fragment.swipe_two;
import com.sadak.bitcoin.model.BtcBalance.Datum;
import com.sadak.bitcoin.model.BtcBalance.Profile;
import com.sadak.bitcoin.model.Sellorder.Example;

import java.util.ArrayList;
import java.util.List;

import me.kaelaela.verticalviewpager.transforms.ZoomOutTransformer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Trade_buy_sell extends Fragment {
    VerticalViewPager verticalViewPager;
    static final int NUMBER_OF_PAGES = 2;

    MyAdapter mAdapter;
    com.prabhat1707.verticalpager.VerticalViewPager mPager;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.buy_sell_trade, container, false);

        mAdapter = new MyAdapter(getChildFragmentManager());
        mPager = v.findViewById(R.id.viewpager);

        mPager.setPageTransformer(false, new ZoomOutTransformer());

        // Toast.makeText(getContext(), "qwerty", Toast.LENGTH_SHORT).show();
        mPager.setAdapter(mAdapter);


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    public static class MyAdapter extends FragmentStatePagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUMBER_OF_PAGES;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return swipe_one.newInstance(0);
                case 1:
                    // return a different Fragment class here
                    // if you want want a completely different layout
                    return swipe_two.newInstance(1);
                default:
                    return null;
            }
        }
    }
}
