package lanou.dllo.yohonow.video;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.base.BaseFragment;
import lanou.dllo.yohonow.video.live.LiveFragment;
import lanou.dllo.yohonow.video.videoson.VideoSonFragment;

/**
 * Created by dllo on 16/11/24.
 */

public class VideoFragment extends BaseFragment {

    private TabLayout mTab;
    private ViewPager mVp;

    /**
     * 绑定布局
     * @return
     */
    @Override
    protected int setLayout() {
        return R.layout.video_fragment;
    }

    /**
     * 初始化布局
     */
    @Override
    protected void initView() {
        mTab = bindView(R.id.tab_video_fragment);
        mVp = bindView(R.id.vp_video_fragment);
        /**
         * 去除tabLayout下面的线
         */
        mTab.setSelectedTabIndicatorColor(Color.argb(00,00,00,00));
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        VideoFragmentAdapter videoFragmentAdapter = new VideoFragmentAdapter(getChildFragmentManager());
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new VideoSonFragment());
        fragments.add(new LiveFragment());
        videoFragmentAdapter.setFragments(fragments);
        mVp.setAdapter(videoFragmentAdapter);
        mTab.setupWithViewPager(mVp);
    }
}
