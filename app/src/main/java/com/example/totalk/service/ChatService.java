package com.example.totalk.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.totalk.MainActivity;
import com.example.totalk.network.NetWorkManager;
import com.example.totalk.state.IActivity;

public class ChatService extends Service {

    private IActivity listener;

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("onBind",intent.toString());
        return new ChatBinder();
    }

    public class ChatBinder extends Binder {
        public ChatService getService() {
            return ChatService.this;
        }
    }

    public void setListener(IActivity listener) {
        Log.e("setListener","setListener");
        this.listener = listener;
    }

    @Override
    public void onCreate() {
        Log.e("onCreate","start service");

        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    NetWorkManager network=NetWorkManager.getInstance();
                    try {
                        network.init();
                        network.connect();
                    } catch (Exception e) {
                    }

                }
            }).start();

        } catch (Exception e) {
            Log.e("onCreate",e.getMessage());
            Log.e("onCreate","create service error");
        }
        super.onCreate();

    }
}
