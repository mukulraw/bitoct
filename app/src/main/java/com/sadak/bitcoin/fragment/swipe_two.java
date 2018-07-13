package com.sadak.bitcoin.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.sadak.bitcoin.NetworkConnection.Apidata;
import com.sadak.bitcoin.NetworkConnection.RetrofitInstance;
import com.sadak.bitcoin.R;
import com.sadak.bitcoin.adapter.Trade.Recycler_Sell_Order;
import com.sadak.bitcoin.adapter.Trade.Trade_history;
import com.sadak.bitcoin.model.BtcBalance.Datum;
import com.sadak.bitcoin.model.BtcBalance.Profile;
import com.sadak.bitcoin.model.Sellorder.Example;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class swipe_two extends Fragment {
    private static final String MY_NUM_KEY = "num";
    RecyclerView recyclersellorder,recyclerviewbuyorder,recyclerviewtradehistory;
    String urlGetBalanceBtc = "https://api.bitoct.com/api/bitoct/getCoinBTCBalance?marketId=3&memberid=11";
    String urlSellOrderBymarhet = "api/bitoct/getSellOrdersByMarketID?marketid=3";
    String urlTradeHistory = "api/bitoct/getTradeHistoryByMarketID?marketId=3&memberid=11";
    TextView buyprice,calculatedbalance,avablance;
    EditText txtinputcoin;
    List<Datum> list = new ArrayList<>();
    Spinner order;
    String orders[] ={"Limit Order","Market Order","Stop-Limit"};
    NestedScrollView nested;
    List<com.sadak.bitcoin.model.Sellorder.Datum> listsellorder = new ArrayList<>();
    List<com.sadak.bitcoin.model.tradehistory.Datum> listTrade = new ArrayList<>();
    LinearLayoutManager llsellorder,lltradehistory;
    private int mNum;
    public static swipe_two newInstance(int num) {
        swipe_two f = new swipe_two();
        Bundle args = new Bundle();
        args.putInt(MY_NUM_KEY, num);
        f.setArguments(args);
        return f;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getInt(MY_NUM_KEY) : 0;
        //mColor = getArguments() != null ? getArguments().getInt(MY_COLOR_KEY) : Color.BLACK;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_swipe_two, container, false);
        recyclersellorder = (RecyclerView) v.findViewById(R.id.recyclersellorder);
        recyclerviewbuyorder = (RecyclerView) v.findViewById(R.id.recyclerviewbuyorder);
        recyclerviewtradehistory = (RecyclerView) v.findViewById(R.id.tradehistory);
        buyprice = (TextView) v.findViewById(R.id.txtbuyprice);
        calculatedbalance = (TextView) v.findViewById(R.id.calculatebalance);
        avablance = (TextView) v.findViewById(R.id.txtavabilebalance);
        txtinputcoin = (EditText) v.findViewById(R.id.txtinputcoin);
        order = (Spinner) v.findViewById(R.id.order);
//        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, orders);
//        order.setAdapter(adapter);

//        Apidata apidata = RetrofitInstance.getRetrofitInstance().create(Apidata.class);
//        Call<Profile> call = apidata.getBtcBalance(urlGetBalanceBtc);
//        call.enqueue(new Callback<Profile>() {
//            @Override
//            public void onResponse(Call<Profile> call, Response<Profile> response) {
//                list = response.body().getData();
//                for (int i = 0; i < list.size(); i++) {
//                    Datum dd = list.get(i);
//                    buyprice.setText("" + dd.getBTCPrice());
//                    avablance.setText("" + dd.getBalBTC());
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Profile> call, Throwable t) {
//
//            }
//        });
//
//        Apidata apidata1 = RetrofitInstance.getRetrofitInstance().create(Apidata.class);
//        Call<Example> call1 = apidata1.getSellOrder(urlSellOrderBymarhet);
//        call1.enqueue(new Callback<Example>() {
//            @Override
//            public void onResponse(Call<Example> call, Response<Example> response) {
//                //Log.e("qwwwww",""+response.body().getData());
//                listsellorder = response.body().getData();
//                Recycler_Sell_Order recycler_sell_order = new Recycler_Sell_Order(getContext(), listsellorder);
//                llsellorder = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//                recyclersellorder.setLayoutManager(llsellorder);
//                recyclersellorder.setAdapter(recycler_sell_order);
//
//            }
//
//            @Override
//            public void onFailure(Call<Example> call, Throwable t) {
//
//            }
//        });

        Apidata apidata2 = RetrofitInstance.getRetrofitInstance().create(Apidata.class);
        Call<com.sadak.bitcoin.model.tradehistory.Example> call2 = apidata2.getTradeHistory(urlTradeHistory);
        call2.enqueue(new Callback<com.sadak.bitcoin.model.tradehistory.Example>() {
            @Override
            public void onResponse(Call<com.sadak.bitcoin.model.tradehistory.Example> call, Response<com.sadak.bitcoin.model.tradehistory.Example> response) {
                listTrade = response.body().getData();
                Trade_history t = new Trade_history(getContext(), listTrade);
                lltradehistory = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerviewtradehistory.setLayoutManager(lltradehistory);
                recyclerviewtradehistory.setAdapter(t);
            }

            @Override
            public void onFailure(Call<com.sadak.bitcoin.model.tradehistory.Example> call, Throwable t) {

            }
        });


//        txtinputcoin.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start,
//                                          int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start,
//                                      int before, int count) {
//                if (s.length() != 0) {
//                    String a = buyprice.getText().toString();
//                    String b = txtinputcoin.getText().toString();
//                    Double c = Double.parseDouble(a) * Double.parseDouble(b);
//                    calculatedbalance.setText("" + String.format("%.8f", c));
//                }
//            }
//        });


        return v;
    }
}
