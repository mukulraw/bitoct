package com.sadak.bitcoin.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sadak.bitcoin.fragment.Loser;
import com.sadak.bitcoin.fragment.OrderFrag;
import com.sadak.bitcoin.fragment.Trade_all.Swipe_two_sell;
import com.sadak.bitcoin.fragment.Trade_all.Trade_buy_sell;

public class Trade_adapter extends FragmentStatePagerAdapter {

    public Trade_adapter(FragmentManager fragmentManager)
    {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0)
        {
            return new Trade_buy_sell();
        }
        else if (position==1)
        {
            return new Swipe_two_sell();
        }

        else
        {
            return new OrderFrag();
        }



//         else if(position==2)
//        {
//            return new OrderFrag();
//        }
//        return new Trade_buy_sell();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position==0)
        {
            return "Buy";
        }
        else if (position==1)
        {
            return "Sell";
        }
        else
        {
            return "Open Order";
        }
    }
}
