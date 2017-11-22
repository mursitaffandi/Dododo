package com.mursitaffandi.dododo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.mursitaffandi.dododo.R;

import java.util.ArrayList;

public class AntrianKuActivity extends AppCompatActivity {
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antrian_ku);

        recycler = (RecyclerView) findViewById(R.id.recyclerView);
    }
}
