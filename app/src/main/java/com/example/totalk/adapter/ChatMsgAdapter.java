package com.example.totalk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.totalk.R;
import com.example.totalk.entities.ChatEvt;

import java.util.List;

public class ChatMsgAdapter extends ArrayAdapter {
    private int resourceId;

    public ChatMsgAdapter(Context context, int resource, List<ChatEvt> list) {
        super(context, resource, list);
        this.resourceId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatEvt chatEvt = (ChatEvt) getItem(position); // 获取当前项的Fruit实例
        int resource;
        if(chatEvt.getLayout()=='L'){
            resource=R.layout.item_chat_left;
        }else{
            resource=R.layout.item_chat_right;
        }
        View view = LayoutInflater.from(getContext()).inflate(resource, null);//实例化一个对象
        TextView userName = (TextView) view.findViewById(R.id.chat_msg);//获取该布局内的文本视图
        userName.setText(chatEvt.getMessage());//为文本视图设置文本内容
        return view;
    }
}
