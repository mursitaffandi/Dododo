package com.mursitaffandi.dododo;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    private EditText edt_tgl, edt_no_hp, edt_nama, edt_email;
    private Button btn_daftar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        initViewOnClick();
    }

    private void initViewOnClick() {

    }

    @Override
    public void onClick(View view) {

    }
}
