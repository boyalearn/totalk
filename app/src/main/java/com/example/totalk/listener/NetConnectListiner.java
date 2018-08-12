package com.example.totalk.listener;

import android.os.Message;
import android.util.Log;

import com.example.totalk.chatutil.LoginType;
import com.example.totalk.message.MessageDellCenter;
import com.example.totalk.state.inteface.IActivity;

import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.XMPPConnection;

public class NetConnectListiner implements ConnectionListener {
    private IActivity listener;
    private MessageDellCenter messageDellCenter;
    public NetConnectListiner(IActivity listener){
        this.listener=listener;
        messageDellCenter=new MessageDellCenter();
    }
    @Override
    public void connected(XMPPConnection connection) {
        Log.e("connect","connected");
    }

    @Override
    public void authenticated(XMPPConnection connection, boolean resumed) {
        Message msg = new Message();
        msg.obj=LoginType.SUCCESS;
        listener.getChatHandler().sendMessage(msg);
        Log.e("connect","authenticated");
    }

    @Override
    public void connectionClosed() {
        Log.e("connect","connectionClosed");
    }

    @Override
    public void connectionClosedOnError(Exception e) {
        Log.e("connect","connectionClosedOnError");
    }

    @Override
    public void reconnectionSuccessful() {
        Log.e("connect","reconnectionSuccessful");
    }

    @Override
    public void reconnectingIn(int seconds) {
        Log.e("connect","reconnectingIn");
    }

    @Override
    public void reconnectionFailed(Exception e) {
        Log.e("connect","reconnectionFailed");
    }
}
