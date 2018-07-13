package com.sadak.bitcoin.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sadak.bitcoin.R;
import com.sadak.bitcoin.adapter.First_recyclerview_home;
import com.sadak.bitcoin.model.firstRecyclerView.Datum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 24-05-2018.
 */

public class Gainer extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ArrayList<String> aa,bb,cc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.gainer,container,false);
        recyclerView = v.findViewById(R.id.rvgainer);
        String a ="2";
        aa = new ArrayList<>();
        bb = new ArrayList<>();
        cc = new ArrayList<>();
        List<Datum> datum = Home.list1;
        for(int i =0;i<datum.size();i++)
        {
            Datum datum1 =datum.get(i);
            aa.add( datum1.getMarketAssetCode());
            bb.add( datum1.getLastPrice());
            cc.add(datum1.getChange());

        }

        //First_recyclerview_home first_recyclerview_home = new First_recyclerview_home(getContext(),aa,bb,cc,a);
        First_recyclerview_home first_recyclerview_home = new First_recyclerview_home(getContext(),Home.list1,a);
        linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(first_recyclerview_home);
        return v;
    }
}
