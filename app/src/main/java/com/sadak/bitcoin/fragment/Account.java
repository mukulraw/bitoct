package com.sadak.bitcoin.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sadak.bitcoin.Activity.Security;
import com.sadak.bitcoin.R;

import static android.content.Context.MODE_PRIVATE;

public class Account extends Fragment {
    LinearLayout support,security;
    TextView user_id;
    Button logout;
    SharedPreferences sp_user;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.account,container,false);

        support = (LinearLayout) v.findViewById(R.id.llsupport);
        security = (LinearLayout) v.findViewById(R.id.llsecurity);
        user_id = (TextView) v.findViewById(R.id.txtuserid);
        logout = (Button) v.findViewById(R.id.btnlogout);

        sp_user = getContext().getSharedPreferences("Bitoct_user_email",MODE_PRIVATE);
        user_id.setText(sp_user.getString("user_email", ""));


        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), Security.class);
                startActivity(intent);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        return v;
    }
}
