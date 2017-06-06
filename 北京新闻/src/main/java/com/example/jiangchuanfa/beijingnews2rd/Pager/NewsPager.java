package com.example.jiangchuanfa.beijingnews2rd.Pager;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.jiangchuanfa.beijingnews2rd.Activity.MainActivity;
import com.example.jiangchuanfa.beijingnews2rd.Base.BasePager;
import com.example.jiangchuanfa.beijingnews2rd.Base.MenuDetailBasePager;
import com.example.jiangchuanfa.beijingnews2rd.DetailPager.InteractMenuDetailPager;
import com.example.jiangchuanfa.beijingnews2rd.DetailPager.NewsMenuDetailPager;
import com.example.jiangchuanfa.beijingnews2rd.DetailPager.PhotosMenuDetailPager;
import com.example.jiangchuanfa.beijingnews2rd.DetailPager.TopicMenuDetailPager;
import com.example.jiangchuanfa.beijingnews2rd.DetailPager.VoteMenuDetailPager;
import com.example.jiangchuanfa.beijingnews2rd.DoMain.NewsCenterBean;
import com.example.jiangchuanfa.beijingnews2rd.Fragment.LeftMenuFragment;
import com.example.jiangchuanfa.beijingnews2rd.Utils.ConstantUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by crest on 2017/6/3.
 */

public class NewsPager extends BasePager {
    TextView textView;

    public List<NewsCenterBean.DataBean> datas;

    private List<MenuDetailBasePager> basePagers;


    public NewsPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {

        super.initData();

        Log.e("TAG", "NewsPager-数据初始化...");

        tv_title.setText("新闻");
        ib_menu.setVisibility(View.VISIBLE);


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
                        Log.e("TAG", "请求失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "请求成功==" + response);
                        processData(response);

                    }
                });


    }

    private void processData(String json) {
//        NewsCenterBean newsCenterBean = new Gson().fromJson(json, NewsCenterBean.class);


        //使用系统的API解析json数据
        NewsCenterBean newsCenterBean = parseJson(json);

        Log.e("TAG", "解析成功了哦==" + newsCenterBean.getData().get(0).getChildren().get(0).getTitle());
        datas = newsCenterBean.getData();

        /**
         * 将解析出来的数据传到左侧菜单
         *
         */
        MainActivity mainActivity = (MainActivity) context;

        //实例化详情页面
        basePagers = new ArrayList<>();
        basePagers.add(new NewsMenuDetailPager(context,datas.get(0).getChildren()));//新闻详情页面
        basePagers.add(new TopicMenuDetailPager(context));//专题详情页面
        basePagers.add(new PhotosMenuDetailPager(context));//组图详情页面
        basePagers.add(new InteractMenuDetailPager(context));//互动详情页面
        basePagers.add(new VoteMenuDetailPager(context));//投票详情页面


        //获取左侧菜单（mainActivity 继承了SlidingFragmentActivity 可通过字段LEFT_TAG在mainActivity获取左侧菜单）
        LeftMenuFragment leftMenuFragment = mainActivity.getLeftMenuFragment();
        //为左侧菜单设置数据
        leftMenuFragment.setData(datas);


    }

    private NewsCenterBean parseJson(String json) {

        NewsCenterBean newsCenterBean = new NewsCenterBean();

        try {
            JSONObject jsonObject = new JSONObject(json);
            //解析retcode
            int retcode = jsonObject.optInt("retcode");
            //设置数据
            newsCenterBean.setRetcode(retcode);


            //集合
            JSONArray jsonArray = jsonObject.optJSONArray("data");
            List<NewsCenterBean.DataBean> data = new ArrayList<>();
            newsCenterBean.setData(data);

            for(int i = 0; i <jsonArray.length() ; i++) {
                //数据
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                if(jsonObject1 !=null) {

                    NewsCenterBean.DataBean dataBean = new NewsCenterBean.DataBean();
                    dataBean.setId(jsonObject1.optInt("id"));
                    dataBean.setType(jsonObject1.optInt("type"));
                    String title = jsonObject1.optString("title");
                    dataBean.setTitle(title);
                    String url = jsonObject1.optString("url");
                    dataBean.setUrl(url);

                    JSONArray jsonArray1 = jsonObject1.optJSONArray("children");

                    if(jsonArray1 != null) {
                        List<NewsCenterBean.DataBean.ChildrenBean> children = new ArrayList<>();
                        //设置children数据的
                        dataBean.setChildren(children);
                        for(int i1 = 0; i1 <jsonArray1.length(); i1++) {
                            JSONObject jsonObject2 = jsonArray1.getJSONObject(i1);
                            NewsCenterBean.DataBean.ChildrenBean childrenBean = new NewsCenterBean.DataBean.ChildrenBean();
                            //解析数据了
                            childrenBean.setId(jsonObject2.optInt("id"));
                            childrenBean.setType(jsonObject2.optInt("type"));
                            childrenBean.setTitle(jsonObject2.optString("title"));
                            childrenBean.setUrl(jsonObject2.optString("url"));

                            //添加数据到集合中
                            children.add(childrenBean);
                        }
                    }

                    //添加到集合中
                    data.add(dataBean);

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return newsCenterBean;
    }

    public void swichPager(int prePosition) {
        MenuDetailBasePager basePager = basePagers.get(prePosition);
        View rootView = basePager.rootView;
        fl_content.removeAllViews();
        fl_content.addView(rootView);

        tv_title.setText(datas.get(prePosition).getTitle());

        basePager.initData();


    }
}
