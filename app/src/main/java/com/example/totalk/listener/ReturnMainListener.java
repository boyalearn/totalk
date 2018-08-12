package com.example.totalk.listener;

import android.util.Log;
import android.view.View;

import com.example.totalk.MainActivity;
import com.example.totalk.container.CurChatContainer;
import com.example.totalk.state.MainState;

public class ReturnMainListener implements View.OnClickListener {
    private MainActivity activity;

    public ReturnMainListener(MainActivity activity){
        this.activity=activity;
    }
    @Override
    public void onClick(View view) {
        CurChatContainer.currentUser=null;
        try {
            activity.setState(new MainState());
        } catch (Exception e) {
            Log.e(this.getClass().getName(),e.getMessage());
        }
    }
}
