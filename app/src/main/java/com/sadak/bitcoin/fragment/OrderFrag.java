package com.sadak.bitcoin.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sadak.bitcoin.NetworkConnection.Apidata;
import com.sadak.bitcoin.NetworkConnection.RetrofitInstance;
import com.sadak.bitcoin.R;
import com.sadak.bitcoin.adapter.Market_recyclerview;
import com.sadak.bitcoin.adapter.Market_recyclerview_adapter.First_market_tab;
import com.sadak.bitcoin.model.market.Datum;
import com.sadak.bitcoin.model.market.Profile;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderFrag extends Fragment {

    SharedPreferences sharedPreferences;
    String member_id;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.order_history, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.order_history);
        sharedPreferences = getContext().getSharedPreferences("Bitoct_user", Context.MODE_PRIVATE);
        member_id = sharedPreferences.getString("user_id", "");


        return v;
    }


    @Override
    public void onResume() {
        super.onResume();

        setdata();

    }

    public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
        public OrderAdapter(List<com.sadak.bitcoin.model.OrderModel.Datum> arrayList, Context context) {
            this.arrayList = arrayList;
            this.context = context;
        }

        List<com.sadak.bitcoin.model.OrderModel.Datum> arrayList = new ArrayList<>();
        Context context;

        @Override
        public OrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.order_item, parent, false);

            return new OrderAdapter.ViewHolder(v);
        }

        @Override

        public void onBindViewHolder(OrderAdapter.ViewHolder holder, int position) {

            final com.sadak.bitcoin.model.OrderModel.Datum datum = arrayList.get(position);
            holder.name.setText(datum.getMarket());
            holder.date.setText(datum.getTicker());
            holder.amount.setText(datum.getFee());
            holder.price.setText(datum.getPrice());
            holder.cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String url = "api/bitoct/cancelOrder?orderid=" + datum.getTickerid();
                    Apidata apidata = RetrofitInstance.getRetrofitInstance().create(Apidata.class);
                    Call<com.sadak.bitcoin.model.Cancel.Profile> call = apidata.cancleOrder(url);
                    call.enqueue(new Callback<com.sadak.bitcoin.model.Cancel.Profile>() {
                        @Override
                        public void onResponse(Call<com.sadak.bitcoin.model.Cancel.Profile> call, Response<com.sadak.bitcoin.model.Cancel.Profile> response) {
                            if (response.body().getStatus().equals("Succeed")) {
                                setdata();
                            }
                        }

                        @Override
                        public void onFailure(Call<com.sadak.bitcoin.model.Cancel.Profile> call, Throwable t) {

                        }
                    });

                }
            });

        }

        @Override
        public int getItemCount() {
            return arrayList == null ? 0 : arrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView name, date, time, amount, price, cancle;

            public ViewHolder(View itemView) {
                super(itemView);
                name = (TextView) itemView.findViewById(R.id.market);
                date = (TextView) itemView.findViewById(R.id.date);
                time = (TextView) itemView.findViewById(R.id.time);
                amount = (TextView) itemView.findViewById(R.id.amount);
                price = (TextView) itemView.findViewById(R.id.price);
                cancle = (TextView) itemView.findViewById(R.id.cancel);
            }
        }
    }

    public void setdata() {

        //Toast.makeText(getActivity(), "order"+member_id, Toast.LENGTH_LONG).show();
        String url = "https://api.bitoct.com/api/bitoct/getOpenHistory?MemberId=" + member_id;
        Apidata apidata = RetrofitInstance.getRetrofitInstance().create(Apidata.class);
        Call<com.sadak.bitcoin.model.OrderModel.Profile> call = apidata.getdata(url);
        call.enqueue(new Callback<com.sadak.bitcoin.model.OrderModel.Profile>() {
            @Override
            public void onResponse(Call<com.sadak.bitcoin.model.OrderModel.Profile> call, Response<com.sadak.bitcoin.model.OrderModel.Profile> response) {
//                Log.e(">>>>",""+response.body().getStatus());
                recyclerView.setAdapter(new OrderAdapter(response.body().getData(), getContext()));
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            }


            @Override
            public void onFailure(Call<com.sadak.bitcoin.model.OrderModel.Profile> call, Throwable t) {

            }
        });


    }
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser)
//        {
//           setdata();
//
//        }
//
//
//    }

}