package com.example.jiangchuanfa.beijingnews2rd.Pager;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import com.example.jiangchuanfa.beijingnews2rd.Base.BasePager;

/**
 * Created by crest on 2017/6/3.
 */

public class SettingPager extends BasePager {
    TextView textView;


    public SettingPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {

        super.initData();

        Log.e("TAG","SettingPager-数据初始化...");

        tv_title.setText("设置");
        textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLACK);
        textView.setText("设置页面内容");
        fl_content.addView(textView);

    }
}
