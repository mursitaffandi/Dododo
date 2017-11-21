package com.mursitaffandi.dododo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences loginPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loginPref = getSharedPreferences(LoginActivity.LOGIN_PREF, MODE_PRIVATE);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // jika tidak ada token masuk ke loginActivity, jika ada token masuk ke Main
                if(!loginPref.contains("token")){
                    Intent in = new Intent(SplashActivity.this, LoginActivity.class);
                    in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(in);
                    finish();
                }else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        }, 3000L);
    }
}
