
package com.sadak.bitcoin.model.market;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable
{

    @SerializedName("MarketId")
    @Expose
    private String marketId;
    @SerializedName("MarketAssetCode")
    @Expose
    private String marketAssetCode;
    @SerializedName("MarketAssetName")
    @Expose
    private String marketAssetName;
    @SerializedName("LastPrice")
    @Expose
    private String lastPrice;
    @SerializedName("Change")
    @Expose
    private String change;
    @SerializedName("HighPrice")
    @Expose
    private String highPrice;
    @SerializedName("LowPrice")
    @Expose
    private String lowPrice;
    @SerializedName("Volume")
    @Expose
    private String volume;
    @SerializedName("BaseCurrencyID")
    @Expose
    private String baseCurrencyID;
    @SerializedName("Dollar")
    @Expose
    private String dollar;
    @SerializedName("Change24")
    @Expose
    private String change24;
    private final static long serialVersionUID = -3257813385633965112L;

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getMarketAssetCode() {
        return marketAssetCode;
    }

    public void setMarketAssetCode(String marketAssetCode) {
        this.marketAssetCode = marketAssetCode;
    }

    public String getMarketAssetName() {
        return marketAssetName;
    }

    public void setMarketAssetName(String marketAssetName) {
        this.marketAssetName = marketAssetName;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
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

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getBaseCurrencyID() {
        return baseCurrencyID;
    }

    public void setBaseCurrencyID(String baseCurrencyID) {
        this.baseCurrencyID = baseCurrencyID;
    }

    public String getDollar() {
        return dollar;
    }

    public void setDollar(String dollar) {
        this.dollar = dollar;
    }

    public String getChange24() {
        return change24;
    }

    public void setChange24(String change24) {
        this.change24 = change24;
    }

}
