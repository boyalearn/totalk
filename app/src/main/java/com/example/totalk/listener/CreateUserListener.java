package com.example.totalk.listener;

import android.view.View;
import android.widget.EditText;

import com.example.totalk.MainActivity;
import com.example.totalk.R;
import com.example.totalk.network.NetWorkManager;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.iqregister.AccountManager;
import org.jxmpp.jid.parts.Localpart;
import org.jxmpp.stringprep.XmppStringprepException;

public class CreateUserListener implements View.OnClickListener {
    private MainActivity activity;

    public CreateUserListener(MainActivity activity){
        this.activity=activity;
    }
    @Override
    public void onClick(View view) {
        EditText jid=(EditText) activity.findViewById(R.id.registerJid);
        EditText pass=(EditText)activity.findViewById(R.id.registerPass);
        NetWorkManager network=NetWorkManager.getInstance();
        try {
            Localpart account=Localpart.from(jid.getText().toString());
            String password=pass.getText().toString();
            AccountManager.sensitiveOperationOverInsecureConnectionDefault(true);
            AccountManager.getInstance(network.getConnection()).createAccount(account, password);
        } catch (SmackException.NoResponseException e) {
            e.printStackTrace();
        } catch (XMPPException.XMPPErrorException e) {
            e.printStackTrace();
        } catch (SmackException.NotConnectedException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (XmppStringprepException e) {
            e.printStackTrace();
        }
    }
}
