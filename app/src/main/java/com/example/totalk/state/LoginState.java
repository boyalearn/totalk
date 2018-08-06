package com.example.totalk.state;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.TextView;

import com.example.totalk.MainActivity;
import com.example.totalk.R;
import com.example.totalk.listener.LoginClickListener;
import com.example.totalk.listener.RegisterClickListener;
import com.example.totalk.service.ChatService;

public class LoginState implements ActionState{
    @Override
    public void reflash(MainActivity activity) {
        activity.setContentView(R.layout.activity_login);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Intent intent = new Intent(activity, ChatService.class);
        activity.bindService(intent, activity.conn, Context.BIND_AUTO_CREATE);
        Button loginBtn=(Button)activity.findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new LoginClickListener(activity));

        TextView registerView=(TextView)activity.findViewById(R.id.register);
        registerView.setOnClickListener(new RegisterClickListener(activity));

    }
}
