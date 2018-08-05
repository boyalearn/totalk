package com.example.totalk.listener;

import android.util.Log;
import android.view.View;

import com.example.totalk.MainActivity;
import com.example.totalk.container.CurChatContainer;

public class ReturnMainListener implements View.OnClickListener {
    private MainActivity activity;

    public ReturnMainListener(MainActivity activity){
        this.activity=activity;
    }
    @Override
    public void onClick(View view) {
        CurChatContainer.currentUser=null;
        try {
            activity.intoMainUI();
        } catch (Exception e) {
            Log.e(this.getClass().getName(),e.getMessage());
        }
    }
}
