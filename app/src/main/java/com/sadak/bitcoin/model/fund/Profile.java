
package com.sadak.bitcoin.model.fund;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile implements Serializable
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
    private final static long serialVersionUID = -6304245860108751138L;

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
