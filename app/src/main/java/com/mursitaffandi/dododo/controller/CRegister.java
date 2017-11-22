package com.mursitaffandi.dododo.controller;

import com.mursitaffandi.dododo.ApplicationBase;
import com.mursitaffandi.dododo.model.MRegister;
import com.mursitaffandi.dododo.network.SRegister;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mursitaffandi on 22/11/17.
 */

public class CRegister {
    private Progres event = new Progres();
    private EventBus eventBus = ApplicationBase.getInstance().getEventBus();

    public void getBaking() {
        SRegister apiService =
                SRegister.client.create(SRegister.class);
        Call<MRegister> listBakingCall = apiService.getJsonMRegister();
        listBakingCall.enqueue(new Callback<MRegister>() {
            @Override
            public void onResponse(Call<MRegister> call, Response<MRegister> response) {
                event.setSuccess(true);
                event.setMessage(response.message());
                MRegister mregister = new MultiBaking(response.body());
                event.setBakings(multiBaking);
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
