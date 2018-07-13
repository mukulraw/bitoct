
package com.sadak.bitcoin.model.Buy;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable
{

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("toamount_tot")
    @Expose
    private String toamountTot;
    @SerializedName("tocurrency")
    @Expose
    private String tocurrency;
    @SerializedName("fromamount")
    @Expose
    private String fromamount;
    @SerializedName("fromcurrency")
    @Expose
    private String fromcurrency;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("fee")
    @Expose
    private String fee;
    @SerializedName("toamount")
    @Expose
    private String toamount;
    @SerializedName("isok")
    @Expose
    private String isok;
    private final static long serialVersionUID = -6229299068123917842L;

    public String getKey() {
        return key;
    }

    public void setKey(String key1) {
        this.toamountTot = key;
    }

    public String getToamountTot() {
        return toamountTot;
    }

    public void setToamountTot(String toamountTot) {
        this.toamountTot = toamountTot;
    }

    public String getTocurrency() {
        return tocurrency;
    }

    public void setTocurrency(String tocurrency) {
        this.tocurrency = tocurrency;
    }

    public String getFromamount() {
        return fromamount;
    }

    public void setFromamount(String fromamount) {
        this.fromamount = fromamount;
    }

    public String getFromcurrency() {
        return fromcurrency;
    }

    public void setFromcurrency(String fromcurrency) {
        this.fromcurrency = fromcurrency;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getToamount() {
        return toamount;
    }

    public void setToamount(String toamount) {
        this.toamount = toamount;
    }

    public String getIsok() {
        return isok;
    }

    public void setIsok(String isok) {
        this.isok = isok;
    }

}
