package com.mursitaffandi.dododo;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mursitaffandi.dododo.util.Session;

import org.greenrobot.eventbus.EventBus;


public class ApplicationBase extends Application {
    private static ApplicationBase instance;
    private Gson gson;
    private EventBus eventBus;

    public Session getSession() {
        return session;
    }

    private Session session;

    public ApplicationBase() {
        instance = this;
    }

    private SharedPreferences prefs;

    public static ApplicationBase getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createGson();
        createEventBus();
        createPreference();
        createSession();
    }

    private void createSession() {
        session = new Session(instance);
    }

    private void createPreference() {
        prefs = PreferenceManager.getDefaultSharedPreferences(instance);
    }

    private void createEventBus() {
        eventBus = EventBus.builder().logNoSubscriberMessages(false).sendNoSubscriberEvent(false).build();
    }

    private void createGson() {
        gson = new GsonBuilder().create();
    }

    public Gson getGson() {
        return gson;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public SharedPreferences getPrefs() {
        return prefs;
    }
}