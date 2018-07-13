package com.sadak.bitcoin.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.sadak.bitcoin.MainActivity;
import com.sadak.bitcoin.NetworkConnection.Apidata;
import com.sadak.bitcoin.NetworkConnection.RetrofitInstance;
import com.sadak.bitcoin.R;
import com.sadak.bitcoin.adapter.Trade.Recycler_Sell_Order;
import com.sadak.bitcoin.adapter.Trade.Trade_history;
import com.sadak.bitcoin.fragment.Trade_all.Swipe_one_sell;
import com.sadak.bitcoin.model.BtcBalance.Datum;
import com.sadak.bitcoin.model.BtcBalance.Profile;
import com.sadak.bitcoin.model.Sellorder.Example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class swipe_one extends Fragment {
    private static final String MY_NUM_KEY = "num";
    RecyclerView recyclersellorder,recyclerviewbuyorder,recyclerviewtradehistory;
    String urlGetBalanceBtc ;
    String urlSellOrderBymarhet ;
    String urlBuyOrderBymarhet ;
    String urlTradeHistory ;
    TextView calculatedbalance,avablance,txtmarketorder,calculatebalance1;
    public static EditText txtinputcoin,buyprice;
    List<Datum> list = new ArrayList<>();
    Spinner order;
    String orders[] ={"Limit Order","Market Order","Stop-Limit"};
    NestedScrollView nested;
    List<com.sadak.bitcoin.model.Sellorder.Datum> listsellorder = new ArrayList<>();
    List<com.sadak.bitcoin.model.tradehistory.Datum> listTrade = new ArrayList<>();
    LinearLayoutManager llsellorder,lltradehistory;
    LinearLayout lledt;
   GraphView graph ;
   Button buy;
    private int mNum;
    public static swipe_one newInstance(int num) {
        swipe_one ff = new swipe_one();
        Bundle args = new Bundle();
        args.putInt(MY_NUM_KEY, num);
        ff.setArguments(args);
        return ff;
    }

    SharedPreferences sharedPreferences,sharedPreferencesrecyclerorder;
    String member_id;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getInt(MY_NUM_KEY) : 0;


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_swipe_one, container, false);

        sharedPreferences = getContext().getSharedPreferences("Bitoct_user",Context.MODE_PRIVATE);
        member_id = sharedPreferences.getString("user_id","");
        sharedPreferencesrecyclerorder = getContext().getSharedPreferences("Tosettradetext", Context.MODE_PRIVATE);
        Log.e(".....",""+member_id);
       // Toast.makeText(getActivity(), "abc"+member_id, Toast.LENGTH_LONG).show();
        //mColor = getArguments() != null ? getArguments().getInt(MY_COLOR_KEY) : Color.BLACK;
        urlGetBalanceBtc = "https://api.bitoct.com/api/bitoct/getCoinBTCBalance?marketId=3&memberid="+member_id;
        urlSellOrderBymarhet = "api/bitoct/getSellOrdersByMarketID?marketid=3";
        urlBuyOrderBymarhet = "api/bitoct/getBuyOrdersByMarketID?marketid=3";
        urlTradeHistory = "api/bitoct/getTradeHistoryByMarketID?marketId=3&memberid="+member_id;
        graph = (GraphView)v.findViewById(R.id.graph);

        buy=(Button)v.findViewById(R.id.btnbuy);

        recyclersellorder = (RecyclerView) v.findViewById(R.id.recyclersellorder);
        recyclerviewbuyorder = (RecyclerView) v.findViewById(R.id.recyclerviewbuyorder);
        recyclerviewtradehistory = (RecyclerView) v.findViewById(R.id.tradehistory);
        buyprice = (EditText) v.findViewById(R.id.txtbuyprice);
        calculatedbalance = (TextView) v.findViewById(R.id.calculatebalance);
        avablance = (TextView) v.findViewById(R.id.txtavabilebalance);
        txtmarketorder = (TextView) v.findViewById(R.id.txtmarketorder);
        calculatebalance1 = (TextView) v.findViewById(R.id.calculatebalance1);
        txtinputcoin = (EditText) v.findViewById(R.id.txtinputcoin);
        order = (Spinner) v.findViewById(R.id.order);
        lledt = (LinearLayout) v.findViewById(R.id.lledt);

        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, orders);
        order.setAdapter(adapter);

        order.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0)
                {
                    txtmarketorder.setVisibility(View.GONE);
                    lledt.setVisibility(View.VISIBLE);
                    calculatebalance1.setVisibility(View.GONE);
                    calculatedbalance.setVisibility(View.VISIBLE);


                }
                else if(i==1)
                {
                    txtmarketorder.setVisibility(View.VISIBLE);
                    lledt.setVisibility(View.GONE);
                    calculatebalance1.setVisibility(View.VISIBLE);
                    calculatedbalance.setVisibility(View.GONE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getbalanceBtc();
        getselloreder();
        getbuyorder();






        txtinputcoin.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() != 0) {
                    String a = buyprice.getText().toString();
                    String b = txtinputcoin.getText().toString();
                    Double c = Double.parseDouble(a) * Double.parseDouble(b);
                    calculatedbalance.setText("" + String.format("%.8f", c));
                }
            }
        });

//        buyprice.addTextChangedListener(new TextWatcher() {
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


        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              double fee = (Double.parseDouble( buyprice.getText().toString())*Double.parseDouble(txtinputcoin.getText().toString()))*0.0020;
              double nettotal =fee+Double.parseDouble(calculatedbalance.getText().toString());
                BigDecimal number = new BigDecimal(fee);
                Float x = number.floatValue();
                Log.e(">>>",""+x);

                double a = 0.00000020;
                double y = x;

                Log.e("ddd",""+Double.compare(y,a));

              if(buyprice.getText().toString().isEmpty())
              {
                  Toast.makeText(getContext(), "Please enter amount", Toast.LENGTH_SHORT).show();
              }
              else if(buyprice.getText().toString().equals("0.000"))
              {
                  Toast.makeText(getContext(), "Price mist be greater than 0", Toast.LENGTH_SHORT).show();
              }
              else if(Double.compare(y,a)<=0)
              {
                  Toast.makeText(getContext(), "Fee must add upto 0.000000020", Toast.LENGTH_SHORT).show();
              }
              else if(nettotal>Double.parseDouble(avablance.getText().toString()))
              {
                  Toast.makeText(getContext(), "Insufficient balance", Toast.LENGTH_SHORT).show();
              }
              else
              {
                  String m = MainActivity.market_name;
                  String c = m.substring(0, m.indexOf('/'));

                  Apidata apidata4 = RetrofitInstance.getRetrofitInstance().create(Apidata.class);
                  Call<com.sadak.bitcoin.model.Buy.Profile> call3 = apidata4.getBuy(c,buyprice.getText().toString(),"3",calculatedbalance.getText().toString(),txtinputcoin.getText().toString());
                  call3.enqueue(new Callback<com.sadak.bitcoin.model.Buy.Profile>() {
                      @Override
                      public void onResponse(Call<com.sadak.bitcoin.model.Buy.Profile> call, Response<com.sadak.bitcoin.model.Buy.Profile> response) {
                          Log.e("sucs",""+response.body().getStatus());
                          if(response.body().getStatus().equals("Succeed")) {


                                  if(response.body().getData().get(0).getIsok().equals("0"))
                                  {
                                      final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                      //builder.setMessage("#"+response.body().getData().get(0).getPrice()+response.body().getData().get(0).getToamount()+"-"+response.body().getData().get(0).getFromcurrency()+response.body().getData().get(0).getFromamount()+"Price:"+response.body().getData().get(0).getPrice()+"fee:"+response.body().getData().get(0).getFee()+response.body().getData().get(0).getFromcurrency()+response.body().getData().get(0).getToamount());

                                      builder.setMessage("#"+response.body().getData().get(0).getKey()+" "+"Bought"+" "+response.body().getData().get(0).getToamount()+" "+response.body().getData().get(0).getFromcurrency()+" "+"With"+" "+response.body().getData().get(0).getToamount()+" "+"Price:"+" "+response.body().getData().get(0).getPrice()+" "+"Fee:"+" "+response.body().getData().get(0).getFee()+" "+response.body().getData().get(0).getFromcurrency()+" "+"Net total:"+" "+response.body().getData().get(0).getToamount());
                                      builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                          @Override
                                          public void onClick(DialogInterface dialogInterface, int i) {
                                              Apidata apidata4 = RetrofitInstance.getRetrofitInstance().create(Apidata.class);
                                              Call<com.sadak.bitcoin.model.Buy.Profile> call3 = apidata4.buy("BSS", buyprice.getText().toString(), "3", calculatedbalance.getText().toString(), txtinputcoin.getText().toString(), "true", member_id);
                                              call3.enqueue(new Callback<com.sadak.bitcoin.model.Buy.Profile>() {
                                                  @Override
                                                  public void onResponse(Call<com.sadak.bitcoin.model.Buy.Profile> call, Response<com.sadak.bitcoin.model.Buy.Profile> response) {

                                                      Log.e("MMM",""+response.body().getStatus());
                                                      if(response.body().getStatus().equals("Succeed"))
                                                      {
                                                          builder.setCancelable(true);
                                                          Toast.makeText(getContext(), "Succeed", Toast.LENGTH_SHORT).show();
                                                          getbalanceBtc();
                                                          getselloreder();
                                                          getbuyorder();
                                                          txtinputcoin.setText("");

                                                      }
                                                      else
                                                      {
                                                          Toast.makeText(getContext(), "Not Succeed", Toast.LENGTH_SHORT).show();
                                                      }

                                                  }

                                                  @Override
                                                  public void onFailure(Call<com.sadak.bitcoin.model.Buy.Profile> call, Throwable t) {

                                                  }
                                              });





                                          }



                                      });

                                      builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                          @Override
                                          public void onClick(DialogInterface dialogInterface, int i) {

                                          }
                                      });
                                      builder.show();
                                  }
                                  else
                                  {
                                      AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                      LayoutInflater inflater = getActivity().getLayoutInflater();
                                      View dialogView = inflater.inflate(R.layout.dialog, null);
                                      builder.setMessage("");
                                      TextView one = (TextView)dialogView.findViewById(R.id.price);
                                      TextView two = (TextView)dialogView.findViewById(R.id.tocurrency);
                                      TextView three =(TextView)dialogView.findViewById(R.id.fromcurrency);
                                      TextView four =(TextView)dialogView.findViewById(R.id.fee);;

                                      one.setText(response.body().getData().get(0).getPrice());
                                      two.setText(response.body().getData().get(0).getToamount());
                                      three.setText(response.body().getData().get(0).getFromamount());
                                      four.setText(response.body().getData().get(0).getFee());

                                      builder.show();
                                  }
                          }

                      }

                      @Override
                      public void onFailure(Call<com.sadak.bitcoin.model.Buy.Profile> call, Throwable t) {

                      }
                  });

              }
            }
        });

        return v;
    }

    public void setGraph()
    {
        Apidata apidata3 = RetrofitInstance.getRetrofitInstance().create(Apidata.class);
        Call<com.sadak.bitcoin.model.Graph.Profile> call3 = apidata3.getGraph("https://api.bitoct.com/api/bitoct/getGraphMarketDataByMarketIDLive?marketid=3&history=1800");
        call3.enqueue(new Callback<com.sadak.bitcoin.model.Graph.Profile>() {
            @Override
            public void onResponse(Call<com.sadak.bitcoin.model.Graph.Profile> call, Response<com.sadak.bitcoin.model.Graph.Profile> response) {
                List<com.sadak.bitcoin.model.Graph.Datum> arrayList=new ArrayList<>();
                arrayList=response.body().getData();
                for(int i =0;i<arrayList.size();i++)
                {
                    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                            new DataPoint((Double.parseDouble(arrayList.get(i).getVolume())), Double.parseDouble(arrayList.get(i).getAverage()))});
                    graph.addSeries(series);
                }
            }


            @Override
            public void onFailure(Call<com.sadak.bitcoin.model.Graph.Profile> call, Throwable t) {

            }
        });


    }

    public void getbalanceBtc()
    {
        Apidata apidata = RetrofitInstance.getRetrofitInstance().create(Apidata.class);
        Call<Profile> call = apidata.getBtcBalance(urlGetBalanceBtc);
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                list = response.body().getData();
                for (int i = 0; i < list.size(); i++) {
                    Datum dd = list.get(i);
                    buyprice.setText("" + dd.getBTCPrice());
                    avablance.setText("" + dd.getBalBTC());

                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {

            }
        });

    }

    public void getselloreder()
    {
        Apidata apidata1 = RetrofitInstance.getRetrofitInstance().create(Apidata.class);
        Call<Example> call1 = apidata1.getSellOrder(urlSellOrderBymarhet);
        call1.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                //Log.e("qwwwww",""+response.body().getData());

                listsellorder = response.body().getData();

                SharedPreferences.Editor editor = sharedPreferencesrecyclerorder.edit();
                editor.putString("tosettext","buy");
                editor.commit();
                Recycler_Sell_Order recycler_sell_order = new Recycler_Sell_Order(getContext(), listsellorder);
                llsellorder = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclersellorder.setLayoutManager(llsellorder);
                recyclersellorder.setAdapter(recycler_sell_order);

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }

    public void getbuyorder()
    {
        Apidata apidata3 = RetrofitInstance.getRetrofitInstance().create(Apidata.class);
        Call<Example> call3 = apidata3.getBuyOrder(urlBuyOrderBymarhet);
        call3.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.e("1234",""+response.body().getData());
                listsellorder = response.body().getData();
                Recycler_Sell_Order recycler_sell_order = new Recycler_Sell_Order(getContext(), listsellorder);
                llsellorder = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerviewbuyorder.setLayoutManager(llsellorder);
                recyclerviewbuyorder.setAdapter(recycler_sell_order);

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser)
        {
            getbalanceBtc();
            getselloreder();
            getbuyorder();


        }


    }

}
