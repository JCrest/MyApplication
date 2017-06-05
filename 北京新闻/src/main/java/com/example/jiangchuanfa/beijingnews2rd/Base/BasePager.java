package com.example.jiangchuanfa.beijingnews2rd.Base;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jiangchuanfa.beijingnews2rd.Activity.MainActivity;
import com.example.jiangchuanfa.beijingnews2rd.R;

/**
 * Created by crest on 2017/6/3.
 */

public class BasePager {
    public Context context;

    public View rootView;
    public TextView tv_title;
    public ImageButton ib_menu;


    public FrameLayout fl_content;


    public BasePager(final Context context){
        this.context = context;

        rootView = View.inflate(context, R.layout.base_pager,null);
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        ib_menu = (ImageButton) rootView.findViewById(R.id.ib_menu);
        fl_content = (FrameLayout) rootView.findViewById(R.id.fl_content);

        //设置点击事件(打开关闭左侧菜单)
        ib_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关-开
                ((MainActivity) context).getSlidingMenu().toggle();
            }
        });
    }

    public void initData(){

    }
}
