
package com.sadak.bitcoin.model.tradehistory;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable
{

    @SerializedName("tradetype")
    @Expose
    private String tradetype;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("volume")
    @Expose
    private String volume;
    @SerializedName("btcvalue")
    @Expose
    private String btcvalue;
    @SerializedName("tickerid")
    @Expose
    private Integer tickerid;
    @SerializedName("ticker")
    @Expose
    private String ticker;
    @SerializedName("basecurrency")
    @Expose
    private String basecurrency;
    @SerializedName("basevolume")
    @Expose
    private String basevolume;
    private final static long serialVersionUID = -8296565426074074868L;

    public String getTradetype() {
        return tradetype;
    }

    public void setTradetype(String tradetype) {
        this.tradetype = tradetype;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getBtcvalue() {
        return btcvalue;
    }

    public void setBtcvalue(String btcvalue) {
        this.btcvalue = btcvalue;
    }

    public Integer getTickerid() {
        return tickerid;
    }

    public void setTickerid(Integer tickerid) {
        this.tickerid = tickerid;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getBasecurrency() {
        return basecurrency;
    }

    public void setBasecurrency(String basecurrency) {
        this.basecurrency = basecurrency;
    }

    public String getBasevolume() {
        return basevolume;
    }

    public void setBasevolume(String basevolume) {
        this.basevolume = basevolume;
    }

}
