package com.example.totalk.state;

import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.totalk.MainActivity;
import com.example.totalk.R;
import com.example.totalk.listener.RegisterClickListener;
import com.example.totalk.network.NetWorkManager;
import com.example.totalk.state.inteface.ActionState;

public class LoginState implements ActionState {
    @Override
    public void reflash(MainActivity activity) {
        activity.setContentView(R.layout.activity_login);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Button loginBtn=(Button)activity.findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new LoginClickListener(activity));

        TextView registerView=(TextView)activity.findViewById(R.id.register);
        registerView.setOnClickListener(new RegisterClickListener(activity));

    }

    public class LoginClickListener  implements View.OnClickListener{
        private MainActivity activity;
        public LoginClickListener(MainActivity activity){
            this.activity=activity;
        }
        @Override
        public void onClick(View view) {
            EditText userNameEdit=(EditText)this.activity.findViewById(R.id.userName);
            EditText passWordEdit=(EditText)this.activity.findViewById(R.id.passWord);
            NetWorkManager network=NetWorkManager.getInstance();
            if(network.getConnection().isConnected()) {
                if(network.getConnection().isAuthenticated()){
                    activity.setState(new MainState());
                    return ;
                }
                network.addNetWorkListener(activity);
                try {
                    network.login(userNameEdit.getText().toString(), passWordEdit.getText().toString());
                } catch (Exception e) {
                    Log.e("login", "error");
                }
            }else{
                Toast.makeText(activity, "没有连接成功", Toast.LENGTH_LONG).show();
            }
        }
    }
}
