package com.mursitaffandi.dododo.model;

/**
 * Created by mursitaffandi on 22/11/17.
 */

public class SMSContent {
    String strContent;
    String strSender;
    Long strTime;

    public SMSContent(String strContent, String strSender, Long strTime) {
        this.strContent = strContent;
        this.strSender = strSender;
        this.strTime = strTime;
    }

    public String getStrContent() {
        return strContent;
    }

    public void setStrContent(String strContent) {
        this.strContent = strContent;
    }

    public String getStrSender() {
        return strSender;
    }

    public void setStrSender(String strSender) {
        this.strSender = strSender;
    }

    public Long getStrTime() {
        return strTime;
    }

    public void setStrTime(Long strTime) {
        this.strTime = strTime;
    }
}
