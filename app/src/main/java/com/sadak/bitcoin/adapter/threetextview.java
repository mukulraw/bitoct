package com.sadak.bitcoin.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sadak.bitcoin.Activity.MarketGraph;
import com.sadak.bitcoin.R;
import com.sadak.bitcoin.model.threetextview.Datum;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by USER on 22-05-2018.
 */

public class threetextview extends RecyclerView.Adapter<threetextview.ViewHolder> {
    Context c;
    List<Datum> data = new ArrayList<>();
    SharedPreferences sharedPreferencesMarketid;




    public threetextview(Context c,List<Datum> data) {
        this.c = c;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.threetextview,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Datum d = data.get(position);
        holder.whittxt.setText(""+d.getCurrency());
        holder.pinktxt.setText(""+d.getLastPrice());
        holder.greentxt.setText(""+d.getChange()+"%");
        Log.e("data",""+d.getCurrency());



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView whittxt,pinktxt,greentxt;
        public ViewHolder(View itemView) {
            super(itemView);
            whittxt = (TextView) itemView.findViewById(R.id.txtwhite);
            pinktxt = (TextView) itemView.findViewById(R.id.txtpink);
            greentxt = (TextView) itemView.findViewById(R.id.txtgreen);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sharedPreferencesMarketid = c.getSharedPreferences("Marketid",MODE_PRIVATE);
                    data.get(getAdapterPosition()).getMarketID();
                    //Toast.makeText(context,  "market"+list.get(getAdapterPosition()).getMarketId(), Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferencesMarketid.edit();
                    editor.putString("market_id",""+data.get(getAdapterPosition()).getMarketID());
                    editor.apply();
                    Intent i = new Intent(c, MarketGraph.class);
                    i.putExtra("MarketData",data.get(getAdapterPosition()));
                    i.putExtra("Source","threetext");
                    i.putExtra("marketId" , data.get(getAdapterPosition()).getMarketID());
                    c.startActivity(i);
                }
            });
        }
    }
}
