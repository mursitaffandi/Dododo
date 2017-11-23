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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mursitaffandi.dododo.R;
import com.mursitaffandi.dododo.util.ConfigUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    send_numberOTP();
}

    private void send_numberOTP() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigUrl.BASE_URL + "send", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("dfdf",response);
                    JSONArray jsonArr = new JSONArray(response);
                    JSONObject jsonOb = jsonArr.getJSONObject(0);
                    boolean isSuccec = jsonOb.getBoolean("sukses");
                    if (isSuccec){
                        CountDownTimer Count = new CountDownTimer(60000, 1000) {
                            public void onTick(long millisUntilFinished) {
                                tv_timer.setText(+ millisUntilFinished / 1000 +"detik lagi");
                            }

                            public void onFinish() {
                                tv_timer.setVisibility(View.INVISIBLE);
                                btn_resend_code.setVisibility(View.VISIBLE);
                            }
                        };
                        Count.start();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("no_hp", getIntent().getStringExtra("no_hp"));
                return param;
            }
        };
        requestQueue.add(stringRequest);
        Log.d("links",ConfigUrl.BASE_URL+"send");
        Log.d("no_hp",getIntent().getStringExtra("no_hp"));
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
