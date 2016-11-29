package lanou.dllo.yohonow.magazine;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.base.BaseFragment;
import lanou.dllo.yohonow.magazine.mzson.MZSonFragment;
import lanou.dllo.yohonow.magazine.wallpaper.WPFragment;

/**
 * Created by dllo on 16/11/24.
 */

public class MagazineFragment extends BaseFragment {

    private TabLayout mTab;
    private ViewPager mVp;
    private MagazineAdapter mMagazineAdapter;

    /**
     * 绑定布局
     * @return
     */
    @Override
    protected int setLayout() {
        return R.layout.magazine_fragment;
    }

    /**
     * 初始化布局
     */
    @Override
    protected void initView() {
        mTab = bindView(R.id.tab_magazine_fragment);
        mVp = bindView(R.id.vp_magazine_fragment);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        mMagazineAdapter = new MagazineAdapter(getChildFragmentManager());
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new MZSonFragment());
        fragments.add(new WPFragment());
        mMagazineAdapter.setFragments(fragments);
        mVp.setAdapter(mMagazineAdapter);
        mTab.setupWithViewPager(mVp);
        /**
         * 把tablayout的下划线设置透明
         */
        mTab.setSelectedTabIndicatorColor(Color.argb(00,00,00,00));
    }
}
