
package com.sadak.bitcoin.model.fund;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable
{

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("MarketID")
    @Expose
    private String marketID;
    @SerializedName("Coin")
    @Expose
    private String coin;
    @SerializedName("CoinName")
    @Expose
    private String coinName;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("BTCBalance")
    @Expose
    private String bTCBalance;
    @SerializedName("TradeBalace")
    @Expose
    private String tradeBalace;
    @SerializedName("TotalBTC")
    @Expose
    private String totalBTC;
    private final static long serialVersionUID = 1860648412140121384L;

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getMarketID() {
        return marketID;
    }

    public void setMarketID(String marketID) {
        this.marketID = marketID;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getBTCBalance() {
        return bTCBalance;
    }

    public void setBTCBalance(String bTCBalance) {
        this.bTCBalance = bTCBalance;
    }

    public String getTradeBalace() {
        return tradeBalace;
    }

    public void setTradeBalace(String tradeBalace) {
        this.tradeBalace = tradeBalace;
    }

    public String getTotalBTC() {
        return totalBTC;
    }

    public void setTotalBTC(String totalBTC) {
        this.totalBTC = totalBTC;
    }

}
