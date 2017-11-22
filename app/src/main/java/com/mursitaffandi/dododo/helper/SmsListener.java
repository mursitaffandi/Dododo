package com.mursitaffandi.dododo.helper;

import com.mursitaffandi.dododo.model.SMSContent;

/**
 * Created by mursitaffandi on 22/11/17.
 */

public interface SmsListener {
    public void messageReceived(SMSContent message);
}
