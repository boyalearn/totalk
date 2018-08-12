package com.example.totalk.state.inteface;


import com.example.totalk.handler.ChatHandler;

public interface IActivity {
    public void setState(ActionState state);

    public ChatHandler getChatHandler();
}
