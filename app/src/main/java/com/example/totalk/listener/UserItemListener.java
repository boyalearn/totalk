package com.example.totalk.listener;

import android.view.View;
import android.widget.AdapterView;
import com.example.totalk.MainActivity;
import com.example.totalk.state.ChatState;


public class UserItemListener implements AdapterView.OnItemClickListener {

    private MainActivity activity;

    public UserItemListener(MainActivity activity){
        this.activity=activity;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        ChatState chatState=new ChatState(view,position,id);
        chatState.reflash(activity);
    }
}
