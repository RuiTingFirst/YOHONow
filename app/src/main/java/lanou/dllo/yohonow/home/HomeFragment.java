package lanou.dllo.yohonow.home;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.base.BaseFragment;
import lanou.dllo.yohonow.home.homeseek.SeekActivity;
import lanou.dllo.yohonow.tools.urltools.URLValues;
import lanou.dllo.yohonow.tools.volleytools.NetHelper;
import lanou.dllo.yohonow.tools.volleytools.NetListener;

/**
 * Created by dllo on 16/11/23.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener, OnRefreshListener, OnLoadMoreListener {
    private ListView mListView;
    private HomeAdapter mHomeAdapter;
    private ArrayList<String> mImage;
    private Banner mBanner;
    private DrawerLayout mDrawerLayout;
    private ImageView mIvMine;
    private ImageView mIvSearch;
    private SwipeToLoadLayout mSwipeToLoadLayout;

    private int num = 0;

    //解析post接口用到的
    private HashMap<String, String> mapHome;
    private HashMap<String, String> map;
    private String value;
    private Gson gson;
    /**
     * 绑定布局
     *
     * @return
     */
    @Override
    protected int setLayout() {
        return R.layout.home_fragment;
    }

    /**
     * 初始化布局
     */
    @Override
    protected void initView() {

        /**
         * 下拉刷新, 上拉加载
         */
        mSwipeToLoadLayout = bindView(R.id.swipe_toload_layout_home_fragment);
        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
        mListView = bindView(R.id.swipe_target);

        mIvMine = bindView(R.id.iv_toolbar_mine_main);
        mIvSearch = bindView(R.id.iv_toolbar_search_main);
        /**
         * 抽屉操作
         */
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.draw_layout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        /**
         * 添加头布局
         */
        View mHeadView = LayoutInflater.from(mContext).inflate(R.layout.head_item, null);
        mListView.addHeaderView(mHeadView);
        mBanner = (Banner) mHeadView.findViewById(R.id.banner_head_item_home_fragment);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        mIvMine.setOnClickListener(this);
        mIvSearch.setOnClickListener(this);
        mHomeAdapter = new HomeAdapter(mContext);
        /**
         * post请求, 解析数据
         */
        initPost();
        /**
         * post请求, 解析 轮播图数据
         */
        initTrunUrlData();

        homeMap();

    }
    /**
     * map
     */
    private void homeMap() {
        map = new HashMap<>();
        map.put("newsEndtime", "0");
        map.put("otherEndTime", "0");
        map.put("magazineType", "3");
        map.put("WallpaperType", "3");
        map.put("scale", "2");
        map.put("num", String.valueOf(num));
        map.put("platform", "4");
        map.put("locale", "zh-Hans");
        map.put("language", "zh-Hans");
        map.put("udid", "00000000000000063aa461b71c4cfcf");
        map.put("curVersion", "5.0.4");

        HashMap<String, String> mm = new HashMap<>();
        mm.put("udid", "00000000000000063aa461b71c4cfcf");
        String a = new Gson().toJson(mm).toString();
        map.put("authInfo", a);
        gson = new Gson();
        // key value 反转化
        value = gson.toJson(map).toString();
        mapHome = new HashMap<>();
        mapHome.put(URLValues.HOME_KEY, value);

    }

    /**
     * post请求, 解析 轮播图数据
     */
    private void initTrunImage() {
        // 设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        // 设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        // 设置图片集合
        mBanner.setImages(mImage);
        // 设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        // 设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        // 设置轮播时间
        mBanner.setDelayTime(2000);
        // 设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        // banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    /**
     * post请求, 解析 轮播图数据
     */
    private void initTrunUrlData() {
        HashMap<String, String> map = new HashMap<>();
        map.put(URLValues.HOME_TRUN_KEY, URLValues.HOME_TRUN_VALUE);
        NetHelper.MyRequest(URLValues.HOME_TRUN_URL, HomeTrunBean.class, new NetListener<HomeTrunBean>() {
            @Override
            public void successListener(HomeTrunBean response) {
                mImage = new ArrayList<>();
                for (int i = 0; i < response.getData().size(); i++) {
                    mImage.add(response.getData().get(i).getImage());
                }
                /**
                 * 实现轮播效果
                 */
                initTrunImage();
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, map);
    }

    /**
     * post请求, 解析数据
     */
    private void initPost() {
        HashMap<String, String> map = new HashMap<>();
        map.put(URLValues.HOME_KEY, URLValues.HOME_VALUE);
        NetHelper.MyRequest(URLValues.HOME_URL, HomeBean.class, new NetListener<HomeBean>() {
            @Override
            public void successListener(HomeBean response) {
                mHomeAdapter.setHomeBean(response);
                mListView.setAdapter(mHomeAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, map);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_mine_main:
                // 左边拉出抽屉
                mDrawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.iv_toolbar_search_main:
                Intent intent = new Intent(getActivity(), SeekActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        /**
         * 刷新时间
         */
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                // true 是一直刷新 false 不让一直刷新
                mSwipeToLoadLayout.setRefreshing(false);
                getRefreshData();
            }
        }, 2000);
    }

    /**
     * 获取刷新数据
     */
    private void getRefreshData() {
        NetHelper.MyRequest(URLValues.HOME_URL, HomeBean.class, new NetListener<HomeBean>() {
            @Override
            public void successListener(HomeBean response) {
                mHomeAdapter.setHomeBean(response);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, mapHome);

    }

    /**
     * 上拉加载
     */
    @Override
    public void onLoadMore() {
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                // true 是自动加载更多 false 是手动加载
                mSwipeToLoadLayout.setLoadingMore(false);
                getLoadData();
            }
        }, 1000);
    }

    /**
     * 获取加载数据
     */
    private void getLoadData() {
        NetHelper.MyRequest(URLValues.HOME_URL, HomeBean.class, new NetListener<HomeBean>() {
            @Override
            public void successListener(HomeBean response) {
                mHomeAdapter.addMore(response);
                map.put("num", String.valueOf(num));
                num += 16;
                value = gson.toJson(map).toString();
                mapHome.put(URLValues.HOME_KEY, value);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, mapHome);
    }
}
