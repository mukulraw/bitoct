package com.sadak.bitcoin.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sadak.bitcoin.Activity.MarketGraph;
import com.sadak.bitcoin.R;
import com.sadak.bitcoin.model.firstRecyclerView.Datum;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by USER on 24-05-2018.
 */

public class First_recyclerview_home extends RecyclerView.Adapter<First_recyclerview_home.ViewHolder> {

    Context context;
    List<Datum> list = new ArrayList<>();
    String a;
    ArrayList<String> firstarray,secondarray,thirdarray;
    SharedPreferences sharedPreferencesMarketid;

    public First_recyclerview_home(Context cc,List<Datum> l,String aa)
    {
        this.context = cc;
        this.list = l;
        this.a = aa;
    }

    public First_recyclerview_home(Context cc,ArrayList firstarray1,ArrayList secondarray1,ArrayList thirdarray1,String aa)
    {
        this.context = cc;
        this.firstarray = firstarray1;
        this.secondarray = secondarray1;
        this.thirdarray = thirdarray1;
        this.a = aa;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.first_recyclerview_home,parent,false);

        return new ViewHolder(v);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (a.equals("1"))
        {
            Datum d = list.get(position);
            String marketAssetcode = d.getMarketAssetCode();
            String[] splited1 = marketAssetcode.split("\\/");
            holder.first.setText("" + splited1[0]);
            holder.txtfirsthomerecyclermarket.setText("/"+splited1[1]);
            holder.second.setText("" + d.getLastPrice());
            holder.third.setText("" + d.getVolume());
            holder.third.setTextColor(Color.BLACK);
        }
        if (a.equals("2"))

        {


            ArrayList<String> firstarray = new ArrayList();
            ArrayList<String> secondarray = new ArrayList();
            ArrayList<String> thirdarray = new ArrayList();
            Datum d = list.get(position);
            firstarray.add( d.getMarketAssetCode());
            secondarray.add( d.getLastPrice());
            thirdarray.add(d.getChange());
            for (int i = 0;i<thirdarray.size();i++)
            {
                String a = thirdarray.get(i);
                String c=""+a.charAt(0);

                String marketAssetcode = firstarray.get(i);
                String[] splited1 = marketAssetcode.split("\\/");

                String secondword=secondarray.get(i);
                if (c.equals("-")) {

                }
                else
                {
                    holder.first.setText("" + splited1[0]);
                    holder.txtfirsthomerecyclermarket.setText("/"+splited1[1]);
                    holder.second.setText(""+secondword);
                    holder.third.setText(""+a);
                    holder.third.setTextColor(Color.BLACK);
                    holder.third.setBackgroundColor(Color.parseColor("#73a414"));

                }
            }


        }
        if (a.equals("3"))
        {
            ArrayList<String> firstarray = new ArrayList();
            ArrayList<String> secondarray = new ArrayList();
            ArrayList<String> thirdarray = new ArrayList();
            Datum d = list.get(position);
            firstarray.add( d.getMarketAssetCode());
            secondarray.add( d.getLastPrice());
            thirdarray.add(d.getChange());
            for (int i = 0;i<thirdarray.size();i++)
            {
                String a = thirdarray.get(i);
                String c=""+a.charAt(0);

                String marketAssetcode = firstarray.get(i);
                String[] splited1 = marketAssetcode.split("\\/");
                String secondword=secondarray.get(i);
                if (c.equals("-")) {
                    holder.first.setText("" + splited1[0]);
                    holder.txtfirsthomerecyclermarket.setText("/"+splited1[1]);
                    holder.second.setText(""+secondword);
                    holder.third.setText(""+a);
                    holder.third.setTextColor(Color.BLACK);
                    holder.third.setBackgroundColor(Color.parseColor("#eb0070"));
                }
                else
                    {

                    }
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView first,second,third,txtfirsthomerecyclermarket;
        public ViewHolder(View itemView) {
            super(itemView);
            first = (TextView) itemView.findViewById(R.id.txtfirsthomerecycler);
            second = (TextView) itemView.findViewById(R.id.txtsecondhomerecycler);
            third = (TextView) itemView.findViewById(R.id.txtthirdhomerecycler);
            txtfirsthomerecyclermarket = (TextView) itemView.findViewById(R.id.txtfirsthomerecyclermarket);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sharedPreferencesMarketid = context.getSharedPreferences("Marketid",MODE_PRIVATE);
                    list.get(getAdapterPosition()).getMarketId();
                    //Toast.makeText(context,  "market"+list.get(getAdapterPosition()).getMarketId(), Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferencesMarketid.edit();
                    editor.putString("market_id",""+list.get(getAdapterPosition()).getMarketId());
                    editor.commit();
                    Intent i = new Intent(context, MarketGraph.class);
                    i.putExtra("MarketData",list.get(getAdapterPosition()));
                    i.putExtra("Source","first");
                    i.putExtra("marketId" , list.get(getAdapterPosition()).getMarketId());
                    context.startActivity(i);
                }
            });

        }
    }
}