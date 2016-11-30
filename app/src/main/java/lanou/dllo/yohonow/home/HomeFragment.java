package lanou.dllo.yohonow.home;

import android.support.v4.widget.DrawerLayout;
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
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.base.BaseFragment;
import lanou.dllo.yohonow.tools.urltools.URLValues;
import lanou.dllo.yohonow.tools.volleytools.NetHelper;
import lanou.dllo.yohonow.tools.volleytools.NetListener;

/**
 * Created by dllo on 16/11/23.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private ListView mListView;
    private HomeAdapter mHomeAdapter;
    private ArrayList<String> mImage;
    private Banner mBanner;
    private DrawerLayout mDrawerLayout;
    private ImageView mIvMine;
    private ImageView mIvSearch;

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

        mListView = bindView(R.id.lv_home_fragment);
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

                break;
        }
    }
}
