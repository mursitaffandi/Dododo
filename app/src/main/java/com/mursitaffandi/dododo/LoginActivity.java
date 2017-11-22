package com.mursitaffandi.dododo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edt_email, edt_password;
    Button btn_login,btn_link_register;
    public static final String LOGIN_PREF = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_password = (EditText) findViewById(R.id.edt_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_link_register = (Button) findViewById(R.id.btn_link_register);
        btn_login.setOnClickListener(this);
        btn_link_register.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_login :
                String email = edt_email.getText().toString();
                String password = edt_password.getText().toString();

                boolean isEmptyFields= false;
                if (TextUtils.isEmpty(email)){
                    isEmptyFields = true;
                    edt_email.setError("Wajib diisi");
                }

                if (TextUtils.isEmpty(password)){
                    isEmptyFields = true;
                    edt_password.setError("Wajib diisi");
                }

                if (!isEmptyFields){
                    // TODO Cek email & password
                    if (email.equals("sandec") && password.equals("quepi")){
                        SharedPreferences sp = getSharedPreferences(LOGIN_PREF, MODE_PRIVATE);

                        SharedPreferences.Editor spEdit = sp.edit();

                        spEdit.putString("email",email);
                        spEdit.putString("token","sdkj23h4r9dfhakse4jhsadf9813e23rhkj");

                        spEdit.apply();

                        Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                        Intent in =new Intent(this,MainActivity.class);
                        // khusus login, menghapus activity sebelumnya
                        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(in);

                        // TODO setting MainActivity
                    }
                }
                break;
            case R.id.btn_link_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }
}
