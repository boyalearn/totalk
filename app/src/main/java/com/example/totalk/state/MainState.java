package com.example.totalk.state;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.totalk.MainActivity;
import com.example.totalk.R;
import com.example.totalk.adapter.UserEvtAdapter;
import com.example.totalk.container.ApplicationContainer;
import com.example.totalk.entities.UserEvt;
import com.example.totalk.listener.FriendClickListener;
import com.example.totalk.listener.MyInfoClickListener;
import com.example.totalk.listener.UserItemListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainState implements ActionState{
    @Override
    public void reflash(MainActivity activity) {
        activity.setContentView(R.layout.activity_main);



        ImageView imageView=(ImageView)activity.findViewById(R.id.myHeadImg);
        imageView.setOnClickListener(new MyInfoClickListener(activity));
        TextView addFriend=(TextView)activity.findViewById(R.id.addFriend);
        addFriend.setOnClickListener(new FriendClickListener(activity));
        Iterator it = ApplicationContainer.CHAT_LIST_INFO.entrySet().iterator();
        List<UserEvt> userList=new ArrayList<UserEvt>();
        while (it.hasNext()){
            Map.Entry entry=(Map.Entry)it.next();
            String jid=entry.getKey().toString();
            userList.add(ApplicationContainer.USER_LIST.get(jid));
        }
        UserEvtAdapter adapter = new UserEvtAdapter(activity, R.layout.item_user_list, userList);
        ListView listView = (ListView) activity.findViewById(R.id.user_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new UserItemListener(activity));

    }
}
