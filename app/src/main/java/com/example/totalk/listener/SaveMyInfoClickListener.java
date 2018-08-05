package com.example.totalk.listener;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.totalk.MainActivity;
import com.example.totalk.R;
import com.example.totalk.network.NetWorkManager;
import org.jivesoftware.smackx.vcardtemp.packet.VCard;

public class SaveMyInfoClickListener implements View.OnClickListener{
    private MainActivity activity;

    public SaveMyInfoClickListener(MainActivity activity){
        this.activity=activity;
    }

    @Override
    public void onClick(View view) {
        NetWorkManager network=NetWorkManager.getInstance();
        EditText nickText=(EditText)activity.findViewById(R.id.nickName);
        VCard vcard = new VCard();
        try {
            vcard.load(network.getConnection());
            vcard.setNickName(nickText.getText().toString());
            vcard.save(network.getConnection());
        } catch (Exception e) {
            Log.e(this.getClass().getName(),e.getMessage());
        }

    }
}
