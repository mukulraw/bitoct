package com.sadak.bitcoin.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sadak.bitcoin.R;
import com.sadak.bitcoin.adapter.First_recyclerview_home;

/**
 * Created by USER on 24-05-2018.
 */

public class Loser extends Fragment {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.losser, container, false);
        recyclerView = v.findViewById(R.id.rvlosser);
        String a ="3";
        First_recyclerview_home first_recyclerview_home = new First_recyclerview_home(getContext(),Home.list1,a);
        linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(first_recyclerview_home);
        return v;
    }
}
