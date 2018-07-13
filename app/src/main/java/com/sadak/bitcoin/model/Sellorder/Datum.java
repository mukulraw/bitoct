
package com.sadak.bitcoin.model.Sellorder;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable
{

    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("basevalue")
    @Expose
    private String basevalue;
    @SerializedName("rowtotalbase")
    @Expose
    private String rowtotalbase;
    @SerializedName("totalCurr")
    @Expose
    private String totalCurr;
    private final static long serialVersionUID = 6322428884804410043L;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getBasevalue() {
        return basevalue;
    }

    public void setBasevalue(String basevalue) {
        this.basevalue = basevalue;
    }

    public String getRowtotalbase() {
        return rowtotalbase;
    }

    public void setRowtotalbase(String rowtotalbase) {
        this.rowtotalbase = rowtotalbase;
    }

    public String getTotalCurr() {
        return totalCurr;
    }

    public void setTotalCurr(String totalCurr) {
        this.totalCurr = totalCurr;
    }

}
