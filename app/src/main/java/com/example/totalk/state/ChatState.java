package com.example.totalk.state;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.totalk.MainActivity;
import com.example.totalk.R;
import com.example.totalk.adapter.ChatMsgAdapter;
import com.example.totalk.container.ApplicationContext;
import com.example.totalk.container.CurChatContainer;
import com.example.totalk.entities.ChatEvt;
import com.example.totalk.listener.ReturnMainListener;
import com.example.totalk.listener.SendClickListener;
import com.example.totalk.state.inteface.ActionState;

import java.util.List;

public class ChatState implements ActionState {

    private View view;

    private int position;

    private long id;

    public ChatState(View view,int position,long id){
        this.view=view;
        this.id=id;
        this.position=position;
    }
    @Override
    public void reflash(MainActivity activity) {
        //通过view获取其内部的组件，进而进行操作
        String nickName = (String) ((TextView)view.findViewById(R.id.nickName)).getText();
        String jid = (String) ((TextView)view.findViewById(R.id.jid)).getText();
        //大多数情况下，position和id相同，并且都从0开始
        String showText = "点击第" + position + "项，文本内容为：" + nickName + "，ID为：" + id;
        Toast.makeText(activity, showText, Toast.LENGTH_LONG).show();
        activity.setContentView(R.layout.acitvity_chat);

        List<ChatEvt> list=ApplicationContext.CHAT_LIST_INFO.get(jid);
        ChatMsgAdapter adapter = new ChatMsgAdapter(activity, R.layout.item_chat_right, list);
        ListView listView = (ListView) activity.findViewById(R.id.chat_list);
        listView.setAdapter(adapter);
        TextView chatUser=(TextView)activity.findViewById(R.id.chat_user);
        TextView curJid=(TextView)activity.findViewById(R.id.curJid);
        chatUser.setText(nickName);
        curJid.setText(jid);
        Button sendBtn=(Button)activity.findViewById(R.id.sendBtn);


        CurChatContainer.chatList=list;
        CurChatContainer.currentUser=jid;
        CurChatContainer.adapter=adapter;
        CurChatContainer.listView=listView;

        sendBtn.setOnClickListener(new SendClickListener(activity,list,adapter));

        Button returnBtn=(Button)activity.findViewById(R.id.returnBtn);
        returnBtn.setOnClickListener(new ReturnMainListener(activity));
        adapter.notifyDataSetChanged();
        listView.setSelection(CurChatContainer.chatList.size()-1);
    }
}
