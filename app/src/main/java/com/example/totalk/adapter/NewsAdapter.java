package com.example.totalk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.totalk.R;

import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;

import java.util.List;


public class NewsAdapter extends ArrayAdapter {
    private int resourceId;

    public NewsAdapter(Context context, int viewResourceId, List<Stanza> list) {
        super(context, viewResourceId, list);
        this.resourceId=viewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Presence pocket = (Presence) getItem(position); // 获取当前项的Fruit实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);//实例化一个对象
        TextView message=(TextView) view.findViewById(R.id.messageType);
        message.setText(getType(pocket));
        TextView jid=(TextView) view.findViewById(R.id.jid);
        jid.setText(getJID(pocket));
        return view;

    }
    private String getType(Presence pocket){
        if(pocket.getType().equals(Presence.Type.subscribe)){
            return "好友请求";
        }
        return "一般消息";
    }
    private String getJID(Presence pocket){
        return pocket.getFrom().toString();
    }
}
