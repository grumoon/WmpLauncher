
package com.tencent.wmp.launcher;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private Button mBtnCast;
    private Button mBtnSetting;
    private Button mBtnAppList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        int iconWidth = getResources().getDimensionPixelOffset(R.dimen.home_btn_icon_width);
        int iconHeight = getResources().getDimensionPixelOffset(R.dimen.home_btn_icon_height);

        mBtnCast = (Button) findViewById(R.id.btn_cast);
        mBtnCast.setOnClickListener(this);
        Drawable castDrawable = getResources().getDrawable(R.drawable.icon_cast);
        castDrawable.setBounds(0, 0, iconWidth, iconHeight);
        mBtnCast.setCompoundDrawables(null, castDrawable, null, null);

        mBtnSetting = (Button) findViewById(R.id.btn_setting);
        mBtnSetting.setOnClickListener(this);
        Drawable settingDrawable = getResources().getDrawable(R.drawable.icon_setting);
        settingDrawable.setBounds(0, 0, iconWidth, iconHeight);
        mBtnSetting.setCompoundDrawables(null, settingDrawable, null, null);

        mBtnAppList = (Button) findViewById(R.id.btn_app_list);
        mBtnAppList.setOnClickListener(this);
        Drawable appListDrawable = getResources().getDrawable(R.drawable.icon_app_list);
        appListDrawable.setBounds(0, 0, iconWidth, iconHeight);
        mBtnAppList.setCompoundDrawables(null, appListDrawable, null, null);
    }

    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cast: {
                try {
                    Intent intent = new Intent();
                    intent.setClassName("com.tencent.wmp.demo.box", "com.tencent.wmp.demo.box.activity.SplashActivity");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } catch (Throwable e) {
                    showInfo("打开无线投屏失败：e = " + e);
                }
                break;
            }
            case R.id.btn_setting: {
                try {
                    Intent intent = new Intent(Settings.ACTION_SETTINGS);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } catch (Throwable e) {
                    showInfo("打开系统设置失败：e = " + e);
                }
                break;
            }
            case R.id.btn_app_list: {
                try {
                    Intent intent = new Intent(this, AppListActivity.class);
                    startActivity(intent);
                } catch (Throwable e) {
                    showInfo("打开应用列表失败：e = " + e);
                }
                break;
            }
            default:
                break;
        }
    }


}
