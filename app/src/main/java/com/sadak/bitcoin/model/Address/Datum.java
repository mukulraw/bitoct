
package com.sadak.bitcoin.model.Address;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable
{

    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("qr")
    @Expose
    private String qr;
    private final static long serialVersionUID = 2107236460937492954L;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

}
