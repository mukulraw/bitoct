package com.sadak.bitcoin.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sadak.bitcoin.Activity.Deposit;
import com.sadak.bitcoin.Activity.Withdrawal;
import com.sadak.bitcoin.NetworkConnection.Apidata;
import com.sadak.bitcoin.NetworkConnection.RetrofitInstance;
import com.sadak.bitcoin.R;
import com.sadak.bitcoin.adapter.Fund_recycler_view;
import com.sadak.bitcoin.model.fund.Datum;
import com.sadak.bitcoin.model.fund.Profile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class Fund extends Fragment {
    RecyclerView recyclerView;
    TextView deposite,witjhdrawal;
    SharedPreferences sp;
    String s;
    List<Datum> list = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    String click_string = "1";





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fund,container,false);
        sp = getActivity().getSharedPreferences("Bitoct_user",MODE_PRIVATE);
        s = sp.getString("user_id", "");
        Log.e("sss",""+s);
        deposite = (TextView) v.findViewById(R.id.txtdepositefund);
        witjhdrawal = (TextView) v.findViewById(R.id.txtwithdrawalfund);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerviewfund);


        Apidata apidata = RetrofitInstance.getRetrofitInstance().create(Apidata.class);
        Call<Profile> call = apidata.getFundData("api/bitoct/getBalanceCurreny?MemberId="+s);
        Log.e("ssssssp","api/bitoct/getBalanceCurreny?MemberId="+s);
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                list = response.body().getData();

                Log.e("qw",""+list);
                Fund_recycler_view f = new Fund_recycler_view(getContext(),list,click_string);
                linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(f);


            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Log.e("eeeee",""+t);
            }
        });




        deposite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Deposit.class);
                i.putExtra("coinlist", (Serializable) list);
                startActivity(i);

            }
        });


        witjhdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Withdrawal.class);
                i.putExtra("coinlist", (Serializable) list);
                startActivity(i);

            }
        });




        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser)
        {
//            Apidata apidata = RetrofitInstance.getRetrofitInstance().create(Apidata.class);
//            Call<Profile> call = apidata.getFundData("api/bitoct/getBalanceCurreny?MemberId="+s);
//            Log.e("ssssssp","api/bitoct/getBalanceCurreny?MemberId="+s);
//            call.enqueue(new Callback<Profile>() {
//                @Override
//                public void onResponse(Call<Profile> call, Response<Profile> response) {
//                    list = response.body().getData();
//                    Log.e("qw",""+list);
//                    Fund_recycler_view f = new Fund_recycler_view(getContext(),list);
//                    linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
//                    recyclerView.setLayoutManager(linearLayoutManager);
//                    recyclerView.setAdapter(f);
//
//
//                }
//
//                @Override
//                public void onFailure(Call<Profile> call, Throwable t) {
//
//                }
//            });
        }
    }
}