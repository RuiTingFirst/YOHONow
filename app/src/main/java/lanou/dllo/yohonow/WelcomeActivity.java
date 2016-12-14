package lanou.dllo.yohonow;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;

import java.util.HashMap;

import lanou.dllo.yohonow.base.BaseActivity;
import lanou.dllo.yohonow.tools.urltools.URLValues;
import lanou.dllo.yohonow.tools.volleytools.NetHelper;
import lanou.dllo.yohonow.tools.volleytools.NetListener;

/**
 * Created by dllo on 16/12/9.
 */

public class WelcomeActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mIv;
    private Button mBtn;
    private CountDownTimer timer;

    @Override
    protected int setLayout() {
        return R.layout.welcome_activity;
    }

    @Override
    protected void initView() {
        mIv = bindView(R.id.iv_welcome_activity);
        mBtn = bindView(R.id.btn_welcome_activity);
    }

    @Override
    protected void initData() {
        /**
         * 获取图片
         */
        initGetImage();
        /**
         * 点击事件
         */
        mBtn.setOnClickListener(this);
        /**
         * 倒计时
         */
        setTimeDesign();

    }

    private void initGetImage() {
        HashMap<String, String> map = new HashMap<>();
        map.put(URLValues.COLUMN_KEY, URLValues.WELCOM_VALUE);
        NetHelper.MyRequest(URLValues.WELCOME_URL, WelcomeBean.class, new NetListener<WelcomeBean>() {
            @Override
            public void successListener(WelcomeBean response) {
                if (!response.getData().getPic().isEmpty()) {
                    Glide.with(WelcomeActivity.this).load(response.getData().getPic()).into(mIv);
                }
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, map);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    // 倒计时方法
    private void setTimeDesign() {

        timer = new CountDownTimer(6000, 1000) {

            @Override
            public void onTick(long l) {

                mBtn.setText("跳转" + (l / 1000));
            }
            @Override
            public void onFinish() {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                timer.cancel();
                finish();
            }
        };

        timer.start();
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                timer.cancel();
            }
        });
    }
}
