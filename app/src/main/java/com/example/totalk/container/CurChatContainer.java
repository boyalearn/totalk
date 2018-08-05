package com.example.totalk.container;

import android.widget.ListView;

import com.example.totalk.adapter.ChatMsgAdapter;
import com.example.totalk.entities.ChatEvt;

import java.util.List;

public class CurChatContainer {
    public static List<ChatEvt> chatList;
    public static String currentUser;
    public static ChatMsgAdapter adapter;
    public static ListView listView;

}
