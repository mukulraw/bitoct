
package com.sadak.bitcoin.model.tradehistory;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("Data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("Message")
    @Expose
    private String message;
    private final static long serialVersionUID = -6962406116611068768L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
