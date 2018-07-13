package com.sadak.bitcoin.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sadak.bitcoin.fragment.Gainer;
import com.sadak.bitcoin.fragment.Loser;
import com.sadak.bitcoin.fragment.Market_viewpager.fifth_tab_market;
import com.sadak.bitcoin.fragment.Market_viewpager.first_tab_market;
import com.sadak.bitcoin.fragment.Market_viewpager.fourth_tab_market;
import com.sadak.bitcoin.fragment.Market_viewpager.second_tab_market;
import com.sadak.bitcoin.fragment.Market_viewpager.seventh_tab_market;
import com.sadak.bitcoin.fragment.Market_viewpager.sixth_tab_market;
import com.sadak.bitcoin.fragment.Market_viewpager.third_tab_market;

/**
 * Created by USER on 25-05-2018.
 */

public class Market_viewpager_adapter extends FragmentPagerAdapter {
    public Market_viewpager_adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0)
        {
            return new first_tab_market();
        }
        else if (position==1)
        {
            return new second_tab_market();
        }
        else if (position==2)
        {
            return new third_tab_market();
        }
        else if (position==3)
        {
            return new fourth_tab_market();
        }
        else if (position==4)
        {
            return new fifth_tab_market();
        }
        else if (position==5)
        {
            return new sixth_tab_market();
        }
        else
        {
            return new seventh_tab_market();
        }
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position==0)
        {
            return "BTC";
        }
        else if (position==1)
        {
            return "LTC";
        }
        else if (position==2)
        {
            return "ETH";
        }
        else if (position==3)
        {
            return "DASH";
        }
        else if (position==4)
        {
            return "ZCASH";
        }
        else if (position==5)
        {
            return "IOTA";
        }
        else
        {
            return "EOS";
        }

    }
}
