package com.sadak.bitcoin.NetworkConnection;

import com.sadak.bitcoin.Activity.Registration;
import com.sadak.bitcoin.lineChartPOJO.lineChartBean;
import com.sadak.bitcoin.model.Buy.Datum;
import com.sadak.bitcoin.model.Profile;
import com.sadak.bitcoin.model.Sellorder.Example;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by USER on 23-05-2018.
 */

public interface Apidata {
    @FormUrlEncoded
    @Headers({"Content-type: application/x-www-form-urlencoded"})
    @POST("api/bitoct/Register")
    Call<Profile> getRegistrationData(@Field("UserName")String username,@Field("EmailId")String email,@Field("Password")String password);

    @FormUrlEncoded
    @Headers({"Content-type: application/x-www-form-urlencoded"})
    @POST("api/bitoct/login")
    Call<com.sadak.bitcoin.model.Login.Profile> getLoginData(@Field("UserName")String username,@Field("Password")String password);

    @GET("api/bitoct/getThemeList")
    Call<com.sadak.bitcoin.model.Image_viewpager.Profile> getViewPagerImage();

    @GET("api/bitoct/getTopFourMarket")
    Call<com.sadak.bitcoin.model.threetextview.Profile> gettextViewdata();

    @GET("api/bitoct/getMarketDataBtc")
    Call<com.sadak.bitcoin.model.firstRecyclerView.Profile> getfirstrecyclerviewdat();

    @GET("api/bitoct/getMarketData")
    Call<com.sadak.bitcoin.model.market.Profile> getMarketdata();

    @GET
    Call<com.sadak.bitcoin.model.OrderModel.Profile>getdata(@Url String url);

    @GET
    Call<com.sadak.bitcoin.model.BtcBalance.Profile> getBtcBalance(@Url String url);

    @GET
    Call<com.sadak.bitcoin.model.fund.Profile> getFundData(@Url String url);

    @GET
    Call<com.sadak.bitcoin.model.Address.Profile> getAddress(@Url String url);

    @GET
    Call<com.sadak.bitcoin.model.Withdrawal.Profile> getWithdrawalDetail(@Url String url);

    @GET
    Call<com.sadak.bitcoin.model.Withdrawl_btn.Profile> getWithdrawalResult(@Url String url);

    @GET
    Call<Example> getSellOrder(@Url String url);

    @GET
    Call<com.sadak.bitcoin.model.tradehistory.Example> getTradeHistory(@Url String url);

    @GET
    Call<Example> getBuyOrder(@Url String url);

    @GET
    Call<com.sadak.bitcoin.model.Graph.Profile> getGraph(@Url String url);

    @FormUrlEncoded
    @Headers({"Content-type: application/x-www-form-urlencoded"})
    @POST("/api/bitoct/buyCurrencyCheck")
    Call<com.sadak.bitcoin.model.Buy.Profile>getBuy(@Field("curreny")String currency,@Field("Price") String price,@Field("MarketId")String  marketid,@Field("TotalPrice")String totalprice,@Field("Amount")String amount);

    @FormUrlEncoded
    @Headers({"Content-type: application/x-www-form-urlencoded"})
    @POST("/api/bitoct/buyCurrencyCheck")
    Call<com.sadak.bitcoin.model.Buy.Profile>buy(@Field("curreny")String currency,@Field("Price") String price,@Field("MarketId")String  marketid,@Field("TotalPrice")String totalprice,@Field("Amount")String amount,@Field("BuyClick") String buyclick,@Field("memberid")String memberid);

    @FormUrlEncoded
    @Headers({"Content-type: application/x-www-form-urlencoded"})
    @POST("/api/bitoct/sellCurrencycheck")
    Call<com.sadak.bitcoin.model.Buy.Profile>getSell(@Field("curreny")String currency,@Field("Price") String price,@Field("MarketId")String  marketid,@Field("TotalPrice")String totalprice,@Field("Amount")String amount);

    @FormUrlEncoded
    @Headers({"Content-type: application/x-www-form-urlencoded"})
    @POST("/api/bitoct/sellCurrencycheck")
    Call<com.sadak.bitcoin.model.Buy.Profile>sell(@Field("curreny")String currency,@Field("Price") String price,@Field("MarketId")String  marketid,@Field("TotalPrice")String totalprice,@Field("Amount")String amount,@Field("BuyClick") String buyclick,@Field("memberid")String memberid);

    @GET
    Call<com.sadak.bitcoin.model.Cancel.Profile> cancleOrder(@Url String url);

    @GET
    Call<com.sadak.bitcoin.model.History.Profile> history(@Url String url);

    @FormUrlEncoded
    @Headers({"Content-type: application/x-www-form-urlencoded"})
    @POST
    Call<com.sadak.bitcoin.model.Changepass.Profile> changepass(@Field("MemberId")String memberid,@Field("oldPassword")String oldpass,@Field("newPassword")String newpass);

    @GET("api/bitoct/getGraphMarketDataByMarketIDLive")
    Call<lineChartBean> getLineChartData(
            @Query("marketid") String marketId,
            @Query("history") String history
    );

    @GET("api/bitoct/getGraphMarketDataByMarketVolume")
    Call<lineChartBean> getBarChartData(
            @Query("marketid") String marketId,
            @Query("history") String history
    );

}
