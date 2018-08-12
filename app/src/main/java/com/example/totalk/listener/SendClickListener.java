package com.example.totalk.listener;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.totalk.MainActivity;
import com.example.totalk.R;
import com.example.totalk.adapter.ChatMsgAdapter;
import com.example.totalk.container.ApplicationContext;
import com.example.totalk.entities.ChatEvt;
import com.example.totalk.network.NetWorkManager;

import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.packet.Message;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.impl.JidCreate;

import java.util.List;

public class SendClickListener implements View.OnClickListener {
    private MainActivity activity;
    private ChatMsgAdapter adapter;
    private List<ChatEvt> list;
    public SendClickListener(MainActivity activity,List<ChatEvt> list,ChatMsgAdapter adapter){
        this.activity=activity;
        this.adapter=adapter;
        this.list=list;
    }

    @Override
    public void onClick(View view) {
        NetWorkManager network=NetWorkManager.getInstance();
        ChatManager chatManager=ChatManager.getInstanceFor(network.getConnection());
        try {
            TextView textView=(TextView) activity.findViewById(R.id.curJid);
            EntityBareJid jid = JidCreate.entityBareFrom(textView.getText().toString());
            Chat chat=chatManager.chatWith(jid);
            Message message=new Message();
            EditText editText=(EditText) activity.findViewById(R.id.sendMessage);
            message.setBody(editText.getText().toString());
            chat.send(message);
            ChatEvt chatEvt=new ChatEvt();
            chatEvt.setMessage(editText.getText().toString());
            chatEvt.setJid(ApplicationContext.getCurrentLoginUser().getJid().toString());
            list.add(chatEvt);
            adapter.notifyDataSetChanged();
            //列表显示最后一条数据
            ListView listview=(ListView)activity.findViewById(R.id.chat_list) ;
            listview.setSelection(list.size()-1);
            editText.setText("");
        } catch (Exception e) {
            Log.e("send",e.getMessage());
        }

    }
}
