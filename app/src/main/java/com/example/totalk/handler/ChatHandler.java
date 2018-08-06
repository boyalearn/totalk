package com.example.totalk.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.example.totalk.MainActivity;
import com.example.totalk.container.ApplicationContainer;
import com.example.totalk.container.CurChatContainer;
import com.example.totalk.entities.ChatEvt;
import com.example.totalk.state.MainState;

import org.jivesoftware.smack.packet.Presence;

import java.util.List;

import static com.example.totalk.container.CurChatContainer.adapter;

public class ChatHandler extends Handler {
    private MainActivity activity;
    public ChatHandler(MainActivity activity) {
        this.activity=activity;
    }

    // 子类必须重写此方法，接受数据
    @Override
    public void handleMessage(Message msg) {
        // TODO Auto-generated method stub
        Log.d("MyHandler","handleMessage.....");
        super.handleMessage(msg);
        // 此处可以更新UI
        if(msg.obj instanceof Presence){
            Presence presence=(Presence)msg.obj;
            /**如果登录成功*/
            if("available".equals(presence.getType().name())){
                try {
                    activity.setState(new MainState());
                } catch (Exception e) {
                    Log.e(this.getClass().getName(),e.getMessage());
                }

            }
            Log.e("iq",msg.obj.toString());
        }
        //收到聊天信息更新UI
        if(msg.obj instanceof org.jivesoftware.smack.packet.Message){
            org.jivesoftware.smack.packet.Message message =(org.jivesoftware.smack.packet.Message)msg.obj;
            String jid=message.getFrom().asBareJid().toString();
            Log.e("chatMsg",message.getFrom().toString());
            ChatEvt chatEvt=new ChatEvt();
            chatEvt.setLayout('L');
            chatEvt.setMessage(message.getBody());
            List<ChatEvt> list=ApplicationContainer.CHAT_LIST_INFO.get(jid);
            if(null!=list){
                list.add(chatEvt);
            }
            if(null!=CurChatContainer.currentUser&&jid.equals(CurChatContainer.currentUser)){
                adapter.notifyDataSetChanged();
                CurChatContainer.listView.setSelection(CurChatContainer.chatList.size()-1);
            }

        }

    }
}
