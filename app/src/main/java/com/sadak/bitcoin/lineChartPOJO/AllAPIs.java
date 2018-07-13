package com.sadak.bitcoin.lineChartPOJO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AllAPIs {

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
