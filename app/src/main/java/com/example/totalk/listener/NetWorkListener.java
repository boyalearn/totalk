package com.example.totalk.listener;

import android.os.Message;
import android.util.Log;
import com.example.totalk.service.ChatService;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Stanza;

public class NetWorkListener implements StanzaListener {
    private ChatService.ChatActivityListener listener;
    public NetWorkListener(ChatService.ChatActivityListener listener){
        this.listener=listener;
    }
    @Override
    public void processStanza(Stanza packet)  {
        Log.e("onNetWorkMessage",packet.toXML().toString());
        Message msg = new Message();
        msg.obj = packet;
        listener.getChatHandler().sendMessage(msg);
    }
}
