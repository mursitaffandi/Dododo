package com.mursitaffandi.dododo.view;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mursitaffandi.dododo.R;

import java.util.ArrayList;
import java.util.List;

public class OTPActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edt_otp1;
    TextView tv_timer;
    Button btn_konfirmasi,btn_resend_code;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        initView();

        // TODO CountDown

        if (checkAndRequestPermissions()) {}
        countDown();

    }
public void countDown(){
    btn_resend_code.setVisibility(View.INVISIBLE);
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

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase("otp")) {
                final String message = intent.getStringExtra("message");

                edt_otp1.setText(message);
            }
        }
    };

    private  boolean checkAndRequestPermissions() {
        int permissionSendMessage = ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);
        int receiveSMS = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        int readSMS = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (receiveSMS != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.RECEIVE_MMS);
        }
        if (readSMS != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_SMS);
        }
        if (permissionSendMessage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.SEND_SMS);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onResume() {
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("otp"));
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }
}
