package com.mursitaffandi.dododo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.mursitaffandi.dododo.R;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        TextView tvDokter = (TextView) findViewById(R.id.tv_dokter);
        String dokter = getIntent().getStringExtra("parse_dokter");
        String tanggal = getIntent().getStringExtra("parse_tanggal");
        tvDokter.setText(dokter);
        TextView tvTanggal = (TextView) findViewById(R.id.tv_tanggal);
        tvTanggal.setText(tanggal);

    }
}
