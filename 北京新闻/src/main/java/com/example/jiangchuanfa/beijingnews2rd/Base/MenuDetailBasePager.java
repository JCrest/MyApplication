package com.example.jiangchuanfa.beijingnews2rd.Base;

import android.content.Context;
import android.view.View;

/**
 * Created by crest on 2017/6/6.
 */

public abstract class MenuDetailBasePager {
    public final Context context;
    public View rootView;

    public MenuDetailBasePager(Context context) {
        this.context = context;
        rootView = initView();
    }

    //抽象方法由子类实现
    public abstract View initView();

    public void initData() {
    }
}
