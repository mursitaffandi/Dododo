package com.mursitaffandi.dododo;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OTPActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edt_otp1;
    TextView tv_timer;
    Button btn_konfirmasi,btn_resend_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        initView();

        btn_resend_code.setVisibility(View.INVISIBLE);
        // TODO CountDown
        CountDownTimer Count = new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                tv_timer.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                tv_timer.setVisibility(View.INVISIBLE);
                btn_resend_code.setVisibility(View.VISIBLE);
            }
        };
        Count.start();

    }

    private void initView() {
        edt_otp1 = (EditText) findViewById(R.id.edt_otp1);
        tv_timer = (TextView) findViewById(R.id.tv_timer);
        btn_resend_code = (Button) findViewById(R.id.btn_resend_code);
        btn_konfirmasi = (Button) findViewById(R.id.btn_konfirmasi);

        btn_resend_code.setOnClickListener(this);
        btn_konfirmasi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_konfirmasi:

                String kodeOtp = edt_otp1.getText().toString();
                boolean isEmptyFields= false;
                if (TextUtils.isEmpty(kodeOtp)){
                    isEmptyFields = true;
                    edt_otp1.setError("Wajib diisi");
                }

                if (!isEmptyFields) {
                    // TODO Verifikasi OTP
                    startActivity(new Intent(OTPActivity.this, LoginActivity.class));
                }
                break;
            case R.id.btn_resend_code:
                break;
        }
    }
}
