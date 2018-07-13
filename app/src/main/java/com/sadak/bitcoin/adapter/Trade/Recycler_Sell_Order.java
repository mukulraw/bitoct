package com.sadak.bitcoin.adapter.Trade;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sadak.bitcoin.R;
import com.sadak.bitcoin.fragment.Trade_all.Swipe_one_sell;
import com.sadak.bitcoin.fragment.swipe_one;
import com.sadak.bitcoin.model.Sellorder.Datum;
import com.sadak.bitcoin.model.Sellorder.Example;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Recycler_Sell_Order extends RecyclerView.Adapter<Recycler_Sell_Order.ViewHolder> {

    Context context;
    List<Datum> list;
    SharedPreferences sharedPreferencesrecyclerorder;
    String tosettext;

    public Recycler_Sell_Order(Context c)
    {
        this.context = c;

    }

    public Recycler_Sell_Order(Context c,List<Datum> l)
    {
        this.context = c;
        this.list = l;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.sell_order_recyclerview,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Log.e("yyy",""+list.size());
        final Datum dd = list.get(position);
        for (int i = 0;i<list.size();i++)
        {
            Double priced = Double.parseDouble(dd.getPrice());
            String aa = dd.getAmount();
//            String[] parts = aa.split(".");
//            String part1 = parts[0];
//            String part2 = parts[1];
            String c = aa.substring(0, aa.indexOf('.'));
            holder.price.setText(""+String.format("%.8f",priced));
            holder.amount.setText(""+c);
        }

        holder.llsell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferencesrecyclerorder = context.getSharedPreferences("Tosettradetext",MODE_PRIVATE);
                tosettext = sharedPreferencesrecyclerorder.getString("tosettext","");
                Log.e("aaaaaaaa",tosettext);
                if (tosettext.equals("buy")) {
                    swipe_one.buyprice.setText("" + holder.price.getText());
                    Swipe_one_sell.buyprice.setText(""+ holder.price.getText());
                }
                else
                {
                    Swipe_one_sell.buyprice.setText(""+ holder.price.getText());
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
         TextView price,amount;
         LinearLayout llsell;

        public ViewHolder(View itemView) {
            super(itemView);
            price = (TextView) itemView.findViewById(R.id.sellorderprice);
            amount = (TextView) itemView.findViewById(R.id.sellorderamount);
            llsell = (LinearLayout) itemView.findViewById(R.id.llsell);

            }
    }
}
