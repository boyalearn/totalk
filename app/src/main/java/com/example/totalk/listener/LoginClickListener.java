package com.example.totalk.listener;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.totalk.MainActivity;
import com.example.totalk.R;
import com.example.totalk.network.NetWorkManager;

public class LoginClickListener  implements View.OnClickListener {
    private MainActivity activity;
    public LoginClickListener(MainActivity activity){
        this.activity=activity;
    }
    @Override
    public void onClick(View view) {
        EditText userNameEdit=(EditText)this.activity.findViewById(R.id.userName);
        EditText passWordEdit=(EditText)this.activity.findViewById(R.id.passWord);
        NetWorkManager network=NetWorkManager.getInstance();
        network.addNetWorkListener(activity);
        try {
            network.login(userNameEdit.getText().toString(),passWordEdit.getText().toString());
        } catch (Exception e) {
            Log.e("login","error");
        }
    }
}
