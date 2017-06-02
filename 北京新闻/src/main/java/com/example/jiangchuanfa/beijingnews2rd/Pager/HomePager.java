package com.example.jiangchuanfa.beijingnews2rd.Pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.example.jiangchuanfa.beijingnews2rd.Base.BasePager;

/**
 * Created by crest on 2017/6/3.
 */

public class HomePager extends BasePager {
    TextView textView;


    public HomePager(Context context) {
        super(context);
    }

    @Override
    public void initData() {

        super.initData();
        tv_title.setText("主页");
        textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setText("主页页面内容");
        fl_content.addView(textView);

    }
}
