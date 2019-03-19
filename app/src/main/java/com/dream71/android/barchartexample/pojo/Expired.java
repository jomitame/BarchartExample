package com.dream71.android.barchartexample.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Expired {

    @SerializedName("total_issue")
    @Expose
    private int totalIssue;
    @SerializedName("color")
    @Expose
    private String color;

    public int getTotalIssue() {
        return totalIssue;
    }

    public void setTotalIssue(int totalIssue) {
        this.totalIssue = totalIssue;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
