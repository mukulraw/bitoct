package com.sadak.bitcoin.fragment.Trade_all;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sadak.bitcoin.R;
import com.sadak.bitcoin.VerticalViewPager;
import com.sadak.bitcoin.fragment.swipe_one;
import com.sadak.bitcoin.fragment.swipe_two;

import me.kaelaela.verticalviewpager.transforms.ZoomOutTransformer;

public class Swipe_two_sell extends Fragment {
    VerticalViewPager verticalViewPager;
    static final int NUMBER_OF_PAGES = 2;

    MyAdapter mAdapter;
    com.prabhat1707.verticalpager.VerticalViewPager mPager;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sellfrag, container, false);

        mAdapter = new MyAdapter(getActivity().getSupportFragmentManager());
        mPager = v.findViewById(R.id.viewpagersell);
        mPager.setPageTransformer(false , new ZoomOutTransformer());
        mPager.setAdapter(mAdapter);

        return v;
    }

    public static class MyAdapter extends FragmentPagerAdapter {
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
                    return Swipe_one_sell.newInstance(0);
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