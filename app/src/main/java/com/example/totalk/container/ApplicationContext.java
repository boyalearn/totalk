package com.example.totalk.container;

import com.example.totalk.entities.ChatEvt;
import com.example.totalk.entities.UserEvt;

import org.jivesoftware.smack.packet.Stanza;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {

    /**当前登录用户信息*/
    private static UserEvt currentLoginUser=new UserEvt();

    public static Map<String,ArrayList<ChatEvt>> CHAT_LIST_INFO=new ConcurrentHashMap<String,ArrayList<ChatEvt>>();

    public static Map<String,UserEvt> USER_LIST=new ConcurrentHashMap<String,UserEvt>();
    /**好友请求相关未处理消息*/
    private static Set<Stanza> unDellMessage=new HashSet<Stanza>();
    /**好友请求相关处理消息*/
    private static Set<Stanza> dellMessage=new HashSet<Stanza>();

    public static Set<Stanza> getUnDellMessage() {
        return unDellMessage;
    }

    public static UserEvt getCurrentLoginUser() {
        return currentLoginUser;
    }
}
