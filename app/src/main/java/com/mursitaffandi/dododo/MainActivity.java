package com.mursitaffandi.dododo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_informasi,btn_penjadwalan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btn_informasi = (Button) findViewById(R.id.btn_informasi);
        btn_penjadwalan = (Button) findViewById(R.id.btn_penjadwalan);

        btn_penjadwalan.setOnClickListener(this);
        btn_informasi.setOnClickListener(this);
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
