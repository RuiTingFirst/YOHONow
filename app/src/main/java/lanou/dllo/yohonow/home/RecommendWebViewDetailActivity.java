package lanou.dllo.yohonow.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.base.BaseActivity;

public class RecommendWebViewDetailActivity extends BaseActivity {


   private WebView mWebView;
    @Override
    protected int setLayout() {
        return R.layout.activity_recommend_web_view_detail;
    }

    @Override
    protected void initView() {
      mWebView = bindView(R.id.activity_recommend_web_view_detail_webView);
    }

    @Override
    protected void initData() {

    }
}
