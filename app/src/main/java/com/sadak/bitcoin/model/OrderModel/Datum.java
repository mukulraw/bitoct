
package com.sadak.bitcoin.model.OrderModel;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable
{

    @SerializedName("Sno")
    @Expose
    private String sno;
    @SerializedName("tickerid")
    @Expose
    private String tickerid;
    @SerializedName("tradetype")
    @Expose
    private String tradetype;
    @SerializedName("ticker")
    @Expose
    private String ticker;
    @SerializedName("MarketId")
    @Expose
    private String marketId;
    @SerializedName("Market")
    @Expose
    private String market;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("volume")
    @Expose
    private Integer volume;
    @SerializedName("btcvalue")
    @Expose
    private Double btcvalue;
    @SerializedName("Fee")
    @Expose
    private String fee;
    @SerializedName("NetTotal")
    @Expose
    private Double netTotal;
    private final static long serialVersionUID = 3114421169000339228L;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getTickerid() {
        return tickerid;
    }

    public void setTickerid(String tickerid) {
        this.tickerid = tickerid;
    }

    public String getTradetype() {
        return tradetype;
    }

    public void setTradetype(String tradetype) {
        this.tradetype = tradetype;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Double getBtcvalue() {
        return btcvalue;
    }

    public void setBtcvalue(Double btcvalue) {
        this.btcvalue = btcvalue;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public Double getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(Double netTotal) {
        this.netTotal = netTotal;
    }

}
