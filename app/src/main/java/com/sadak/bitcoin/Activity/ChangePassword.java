package com.sadak.bitcoin.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.sadak.bitcoin.NetworkConnection.Apidata;
import com.sadak.bitcoin.NetworkConnection.RetrofitInstance;
import com.sadak.bitcoin.R;
import com.sadak.bitcoin.model.Changepass.Profile;

import okhttp3.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity {

    EditText oldpass,newpass,confirmpass;
    SharedPreferences sharedPreferences;
    String member_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        sharedPreferences = getSharedPreferences("Bitoct_user", Context.MODE_PRIVATE);
        member_id = sharedPreferences.getString("user_id","");
        getSupportActionBar().hide();
        oldpass = (EditText) findViewById(R.id.edtoldpass);
        newpass = (EditText) findViewById(R.id.edtnewpass);
        confirmpass = (EditText) findViewById(R.id.edtconfirmpass);
    }

    public  void  changepass()
    {
        String pass = oldpass.getText().toString();
        String newPass = newpass.getText().toString();
        String cnfrmpass = confirmpass.getText().toString();
        if(pass.isEmpty())
        {
            Toast.makeText(this, "Please enter Old password", Toast.LENGTH_SHORT).show();
        }
        else if(newPass.isEmpty())
        {
            Toast.makeText(this, "Please enter New password", Toast.LENGTH_SHORT).show();
        }
        else if(cnfrmpass.isEmpty())
        {
            Toast.makeText(this, "Please enter Confirm password", Toast.LENGTH_SHORT).show();
        }
        else if(newPass.equals(cnfrmpass))
        {
            Toast.makeText(this, "Passwords are not same", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Apidata apidata4 = RetrofitInstance.getRetrofitInstance().create(Apidata.class);
            retrofit2.Call<com.sadak.bitcoin.model.Changepass.Profile>call=apidata4.changepass(member_id,pass,newPass);
            call.enqueue(new Callback<Profile>() {
                @Override
                public void onResponse(retrofit2.Call<Profile> call, Response<Profile> response) {

                }

                @Override
                public void onFailure(retrofit2.Call<Profile> call, Throwable t) {

                }
            });

        }
    }
}


