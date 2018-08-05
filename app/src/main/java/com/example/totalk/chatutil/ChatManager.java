package com.example.totalk.chatutil;

import android.util.Log;

import com.example.totalk.container.ApplicationContainer;
import com.example.totalk.entities.UserEvt;
import com.example.totalk.network.NetWorkManager;

import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.roster.RosterGroup;
import org.jivesoftware.smackx.vcardtemp.packet.VCard;
import org.jxmpp.jid.BareJid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class ChatManager {
    public static List<UserEvt> getGroupFriends(){
        NetWorkManager network=NetWorkManager.getInstance();
        Roster roster = Roster.getInstanceFor(network.getConnection());
        Collection<RosterGroup> entries = roster.getGroups();
        List<UserEvt> userList=new ArrayList<UserEvt>();
        for(RosterGroup entry:entries){
            UserEvt userEvt=new UserEvt();
            userEvt.setUserName(entry.getName());
            userList.add(userEvt);
            List<RosterEntry> users=entry.getEntries();
            for(RosterEntry user:users){
                UserEvt uEvt=new UserEvt();
                uEvt.setUserName(user.getJid().toString());
                userList.add(uEvt);
            }
        }
        return userList;
    }
    public static List<UserEvt> getFriends() throws Exception {
        NetWorkManager network=NetWorkManager.getInstance();
        Roster roster = Roster.getInstanceFor(network.getConnection());
        Set<RosterEntry> entries = roster.getEntries();
        List<UserEvt> userList=new ArrayList<UserEvt>();
        for(RosterEntry entry:entries){
            UserEvt userEvt=new UserEvt();
            VCard vcard=new VCard();
            vcard.load(network.getConnection(), JidCreate.entityBareFrom(entry.getJid().toString()));
            userEvt.setAvatar(vcard.getAvatar());
            userEvt.setNickName(vcard.getNickName());
            userEvt.setJid(entry.getJid());
            Log.e("name",String.valueOf(entry.getName()));
            Log.e("type",entry.getType().name());
            userList.add(userEvt);
            ApplicationContainer.USER_LIST.put(userEvt.getJid().toString(),userEvt);
        }
        return userList;
    }


}
