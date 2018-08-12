package com.example.totalk.message;

import android.util.Log;

import com.example.totalk.container.ApplicationContext;

import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;

public class MessageDellCenter {

    private final static int FRIEND=1;

    public void dellMessage(Stanza packet){
        switch (getType(packet)){
            case FRIEND:doFriendStateMessage(packet);break;
        }
    }
    public void doFriendStateMessage(Stanza packet){
        ApplicationContext.getUnDellMessage().add(packet);
    }
    private int getType(Stanza packet){
        if(packet instanceof Presence){
            Presence presence=(Presence)packet;
            if(presence.getType().equals(Presence.Type.subscribe)
                    ||presence.getType().equals(Presence.Type.subscribed)
                    ||presence.getType().equals(Presence.Type.unsubscribe)
                    ||presence.getType().equals(Presence.Type.unsubscribed)){
                return FRIEND;
            }

        }
        return 0;
    }
    private void log(String msg){
        Log.e("MessageDellCenter",msg);
    }
}
