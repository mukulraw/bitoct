package com.sadak.bitcoin.Activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sadak.bitcoin.NetworkConnection.Apidata;
import com.sadak.bitcoin.NetworkConnection.RetrofitInstance;
import com.sadak.bitcoin.R;
import com.sadak.bitcoin.model.Address.Datum;
import com.sadak.bitcoin.model.Address.Profile;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Address extends AppCompatActivity {

    ImageView imageView;
    TextView name,add;
    LinearLayout copyl,qrl;
    SharedPreferences sp;
    String s;
    List<Datum> list = new ArrayList<>();
    Datum dd = new Datum();
     AlertDialog.Builder builder ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        getSupportActionBar().hide();
        imageView = (ImageView) findViewById(R.id.imgadd);
        name = (TextView) findViewById(R.id.txtcname);
        add = (TextView) findViewById(R.id.txtaddress);
        copyl = (LinearLayout) findViewById(R.id.llcopy);
        qrl = (LinearLayout) findViewById(R.id.llqr);
        name.setText(""+getIntent().getStringExtra("coin"));
        Picasso.get().load(getIntent().getStringExtra("image")).into(imageView);
        sp = getSharedPreferences("Bitoct_user",MODE_PRIVATE);
        s = sp.getString("user_id", "");

        builder = new AlertDialog.Builder(Address.this);
        Apidata apidata = RetrofitInstance.getRetrofitInstance().create(Apidata.class);
        Call<Profile> call = apidata.getAddress("api/bitoct/genAddress?currency="+getIntent().getStringExtra("coin")+"&MemberId="+s);
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                list = response.body().getData();

                for (int i =0;i<list.size();i++)
                {
                     dd = list.get(i);

                }
                add.setText(""+dd.getAddress());
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {

            }
        });



        copyl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(add.getText());
            }
        });


        qrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byte[] decodedString = Base64.decode(dd.getQr(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                View view1  = getLayoutInflater().inflate(R.layout.deposite_aleart_dialog,null,false);


                builder.setView(view1);
                final TextView address_aleart;
                ImageView img_qr,img_cancle;
                LinearLayout lcopy_aleart;
                address_aleart = (TextView) view1.findViewById(R.id.txtaddressaleart);
                img_qr = (ImageView) view1.findViewById(R.id.imgqraleart);
                img_cancle = (ImageView) view1.findViewById(R.id.imgcancle);
                lcopy_aleart = (LinearLayout) view1.findViewById(R.id.llcopy);

                address_aleart.setText(""+dd.getAddress());
                img_qr.setImageBitmap(decodedByte);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                img_cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();

                    }
                });
                lcopy_aleart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ClipboardManager cm = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                        cm.setText(address_aleart.getText());
                    }
                });

            }
        });

    }
}