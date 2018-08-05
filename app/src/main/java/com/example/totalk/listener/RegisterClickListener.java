package com.example.totalk.listener;

import android.view.View;
import android.widget.Button;

import com.example.totalk.MainActivity;
import com.example.totalk.R;

public class RegisterClickListener implements  View.OnClickListener{
    private MainActivity activity;

    public RegisterClickListener(MainActivity activity){
        this.activity=activity;
    }

    @Override
    public void onClick(View view) {
        activity.setContentView(R.layout.activity_register);
        Button register=(Button)activity.findViewById(R.id.registerBtn);
        register.setOnClickListener(new CreateUserListener(activity));
    }
}
