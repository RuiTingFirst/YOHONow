package lanou.dllo.yohonow.community;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.base.BaseFragment;
import lanou.dllo.yohonow.home.GlideImageLoader;
import lanou.dllo.yohonow.tools.urltools.URLValues;
import lanou.dllo.yohonow.tools.volleytools.NetHelper;
import lanou.dllo.yohonow.tools.volleytools.NetListener;

/**
 * Created by dllo on 16/11/24.
 */

public class CommunityFragment extends BaseFragment {

    private ListView mListView;
    private ViewPager mViewPager;
    private List<Integer> mImage;
    private Banner mBanner;
    private CommunityFmAdapter mCommunityFmAdapter;

    @Override
    protected int setLayout() {
        return R.layout.community_fragment;
    }

    @Override
    protected void initView() {
        mListView = bindView(R.id.lv_community_fragment);
        View headView = LayoutInflater.from(mContext).inflate(R.layout.head_item_community_fragment, null);
        mListView.addHeaderView(headView);
        mBanner = (Banner) headView.findViewById(R.id.banner_head_item_community_fragment);
        mViewPager = (ViewPager) headView.findViewById(R.id.vp_head_item_community_fragment);
    }

    @Override
    protected void initData() {
        mCommunityFmAdapter = new CommunityFmAdapter(mContext);
        /**
         * 一张图片的轮播图
         */
        initTrunImage();
        /**
         * get请求, 获取网络数据
         */
        initUrlGetData();

    }
    /**
     * get请求, 获取网络数据
     */
    private void initUrlGetData() {
        NetHelper.MyRequest(URLValues.COMMUNITY_URL, CommunityBean.class, new NetListener<CommunityBean>() {
            @Override
            public void successListener(CommunityBean response) {
                mCommunityFmAdapter.setCommunityBean(response);
                mListView.setAdapter(mCommunityFmAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    /**
     * 一张图片的轮播图
     */
    private void initTrunImage() {
        mImage = new ArrayList<>();
        mImage.add(R.mipmap.yohocomtop);
        mImage.add(R.mipmap.yohocomtop);
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
}
//    int a = mString.indexOf("?");
//        mString.substring(0,a);