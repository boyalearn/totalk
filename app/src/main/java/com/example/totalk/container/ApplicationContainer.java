package com.example.totalk.container;

import com.example.totalk.entities.ChatEvt;
import com.example.totalk.entities.UserEvt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContainer {
    public static Map<String,ArrayList<ChatEvt>> CHAT_LIST_INFO=new ConcurrentHashMap<String,ArrayList<ChatEvt>>();

    public static Map<String,UserEvt> USER_LIST=new ConcurrentHashMap<String,UserEvt>();;
}
