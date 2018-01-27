package com.tencent.wmp.launcher;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;


public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    /**
     * 构造一个主线程的Handler，用于投递消息
     */
    protected Handler mBaseHandler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mBaseHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                baseHandleMessage(msg);
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


        mBaseHandler.removeCallbacksAndMessages(null);
        mBaseHandler = null;
    }


    /**
     * 子类重写此方法，进行MSG处理
     *
     * @param msg
     */
    public void baseHandleMessage(Message msg) {

    }


    protected void showInfo(String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }


}