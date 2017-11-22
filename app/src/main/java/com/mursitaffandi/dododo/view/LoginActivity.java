package com.mursitaffandi.dododo.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mursitaffandi.dododo.ApplicationBase;
import com.mursitaffandi.dododo.R;
import com.mursitaffandi.dododo.event.EVLogin;
import com.mursitaffandi.dododo.event.EVRegister;
import com.mursitaffandi.dododo.model.MLogin;
import com.mursitaffandi.dododo.model.MRegister;
import com.mursitaffandi.dododo.presenter.CLogin;
import com.mursitaffandi.dododo.presenter.CRegister;
import com.mursitaffandi.dododo.util.Session;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edt_email, edt_password;
    Button btn_login,btn_link_register;
    public static final String LOGIN_PREF = "";

    private CLogin contollerLogin;
    EventBus eventBus = ApplicationBase.getInstance().getEventBus();

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
    String password, email;
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_login :
                email = edt_email.getText().toString();
                password = edt_password.getText().toString();

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
                    contollerLogin = new CLogin(email, password);
                    contollerLogin.sendLogin();
                }
                break;
            case R.id.btn_link_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        eventBus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }
Session session = ApplicationBase.getInstance().getSession();
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getLogin(EVLogin event) {
        final MLogin mLogin = event.getmLogin();
        if (event.isSuccess()) {
            session.createUserLoginSession(mLogin.getToken(), email);
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("data_login",mLogin);
            // khusus login, menghapus activity sebelumnya
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        } else {
            Toast.makeText(this, event.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
