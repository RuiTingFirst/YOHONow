package lanou.dllo.yohonow.login;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import lanou.dllo.yohonow.MainActivity;
import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.base.BaseActivity;

/**
 * Created by dllo on 16/12/3.
 */
public class LogInActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private ImageView mIvClose;
    public static ViewPager mVp;
    private TextView mTvYoho;

    @Override
    protected int setLayout() {
        return R.layout.log_in_activity;
    }

    @Override
    protected void initView() {
        mIvClose = bindView(R.id.iv_close_big_log_in_activity);
        mVp = bindView(R.id.vp_login_activity);
        mTvYoho = bindView(R.id.tv_yoho_family_login_activity);
    }

    @Override
    protected void initData() {
        /**
         * 点击事件
         */
        setClick(this, mIvClose);
        /**
         * 注入fragment
         */
        initFragment();
        /**
         * vp 滑动监听 在注册页显示标题
         */
        mVp.setOnPageChangeListener(this);
    }

    private void initFragment() {
        LogInAdapter adapter = new LogInAdapter(getSupportFragmentManager());
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new LogInFragment());
        fragments.add(new RegisterFragment());
        adapter.setFragments(fragments);
        mVp.setAdapter(adapter);
    }


    /**
     * 点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            /**
             * 返回
             */
            case R.id.iv_close_big_log_in_activity:
                finish();
                break;
        }
    }

    /**
     * 正在滑动页
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 滑动到 页
     * @param position
     */
    @Override
    public void onPageSelected(int position) {

        if (position == 1){
            mTvYoho.setVisibility(View.VISIBLE);
        } else {
            mTvYoho.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 滑动状态改变
     * @param state
     */
    @Override
    public void onPageScrollStateChanged(int state) {

    }

//    /**
//     * edt 改变前执行的方法
//     * @param charSequence
//     * @param i
//     * @param i1
//     * @param i2
//     */
//    @Override
//    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//    }
//
//    /**
//     * edt 改变时执行的方法
//     * @param charSequence
//     * @param i
//     * @param i1
//     * @param i2
//     */
//    @Override
//    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//        /**
//         * edt 的内容需要在这个方法里面获取
//         */
//        mNum = mEdtNum.getText().toString();
//        mPassword = mEdtPassword.getText().toString();
//        if (!mPassword.isEmpty()){
//            mIvCloseSmall.setVisibility(View.VISIBLE);
//        } else {
//            mIvCloseSmall.setVisibility(View.GONE);
//        }
//    }
//
//    /**
//     * EditText内容已经改变之后调用
//     * @param editable
//     */
//    @Override
//    public void afterTextChanged(Editable editable) {
//
//    }
}
