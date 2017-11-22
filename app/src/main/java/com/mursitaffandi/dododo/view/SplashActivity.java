package com.mursitaffandi.dododo.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mursitaffandi.dododo.R;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // jika tidak ada token masuk ke loginActivity, jika ada token masuk ke Main

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();

            }
        }, 3000L);
    }
}
