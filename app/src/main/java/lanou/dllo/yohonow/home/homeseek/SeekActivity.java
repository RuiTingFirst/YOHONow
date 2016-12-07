package lanou.dllo.yohonow.home.homeseek;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;

import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.base.BaseActivity;
import lanou.dllo.yohonow.tools.urltools.URLValues;
import lanou.dllo.yohonow.tools.volleytools.NetHelper;
import lanou.dllo.yohonow.tools.volleytools.NetListener;

public class SeekActivity extends BaseActivity implements View.OnClickListener {


    private TabLayout mTab;
    private ViewPager mVp;
    private ImageView mIvSearch;
    private EditText mEdtSearch;
    private SeekAdapter mSeekAdapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_seek;
    }

    @Override
    protected void initView() {
        mTab = bindView(R.id.tab_home_seek_activity);
        mVp = bindView(R.id.vp_home_seek_activity);
        mEdtSearch = bindView(R.id.edt_home_seek_activity);
        mIvSearch = bindView(R.id.iv_search_cancel_home_seek_activity);
    }

    @Override
    protected void initData() {
        /**
         * 搜索
         */
        setClick(this, mIvSearch);
        /**
         * 获取tab 的标题
         */
        mSeekAdapter = new SeekAdapter(getSupportFragmentManager());
        initGetTabTitle();
    }

    private void initGetTabTitle() {
        NetHelper.MyRequest(URLValues.HOME_SEEK_TITLE_URL, SeekTitleBean.class, new NetListener<SeekTitleBean>() {
            @Override
            public void successListener(SeekTitleBean response) {
                mSeekAdapter.setSeekTitleBean(response);
                mVp.setAdapter(mSeekAdapter);
                mTab.setupWithViewPager(mVp);
                mTab.setTabMode(TabLayout.MODE_SCROLLABLE);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
