package com.tencent.wmp.launcher.adapter;


import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.wmp.launcher.R;

import java.util.List;


public class AppListAdapter extends BaseAdapter {

    public static class AppInfo {
        public String appName;
        public Drawable appIcon;
        public String appPackageName;
    }

    private List<AppInfo> mAppInfoList;
    private LayoutInflater inflater;
    private PackageManager mPackageManager;

    public AppListAdapter(Context context, List<AppInfo> appInfoList) {

        this.mAppInfoList = appInfoList;
        this.inflater = LayoutInflater.from(context);
        this.mPackageManager = context.getPackageManager();
    }

    @Override
    public int getCount() {
        if (mAppInfoList == null) {
            return 0;
        } else {
            return mAppInfoList.size();
        }
    }

    @Override
    public Object getItem(int position) {
        if (mAppInfoList == null || position >= mAppInfoList.size()) {
            return null;
        } else {
            return mAppInfoList.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = this.inflater.inflate(R.layout.app_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ivAppIcon = (ImageView) convertView.findViewById(R.id.iv_app_icon);
            viewHolder.tvAppName = (TextView) convertView.findViewById(R.id.tv_app_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        AppInfo appInfo = mAppInfoList.get(position);
        viewHolder.ivAppIcon.setImageDrawable(appInfo.appIcon);
        viewHolder.tvAppName.setText(appInfo.appName);

        return convertView;
    }

    static class ViewHolder {
        ImageView ivAppIcon;
        TextView tvAppName;
    }
}
