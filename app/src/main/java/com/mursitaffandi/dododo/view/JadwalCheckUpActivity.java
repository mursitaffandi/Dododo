package com.mursitaffandi.dododo.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.mursitaffandi.dododo.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class JadwalCheckUpActivity extends AppCompatActivity implements View.OnClickListener{

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private ArrayList<String> dokter,poli,jam_praktek;
    EditText edt_tgl_checkup;
    TextView nama;
    Button btn_daftar, btn_batal;
    Spinner spn_poli, spn_dokter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_check_up);

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        initView();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.list_poli, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_poli.setAdapter(adapter);


        ArrayAdapter<CharSequence> adapterDok = ArrayAdapter.createFromResource(this,R.array.list_dokter,
                android.R.layout.simple_spinner_item);
        adapterDok.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_dokter.setAdapter(adapterDok);
    }

    private void initView() {
        edt_tgl_checkup = (EditText) findViewById(R.id.edt_tgl_checkup);
        btn_batal = (Button) findViewById(R.id.btn_batal);
        btn_daftar = (Button) findViewById(R.id.btn_daftar);
        //nama = (TextView) findViewById(R.id.nama);
        spn_dokter = (Spinner) findViewById(R.id.spn_dokter);
        spn_poli = (Spinner) findViewById(R.id.spn_poli);

        edt_tgl_checkup.setOnClickListener(this);
        btn_daftar.setOnClickListener(this);
        btn_batal.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_batal:
                break;
            case R.id.btn_daftar:
          //      String snama = nama.toString();
                String tgl_checkup = edt_tgl_checkup.getText().toString();
                String poli = spn_poli.getSelectedItem().toString();
                String dokter = spn_dokter.getSelectedItem().toString();

                startActivity(new Intent(this,HistoryActivity.class));
                break;
            case R.id.edt_tgl_checkup:
                showDateDialog();
                break;
        }
    }


    // TODO showDatePicker
    private void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                edt_tgl_checkup.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}
