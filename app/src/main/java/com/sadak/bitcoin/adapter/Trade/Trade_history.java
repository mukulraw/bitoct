package com.sadak.bitcoin.adapter.Trade;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sadak.bitcoin.R;
import com.sadak.bitcoin.model.tradehistory.Datum;

import java.util.List;

public class Trade_history extends RecyclerView.Adapter<Trade_history.ViewHolder> {

    List<Datum> list;
    Context context;

    public Trade_history(Context c,List<Datum> l)
    {
        this.context = c;
        this.list = l;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.tradehistoryrecyclerview,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Datum dd = list.get(position);
        for (int i = 0;i<list.size();i++)
        {
            String tradetype = dd.getTradetype();
            String time = dd.getTicker();
            String amount = dd.getVolume();
            String[] splited = time.split("\\s+");
            String[] splited1 = amount.split("\\.");

            holder.time.setText(""+splited[1]);

            if(tradetype.equals("0"))
            {
                holder.price.setText(""+dd.getPrice());
                holder.amount.setText(splited1[0]);
                holder.amount.setTextColor(Color.parseColor("#73a414"));
                holder.price.setTextColor(Color.parseColor("#73a414"));
            }

            else
            {
                holder.price.setText(""+dd.getPrice());
                holder.amount.setText(splited1[0]);
                holder.amount.setTextColor(Color.parseColor("#eb0070"));
                holder.price.setTextColor(Color.parseColor("#eb0070"));
            }

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView time,price,amount;
        public ViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.txttime);
            price = (TextView) itemView.findViewById(R.id.txtprice);
            amount = (TextView) itemView.findViewById(R.id.txtamount);
        }
    }
}