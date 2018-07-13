
package com.sadak.bitcoin.model.Withdrawal;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable
{

    @SerializedName("toTxfee")
    @Expose
    private String toTxfee;
    @SerializedName("withdrawleft")
    @Expose
    private String withdrawleft;
    @SerializedName("sptoday")
    @Expose
    private String sptoday;
    @SerializedName("CurrencyBalance")
    @Expose
    private String currencyBalance;
    @SerializedName("maxAmount")
    @Expose
    private String maxAmount;
    private final static long serialVersionUID = 6150287041622440526L;

    public String getToTxfee() {
        return toTxfee;
    }

    public void setToTxfee(String toTxfee) {
        this.toTxfee = toTxfee;
    }

    public String getWithdrawleft() {
        return withdrawleft;
    }

    public void setWithdrawleft(String withdrawleft) {
        this.withdrawleft = withdrawleft;
    }

    public String getSptoday() {
        return sptoday;
    }

    public void setSptoday(String sptoday) {
        this.sptoday = sptoday;
    }

    public String getCurrencyBalance() {
        return currencyBalance;
    }

    public void setCurrencyBalance(String currencyBalance) {
        this.currencyBalance = currencyBalance;
    }

    public String getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(String maxAmount) {
        this.maxAmount = maxAmount;
    }

}
