package com.example.jiangchuanfa.beijingnews2rd.DetailPager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.jiangchuanfa.beijingnews2rd.Base.MenuDetailBasePager;

/**
 * Created by crest on 2017/6/6.
 */

public class TopicMenuDetailPager extends MenuDetailBasePager {
    TextView textView;
    public TopicMenuDetailPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        //创建子类的视图
        textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("专题详情页面的内容");

    }
}
