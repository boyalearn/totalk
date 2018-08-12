package com.example.totalk.state;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.totalk.MainActivity;
import com.example.totalk.R;
import com.example.totalk.adapter.NewsAdapter;
import com.example.totalk.container.ApplicationContext;
import com.example.totalk.state.inteface.ActionState;

import org.jivesoftware.smack.packet.Stanza;
import java.util.ArrayList;
import java.util.List;

public class NewsState implements ActionState {
    @Override
    public void reflash(MainActivity activity) {

        activity.setContentView(R.layout.activity_news);

        List<Stanza> newsList=new ArrayList<Stanza>();
        for (Stanza item:ApplicationContext.getUnDellMessage()){
            newsList.add(item);
        }
        //消息列表绑定数据及其ITEM点击事件
        NewsAdapter adapter = new NewsAdapter(activity, R.layout.item_news_list, newsList);
        ListView listView = (ListView) activity.findViewById(R.id.news_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new NewsItemListener(activity));

        //添加联系人和消息BUTTON事件
        TextView linkMan = (TextView) activity.findViewById(R.id.linkMan);
        linkMan.setOnClickListener(new LinkManBtnListener(activity));

        TextView newsBtn = (TextView) activity.findViewById(R.id.news);
        newsBtn.setOnClickListener(new NewsBtnListener(activity));

    }

    public class LinkManBtnListener implements View.OnClickListener{

        private MainActivity activity;
        public LinkManBtnListener( MainActivity activity){
            this.activity=activity;
        }

        @Override
        public void onClick(View view) {
            activity.setState(new MainState());
        }
    }

    public class NewsBtnListener implements View.OnClickListener{

        private MainActivity activity;

        public NewsBtnListener( MainActivity activity){
            this.activity=activity;
        }

        @Override
        public void onClick(View view) {
            activity.setState(NewsState.this);
        }
    }

    public class NewsItemListener implements AdapterView.OnItemClickListener {

        private MainActivity activity;

        public NewsItemListener(MainActivity activity){
            this.activity=activity;
        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            NewsInfoState newsInfoState=new NewsInfoState(view,position,id);
            newsInfoState.reflash(activity);
        }
    }
}
