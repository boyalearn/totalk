package com.example.totalk;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import com.example.totalk.handler.ChatHandler;
import com.example.totalk.service.ChatService;
import com.example.totalk.state.ActionState;
import com.example.totalk.state.IActivity;
import com.example.totalk.state.LoginState;

/**
 * branch 分支
 * 该分支采用设计模式优化代码结构
 * app每个界面是一个状态
 **/

public class MainActivity extends Activity implements IActivity {

    private ChatService chatService;

    private ChatHandler chatHandler=new ChatHandler(MainActivity.this);

    public ChatHandler getChatHandler() {
        return chatHandler;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setState(new LoginState());

    }

    @Override
    public void setState(ActionState state) {
        state.reflash(this);
    }

    public ServiceConnection conn = new ServiceConnection() {
        /** 获取服务对象时的操作 */
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            chatService = ((ChatService.ChatBinder) service).getService();
            chatService.setListener(MainActivity.this);

        }

        /** 无法获取到服务对象时的操作 */
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            chatService = null;
        }

    };

}
