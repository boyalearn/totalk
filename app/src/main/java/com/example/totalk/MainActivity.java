package com.example.totalk;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import com.example.totalk.handler.ChatHandler;
import com.example.totalk.network.NetWorkManager;
import com.example.totalk.service.ChatService;
import com.example.totalk.state.inteface.ActionState;
import com.example.totalk.state.inteface.IActivity;
import com.example.totalk.state.LoginState;
import com.example.totalk.state.MainState;

import java.net.URL;


/**
 * branch 分支
 * 该分支采用设计模式优化代码结构
 * app每个界面是一个状态
 **/

public class MainActivity extends Activity implements IActivity {

    public static int RESULT_LOAD_IMAGE = 10;

    private ChatService chatService;

    private ChatHandler chatHandler=new ChatHandler(MainActivity.this);

    private ActionState state;

    public String imagePath;

    public ChatHandler getChatHandler() {
        return chatHandler;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, ChatService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
        startService(intent);
        NetWorkManager network=NetWorkManager.getInstance();
        if(null!=network.getConnection()&&network.getConnection().isAuthenticated()){
            setState(new MainState());
        }else{
            setState(new LoginState());
        }

    }

    @Override
    public void setState(ActionState state) {
        this.state=state;
        state.reflash(this);
    }

    public ServiceConnection conn = new ServiceConnection() {
        /** 获取服务对象时的操作 */
        public void onServiceConnected(ComponentName name, IBinder service) {
            chatService = ((ChatService.ChatBinder) service).getService();
            chatService.setListener(MainActivity.this);

        }

        /** 无法获取到服务对象时的操作 */
        public void onServiceDisconnected(ComponentName name) {
            chatService = null;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
        Log.e("MainActivit","onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("MainActivit","onRestart");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //我们需要判断requestCode是否是我们之前传给startActivityForResult()方法的RESULT_LOAD_IMAGE，并且返回的数据不能为空
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            //查询我们需要的数据
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            ImageView imageView = (ImageView) findViewById(R.id.headImg);
            imagePath=picturePath;
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }

    }
}
