package com.example.totalk.entities;

import org.jxmpp.jid.BareJid;

public class UserEvt {
    private BareJid jid;
    private String userName;
    private String passWord;
    private String nickName;
    private byte[] avatar;
    public BareJid getJid() {
        return jid;
    }

    public void setJid(BareJid jid) {
        this.jid = jid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }
}
