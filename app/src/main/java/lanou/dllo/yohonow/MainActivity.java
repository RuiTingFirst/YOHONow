package lanou.dllo.yohonow;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import lanou.dllo.yohonow.base.BaseActivity;
import lanou.dllo.yohonow.column.ColumnFragment;
import lanou.dllo.yohonow.community.CommunityFragment;
import lanou.dllo.yohonow.home.HomeFragment;
import lanou.dllo.yohonow.magazine.MagazineFragment;
import lanou.dllo.yohonow.video.VideoFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private FrameLayout mFm;
    private RadioButton mRBHome;
    private RadioButton mRBColumn;
    private RadioButton mRBCommunity;
    private RadioButton mRBVideo;
    private RadioButton mRBMagazine;
    private Intent mIntent;
    private FragmentManager mManager;
    private FragmentTransaction mTransaction;
    private DrawerLayout mDrawerLayout;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mFm = bindView(R.id.fm_main);
        mRBHome = bindView(R.id.rb_footer_home_main);
        mRBColumn = bindView(R.id.rb_footer_column_main);
        mRBCommunity = bindView(R.id.rb_footer_community_main);
        mRBVideo = bindView(R.id.rb_footer_video_main);
        mRBMagazine = bindView(R.id.rb_footer_magazine_main);
        // 抽屉
        mDrawerLayout = bindView(R.id.draw_layout);
        mManager = getSupportFragmentManager();
        // 默认首页为HomeFragment()
        addFragment(new HomeFragment());
    }

    private void addFragment(Fragment fragment) {
        mTransaction = mManager.beginTransaction();
        mTransaction.replace(R.id.fm_main, fragment);
        mTransaction.commit();

    }

    @Override
    protected void initData() {
        setClick(this, mRBHome);
        setClick(this, mRBColumn);
        setClick(this, mRBCommunity);
        setClick(this, mRBVideo);
        setClick(this, mRBMagazine);
    }

    // fragment替换
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rb_footer_home_main:
               addFragment(new HomeFragment());
                break;
            case R.id.rb_footer_column_main:
                addFragment(new ColumnFragment());
                // 禁止抽屉拉出
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                break;
            case R.id.rb_footer_community_main:
                addFragment(new CommunityFragment());
                // 禁止抽屉拉出
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                break;
            case R.id.rb_footer_video_main:
                addFragment(new VideoFragment());
                // 禁止抽屉拉出
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                break;
            case R.id.rb_footer_magazine_main:
                addFragment(new MagazineFragment());
                // 禁止抽屉拉出
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                break;
        }
    }
}
