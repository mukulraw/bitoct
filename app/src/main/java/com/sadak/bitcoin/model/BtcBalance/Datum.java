
package com.sadak.bitcoin.model.BtcBalance;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable
{

    @SerializedName("TotalCoin")
    @Expose
    private String totalCoin;
    @SerializedName("BalBTC")
    @Expose
    private String balBTC;
    @SerializedName("BTCPrice")
    @Expose
    private String bTCPrice;
    @SerializedName("buyprice")
    @Expose
    private String buyprice;
    @SerializedName("sellprice")
    @Expose
    private String sellprice;
    private final static long serialVersionUID = -2964839223561791867L;

    public String getTotalCoin() {
        return totalCoin;
    }

    public void setTotalCoin(String totalCoin) {
        this.totalCoin = totalCoin;
    }

    public String getBalBTC() {
        return balBTC;
    }

    public void setBalBTC(String balBTC) {
        this.balBTC = balBTC;
    }

    public String getBTCPrice() {
        return bTCPrice;
    }

    public void setBTCPrice(String bTCPrice) {
        this.bTCPrice = bTCPrice;
    }

    public String getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(String buyprice) {
        this.buyprice = buyprice;
    }

    public String getSellprice() {
        return sellprice;
    }

    public void setSellprice(String sellprice) {
        this.sellprice = sellprice;
    }

}
