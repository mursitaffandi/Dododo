package com.mursitaffandi.dododo.event;

import com.mursitaffandi.dododo.model.MRegister;

/**
 * Created by mursitaffandi on 22/11/17.
 */

public class EVRegister {
    private String message;
    private boolean success;

    public MRegister getmRegister() {
        return mRegister;
    }

    public void setmRegister(MRegister mRegister) {
        this.mRegister = mRegister;
    }

    private MRegister mRegister;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
