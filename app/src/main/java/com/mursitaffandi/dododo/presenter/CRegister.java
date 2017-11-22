package com.mursitaffandi.dododo.presenter;

import com.mursitaffandi.dododo.ApplicationBase;
import com.mursitaffandi.dododo.model.MRegister;
import com.mursitaffandi.dododo.network.SRegister;
import com.mursitaffandi.dododo.event.EVRegister;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mursitaffandi on 22/11/17.
 */

public class CRegister {
    private EVRegister event = new EVRegister();
    private EventBus eventBus = ApplicationBase.getInstance().getEventBus();
    private Map<String, String> query = new HashMap<>();

    public CRegister(String nama,
                     String email,
                     String no_hp,
                     String tgl_lahir,
                     String password) {
        this.query.put("nama", nama);
        this.query.put("email", email);
        this.query.put("no_hp", no_hp);
        this.query.put("tgl_lahir", tgl_lahir);
        this.query.put("password", password);
    }

    public void sendRegister() {
        SRegister apiService =
                SRegister.client.create(SRegister.class);
        Call<MRegister> listBakingCall = apiService.getJsonMRegister(query);
        listBakingCall.enqueue(new Callback<MRegister>() {
            @Override
            public void onResponse(Call<MRegister> call, Response<MRegister> response) {
                MRegister mregister = response.body();
                if (mregister.getSukses()){
                    event.setSuccess(true);
                    event.setMessage(mregister.getPesan());
                } else {
                    event.setSuccess(false);
                    event.setMessage(mregister.getPesan());
                }

                event.setmRegister(mregister);
                eventBus.post(event);
            }

            @Override
            public void onFailure(Call<MRegister> call, Throwable t) {
                event.setMessage(t.getMessage());
                event.setSuccess(false);
                eventBus.post(event);
            }
        });
    }
}
