package com.dream71.android.barchartexample.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PieStatistics {

    @SerializedName("assigned")
    @Expose
    private Assigned assigned;
    @SerializedName("opened")
    @Expose
    private Opened opened;
    @SerializedName("in-progress")
    @Expose
    private InProgress inProgress;
    @SerializedName("completed")
    @Expose
    private Complete complete;
    @SerializedName("done")
    @Expose
    private Done done;
    @SerializedName("rejected")
    @Expose
    private Rejected rejected;
    @SerializedName("expired")
    @Expose
    private Expired expired;

    public Assigned getAssigned() {
        return assigned;
    }

    public void setAssigned(Assigned assigned) {
        this.assigned = assigned;
    }

    public Opened getOpened() {
        return opened;
    }

    public void setOpened(Opened opened) {
        this.opened = opened;
    }

    public InProgress getInProgress() {
        return inProgress;
    }

    public void setInProgress(InProgress inProgress) {
        this.inProgress = inProgress;
    }

    public Complete getComplete() {
        return complete;
    }

    public void setComplete(Complete complete) {
        this.complete = complete;
    }

    public Done getDone() {
        return done;
    }

    public void setDone(Done done) {
        this.done = done;
    }

    public Rejected getRejected() {
        return rejected;
    }

    public void setRejected(Rejected rejected) {
        this.rejected = rejected;
    }

    public Expired getExpired() {
        return expired;
    }

    public void setExpired(Expired expired) {
        this.expired = expired;
    }
}
