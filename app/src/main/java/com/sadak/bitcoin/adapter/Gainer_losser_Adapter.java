package com.sadak.bitcoin.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sadak.bitcoin.fragment.Gainer;
import com.sadak.bitcoin.fragment.Loser;

/**
 * Created by USER on 24-05-2018.
 */

public class Gainer_losser_Adapter extends FragmentPagerAdapter {




    public Gainer_losser_Adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0)
        {
            return new Gainer();
        }
        else
        {
                return new Loser();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position==0)
        {
            return "Gainers";
        }
        else
        {
            return "Losers";
        }

    }
}
