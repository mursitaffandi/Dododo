package com.mursitaffandi.dododo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mursitaffandi on 23/11/17.
 */

public class MLogin implements Parcelable {

    @SerializedName("sukses")
    @Expose
    private Boolean sukses;
    @SerializedName("pesan")
    @Expose
    private String pesan;
    @SerializedName("token")
    @Expose
    private String token;

    /**
     * No args constructor for use in serialization
     *
     */
    public MLogin() {
    }

    /**
     *
     * @param sukses
     * @param pesan
     * @param token
     */
    public MLogin(Boolean sukses, String pesan, String token) {
        super();
        this.sukses = sukses;
        this.pesan = pesan;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.sukses);
        dest.writeString(this.pesan);
        dest.writeString(this.token);
    }

    protected MLogin(Parcel in) {
        this.sukses = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.pesan = in.readString();
        this.token = in.readString();
    }

    public static final Parcelable.Creator<MLogin> CREATOR = new Parcelable.Creator<MLogin>() {
        @Override
        public MLogin createFromParcel(Parcel source) {
            return new MLogin(source);
        }

        @Override
        public MLogin[] newArray(int size) {
            return new MLogin[size];
        }
    };
}
