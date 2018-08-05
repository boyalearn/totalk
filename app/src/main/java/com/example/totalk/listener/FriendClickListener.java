package com.example.totalk.listener;

import android.view.View;
import android.widget.Button;

import com.example.totalk.MainActivity;
import com.example.totalk.R;

public class FriendClickListener implements View.OnClickListener{
    private MainActivity activity;

    public FriendClickListener(MainActivity activity){
        this.activity=activity;
    }

    @Override
    public void onClick(View view) {
        activity.setContentView(R.layout.activity_friend);
        Button returnBtn=(Button)activity.findViewById(R.id.returnBtn);
        returnBtn.setOnClickListener(new ReturnMainListener(activity));
        Button sendInvitation=(Button)activity.findViewById(R.id.sendInvitation);
        sendInvitation.setOnClickListener(new InvitationFriendListener(activity));

    }
}
