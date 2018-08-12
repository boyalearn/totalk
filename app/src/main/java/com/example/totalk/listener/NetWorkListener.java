package com.example.totalk.listener;

import android.os.Message;
import android.util.Log;

import com.example.totalk.message.MessageDellCenter;
import com.example.totalk.state.inteface.IActivity;

import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Stanza;

public class NetWorkListener implements StanzaListener {
    private IActivity listener;
    private MessageDellCenter messageDellCenter;
    public NetWorkListener(IActivity listener){
        this.listener=listener;
        messageDellCenter=new MessageDellCenter();
    }
    @Override
    public void processStanza(Stanza packet)  {
        Log.e("onNetWorkMessage",packet.toXML().toString());
        Message msg = new Message();
        msg.obj = packet;
        listener.getChatHandler().sendMessage(msg);
        messageDellCenter.dellMessage(packet);
    }
}
