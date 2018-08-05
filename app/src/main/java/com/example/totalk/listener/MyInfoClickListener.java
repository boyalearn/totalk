package com.example.totalk.listener;

import android.view.View;
import android.widget.Button;

import com.example.totalk.MainActivity;
import com.example.totalk.R;

public class MyInfoClickListener implements View.OnClickListener{

    private MainActivity activity;

    public MyInfoClickListener(MainActivity activity){
        this.activity=activity;
    }
    @Override
    public void onClick(View view) {
        activity.setContentView(R.layout.acitvity_myinfo);
        Button saveBtn=(Button)activity.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new SaveMyInfoClickListener(activity));

        Button returnBtn=(Button)activity.findViewById(R.id.returnBtn);
        returnBtn.setOnClickListener(new ReturnMainListener(activity));
    }
}
