package com.sadak.bitcoin.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sadak.bitcoin.NetworkConnection.Apidata;
import com.sadak.bitcoin.NetworkConnection.RetrofitInstance;
import com.sadak.bitcoin.R;
import com.sadak.bitcoin.adapter.Market_recyclerview;
import com.sadak.bitcoin.adapter.Market_viewpager_adapter;
import com.sadak.bitcoin.model.market.Datum;
import com.sadak.bitcoin.model.market.Profile;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by USER on 25-05-2018.
 */

public class




Market extends Fragment {

    ViewPager vp;
    TabLayout tabLayout;
    LinearLayoutManager linearLayoutManager;
    List<Datum> list = new ArrayList<>();




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.market,container,false);
        vp = v.findViewById(R.id.vpmarket);
        tabLayout = v.findViewById(R.id.tablayoutmarket);
        linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        vp.setAdapter(new Market_viewpager_adapter(getFragmentManager()));
        tabLayout.setupWithViewPager(vp);



        return v;
    }
}
