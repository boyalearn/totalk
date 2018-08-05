package com.example.totalk.listener;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.totalk.MainActivity;
import com.example.totalk.R;
import com.example.totalk.network.NetWorkManager;

import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.Roster;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;

public class InvitationFriendListener implements View.OnClickListener{
    private MainActivity activity;

    public InvitationFriendListener(MainActivity activity){
        this.activity=activity;
    }

    @Override
    public void onClick(View view) {
        EditText nickNameText=activity.findViewById(R.id.nickName);
        String nickName=nickNameText.getText().toString();
        Presence subscription = new Presence(Presence.Type.subscribed);
        NetWorkManager network=NetWorkManager.getInstance();
        try {

            network.getConnection().sendStanza(subscription);
            Roster.getInstanceFor(network.getConnection()).createEntry(JidCreate.entityBareFrom(nickName + "@" + network.getConnection().getServiceName()), nickName,
                    new String[]{"myfriends"});
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
