package com.mursitaffandi.dododo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mursitaffandi on 22/11/17.
 */

public class MRegister {
    @SerializedName("sukses")
    @Expose
    private Boolean sukses;
    @SerializedName("pesan")
    @Expose
    private String pesan;

    /**
     * No args constructor for use in serialization
     *
     */
    public MRegister() {
    }

    /**
     *
     * @param sukses
     * @param pesan
     */
    public MRegister(Boolean sukses, String pesan) {
        super();
        this.sukses = sukses;
        this.pesan = pesan;
    }

    public Boolean getSukses() {
        return sukses;
    }

    public void setSukses(Boolean sukses) {
        this.sukses = sukses;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
}
