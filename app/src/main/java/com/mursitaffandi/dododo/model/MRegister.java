package com.mursitaffandi.dododo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mursitaffandi on 22/11/17.
 */

public class MRegister implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.sukses);
        dest.writeString(this.pesan);
    }

    protected MRegister(Parcel in) {
        this.sukses = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.pesan = in.readString();
    }

    public static final Parcelable.Creator<MRegister> CREATOR = new Parcelable.Creator<MRegister>() {
        @Override
        public MRegister createFromParcel(Parcel source) {
            return new MRegister(source);
        }

        @Override
        public MRegister[] newArray(int size) {
            return new MRegister[size];
        }
    };
}
