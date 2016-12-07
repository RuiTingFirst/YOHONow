package lanou.dllo.yohonow.login;

import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import lanou.dllo.yohonow.MainActivity;
import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.base.BaseFragment;

/**
 * Created by dllo on 16/12/6.
 */

public class RegisterFragment extends BaseFragment implements View.OnClickListener, TextWatcher {

    private TextView mTvCountry;
    private EditText mEdtNum;
    private Button mBtnNext;
    private ImageView mIvNumClose;
    private String mNum;

    @Override
    protected int setLayout() {
        return R.layout.register_fragment;
    }

    @Override
    protected void initView() {
        mTvCountry = bindView(R.id.tv_country_register_fragment);
        mEdtNum = bindView(R.id.edt_phone_number_register_fragment);
        mBtnNext = bindView(R.id.btn_next_register_fragment);
        mIvNumClose = bindView(R.id.iv_num_close_small_register_fragment);
    }

    @Override
    protected void initData() {
        /**
         * 点击事件
         */
        setClick(this, mTvCountry, mBtnNext, mIvNumClose);
        /**
         * edt 改变监听
         */
        mEdtNum.addTextChangedListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            /**
             * 选择国家和地区
             */
            case R.id.tv_country_register_fragment:

                break;
            /**
             * 手机号
             */
            case R.id.btn_next_register_fragment:
                if (mNum.length() > 0) {
                    if (mNum.length() == 11) {
                        Intent intent = new Intent(mContext, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(mContext, "你填写的账号格式不正确", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            /**
             * 清空所有
             */
            case R.id.iv_num_close_small_register_fragment:
                mEdtNum.setText("");
                break;
        }
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
        mNum = mEdtNum.getText().toString();
        if (mNum.length() > 0){
            mBtnNext.setBackgroundColor(Color.GREEN);
            mIvNumClose.setVisibility(View.VISIBLE);
        } else {
            mBtnNext.setBackgroundColor(Color.GRAY);
            mIvNumClose.setVisibility(View.GONE);
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
