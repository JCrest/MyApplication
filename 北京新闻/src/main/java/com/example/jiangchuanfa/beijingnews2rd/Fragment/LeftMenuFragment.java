package com.example.jiangchuanfa.beijingnews2rd.Fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.jiangchuanfa.beijingnews2rd.Base.BaseFragment;
import com.example.jiangchuanfa.beijingnews2rd.DoMain.NewsCenterBean;

import java.util.List;

/**
 * Created by crest on 2017/6/3.
 */

public class LeftMenuFragment extends BaseFragment {
    TextView textView;
    private List<NewsCenterBean.DataBean> datas;

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
        textView.setText("左侧菜单");
    }

    //为左侧菜单设置数据的方法
    public void setData(List<NewsCenterBean.DataBean> datas) {
        this.datas = datas;
        for (int i = 0; i < datas.size(); i++) {
            Log.e("TAG", "" + datas.get(i).getTitle());
        }
    }
}
