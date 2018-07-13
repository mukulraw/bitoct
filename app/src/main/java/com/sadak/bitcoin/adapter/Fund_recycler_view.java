package com.sadak.bitcoin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sadak.bitcoin.Activity.Address;
import com.sadak.bitcoin.Activity.Withdrawal_second;
import com.sadak.bitcoin.R;
import com.sadak.bitcoin.model.fund.Datum;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Fund_recycler_view extends RecyclerView.Adapter<Fund_recycler_view.ViewHolder> {

    Context context;
    List<Datum> list;
    String click;


    public Fund_recycler_view(Context c,List<Datum> l,String cc)
    {
        this.context = c;
        this.list = l;
        this.click = cc;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.fund_recyclerview,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Datum dd = list.get(position);

        for (int i = 0;i<list.size();i++)
        {
            holder.name.setText(""+dd.getCoin()+" ("+dd.getCoinName()+")");
            holder.balance.setText(""+dd.getBTCBalance());
            Picasso.get().load(dd.getPicture()).into(holder.iv);
        }

        if (click.equals("2"))
        {
            holder.rv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, Address.class);
                    i.putExtra("coin",dd.getCoin().toString());
                    i.putExtra("image",dd.getPicture());
                    context.startActivity(i);
                }
            });

        }

        if (click.equals("3"))
        {
            holder.rv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, Withdrawal_second.class);
                    i.putExtra("coin",dd.getCoin().toString());
                    i.putExtra("image",dd.getPicture());
                    context.startActivity(i);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,balance;
        ImageView iv;
        RelativeLayout rv;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.txtnamefund);
            balance = (TextView) itemView.findViewById(R.id.txtbalancefund);
            iv = (ImageView) itemView.findViewById(R.id.imgfund);
            rv = (RelativeLayout) itemView.findViewById(R.id.relativeLayoutfund);
        }
    }
}