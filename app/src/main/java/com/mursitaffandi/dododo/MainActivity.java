package com.mursitaffandi.dododo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv = (TextView)findViewById(R.id.tvHello);
        /*SmsReceiver.bindListener(new SmsListener() {
            @Override
            public void messageReceived(SMSContent message) {
                String kejam = message.getStrSender() + " Text " + message.getStrContent() + " time " + String.valueOf(message.getStrTime());
                Log.d("Number ", kejam);
                tv.setText(kejam);
                Toast.makeText(MainActivity.this,"Message: "+kejam,Toast.LENGTH_LONG).show();
            }
        });*/
    }
}
