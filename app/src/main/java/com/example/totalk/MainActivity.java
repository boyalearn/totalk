package com.example.totalk;



import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.totalk.adapter.UserEvtAdapter;
import com.example.totalk.chatutil.ChatManager;
import com.example.totalk.container.ApplicationContainer;
import com.example.totalk.container.CurChatContainer;
import com.example.totalk.entities.ChatEvt;
import com.example.totalk.entities.UserEvt;
import com.example.totalk.listener.FriendClickListener;
import com.example.totalk.listener.LoginClickListener;
import com.example.totalk.listener.MyInfoClickListener;
import com.example.totalk.listener.RegisterClickListener;
import com.example.totalk.listener.UserItemListener;
import com.example.totalk.service.ChatService;

import org.jivesoftware.smack.packet.Presence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.example.totalk.container.CurChatContainer.adapter;


/**
 * branch 分支用于代码优化
 **/

public class MainActivity extends Activity implements ChatService.ChatActivityListener {

    private ChatService chatService;

    private ChatHandler chatHandler=new ChatHandler(MainActivity.this);

    public ChatHandler getChatHandler() {
        return chatHandler;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Intent intent = new Intent(MainActivity.this, ChatService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
        Button loginBtn=(Button)findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new LoginClickListener(this));

        TextView registerView=(TextView)findViewById(R.id.register);
        registerView.setOnClickListener(new RegisterClickListener(this));
    }


    private ServiceConnection conn = new ServiceConnection() {
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
    @Override
    public void onChatMessage(Object message) {
        Log.e("onChatMessage",message.toString());

    }

    public class ChatHandler extends Handler {
        private MainActivity activity;
        public ChatHandler(MainActivity activity) {
            this.activity=activity;
        }

        public ChatHandler(Looper L) {
            super(L);
        }

        // 子类必须重写此方法，接受数据
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            Log.d("MyHandler","handleMessage.....");
            super.handleMessage(msg);
            // 此处可以更新UI
            if(msg.obj instanceof Presence){
                Presence presence=(Presence)msg.obj;
                /**如果登录成功*/
                if("available".equals(presence.getType().name())){
                    try {
                        dellLogin();
                        intoMainUI();
                    } catch (Exception e) {
                        Log.e(this.getClass().getName(),e.getMessage());
                    }

                }
                Log.e("iq",msg.obj.toString());
            }
            //收到聊天信息更新UI
            if(msg.obj instanceof org.jivesoftware.smack.packet.Message){
                org.jivesoftware.smack.packet.Message message =(org.jivesoftware.smack.packet.Message)msg.obj;
                String jid=message.getFrom().asBareJid().toString();
                Log.e("chatMsg",message.getFrom().toString());
                ChatEvt chatEvt=new ChatEvt();
                chatEvt.setLayout('L');
                chatEvt.setMessage(message.getBody());
                List<ChatEvt> list=ApplicationContainer.CHAT_LIST_INFO.get(jid);
                if(null!=list){
                    list.add(chatEvt);
                }
                if(null!=CurChatContainer.currentUser&&jid.equals(CurChatContainer.currentUser)){
                    adapter.notifyDataSetChanged();
                    CurChatContainer.listView.setSelection(CurChatContainer.chatList.size()-1);
                }

            }

        }
    }
    public void dellLogin() throws Exception {
        List<UserEvt> userList=ChatManager.getFriends();
        for(UserEvt userEvt:userList){
            ApplicationContainer.CHAT_LIST_INFO.put(userEvt.getJid().toString(),new ArrayList<ChatEvt>());
        }
    }
    public void intoMainUI() throws Exception {
        setContentView(R.layout.activity_main);
        ImageView imageView=(ImageView)findViewById(R.id.myHeadImg);
        imageView.setOnClickListener(new MyInfoClickListener(this));
        TextView addFriend=(TextView)findViewById(R.id.addFriend);
        addFriend.setOnClickListener(new FriendClickListener(this));
        Iterator it = ApplicationContainer.CHAT_LIST_INFO.entrySet().iterator();
        List<UserEvt> userList=new ArrayList<UserEvt>();
        while (it.hasNext()){
            Map.Entry entry=(Map.Entry)it.next();
            String jid=entry.getKey().toString();
            userList.add(ApplicationContainer.USER_LIST.get(jid));
        }
        UserEvtAdapter adapter = new UserEvtAdapter(this, R.layout.item_user_list, userList);
        ListView listView = (ListView) findViewById(R.id.user_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new UserItemListener(this));
    }


}
