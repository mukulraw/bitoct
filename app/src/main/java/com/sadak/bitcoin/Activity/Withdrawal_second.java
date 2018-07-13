package com.sadak.bitcoin.Activity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sadak.bitcoin.NetworkConnection.Apidata;
import com.sadak.bitcoin.NetworkConnection.RetrofitInstance;
import com.sadak.bitcoin.R;
import com.sadak.bitcoin.model.Withdrawal.Datum;
import com.sadak.bitcoin.model.Withdrawal.Profile;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Withdrawal_second extends AppCompatActivity {


    TextView avl_balance,txt_fee,cointxt,res_amnt;
    ImageView coin_img;
    EditText withdrawal_amnt,address,pin;
    LinearLayout withdrawal;
    SharedPreferences sp;
    String s,max_amnt,fee;
    List<Datum> list = new ArrayList<>();
    Datum dd = new Datum();
    double withdraw_amount, avaible, fee_d, result, result2 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal_second);
        getSupportActionBar().hide();
        avl_balance = (TextView) findViewById(R.id.txtavablance);
        txt_fee = (TextView) findViewById(R.id.txtfee);
        cointxt = (TextView) findViewById(R.id.txtcnamewith);
        res_amnt = (TextView) findViewById(R.id.resamnt);
        coin_img = (ImageView) findViewById(R.id.imgaddwith);
        withdrawal_amnt = (EditText) findViewById(R.id.edtwithdrawalamount);
        address = (EditText) findViewById(R.id.edtaddress);
        pin = (EditText) findViewById(R.id.edtpincode);
        withdrawal = (LinearLayout) findViewById(R.id.llwithdrawal);

        cointxt.setText(""+getIntent().getStringExtra("coin"));
        Picasso.get().load(getIntent().getStringExtra("image")).into(coin_img);

        sp = getSharedPreferences("Bitoct_user",MODE_PRIVATE);
        s = sp.getString("user_id", "");


        final Apidata apidata = RetrofitInstance.getRetrofitInstance().create(Apidata.class);
        Call<Profile> call = apidata.getWithdrawalDetail("api/bitoct/getWithdrawDetails?MemberId="+s+"&currency="+getIntent().getStringExtra("coin"));
       call.enqueue(new Callback<Profile>() {
           @Override
           public void onResponse(Call<Profile> call, Response<Profile> response) {
               list = response.body().getData();

               for (int i =0;i<list.size();i++)
               {
                   dd = list.get(i);

               }
               avl_balance.setText(""+dd.getMaxAmount());
              txt_fee.setText("Fee. "+dd.getToTxfee());
           }

           @Override
           public void onFailure(Call<Profile> call, Throwable t) {

           }
       });

        withdrawal_amnt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(s.length() != 0)
                {
                     withdraw_amount = Double.parseDouble(withdrawal_amnt.getText().toString());
                     avaible = Double.parseDouble(dd.getMaxAmount());
                     fee_d = Double.parseDouble(dd.getToTxfee());
                     result = withdraw_amount-fee_d;
                     result2 = result+avaible;
                    res_amnt.setText(""+result2);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        withdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Apidata apidata1 = RetrofitInstance.getRetrofitInstance().create(Apidata.class);
                Call<com.sadak.bitcoin.model.Withdrawl_btn.Profile> call1 = apidata.getWithdrawalResult("api/bitoct/withdrawToAddress?toamount="+withdrawal_amnt.getText().toString()+"&estimateAmount="+result+"&toaddress="+address.getText().toString()+"&currency="+cointxt.getText().toString()+"&Pin="+pin.getText().toString()+"&MemberId="+s);
                call1.enqueue(new Callback<com.sadak.bitcoin.model.Withdrawl_btn.Profile>() {
                    @Override
                    public void onResponse(Call<com.sadak.bitcoin.model.Withdrawl_btn.Profile> call, Response<com.sadak.bitcoin.model.Withdrawl_btn.Profile> response) {
                        AlertDialog.Builder builder;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            builder = new AlertDialog.Builder(Withdrawal_second.this, android.R.style.Theme_Material_Dialog_Alert);
                        } else {
                            builder = new AlertDialog.Builder(Withdrawal_second.this);
                        }
                        builder.setTitle("Message")
                                .setMessage(response.body().getMessage())
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // continue with delete
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // do nothing
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }

                    @Override
                    public void onFailure(Call<com.sadak.bitcoin.model.Withdrawl_btn.Profile> call, Throwable t) {

                    }
                });
            }
        });


    }
}