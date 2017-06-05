package com.example.jiangchuanfa.beijingnews2rd.Pager;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import com.example.jiangchuanfa.beijingnews2rd.Base.BasePager;
import com.example.jiangchuanfa.beijingnews2rd.DoMain.NewsCenterBean;
import com.example.jiangchuanfa.beijingnews2rd.Utils.ConstantUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by crest on 2017/6/3.
 */

public class NewsPager extends BasePager {
    TextView textView;


    public NewsPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {

        super.initData();

        Log.e("TAG", "NewsPager-数据初始化...");

        tv_title.setText("新闻");
        textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLUE);
        textView.setText("新闻页面内容");
        fl_content.addView(textView);

        getDataFromNet();

    }

    private void getDataFromNet() {
        String url = ConstantUtils.NEWSCENTER_PAGER_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG","请求失败=="+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG","请求成功=="+response);
                        processData(response);

                    }
                });


    }

    private void processData(String json) {
        NewsCenterBean newsCenterBean = new Gson().fromJson(json, NewsCenterBean.class);
        Log.e("TAG","解析成功了哦=="+ newsCenterBean.getData().get(0).getChildren().get(0).getTitle());

    }
}
