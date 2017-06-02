package com.example.jiangchuanfa.beijingnews2rd.Fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.jiangchuanfa.beijingnews2rd.Base.BaseFragment;

/**
 * Created by crest on 2017/6/3.
 */

public class ContentMenuFragment extends BaseFragment {
    TextView textView;
    @Override
    public View initView() {
        textView = new TextView(context);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("主页面");
    }
}
