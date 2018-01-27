package com.tencent.wmp.launcher;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.tencent.wmp.launcher.adapter.AppListAdapter;

import java.util.ArrayList;
import java.util.List;

public class AppListActivity extends BaseActivity {

    private GridView mAppListView;
    private PackageManager mPackageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);
        mAppListView = (GridView) findViewById(R.id.app_list);
        mPackageManager = getPackageManager();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAppList();
    }

    private void loadAppList() {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> resolveInfoList = getPackageManager().queryIntentActivities(mainIntent, 0);
        List<AppListAdapter.AppInfo> appInfoList = new ArrayList<AppListAdapter.AppInfo>();

        for (ResolveInfo resolveInfo : resolveInfoList) {
            AppListAdapter.AppInfo appInfo = new AppListAdapter.AppInfo();
            appInfo.appIcon = resolveInfo.loadIcon(mPackageManager);
            appInfo.appName = resolveInfo.loadLabel(mPackageManager).toString();
            appInfo.appPackageName = resolveInfo.activityInfo.packageName;
            appInfoList.add(appInfo);
        }

        mAppListView.setAdapter(new AppListAdapter(this, appInfoList));
        mAppListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                try {
                    AppListAdapter.AppInfo appInfo = (AppListAdapter.AppInfo) adapterView.getAdapter().getItem(position);
                    Intent intent = mPackageManager.getLaunchIntentForPackage(appInfo.appPackageName);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } catch (Throwable e) {
                    showInfo("打开应用失败：e = " + e);
                }
            }
        });
    }
}
