
package com.sadak.bitcoin.model.threetextview;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable
{

    @SerializedName("MarketID")
    @Expose
    private String marketID;
    @SerializedName("Currency")
    @Expose
    private String currency;
    @SerializedName("LastPrice")
    @Expose
    private String lastPrice;
    @SerializedName("Volume")
    @Expose
    private String volume;
    @SerializedName("Dollar")
    @Expose
    private String dollar;
    @SerializedName("Change")
    @Expose
    private String change;
    @SerializedName("BaseCurrencyId")
    @Expose
    private String baseCurrencyId;
    @SerializedName("HighPrice")
    @Expose
    private String highPrice;
    @SerializedName("LowPrice")
    @Expose
    private String lowPrice;
    @SerializedName("Change24")
    @Expose
    private String change24;
    private final static long serialVersionUID = -2854559041501453526L;

    public String getMarketID() {
        return marketID;
    }

    public void setMarketID(String marketID) {
        this.marketID = marketID;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getDollar() {
        return dollar;
    }

    public void setDollar(String dollar) {
        this.dollar = dollar;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getBaseCurrencyId() {
        return baseCurrencyId;
    }

    public void setBaseCurrencyId(String baseCurrencyId) {
        this.baseCurrencyId = baseCurrencyId;
    }

    public String getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(String highPrice) {
        this.highPrice = highPrice;
    }

    public String getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(String lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getChange24() {
        return change24;
    }

    public void setChange24(String change24) {
        this.change24 = change24;
    }

}
