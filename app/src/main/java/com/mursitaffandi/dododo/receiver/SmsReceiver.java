package com.mursitaffandi.dododo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.SmsMessage;
import android.util.Log;
import com.mursitaffandi.dododo.model.SMSContent;

/**
 * Created by mursitaffandi on 21/11/17.
 */

public class SmsReceiver extends BroadcastReceiver {
    private static final String TAG = SmsReceiver.class.getSimpleName();

    private static SmsListener mListener;
    private SMSContent contentSMS;

    public static void bindListener(SmsListener listener) {
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (Object aPdusObj : pdusObj) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) aPdusObj);
                    String senderAddress = currentMessage.getDisplayOriginatingAddress();
                    String message = currentMessage.getDisplayMessageBody();

                    Log.e(TAG, "Received SMS: " + message + ", Sender: " + senderAddress);

                    // if the SMS is not from our gateway, ignore the message
                    if (!senderAddress.toLowerCase().contains("001")) {
                        return;
                    }

                    // verification code from sms
                    String verificationCode = getVerificationCode(message);

                    Log.e(TAG, "OTP received: " + verificationCode);

                    Intent smsIntent = new Intent("otp");
                    smsIntent.putExtra("message",verificationCode);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(smsIntent);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }

    }

    private String getVerificationCode(String message) {
        String[] arr = message.split(" ");

        for ( String ss : arr) {
            System.out.println(ss);
        }
        return arr[1];
    }
}
