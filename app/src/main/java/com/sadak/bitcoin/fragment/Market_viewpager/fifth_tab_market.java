package com.sadak.bitcoin.fragment.Market_viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sadak.bitcoin.NetworkConnection.Apidata;
import com.sadak.bitcoin.NetworkConnection.RetrofitInstance;
import com.sadak.bitcoin.R;
import com.sadak.bitcoin.adapter.Market_recyclerview_adapter.First_market_tab;
import com.sadak.bitcoin.model.market.Datum;
import com.sadak.bitcoin.model.market.Profile;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fifth_tab_market extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<Datum> list = new ArrayList<>();
    List<Datum> list_tosend = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.market_main_fragment,container,false);
        recyclerView = (RecyclerView) v.findViewById(R.id.market_recyclerview);


        return v;
    }




    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Apidata apidata = RetrofitInstance.getRetrofitInstance().create(Apidata.class);
            Call<Profile> call = apidata.getMarketdata();
            call.enqueue(new Callback<Profile>() {
                @Override
                public void onResponse(Call<Profile> call, Response<Profile> response) {
                    list = response.body().getData();
                    for (int i =0;i<list.size();i++)
                    {
                       // list_tosend.clear();
                        Datum dd = list.get(i);
                        if (dd.getBaseCurrencyID().contains("5"))
                        {
                            list_tosend.add(dd);
                        }

                    }

                    First_market_tab first_market_tab = new First_market_tab(getContext(),list_tosend);
                    linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(first_market_tab);

                }

                @Override
                public void onFailure(Call<Profile> call, Throwable t) {

                }
            });


            // Do your Work
        } else {
            // Do your Work
        }
    }
}

