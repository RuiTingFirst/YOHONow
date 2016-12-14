package lanou.dllo.yohonow.login;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wevey.selector.dialog.DialogOnItemClickListener;
import com.wevey.selector.dialog.MDSelectionDialog;
import com.wevey.selector.dialog.NormalAlertDialog;

import java.util.ArrayList;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import lanou.dllo.yohonow.MainActivity;
import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.base.BaseFragment;

/**
 * Created by dllo on 16/12/6.
 */

public class LogInFragment extends BaseFragment implements View.OnClickListener, TextWatcher {
    private ImageView mIvFamily;
    private ImageView mIvYohoSmall;
    private TextView mTvCanRegister;
    private EditText mEdtNum;
    private EditText mEdtPassword;
    private ImageView mIvSee;
    private ImageView mIvUnSee;
    private Button mBtnLogIn;
    private TextView mTvForgetPassword;
    private ImageView mIvWeiChat;
    private ImageView mIvWeiBo;
    private ImageView mIvQQ;
    private ImageView mIvMore;
    private LinearLayout mLlFUP;
    private ImageView mIvF;
    private ImageView mIvUp;
    private TextView mTvInternational;
    private LinearLayout mLlInternational;
    private TextView mTvRegister;
    private String mNum;
    private String mPassword;
    private ImageView mIvCloseSmall;
    private ImageView mIvNumCloseSmall;
    private NormalAlertDialog mDialog;
    private PopupWindow mMPopupWindow;
    private View mView;
    private TextView mTvPhone;
    private TextView mTvMailbox;
    private TextView mTvCancel;

    @Override
    protected int setLayout() {
        return R.layout.login_fragment;
    }

    @Override
    protected void initView() {
        mIvFamily = bindView(R.id.iv_yoho_family_log_in_activity);
        mIvYohoSmall = bindView(R.id.iv_yoho_famile_small_log_in_activity);
        mTvCanRegister = bindView(R.id.tv_question_log_in_activity);
        mEdtNum = bindView(R.id.edt_phone_number_log_in_activity);
        mEdtPassword = bindView(R.id.edt_password_log_in_activity);
        mIvSee = bindView(R.id.iv_login_password_see_icon_log_in_activity);
        mIvUnSee = bindView(R.id.iv_login_password_unsee_icon_log_in_activity);
        mBtnLogIn = bindView(R.id.btn_denglu_login_activity);
        mTvForgetPassword = bindView(R.id.tv_forget_password_lon_in_activity);
        mIvWeiChat = bindView(R.id.iv_wechat_log_in_activity);
        mIvWeiBo = bindView(R.id.iv_weibo_log_in_activity);
        mIvQQ = bindView(R.id.iv_qq_login_activity);
        mIvMore = bindView(R.id.iv_more_login_activity);
        mLlFUP = bindView(R.id.ll_f_up_login_activity);
        mIvF = bindView(R.id.iv_f_login_activity);
        mIvUp = bindView(R.id.iv_up_lohin_activity);
        mTvInternational = bindView(R.id.tv_international_customer_login_activity);
        mLlInternational = bindView(R.id.ll_international_customer_login_activity);
        mTvRegister = bindView(R.id.tv_register_login_activity);
        mIvCloseSmall = bindView(R.id.iv_close_small_login_activity);
        mIvNumCloseSmall = bindView(R.id.iv_num_close_small_login_activity);
    }

    @Override
    protected void initData() {
        /**
         * edt 输入时
         */
        initEdt();
        /**
         * 获取焦点, 如果有焦点 大图隐藏, 小图显示
         */
        initSetOnFocus();
        /**
         * 点击事件
         */
        setClick(this, mBtnLogIn, mBtnLogIn, mIvFamily, mIvYohoSmall, mTvCanRegister, mIvUnSee, mIvSee, mBtnLogIn, mTvForgetPassword,
                mIvWeiChat, mIvWeiBo, mIvMore, mIvQQ, mIvF, mIvUp, mTvInternational, mTvRegister, mIvCloseSmall, mIvNumCloseSmall, mEdtNum, mEdtPassword);
    }


    /**
     * 获取焦点, 如果有焦点 大图隐藏, 小图显示
     */
    private void initSetOnFocus() {
        mEdtNum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    mIvFamily.setVisibility(View.GONE);
                    mIvYohoSmall.setVisibility(View.VISIBLE);
                } else {
                    mIvFamily.setVisibility(View.VISIBLE);
                    mIvYohoSmall.setVisibility(View.GONE);
                }
            }
        });
        /**
         * 获取密码焦点
         */
        mEdtPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    mIvFamily.setVisibility(View.GONE);
                    mIvYohoSmall.setVisibility(View.VISIBLE);
                } else {
                    mIvFamily.setVisibility(View.VISIBLE);
                    mIvYohoSmall.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            /**
             * 账号可登录
             */
            case R.id.tv_question_log_in_activity:
                initDialog();
                break;
            /**
             * 显示密码
             */
            case R.id.iv_login_password_see_icon_log_in_activity:
                mIvUnSee.setVisibility(View.VISIBLE);
                mIvSee.setVisibility(View.GONE);
                // 隐藏密码
                mEdtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                // 光标在输入位置显示
                mEdtPassword.setSelection(mPassword.length());
                break;
            /**
             * 隐藏密码
             */
            case R.id.iv_login_password_unsee_icon_log_in_activity:
                mIvSee.setVisibility(View.VISIBLE);
                mIvUnSee.setVisibility(View.GONE);
                // 显示密码
                mEdtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                // 光标在输入位置显示
                mEdtPassword.setSelection(mPassword.length());
                break;
            /**
             * 登录
             */
            case R.id.btn_denglu_login_activity:
                Log.d("LogInActivity", "14" + "---");
                break;
            /**
             * 忘记密码
             */
            case R.id.tv_forget_password_lon_in_activity:
                Log.d("LogInFragment", "--" + 1312321);
                initForgetPasswordPop();
                break;
            /**
             * 微信
             */
            case R.id.iv_wechat_log_in_activity:
                Log.d("LogInActivity", "15" + "---");
                break;
            /**
             * 微博
             */
            case R.id.iv_weibo_log_in_activity:
                Log.d("LogInActivity", "16" + "---");
                break;
            /**
             * 更多
             */
            case R.id.iv_more_login_activity:
                Log.d("LogInActivity", "17" + "---");
                mLlFUP.setVisibility(View.VISIBLE);
                mIvQQ.setVisibility(View.VISIBLE);
                mIvMore.setVisibility(View.GONE);
                mLlInternational.setVisibility(View.VISIBLE);
                break;
            /**
             * QQ
             */
            case R.id.iv_qq_login_activity:
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.authorize();
                qq.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        PlatformDb platformDb = platform.getDb();
                        Intent intent = new Intent();
                        Log.e("tag", "name:" + platformDb.getUserName() + "---" + platformDb.getUserIcon());
                        intent.putExtra("name", platformDb.getUserName());
                        intent.putExtra("icon", platformDb.getUserIcon());
                        getActivity().setResult(0, intent);
                        getActivity().finish();
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });
                break;
            case R.id.iv_f_login_activity:
                Log.d("LogInActivity", "1" + "---");
                break;
            /**
             * 向上隐藏
             */
            case R.id.iv_up_lohin_activity:
                Log.d("LogInActivity", "18" + "---");
                mIvQQ.setVisibility(View.GONE);
                mLlFUP.setVisibility(View.GONE);
                mIvMore.setVisibility(View.VISIBLE);
                mLlInternational.setVisibility(View.GONE);
                break;
            case R.id.tv_international_customer_login_activity:
                Log.d("LogInActivity", "22" + "---");
                break;
            /**
             * 注册
             */
            case R.id.tv_register_login_activity:
                /**
                 * 先把vp设置成静态的 然后类调用 setCurrentItem(1)是设置跳到的页 参数是第几页 从0 开始
                 */
                LogInActivity.mVp.setCurrentItem(1);
                break;
            /**
             * 输入密码时显示, 点击时删除所有
             */
            case R.id.iv_close_small_login_activity:
                mEdtPassword.setText("");
                break;
            /**
             * 输入账号时显示, 点击时删除所有
             */
            case R.id.iv_num_close_small_login_activity:
                //   edt_phone_number_log_in_activity
                Log.d("LogInFragment", "强强有点二" + "--");
                mEdtNum.setText("");
                break;
            /**
             * 号码输入
             */
            case R.id.edt_phone_number_log_in_activity:

                break;
        }
    }

    private void initForgetPasswordPop() {

        mMPopupWindow = new PopupWindow(mContext);
        // 铺满屏
        mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_login_fragment, null);
        mTvPhone = (TextView) mView.findViewById(R.id.tv_phone_to_retrieve_password_dialog_login_fragment);
        mTvMailbox = (TextView) mView.findViewById(R.id.tv_mailbox_to_retrieve_password_dialog_login_fragment);
        mTvCancel = (TextView) mView.findViewById(R.id.tv_cancel_dialog_login_fragment);
        RelativeLayout rl = (RelativeLayout) mView.findViewById(R.id.rl_dialog_login_fragment);
        mMPopupWindow = new PopupWindow(mView, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        // 设置动画效果
        mMPopupWindow.setAnimationStyle(R.style.AnimationFade);
        mMPopupWindow.setFocusable(true);
        mMPopupWindow.setContentView(mView);
        // 通过手机找回密码
        mTvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogInActivity.mVp.setCurrentItem(1);
                mMPopupWindow.dismiss();
            }
        });
        // 通过邮箱找回密码
        mTvMailbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogInActivity.mVp.setCurrentItem(1);
                mMPopupWindow.dismiss();
            }
        });
        // 点击pop消失
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMPopupWindow.dismiss();
            }
        });
        if (!mMPopupWindow.isShowing()) {
            mMPopupWindow.showAtLocation(mTvForgetPassword, Gravity.BOTTOM, 0, 0);
        } else {
            mMPopupWindow.dismiss();
        }

    }

    private void initDialog() {
        mDialog = new NormalAlertDialog.Builder(mContext).setHeight(0.3f).setWidth(0.8f)
                .setTitleVisible(true).setTitleText("YoHo!Family").setTitleTextColor(R.color.colorBlack).
                        setContentText("Yoho!Family账号可登录YohoBuy!有货、Yoho!Now及SHOW")
                .setContentTextSize(13).setSingleMode(true).setSingleButtonText("确定").
                        setSingleButtonTextColor(R.color.colorBlack).setCanceledOnTouchOutside(false)
                .setSingleListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDialog.dismiss();
                    }
                }).build();
        mDialog.show();
    }

    /**
     * edt 操作
     */
    private void initEdt() {
        mNum = mEdtNum.getText().toString();
        mPassword = mEdtPassword.getText().toString();
        /**
         * edt 改变时的监听
         */
        mEdtPassword.addTextChangedListener(this);
        mEdtNum.addTextChangedListener(this);

    }

    /**
     * edt 改变前执行的方法
     *
     * @param charSequence
     * @param i
     * @param i1
     * @param i2
     */
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    /**
     * edt 改变时执行的方法
     *
     * @param charSequence
     * @param i
     * @param i1
     * @param i2
     */
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        /**
         * edt 的内容需要在这个方法里面获取
         */
        mNum = mEdtNum.getText().toString();
        mPassword = mEdtPassword.getText().toString();
        /**
         * 显示清空账号按钮
         */
        if (!mNum.isEmpty()) {
            mIvNumCloseSmall.setVisibility(View.VISIBLE);
        } else {
            mIvNumCloseSmall.setVisibility(View.GONE);
        }
        /**
         * 显示清空密码按钮
         */
        if (!mPassword.isEmpty()) {
            mIvCloseSmall.setVisibility(View.VISIBLE);
        } else {
            mIvCloseSmall.setVisibility(View.GONE);
        }
        /**
         * 按钮变色
         */
        if (!mNum.isEmpty() && !mPassword.isEmpty()) {
            mBtnLogIn.setBackgroundColor(Color.GREEN);
        } else {
            mBtnLogIn.setBackgroundColor(Color.GRAY);
        }
    }

    /**
     * EditText内容已经改变之后调用
     *
     * @param editable
     */
    @Override
    public void afterTextChanged(Editable editable) {

    }
}
