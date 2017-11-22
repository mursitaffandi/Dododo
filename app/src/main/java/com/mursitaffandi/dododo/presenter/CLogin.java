package com.mursitaffandi.dododo.presenter;

import com.mursitaffandi.dododo.ApplicationBase;
import com.mursitaffandi.dododo.event.EVLogin;
import com.mursitaffandi.dododo.event.EVRegister;
import com.mursitaffandi.dododo.model.MLogin;
import com.mursitaffandi.dododo.model.MRegister;
import com.mursitaffandi.dododo.network.SLogin;
import com.mursitaffandi.dododo.network.SRegister;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mursitaffandi on 23/11/17.
 */

public class CLogin {
    private EVLogin event = new EVLogin();
    private EventBus eventBus = ApplicationBase.getInstance().getEventBus();
    private Map<String, String> query = new HashMap<>();

    public CLogin(String no_hp, String password) {
        this.query.put("no_hp", no_hp);
        this.query.put("password", password);
    }

    public void sendLogin() {
        SLogin apiService =
                SLogin.client.create(SLogin.class);
        Call<MLogin> loginCall = apiService.getJsonMLogin(query);
        loginCall.enqueue(new Callback<MLogin>() {

            @Override
            public void onResponse(Call<MLogin> call, Response<MLogin> response) {
                MLogin mLogin = response.body();
                if (mLogin.getSukses()){
                    event.setSuccess(true);
                    event.setMessage(mLogin.getPesan());
                } else {
                    event.setSuccess(false);
                    event.setMessage(mLogin.getPesan());
                }

                event.setmLogin(mLogin);
                eventBus.post(event);
            }

            @Override
            public void onFailure(Call<MLogin> call, Throwable t) {
                event.setMessage(t.getMessage());
                event.setSuccess(false);
                eventBus.post(event);
            }
        });
    }
}
