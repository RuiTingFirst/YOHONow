package lanou.dllo.yohonow.home.homeseek;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.base.BaseActivity;

public class SeekActivity extends BaseActivity implements View.OnClickListener {


    private TabLayout mTab;
    private ViewPager mVp;
    private ImageView mIvSearch;
    private EditText mEdtSearch;

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
        mIvSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
