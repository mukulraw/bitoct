package com.sadak.bitcoin.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sadak.bitcoin.Activity.Deposit;
import com.sadak.bitcoin.Activity.Withdrawal;
import com.sadak.bitcoin.NetworkConnection.Apidata;
import com.sadak.bitcoin.NetworkConnection.RetrofitInstance;
import com.sadak.bitcoin.R;
import com.sadak.bitcoin.adapter.First_recyclerview_home;
import com.sadak.bitcoin.adapter.Fund_recycler_view;
import com.sadak.bitcoin.adapter.Gainer_losser_Adapter;
import com.sadak.bitcoin.adapter.threetextview;
import com.sadak.bitcoin.model.Image_viewpager.Datum;
import com.sadak.bitcoin.model.Image_viewpager.Profile;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class Home extends android.support.v4.app.Fragment{

    RecyclerView threetextrecyclerview,pairrecyclerview;
    LinearLayoutManager linearLayoutManagerthreetext;
    TextView txtsupport,txtdeposite,txtwithdrawal;
    ViewPager viewPager,vpgainerloser;
    TabLayout tablayout;
    public static List<com.sadak.bitcoin.model.firstRecyclerView.Datum> list1 = new ArrayList<>();
    ArrayList<String> img = new ArrayList<>();
    String a = "";
    SharedPreferences sp;
    String s;
    List<com.sadak.bitcoin.model.fund.Datum> listt = new ArrayList<>();
    String click_string = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_home,container,false);



        viewPager = (ViewPager) v.findViewById(R.id.viewpager1);
        vpgainerloser = (ViewPager) v.findViewById(R.id.vpgainerlosser);
        tablayout = (TabLayout) v.findViewById(R.id.tablayout);
        threetextrecyclerview = (RecyclerView) v.findViewById(R.id.threetextrecyclerview);
        pairrecyclerview = (RecyclerView) v.findViewById(R.id.pairrecyclerview);
        txtsupport = (TextView) v.findViewById(R.id.txtsupport);
        txtdeposite = (TextView) v.findViewById(R.id.txtdeposite);
        txtwithdrawal = (TextView) v.findViewById(R.id.txtwithdrawal);

        Apidata apidata = RetrofitInstance.getRetrofitInstance().create(Apidata.class);
        Call<Profile> call = apidata.getViewPagerImage();
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {


                List<Datum> list = new ArrayList<>();
                list= response.body().getData();

               for (int i = 0;i<list.size();i++ )
               {
                    img.add(list.get(i).getThemePics());
               }

                //Log.e("img",""+img);
                viewPager.setAdapter(new setFirstImage(getActivity(),img));

            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {

            }
        });

        Apidata apidata11 = RetrofitInstance.getRetrofitInstance().create(Apidata.class);
        Call<com.sadak.bitcoin.model.threetextview.Profile> call1 = apidata.gettextViewdata();
        call1.enqueue(new Callback<com.sadak.bitcoin.model.threetextview.Profile>() {
            @Override
            public void onResponse(Call<com.sadak.bitcoin.model.threetextview.Profile> call, Response<com.sadak.bitcoin.model.threetextview.Profile> response) {
              //  Log.e("respomse",response.body().getMessage());
                List<com.sadak.bitcoin.model.threetextview.Datum> list = new ArrayList<>();
                list = response.body().getData();
                threetextview t = new threetextview(getContext(),list);
                linearLayoutManagerthreetext=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                threetextrecyclerview.setLayoutManager(linearLayoutManagerthreetext);
                threetextrecyclerview.setAdapter(t);
            }

            @Override
            public void onFailure(Call<com.sadak.bitcoin.model.threetextview.Profile> call, Throwable t) {

            }
        });

        Apidata apidata2 = RetrofitInstance.getRetrofitInstance().create(Apidata.class);
        Call<com.sadak.bitcoin.model.firstRecyclerView.Profile> call2 = apidata2.getfirstrecyclerviewdat();
        call2.enqueue(new Callback<com.sadak.bitcoin.model.firstRecyclerView.Profile>() {
            @Override
            public void onResponse(Call<com.sadak.bitcoin.model.firstRecyclerView.Profile> call, Response<com.sadak.bitcoin.model.firstRecyclerView.Profile> response) {
                //Log.e("respomse",response.body().getMessage());
                String a = "1";

                list1 = response.body().getData();
                First_recyclerview_home first_recyclerview_home = new First_recyclerview_home(getContext(),list1,a);
                linearLayoutManagerthreetext=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                pairrecyclerview.setLayoutManager(linearLayoutManagerthreetext);
                pairrecyclerview.setAdapter(first_recyclerview_home);
                vpgainerloser.setAdapter(new Gainer_losser_Adapter(getFragmentManager()));
                tablayout.setupWithViewPager(vpgainerloser);


            }

            @Override
            public void onFailure(Call<com.sadak.bitcoin.model.firstRecyclerView.Profile> call, Throwable t) {

            }
        });

        txtdeposite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                click_string = "deposite";
                getCoin();


            }
        });


        txtwithdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click_string = "withdrawal";
                getCoin();


            }
        });


        return v;
    }

    public class setFirstImage extends PagerAdapter{


        private ArrayList<String> IMAGES;
        private LayoutInflater inflater;
        private Context context;

        public setFirstImage(Context context,ArrayList<String> IMAGES) {
            this.context = context;
            this.IMAGES=IMAGES;


            //java.lang.NullPointerException: Attempt to invoke virtual method 'java.lang.Object android.content.Context.getSystemService(java.lang.String)' on a null object reference
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }



        @Override
        public int getCount() {
           // Log.e("imagesize",""+IMAGES.size());
            return IMAGES.size();

        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            View imageLayout = LayoutInflater.from(context).inflate(R.layout.first_image_viewpager, view, false);

            assert imageLayout != null;
             ImageView imageView = (ImageView) imageLayout
                    .findViewById(R.id.imageView);



                 Picasso.get().load(IMAGES.get(position)).into(imageView);
                 // Log.e("imagepath",IMAGES.get(position));

                 view.addView(imageLayout, 0);


            return imageLayout;
        }


    }

    public void getCoin()
    {

        sp = getActivity().getSharedPreferences("Bitoct_user",MODE_PRIVATE);
        s = sp.getString("user_id", "");
        Apidata apidata = RetrofitInstance.getRetrofitInstance().create(Apidata.class);
        Call<com.sadak.bitcoin.model.fund.Profile> call = apidata.getFundData("api/bitoct/getBalanceCurreny?MemberId="+s);
        Log.e("ssssssp","api/bitoct/getBalanceCurreny?MemberId="+s);
        call.enqueue(new Callback<com.sadak.bitcoin.model.fund.Profile>() {
            @Override
            public void onResponse(Call<com.sadak.bitcoin.model.fund.Profile> call, Response<com.sadak.bitcoin.model.fund.Profile> response) {
                listt = response.body().getData();
                if (click_string.equals("deposite"))
                {


                    Intent i = new Intent(getActivity(), Deposit.class);
                    i.putExtra("coinlist", (Serializable) listt);
                    startActivity(i);

                }

                if (click_string.equals("withdrawal"))
                {
                    Intent i = new Intent(getActivity(), Withdrawal.class);
                    i.putExtra("coinlist", (Serializable) listt);
                    startActivity(i);
                }



            }

            @Override
            public void onFailure(Call<com.sadak.bitcoin.model.fund.Profile> call, Throwable t) {
                Log.e("eeeee",""+t);
            }
        });
    }
}
