package com.mursitaffandi.dododo.event;


import com.mursitaffandi.dododo.model.MLogin;

/**
 * Created by mursitaffandi on 23/11/17.
 */

public class EVLogin {
    private String message;
    private MLogin mLogin;
    private boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MLogin getmLogin() {
        return mLogin;
    }

    public void setmLogin(MLogin mLogin) {
        this.mLogin = mLogin;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
