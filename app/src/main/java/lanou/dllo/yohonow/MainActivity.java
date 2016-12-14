package lanou.dllo.yohonow;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import lanou.dllo.yohonow.base.BaseActivity;
import lanou.dllo.yohonow.collect.CollectActivity;
import lanou.dllo.yohonow.column.ColumnFragment;
import lanou.dllo.yohonow.community.CommunityFragment;
import lanou.dllo.yohonow.home.HomeFragment;
import lanou.dllo.yohonow.login.LogInActivity;
import lanou.dllo.yohonow.magazine.MagazineFragment;
import lanou.dllo.yohonow.mymagazine.MyMZActivity;
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
    private PlatformActionListener platformActionListener;
    private String mName;
    private String mIcon;

    @Override
    protected int setLayout() {
        ShareSDK.initSDK(this, "sharesdk的appkey");
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
        login();
        /**
         * RadioButton 点击事件
         */
        setClick(this, mRBHome, mRBColumn, mRBCommunity, mRBVideo, mRBMagazine);
        /**
         * 抽屉布局的点击事件
         */
        setClick(this, mTvCollect, mIvLonIn, mTvLogIn, mTvComment, mTvFeedback, mTvMyMagazine, mTvMyQuestion, mTvSetting);

    }

    private void login() {
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        //回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
        try {
            PlatformDb platformDb = qq.getDb();
            mName = platformDb.getUserName();
            mIcon = platformDb.getUserIcon();

            if (!TextUtils.isEmpty(mName)) {
                mTvLogIn.setText(mName);
                Glide.with(this).load(mIcon).bitmapTransform(new CropCircleTransformation(this)).into(mIvLonIn);
            }
        } catch (NullPointerException e) {

        }

        platformActionListener = new PlatformActionListener() {

            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                arg2.printStackTrace();
            }

            @Override
            public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                //输出所有授权信息
                arg0.getDb().exportData();
            }

            @Override
            public void onCancel(Platform arg0, int arg1) {
            }
        };

    }

    // fragment替换
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
            /**
             * 登录
             */
            case R.id.tv_draw_layout_deng_lu:
                Intent intentOne = new Intent(this, LogInActivity.class);
                startActivityForResult(intentOne, 1);
                break;
            /**
             * 登录
             */
            case R.id.iv_draw_layout_default_head:
                Intent intentTwo = new Intent(this, LogInActivity.class);
                startActivityForResult(intentTwo, 1);
                break;
            /**
             * 我的杂志
             */
            case R.id.tv_draw_layout_magazine:
                Intent intent = new Intent(this, MyMZActivity.class);
                startActivity(intent);
                break;
            /**
             * 我的收藏
             */
            case R.id.tv_draw_layout_shou_cang:
                Intent intentCollect = new Intent(this, CollectActivity.class);
                startActivity(intentCollect);
                break;
            /**
             * 我的提问
             */
            case R.id.tv_draw_layout_my_question:
                Platform platform = ShareSDK.getPlatform(QQ.NAME);
                if (platform.isAuthValid()) {
                    // isValid和removeAccount不开启线程，会直接返回。
                    platform.removeAccount(true);// 移除授权
                    mTvLogIn.setText("登录");
                    mIvLonIn.setImageResource(R.mipmap.default_head);


                } else {
                    Toast.makeText(this, "退出登录", Toast.LENGTH_SHORT).show();
                }
                // 实现接口回调(login中的)

                platform.setPlatformActionListener(platformActionListener);
                // authorize与showUser单独调用一个即可
//                platform.authorize();//单独授权，OnComplete返回的hashmap是空的
//                platform.showUser(null);//授权并获取用户信息
                setResult(-1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            Toast.makeText(this, "已经登录", Toast.LENGTH_SHORT).show();
            mIcon = "";
            mName = "";
            mTvLogIn.setText("登录");
            mIvLonIn.setImageResource(R.mipmap.default_head);
        }

        if (data != null && requestCode == 1 && resultCode == 0) {

            mTvLogIn.setText(data.getStringExtra("name"));
            Picasso.with(this).load(data.getStringExtra("icon")).into(mIvLonIn);
        }
    }
}
