package com.example.totalk.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.totalk.R;
import com.example.totalk.chatutil.StringUtil;
import com.example.totalk.entities.UserEvt;

import java.util.List;


public class UserEvtAdapter extends ArrayAdapter {

    private int resourceId;

    public UserEvtAdapter(Context context, int viewResourceId, List<UserEvt> list) {
        super(context, viewResourceId, list);
        this.resourceId=viewResourceId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UserEvt userEvt = (UserEvt) getItem(position); // 获取当前项的Fruit实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);//实例化一个对象

        TextView nickName = (TextView) view.findViewById(R.id.nickName);
        TextView jid = (TextView) view.findViewById(R.id.jid);
        ImageView imageView=(ImageView)view.findViewById(R.id.avatar) ;
        nickName.setText(StringUtil.dellEmpty(userEvt.getNickName()));
        jid.setText(userEvt.getJid().toString());
        if(null!=userEvt.getAvatar()) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(userEvt.getAvatar(), 0, userEvt.getAvatar().length);
            imageView.setImageBitmap(bitmap);
        }
        return view;

    }
}
