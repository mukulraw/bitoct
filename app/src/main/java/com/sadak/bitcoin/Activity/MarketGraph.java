package com.sadak.bitcoin.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sadak.bitcoin.NetworkConnection.Apidata;
import com.sadak.bitcoin.NetworkConnection.RetrofitInstance;
import com.sadak.bitcoin.R;
import com.sadak.bitcoin.adapter.Trade.Recycler_Sell_Order;
import com.sadak.bitcoin.fragment.swipe_two;
import com.sadak.bitcoin.lineChartPOJO.lineChartBean;
import com.sadak.bitcoin.model.Sellorder.Example;
import com.sadak.bitcoin.model.market.Datum;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static java.security.AccessController.getContext;

public class MarketGraph extends AppCompatActivity {


    TextView asset, price, p_dollar, change24, change, lowvol, vol, highvol;
    Button buy, sell;
    String source;

    ValueLineChart valueLineChart;
    BarChart barChart;

    Spinner spinner;

    List<com.sadak.bitcoin.lineChartPOJO.Datum> list;

    List<String> ttime;
    List<String> millis;

    ImageButton back;

    String marketId;

    TabLayout tabs;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_graph);
        getSupportActionBar().hide();

        marketId = getIntent().getStringExtra("marketId");

        ttime = new ArrayList<>();
        millis = new ArrayList<>();
        list = new ArrayList<>();

        ttime.add("15m");
        ttime.add("30m");
        ttime.add("1H");
        ttime.add("2H");
        ttime.add("6H");
        ttime.add("1D");
        ttime.add("2D");
        ttime.add("1W");
        ttime.add("2W");
        ttime.add("1M");
        ttime.add("2M");
        ttime.add("6M");

        millis.add("900");
        millis.add("1800");
        millis.add("3600");
        millis.add("7200");
        millis.add("21600");
        millis.add("86400");
        millis.add("172800");
        millis.add("604800");
        millis.add("1209600");
        millis.add("2419200");
        millis.add("4838400");
        millis.add("14515200");

        asset = (TextView) findViewById(R.id.asset);
        price = (TextView) findViewById(R.id.price);
        p_dollar = (TextView) findViewById(R.id.p_dollar);
        change24 = (TextView) findViewById(R.id.change24);
        change = (TextView) findViewById(R.id.change);
        lowvol = (TextView) findViewById(R.id.lowvol);
        vol = (TextView) findViewById(R.id.vol);
        highvol = (TextView) findViewById(R.id.highvol);
        spinner = findViewById(R.id.spinner);
        back = findViewById(R.id.back);
        tabs = findViewById(R.id.tabs);
        pager = findViewById(R.id.viewpager);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MarketGraph.this, R.layout.spinner_item_layout, ttime);

        spinner.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        valueLineChart = findViewById(R.id.chart);
        barChart = findViewById(R.id.bar);



        tabs.addTab(tabs.newTab().setText("Order Book"));
        tabs.addTab(tabs.newTab().setText("Market Trade"));


        PagerAdapter adapter1 = new PagerAdapter(getSupportFragmentManager());


        pager.setAdapter(adapter1);

        tabs.setupWithViewPager(pager);

        tabs.getTabAt(0).setText("Order Book");
        tabs.getTabAt(1).setText("Market Trade");


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String history = millis.get(i);
                final ValueLineSeries series = new ValueLineSeries();
                series.setColor(0xFF196981);

                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.bitoct.com/")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                final Apidata cr = retrofit.create(Apidata.class);

                Log.d("asdasd" , marketId);

                Call<lineChartBean> call = cr.getLineChartData(marketId, history);

                call.enqueue(new Callback<lineChartBean>() {
                    @Override
                    public void onResponse(Call<lineChartBean> call, Response<lineChartBean> response) {

                        try {

                            valueLineChart.clearChart();


                            for (int i = 0; i < response.body().getData().size(); i++) {

                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");


                                try {

                                    Date mDate = sdf.parse(response.body().getData().get(i).getTicker());

                                    String dd1[] = response.body().getData().get(i).getTicker().split(" ");
                                    String dd2[] = dd1[1].split(":");

                                    String dd = dd2[0] + ":" + dd2[1];

                                    series.addPoint(new ValueLinePoint(dd, Float.parseFloat(response.body().getData().get(i).getOpen())));

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }


                            }


                            valueLineChart.addSeries(series);
                            valueLineChart.setShowIndicator(false);
                            valueLineChart.setUseCubic(true);
                            valueLineChart.setLineStroke(1);
                            valueLineChart.setBackgroundColor(Color.BLACK);
                            valueLineChart.startAnimation();

                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }



                /*LineDataSet dataSet = new LineDataSet(list , "");

                dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);



                dataSet.setCircleRadius(0);
                dataSet.setDrawCircleHole(false);
                dataSet.setDrawCircles(false);

                LineData lineData = new LineData(dataSet);
                //lineData.setValueTextSize(0);

                lineData.setDrawValues(true);

                lineChart.setData(lineData);
                //lineChart.setBackgroundColor(Color.BLACK);

                lineChart.invalidate();


*/


                    }

                    @Override
                    public void onFailure(Call<lineChartBean> call, Throwable t) {

                    }
                });


                Call<lineChartBean> call1 = cr.getBarChartData(marketId, history);


                call1.enqueue(new Callback<lineChartBean>() {
                    @Override
                    public void onResponse(Call<lineChartBean> call, Response<lineChartBean> response) {


                        try {

                            barChart.clearChart();

                            for (int i = 0; i < response.body().getData().size(); i++) {
                                String tt = response.body().getData().get(i).getTradetype();
                                //Log.d("asdasd", tt);


                                if (tt.equals("1")) {
                                    barChart.addBar(new BarModel(Float.parseFloat(response.body().getData().get(i).getVolume()), 0xFF28B463));
                                } else {
                                    barChart.addBar(new BarModel(Float.parseFloat(response.body().getData().get(i).getVolume()), 0xFFE74C3C));
                                }


                            }


                            barChart.startAnimation();

                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }



                    }

                    @Override
                    public void onFailure(Call<lineChartBean> call, Throwable t) {

                    }
                });


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        source = getIntent().getStringExtra("Source");

        if (source.equals("Market")) {
            Datum datum = (Datum) getIntent().getSerializableExtra("MarketData");
            asset.setText(datum.getMarketAssetCode());
            price.setText(datum.getLastPrice());
            p_dollar.setText(datum.getDollar());
            change24.setText(datum.getChange24());
            change.setText(datum.getChange());
            lowvol.setText(datum.getLowPrice());
            vol.setText(datum.getVolume());
            highvol.setText(datum.getHighPrice());
            buy = (Button) findViewById(R.id.buy);
            sell = (Button) findViewById(R.id.sell);

        }

        if (source.equals("threetext")) {
            com.sadak.bitcoin.model.threetextview.Datum d = (com.sadak.bitcoin.model.threetextview.Datum) getIntent().getSerializableExtra("MarketData");
            asset.setText(d.getCurrency());
            price.setText(d.getLastPrice());
            p_dollar.setText(d.getDollar());
            change24.setText(d.getChange24());
            change.setText(d.getChange());
            lowvol.setText(d.getLowPrice());
            vol.setText(d.getVolume());
            highvol.setText(d.getHighPrice());
            buy = (Button) findViewById(R.id.buy);
            sell = (Button) findViewById(R.id.sell);

        }

        if (source.equals("first")) {
            com.sadak.bitcoin.model.firstRecyclerView.Datum d = (com.sadak.bitcoin.model.firstRecyclerView.Datum) getIntent().getSerializableExtra("MarketData");
            asset.setText(d.getMarketAssetCode());
            price.setText(d.getLastPrice());
            p_dollar.setText(d.getDollar());
            change24.setText(d.getChange24());
            change.setText(d.getChange());
            lowvol.setText(d.getLowPrice());
            vol.setText(d.getVolume());
            highvol.setText(d.getHighPrice());
            buy = (Button) findViewById(R.id.buy);
            sell = (Button) findViewById(R.id.sell);
        }


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    class PagerAdapter extends FragmentStatePagerAdapter
    {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0)
            {
                return new OrderBook();
            }
            else
            {
                return swipe_two.newInstance(1);
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }


    public static class OrderBook extends Fragment
    {


        RecyclerView recyclersellorder,recyclerviewbuyorder;
        LinearLayoutManager llsellorder;
        List<com.sadak.bitcoin.model.Sellorder.Datum> listsellorder = new ArrayList<>();
        String urlSellOrderBymarhet ;
        String urlBuyOrderBymarhet ;
        SharedPreferences sharedPreferences,sharedPreferencesrecyclerorder;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.order_book_layout , container , false);


            sharedPreferences = getContext().getSharedPreferences("Bitoct_user", Context.MODE_PRIVATE);
            sharedPreferencesrecyclerorder = getContext().getSharedPreferences("Tosettradetext", Context.MODE_PRIVATE);

            recyclersellorder = (RecyclerView) view.findViewById(R.id.recyclersellorder);
            recyclerviewbuyorder = (RecyclerView) view.findViewById(R.id.recyclerviewbuyorder);
            urlSellOrderBymarhet = "api/bitoct/getSellOrdersByMarketID?marketid=3";
            urlBuyOrderBymarhet = "api/bitoct/getBuyOrdersByMarketID?marketid=3";


            getselloreder();
            getbuyorder();


            return view;
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


    }







}

