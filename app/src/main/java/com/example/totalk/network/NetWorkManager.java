package com.example.totalk.network;

import com.example.totalk.listener.NetWorkListener;
import com.example.totalk.service.ChatService;
import com.example.totalk.state.IActivity;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import java.net.InetAddress;

public class NetWorkManager {

    private static NetWorkManager instance=new NetWorkManager();

    public static NetWorkManager getInstance(){
        return instance;
    }

    private  XMPPTCPConnection connection;

    public XMPPTCPConnection getConnection() {
        return connection;
    }


    public void init() throws Exception{
        SmackConfiguration.DEBUG = true;
        XMPPTCPConnectionConfiguration.Builder config = XMPPTCPConnectionConfiguration.builder();
        InetAddress address= InetAddress.getByName("192.168.0.101");
        config.setHostAddress(address);
        config.setXmppDomain("zouhuixing-pc");
        config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
        config.setSendPresence(true);
        config.setDebuggerEnabled(true);
        config.setCompressionEnabled(false);
        connection = new XMPPTCPConnection(config.build());
    }
    public void connect() throws Exception{
        connection.connect();
    }

    public void addNetWorkListener(IActivity listener){
        connection.addAsyncStanzaListener(new NetWorkListener(listener),null);


    }

    public void login(String userName,String passWord) throws Exception{
        connection.login(userName,passWord);
    }
}
