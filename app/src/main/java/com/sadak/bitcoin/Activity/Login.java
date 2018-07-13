package com.sadak.bitcoin.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sadak.bitcoin.MainActivity;
import com.sadak.bitcoin.NetworkConnection.Apidata;
import com.sadak.bitcoin.NetworkConnection.RetrofitInstance;
import com.sadak.bitcoin.R;
import com.sadak.bitcoin.model.Login.Datum;
import com.sadak.bitcoin.model.Login.Profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    EditText username,password;
    Button login;
    TextView register;
    String snackbar_message;
    ProgressDialog progressDialog;
    SharedPreferences sp,sp_user;
    String ss,useremail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        sp = getSharedPreferences("Bitoct_user",MODE_PRIVATE);
        sp_user = getSharedPreferences("Bitoct_user_email",MODE_PRIVATE);

        username = (EditText) findViewById(R.id.edtusername);
        password = (EditText) findViewById(R.id.edtpassword);
        register = (TextView) findViewById(R.id.txtregister);
        login = (Button) findViewById(R.id.btnlogin);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this,Registration.class);
                startActivity(i);
                finish();

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (username.getText().toString().equals(""))
                {
                    snackbar_message = "Please enter username";
                    show_snackbar();
                }
                else if (password.getText().toString().equals(""))
                {
                    snackbar_message = "Please enter password";
                    show_snackbar();
                }
                else
                {
                    progressDialog = progressDialog.show(Login.this,"","Please wait...",false,false);
                    Map<String, String> params = new HashMap<>();
                    params.put("UserName", username.getText().toString());
                    params.put("Password", password.getText().toString());

                    Apidata apidata = RetrofitInstance.getRetrofitInstance().create(Apidata.class);
                    Call<Profile> call = apidata.getLoginData(username.getText().toString(),password.getText().toString());
                    call.enqueue(new Callback<Profile>() {
                        @Override
                        public void onResponse(retrofit2.Call<Profile> call, Response<Profile> response) {
                            progressDialog.dismiss();
                            response.body().getMessage();
                            Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            if (response.body().getMessage().equals("login successful"))
                            {
                                List<Datum> arrayList=new ArrayList<>();
                               arrayList=response.body().getData();
                               for (int i=0;i<arrayList.size();i++)
                               {
                                   Datum datum = arrayList.get(i);
                                   ss = datum.getMemberId();
                                   Log.e("res+++",""+ss);
                                   SharedPreferences.Editor editor = sp.edit();
                                   editor.putString("user_id",ss);
                                   editor.commit();

                                   useremail = datum.getEmailId();
                               }



                                SharedPreferences.Editor editor1 = sp_user.edit();
                                editor1.putString("user_email",useremail);
                                editor1.commit();


                                Intent i = new Intent(Login.this,MainActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(retrofit2.Call<Profile> call, Throwable t) {
                            progressDialog.dismiss();

                        }
                    });
                }

            }
        });
    }

    public void show_snackbar()
    {
        View parentLayout = findViewById(android.R.id.content);
        //parentLayout.setBackgroundColor(getResources().getColor(R.color.TabTextBarbrightBlue));
        Snackbar snackbar;

        snackbar=Snackbar.make(parentLayout, snackbar_message, Snackbar.LENGTH_LONG)
                .setAction("", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
        snackbar.show();
        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.TabTextBarbrightBlue));


    }
}
