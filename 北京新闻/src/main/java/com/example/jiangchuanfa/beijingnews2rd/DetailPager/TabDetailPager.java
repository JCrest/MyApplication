package com.example.jiangchuanfa.beijingnews2rd.DetailPager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.jiangchuanfa.beijingnews2rd.Base.MenuDetailBasePager;
import com.example.jiangchuanfa.beijingnews2rd.DoMain.NewsCenterBean;

/**
 * Created by crest on 2017/6/6.
 *
 *
 */

public class TabDetailPager extends MenuDetailBasePager {
    TextView textView;
    NewsCenterBean.DataBean.ChildrenBean childrenBean;

    public TabDetailPager(Context context, NewsCenterBean.DataBean.ChildrenBean childrenBean) {
        super(context);
        this.childrenBean = childrenBean;
    }

    @Override
    public View initView() {
        textView = new TextView(context);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();

        textView.setText(childrenBean.getTitle());
    }
}
