package lanou.dllo.yohonow;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import lanou.dllo.yohonow.base.BaseActivity;
import lanou.dllo.yohonow.column.ColumnFragment;
import lanou.dllo.yohonow.community.CommunityFragment;
import lanou.dllo.yohonow.home.HomeFragment;
import lanou.dllo.yohonow.login.LogInActivity;
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
    private ImageView mIvLonIn;
    private TextView mTvLogIn;
    private TextView mTvCollect;
    private TextView mTvMyQuestion;
    private TextView mTvMyMagazine;
    private TextView mTvFeedback;
    private TextView mTvSetting;
    private TextView mTvComment;

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
        /**
         * 抽屉
         */
        mDrawerLayout = bindView(R.id.draw_layout);
        mIvLonIn = bindView(R.id.iv_draw_layout_default_head);
        mTvLogIn = bindView(R.id.tv_draw_layout_deng_lu);
        mTvCollect = bindView(R.id.tv_draw_layout_shou_cang);
        mTvMyQuestion = bindView(R.id.tv_draw_layout_my_question);
        mTvMyMagazine = bindView(R.id.tv_draw_layout_magazine);
        mTvFeedback = bindView(R.id.feedback_main_draw_layout);
        mTvSetting = bindView(R.id.setting_main_draw_layout);
        mTvComment = bindView(R.id.comment_main_draw_layout);
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
        /**
         * RadioButton 点击事件
         */
        setClick(this, mRBHome, mRBColumn, mRBCommunity, mRBVideo, mRBMagazine);
        /**
         * 抽屉布局的点击事件
         */
        setClick(this, mTvCollect, mIvLonIn, mTvLogIn, mTvComment, mTvFeedback, mTvMyMagazine, mTvMyQuestion, mTvSetting);

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
            case R.id.tv_draw_layout_deng_lu:
                Intent intentOne = new Intent(this, LogInActivity.class);
                startActivity(intentOne);
                break;
            case R.id.iv_draw_layout_default_head:
                Intent intentTwo = new Intent(this, LogInActivity.class);
                startActivity(intentTwo);
                break;
        }
    }
}
