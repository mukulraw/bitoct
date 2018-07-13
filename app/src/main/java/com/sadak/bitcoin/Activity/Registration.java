package com.sadak.bitcoin.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sadak.bitcoin.NetworkConnection.Apidata;
import com.sadak.bitcoin.NetworkConnection.RetrofitInstance;
import com.sadak.bitcoin.R;
import com.sadak.bitcoin.model.Profile;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity {

    EditText name,email,password;
    TextView txtlogin;
    Button btnreg;
    String snackbar_message;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();
        name = (EditText) findViewById(R.id.edtusernamereg);
        email = (EditText) findViewById(R.id.edtemailreg);
        password = (EditText) findViewById(R.id.edtpasswordreg);
        txtlogin = (TextView) findViewById(R.id.txtregister);
        btnreg = (Button) findViewById(R.id.btnloginreg);
        txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Registration.this,Login.class);
                startActivity(i);
                finish();
            }
        });
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name.getText().toString().equals(""))
                {
                    snackbar_message = "Please enter username";
                    show_snackbar();
                }
                else if (email.getText().toString().equals(""))
                {
                    snackbar_message = "Please enter email";
                    show_snackbar();
                }
                else if (password.getText().toString().equals(""))
                {
                    snackbar_message = "Please enter password";
                    show_snackbar();
                }
                else
                {

                    progressDialog = progressDialog.show(Registration.this,"","Please wait...",false,false);
                Map<String, String> params = new HashMap<>();
                params.put("UserName", name.getText().toString());
                params.put("EmailId", email.getText().toString());
                params.put("Password", password.getText().toString());

                Apidata apidata = RetrofitInstance.getRetrofitInstance().create(Apidata.class);
                Call<Profile> call = apidata.getRegistrationData(name.getText().toString(),email.getText().toString(),password.getText().toString());
                call.enqueue(new Callback<Profile>() {
                    @Override
                    public void onResponse(Call<Profile> call, Response<Profile> response) {
                        progressDialog.dismiss();
                        response.body().getMessage();
                        Toast.makeText(Registration.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("response",response.body().getMessage());
                        if (response.body().getMessage().equals("Signup done successfully"))
                        {
                            Intent i = new Intent(Registration.this,Login.class);
                            startActivity(i);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Profile> call, Throwable t) {
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