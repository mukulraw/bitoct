package com.sadak.bitcoin.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sadak.bitcoin.NetworkConnection.Apidata;
import com.sadak.bitcoin.NetworkConnection.RetrofitInstance;
import com.sadak.bitcoin.R;
import com.sadak.bitcoin.fragment.OrderFrag;
import com.sadak.bitcoin.model.Cancel.Profile;
import com.sadak.bitcoin.model.OrderModel.Datum;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class History extends AppCompatActivity {
    RecyclerView recyclerView;
    String member_id;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().hide();
        recyclerView=(RecyclerView)findViewById(R.id.history_recycle);
        getHistory();
    }
  public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>
  {
      Context context;
      List<com.sadak.bitcoin.model.History.Datum> arraylist = new ArrayList<>();

      public HistoryAdapter(Context context, List<com.sadak.bitcoin.model.History.Datum> arraylist) {
          this.context = context;
          this.arraylist = arraylist;
      }

      @Override
      public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          LayoutInflater inflater;
          inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
          View v = inflater.inflate(R.layout.history_item,parent,false);
          return  new HistoryAdapter.ViewHolder(v);
      }

      @Override
      public void onBindViewHolder(HistoryAdapter.ViewHolder holder, int position) {
          com.sadak.bitcoin.model.History.Datum datum = arraylist.get(position);
          holder.name.setText(datum.getMarket());
          holder.price.setText(datum.getPrice());
          holder.amount.setText(String.valueOf(datum.getVolume()));
          String sp=datum.getTicker();
          String[] splited = sp.split("\\s+");
          holder.date.setText( splited[0]);
          holder.anotherprice.setText(datum.getPrice());
          holder.anotheramount.setText(String.valueOf(datum.getVolume()));



      }

      @Override
      public int getItemCount() {
          return arraylist==null?0:arraylist.size();

      }

      public class ViewHolder extends RecyclerView.ViewHolder {
          TextView name,price,amount,date,anotherprice,anotheramount;
          public ViewHolder(View itemView) {
              super(itemView);
              name=(TextView)itemView.findViewById(R.id.name);
              price=(TextView)itemView.findViewById(R.id.price);
              amount=(TextView)itemView.findViewById(R.id.filled_amount);
              date=(TextView)itemView.findViewById(R.id.history_date);
              anotherprice=(TextView)itemView.findViewById(R.id.another_price);
              anotheramount=(TextView)itemView.findViewById(R.id.another_filled);
          }
      }
  }
  public void getHistory()
  {
      sharedPreferences = getSharedPreferences("Bitoct_user",Context.MODE_PRIVATE);
      member_id = sharedPreferences.getString("user_id","");
      String url ="https://api.bitoct.com/api/bitoct/getOrderHistory?MemberId="+member_id;
      Apidata apidata = RetrofitInstance.getRetrofitInstance().create(Apidata.class);
      Call<com.sadak.bitcoin.model.History.Profile> call = apidata.history(url);
      call.enqueue(new Callback<com.sadak.bitcoin.model.History.Profile>() {
          @Override
          public void onResponse(Call<com.sadak.bitcoin.model.History.Profile> call, Response<com.sadak.bitcoin.model.History.Profile> response) {

              Log.e(">>>>>>>++",""+response.body().getStatus());
          recyclerView.setAdapter(new HistoryAdapter(History.this,response.body().getData()));
          recyclerView.setLayoutManager(new LinearLayoutManager(History.this));
          }

          @Override
          public void onFailure(Call<com.sadak.bitcoin.model.History.Profile> call, Throwable t) {

          }
      });

  }

}
