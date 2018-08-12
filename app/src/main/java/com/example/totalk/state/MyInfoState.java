package com.example.totalk.state;

import android.content.Intent;
import android.media.Image;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.totalk.MainActivity;
import com.example.totalk.R;
import com.example.totalk.listener.ReturnMainListener;
import com.example.totalk.network.NetWorkManager;
import com.example.totalk.state.inteface.ActionState;
import org.jivesoftware.smackx.vcardtemp.packet.VCard;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyInfoState implements ActionState {


    @Override
    public void reflash(MainActivity activity) {
        activity.setContentView(R.layout.acitvity_myinfo);

        ImageView myHaed = (ImageView) activity.findViewById(R.id.headImg);
        myHaed.setOnClickListener(new MyHeadImgClickListener(activity));

        //保存按钮添加点击事件
        Button saveBtn = (Button) activity.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new SaveMyInfoClickListener(activity));
        //返回按钮添加点击事件
        Button returnBtn = (Button) activity.findViewById(R.id.returnBtn);
        returnBtn.setOnClickListener(new ReturnMainListener(activity));
    }

    public class MyHeadImgClickListener implements View.OnClickListener {
        private MainActivity activity;

        public MyHeadImgClickListener(MainActivity activity) {
            this.activity = activity;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            //这里要传一个整形的常量RESULT_LOAD_IMAGE到startActivityForResult()方法。
            activity.startActivityForResult(intent, activity.RESULT_LOAD_IMAGE);
        }
    }

    public class SaveMyInfoClickListener implements View.OnClickListener {
        private MainActivity activity;

        public SaveMyInfoClickListener(MainActivity activity) {
            this.activity = activity;
        }

        @Override
        public void onClick(View view) {
            NetWorkManager network = NetWorkManager.getInstance();
            EditText nickText = (EditText) activity.findViewById(R.id.nickName);
            ImageView headImg = (ImageView) activity.findViewById(R.id.headImg);
            VCard vcard = new VCard();
            try {
                vcard.load(network.getConnection());
                byte[] bytes;

                bytes = getFileBytes(new File(activity.imagePath));
                String encodedImage = Base64.encodeToString(bytes,Base64.DEFAULT);
                vcard.setAvatar(bytes, encodedImage);
                vcard.setEncodedImage(encodedImage);
                vcard.setField("PHOTO", "<TYPE>image/jpg</TYPE><BINVAL>"
                        + encodedImage + "</BINVAL>", true);


                vcard.setNickName(nickText.getText().toString());
                vcard.save(network.getConnection());
            } catch (Exception e) {
                Log.e(this.getClass().getName(), e.getMessage());
            }

        }
    }

    private static byte[] getFileBytes(File file) throws IOException {
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(file));
            int bytes = (int) file.length();
            byte[] buffer = new byte[bytes];
            int readBytes = bis.read(buffer);
            if (readBytes != buffer.length) {
                throw new IOException("Entire file not read");
            }
            return buffer;
        } finally {
            if (bis != null) {
                bis.close();
            }
        }
    }
}
