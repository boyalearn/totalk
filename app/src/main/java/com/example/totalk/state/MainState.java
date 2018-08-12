package com.example.totalk.state;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.totalk.MainActivity;
import com.example.totalk.R;
import com.example.totalk.adapter.UserEvtAdapter;
import com.example.totalk.chatutil.ChatManager;
import com.example.totalk.container.ApplicationContext;
import com.example.totalk.entities.ChatEvt;
import com.example.totalk.entities.UserEvt;
import com.example.totalk.listener.FriendClickListener;
import com.example.totalk.listener.UserItemListener;
import com.example.totalk.state.inteface.ActionState;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainState implements ActionState {
    @Override
    public void reflash(MainActivity activity) {
        activity.setContentView(R.layout.activity_main);

        if(ApplicationContext.CHAT_LIST_INFO.size()<=0){
            List<UserEvt> userList= null;
            try {
                userList = ChatManager.getFriends();
            } catch (Exception e) {
                e.printStackTrace();
            }
            for(UserEvt userEvt:userList){
                ApplicationContext.CHAT_LIST_INFO.put(userEvt.getJid().toString(),new ArrayList<ChatEvt>());
            }
        }



        TextView addFriend=(TextView)activity.findViewById(R.id.addFriend);
        addFriend.setOnClickListener(new FriendClickListener(activity));

        Iterator it = ApplicationContext.CHAT_LIST_INFO.entrySet().iterator();
        List<UserEvt> userList=new ArrayList<UserEvt>();
        while (it.hasNext()){
            Map.Entry entry=(Map.Entry)it.next();
            String jid=entry.getKey().toString();
            userList.add(ApplicationContext.USER_LIST.get(jid));
        }
        UserEvtAdapter adapter = new UserEvtAdapter(activity, R.layout.item_user_list, userList);
        ListView listView = (ListView) activity.findViewById(R.id.user_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new UserItemListener(activity));

        //添加HEAD点击事件
        ImageView imageView=(ImageView)activity.findViewById(R.id.myHeadImg);
        imageView.setOnClickListener(new MyInfoClickListener(activity));
        //添加底部消息和联系人BUTTON事件
        TextView newsBtn=(TextView)activity.findViewById(R.id.news);
        newsBtn.setOnClickListener(new NewsBtnListener(activity));

    }

    public class NewsBtnListener implements View.OnClickListener{

        private MainActivity activity;

        public NewsBtnListener( MainActivity activity){
            this.activity=activity;
        }

        @Override
        public void onClick(View view) {
            activity.setState(new NewsState());
        }
    }
    public class MyInfoClickListener implements View.OnClickListener{
        private MainActivity activity;

        public MyInfoClickListener( MainActivity activity){
            this.activity=activity;
        }

        @Override
        public void onClick(View view) {
            activity.setState(new MyInfoState());

        }
    }

}
