package com.sadak.bitcoin.adapter.Market_recyclerview_adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sadak.bitcoin.Activity.MarketGraph;
import com.sadak.bitcoin.MainActivity;
import com.sadak.bitcoin.R;
import com.sadak.bitcoin.model.market.Datum;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class First_market_tab extends RecyclerView.Adapter<First_market_tab.ViewHolder> {

    Context context;
    List<Datum> list = new ArrayList<>();

    SharedPreferences sptodo,sharedPreferencesMarketid;
    String todo;

    public First_market_tab(Context c,List<Datum> l)
    {
        this.context = c;
        this.list = l;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.recyclerview_market,parent,false);
        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Datum d = list.get(position);
        ArrayList<String> marketAssetCode = new ArrayList<>();
        ArrayList<String> volume = new ArrayList<>();
        ArrayList<String> lastprice = new ArrayList<>();
        ArrayList<String> doller = new ArrayList<>();
        ArrayList<String> changemarket = new ArrayList<>();
        ArrayList<String> basecurrentid = new ArrayList<>();
        marketAssetCode.add(d.getMarketAssetCode());
        volume.add(d.getVolume());
        lastprice.add(d.getLastPrice());
        doller.add(d.getDollar());
        changemarket.add(d.getChange());
        basecurrentid.add(d.getBaseCurrencyID());



        for (int i = 0 ;i<marketAssetCode.size();i++)
        {
            String a = marketAssetCode.get(i);

            String marketAssetcode = d.getMarketAssetCode();
            String[] splited1 = marketAssetcode.split("\\/");
//            String b = a.substring(a.length() - 4);
//            //String c = a.substring(a.indexOf("/") + 1);
//            String c = a.substring(0, a.indexOf('/'));

            String volumes = volume.get(i);
            String lastprices = lastprice.get(i);
            String dollers = doller.get(i);
            String changemarkets = changemarket.get(i);
            String basecurrentids = basecurrentid.get(i);


           // String txtvolume = volume.
//            if (basecurrentids.equals("2"))
//            {
                holder.txtmarketaccet.setText(""+splited1[0]);
                holder.txtmarketaccetcode.setText("/"+splited1[1]);
                holder.txtvolume.setText(""+volumes);
                holder.txtlastprice.setText(""+lastprices);
                holder.txtdoller.setText(""+dollers);
                holder.txtchangemarket.setText(""+changemarkets+"%");
//            }

        }
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtmarketaccet,txtmarketaccetcode,txtvolume,txtlastprice,txtdoller,txtchangemarket;
        public ViewHolder(View itemView) {
            super(itemView);
            txtmarketaccet = (TextView) itemView.findViewById(R.id.txtmarketaccet);
            txtmarketaccetcode = (TextView) itemView.findViewById(R.id.txtmarketaccetcode);
            txtvolume = (TextView) itemView.findViewById(R.id.txtvolume);
            txtlastprice = (TextView) itemView.findViewById(R.id.txtlastprice);
            txtdoller = (TextView) itemView.findViewById(R.id.txtdoller);
            txtchangemarket = (TextView) itemView.findViewById(R.id.txtchangemarket);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sptodo = context.getSharedPreferences("Toopen",MODE_PRIVATE);
                    todo = sptodo.getString("todo","");

                    sharedPreferencesMarketid = context.getSharedPreferences("Marketid",MODE_PRIVATE);
                    if (todo.equals("Trade"))
                    {
                        list.get(getAdapterPosition()).getMarketId();
                        //Toast.makeText(context,  "trade"+list.get(getAdapterPosition()).getMarketId(), Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = sharedPreferencesMarketid.edit();
                        editor.putString("market_id",""+list.get(getAdapterPosition()).getMarketId());
                        editor.commit();
                        MainActivity.market_name=list.get(getAdapterPosition()).getMarketAssetCode();
                        Intent i = new Intent(context, MainActivity.class);
                        context.startActivity(i);
                        ((Activity)context).finish();


                    }
                    if (todo.equals("Market"))
                    {
                        list.get(getAdapterPosition()).getMarketId();
                        //Toast.makeText(context,  "market"+list.get(getAdapterPosition()).getMarketId(), Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = sharedPreferencesMarketid.edit();
                        editor.putString("market_id",""+list.get(getAdapterPosition()).getMarketId());
                        editor.commit();
                        Intent i = new Intent(context, MarketGraph.class);
                        i.putExtra("MarketData",list.get(getAdapterPosition()));
                        i.putExtra("Source","Market");
                        i.putExtra("marketId" , list.get(getAdapterPosition()).getMarketId());
                        context.startActivity(i);
        //                ((Activity)context).finish();
                    }
                    list.get(getAdapterPosition()).getMarketId();
                   // Toast.makeText(context,  list.get(getAdapterPosition()).getMarketId(), Toast.LENGTH_SHORT).show();


                }
            });
        }
    }
}
