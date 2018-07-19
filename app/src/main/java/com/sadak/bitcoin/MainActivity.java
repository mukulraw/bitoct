package com.sadak.bitcoin;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.sadak.bitcoin.adapter.threetextview;
import com.sadak.bitcoin.fragment.Account;
import com.sadak.bitcoin.fragment.Fund;
import com.sadak.bitcoin.fragment.Home;
import com.sadak.bitcoin.fragment.Market;
import com.sadak.bitcoin.fragment.Trade;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    android.support.v4.app.FragmentManager fragmentManager;
    FragmentTransaction ftHome, ftMarket, ftTrade, ftFund, ftAccount;
    //Home h = new Home();
    //Trade t = new Trade();
    SharedPreferences sptodo, sharedPreferencesMarketid;
    String marketid, todo;
    public static String market_name = "BSS/BTC";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    Home frag1 = new Home();
                    ft.replace(R.id.linearLayout, frag1);
                    //ft.addToBackStack(null);
                    ft.commit();

                break;
                case R.id.navigation_market:
                    // mTextMessage.setText(R.string.title_dashboard);
                    sptodo = getSharedPreferences("Toopen", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sptodo.edit();
                    editor.putString("todo", "Market");
                    editor.commit();

                    FragmentManager fm1 = getSupportFragmentManager();
                    FragmentTransaction ft1 = fm1.beginTransaction();
                    Market frag2 = new Market();
                    ft1.replace(R.id.linearLayout, frag2);
                    //ft.addToBackStack(null);
                    ft1.commit();

                    /*Bundle bundle = new Bundle();
                    Market m = new Market();
                    ftMarket = fragmentManager.beginTransaction();
                    ftMarket.replace(R.id.linearLayout, m);
                    ftMarket.commit();
*/
                break;
                case R.id.navigation_trade:

                    FragmentManager fm2 = getSupportFragmentManager();
                    FragmentTransaction ft2 = fm2.beginTransaction();
                    Trade frag3 = new Trade();
                    ft2.replace(R.id.linearLayout, frag3);
                    //ft.addToBackStack(null);
                    ft2.commit();
                    /*
                    Trade tdd = new Trade();
                    ftTrade = fragmentManager.beginTransaction();
                    ftTrade.replace(R.id.linearLayout, tdd);
                    ftTrade.commit();*/

                break;
                case R.id.navigation_fund:

                    FragmentManager fm3 = getSupportFragmentManager();
                    FragmentTransaction ft3 = fm3.beginTransaction();
                    Fund frag4 = new Fund();
                    ft3.replace(R.id.linearLayout, frag4);
                    //ft.addToBackStack(null);
                    ft3.commit();
                    /*
                    Fund f = new Fund();
                    ftFund = fragmentManager.beginTransaction();
                    ftFund.replace(R.id.linearLayout, f);
                    ftFund.commit();
*/
                break;
                case R.id.navigation_account:

                    FragmentManager fm4 = getSupportFragmentManager();
                    FragmentTransaction ft4 = fm4.beginTransaction();
                    Account frag5 = new Account();
                    ft4.replace(R.id.linearLayout, frag5);
                    //ft.addToBackStack(null);
                    ft4.commit();
/*
                    Account account = new Account();
                    ftAccount = fragmentManager.beginTransaction();
                    ftAccount.replace(R.id.linearLayout, account);
                    ftAccount.commit();*/

                break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        fragmentManager = getSupportFragmentManager();
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Home frag1 = new Home();
        ft.replace(R.id.linearLayout, frag1);
        //ft.addToBackStack(null);
        ft.commit();



/*
        ftHome = fragmentManager.beginTransaction();
        Home h = new Home();
        ftHome.add(R.id.linearLayout, h);
        ftHome.commit();
*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        sptodo = getSharedPreferences("Toopen", MODE_PRIVATE);
        todo = sptodo.getString("todo", "");
        if (todo.equals("Trade")) {

            FragmentManager fm1 = getSupportFragmentManager();
            FragmentTransaction ft1 = fm1.beginTransaction();
            Trade frag2 = new Trade();
            ft1.replace(R.id.linearLayout, frag2);
            //ft.addToBackStack(null);
            ft1.commit();
            /*
            Trade t = new Trade();
            ftTrade = fragmentManager.beginTransaction();
            ftTrade.replace(R.id.linearLayout, t);
            ftTrade.commit();*/

        }

    }

}
