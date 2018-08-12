package com.example.totalk.state;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.totalk.MainActivity;
import com.example.totalk.R;
import com.example.totalk.network.NetWorkManager;
import com.example.totalk.state.inteface.ActionState;

import org.jivesoftware.smack.packet.Presence;
import org.jxmpp.jid.BareJid;
import org.jxmpp.jid.impl.JidCreate;


public class NewsInfoState implements ActionState {
    private View view;

    private int position;

    private long id;

    public NewsInfoState(View view,int position,long id){
        this.view=view;
        this.id=id;
        this.position=position;
    }
    @Override
    public void reflash(MainActivity activity) {
        //通过view获取其内部的组件，进而进行操作
        String messageType = (String) ((TextView)view.findViewById(R.id.messageType)).getText();
        String jid = (String) ((TextView)view.findViewById(R.id.jid)).getText();
        //大多数情况下，position和id相同，并且都从0开始
        String showText = "点击第" + position + "项，文本内容为：" + messageType + "，ID为：" + id;
        Toast.makeText(activity, showText, Toast.LENGTH_LONG).show();
        activity.setContentView(R.layout.activity_newsinfo);
        TextView mainTitle=(TextView)activity.findViewById(R.id.mainTitle);
        mainTitle.setText(messageType);
        TextView message=(TextView)activity.findViewById(R.id.message);
        Button acceptBtn=(Button)activity.findViewById(R.id.accept);
        acceptBtn.setOnClickListener(new AcceptBtnListenter(jid));
        if("好友请求".equals(messageType)){
            message.setText("来自"+jid+"的好友请求，请同意");

        }else{
            message.setText("收到来自"+jid+"的消息");
        }

    }
    public class AcceptBtnListenter implements View.OnClickListener{
        private String jid;

        public AcceptBtnListenter(String jid){
            this.jid=jid;
        }
        @Override
        public void onClick(View view) {
            try {
                Presence pres = new Presence(Presence.Type.subscribed);
                BareJid bareJid=JidCreate.bareFrom(jid);
                pres.setTo(bareJid);
                NetWorkManager network=NetWorkManager.getInstance();
                network.getConnection().sendStanza(pres);
            } catch (Exception e) {
                Log.e("AcceptBtnListenter",e.getMessage());
                e.printStackTrace();
            }

        }
    }
}
