package com.mursitaffandi.dododo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mursitaffandi.dododo.ApplicationBase;
import com.mursitaffandi.dododo.R;
import com.mursitaffandi.dododo.util.Session;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_informasi,btn_penjadwalan,btn_cek_antrian;
Session session = ApplicationBase.getInstance().getSession();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!session.checkLogin()){
           // finish();
        }
        Log.d("token", String.valueOf(session.getUserDetails().get("token")));
        initView();
    }


    private void initView() {
        btn_informasi = (Button) findViewById(R.id.btn_informasi);
        btn_penjadwalan = (Button) findViewById(R.id.btn_penjadwalan);
        btn_cek_antrian = (Button) findViewById(R.id.btn_cek_antrian);

        btn_penjadwalan.setOnClickListener(this);
        btn_informasi.setOnClickListener(this);
        btn_cek_antrian.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_informasi:
                break;
            case R.id.btn_penjadwalan:
                startActivity(new Intent(MainActivity.this,JadwalCheckUpActivity.class));
                break;
        }


    }
}
