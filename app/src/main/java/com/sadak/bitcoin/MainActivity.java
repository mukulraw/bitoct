package com.sadak.bitcoin;

import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
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
    FragmentTransaction ftHome,ftMarket,ftTrade,ftFund,ftAccount;
    Home h = new Home();
    Trade t = new Trade();
    SharedPreferences sptodo,sharedPreferencesMarketid;
    String marketid,todo;
    public static String market_name =  "BSS/BTC";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                   // mTextMessage.setText(R.string.title_home);
                    ftHome = fragmentManager.beginTransaction();
                    ftHome.replace(R.id.linearLayout,h);
                    ftHome.commit();
                    return true;
                case R.id.navigation_market:
                   // mTextMessage.setText(R.string.title_dashboard);
                    sptodo = getSharedPreferences("Toopen",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sptodo.edit();
                    editor.putString("todo", "Market");
                    editor.commit();
                    Bundle bundle = new Bundle();
                    Market m = new Market();
                   ftMarket = fragmentManager.beginTransaction();
                   ftMarket.replace(R.id.linearLayout,m);
                   ftMarket.commit();
                    return true;
                case R.id.navigation_trade:

                    ftTrade = fragmentManager.beginTransaction();
                    ftTrade.replace(R.id.linearLayout,t);
                    ftTrade.commit();
                    return true;

                case R.id.navigation_fund:
                    Fund f = new Fund();
                    ftFund = fragmentManager.beginTransaction();
                    ftFund.replace(R.id.linearLayout,f);
                    ftFund.commit();
                    return true;

                case R.id.navigation_account:
                    Account account = new Account();
                    ftAccount = fragmentManager.beginTransaction();
                    ftAccount.replace(R.id.linearLayout,account);
                    ftAccount.commit();
                    return true;
            }
            return false;
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
        ftHome = fragmentManager.beginTransaction();
        ftHome.add(R.id.linearLayout,h);
        ftHome.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        sptodo = getSharedPreferences("Toopen",MODE_PRIVATE);
        todo = sptodo.getString("todo","");
        if (todo.equals("Trade"))
        {
            ftTrade = fragmentManager.beginTransaction();
            ftTrade.replace(R.id.linearLayout,t);
            ftTrade.commit();

        }

    }

}
