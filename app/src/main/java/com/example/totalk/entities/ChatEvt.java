package com.example.totalk.entities;

public class ChatEvt {

    private String message;

    private String jid;

    private char layout;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public char getLayout() {
        return layout;
    }

    public void setLayout(char layout) {
        this.layout = layout;
    }
}
