package lanou.dllo.yohonow.column.columndetail.columnwebview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.base.BaseActivity;
import lanou.dllo.yohonow.greendao.DetailBean;
import lanou.dllo.yohonow.greendao.DetailDBTools;
import lanou.dllo.yohonow.tools.stringtools.StringTools;

/**
 * Created by dllo on 16/12/10.
 */

public class ColumnWebViewDetailAty extends BaseActivity implements View.OnClickListener {

    private String mPublishURL;
    private ImageView mIvBack;
    private ImageView mIvShare;
    private EditText mEdtWriteComment;
    private WebView mWebView;
    private ProgressDialog mPd;
    private ImageView mIvCollect;
    private ImageView mIvCancelCollect;
    private String mImage;
    private String mCreateTime;
    private String mTitle;
    private String mTagName;

    private boolean collect = true;
    private TextView mTvCollect;

    @Override
    protected int setLayout() {
        ShareSDK.initSDK(this, "sharesdk的appkey");
        return R.layout.activity_column_webview_detail;
    }

    @Override
    protected void initView() {
        mIvBack = bindView(R.id.iv_menu_back_activity_column_webview_detail);
        mIvShare = bindView(R.id.iv_ic_share_activity_column_webview_detail);
        mEdtWriteComment = bindView(R.id.edt_write_comment_activity_column_detail);
        mWebView = bindView(R.id.webview_activity_column_webview_detail);
        mIvCollect = bindView(R.id.iv_love_b_activity_column_webview_detail);
        mTvCollect = bindView(R.id.tv_activity_column_webview_detail);
    }

    private void initGetIntentData() {
        Intent intent = getIntent();
        mPublishURL = intent.getStringExtra("publishURL");
        mImage = intent.getStringExtra("image");
        mCreateTime = intent.getStringExtra("createTime");
        mTitle = intent.getStringExtra("title");
        mTagName = intent.getStringExtra("tagName");
    }

    @Override
    protected void initData() {
        /**
         * 获取上一个界面传过来的值
         */
        initGetIntentData();
        /**
         * webView 获取二级界面传过来的数据 显示数据
         */
//        initGetWebViewData();
        /**
         * 点击事件
         */
        setClick(this, mIvBack, mIvShare, mIvCollect);
        /**
         * 根据数据库是否已经收藏本条数据, 显示对应收藏图标
         */
        initSetMipmap();

        initGetWebViewData();

    }

    /**
     * 根据数据库是否已经收藏本条数据, 显示对应收藏图标
     */
    private void initSetMipmap() {
        if (DetailDBTools.getInstance().isHaveTheTitle(mTitle)) {
            mIvCollect.setImageResource(R.mipmap.love_b_s);
            mTvCollect.setText("16");
            collect = false;
        } else {
            mIvCollect.setImageResource(R.mipmap.love_b);
            mTvCollect.setText("15");
            collect = true;
        }
    }

    private void initGetWebViewData() {
        mWebView.loadUrl(mPublishURL);
        /**
         * 设置用webView加载网页
         */
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(mPublishURL);
                return true;
            }
        });
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

    }

//    private void initGetCollectWebViewData() {
//        mWebView.loadUrl(mCollectPublishURL);
//        /**
//         * 设置用webView加载网页
//         */
//        mWebView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                view.loadUrl(mCollectPublishURL);
//                return true;
//            }
//        });
//        WebSettings webSettings = mWebView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//
//    }

    /**
     * 按物理返回键返回上一界面
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
            } else {
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            /**
             * 返回
             */
            case R.id.iv_menu_back_activity_column_webview_detail:
                finish();
                break;
            /**
             * 分享
             */
            case R.id.iv_ic_share_activity_column_webview_detail:
                showShare();
                break;
            /**
             * 收藏 及 取消收藏
             */
            case R.id.iv_love_b_activity_column_webview_detail:
                if (collect) {
                    collect = !collect;
                    mIvCollect.setImageResource(R.mipmap.love_b_s);
                    mTvCollect.setText("16");
                    if (!DetailDBTools.getInstance().isHaveTheTitle(mTitle)) {
                        List<DetailBean> list = new ArrayList<>();
                        DetailBean detailBean = new DetailBean(null, mPublishURL, mTitle, mCreateTime, mTagName, mImage, null, 1);
                        list.add(0, detailBean);
                        DetailDBTools.getInstance().insertList(list);
                        Intent intent = new Intent(StringTools.COLLECT_BROAD);
                        sendBroadcast(intent);
                    }
                } else {
                    collect = !collect;
                    mIvCollect.setImageResource(R.mipmap.love_b);
                    mTvCollect.setText("15");
                    if (DetailDBTools.getInstance().isHaveTheTitle(mTitle)) {
                        DetailDBTools.getInstance().deleteByTitle(mTitle);
                        Intent intent = new Intent(StringTools.COLLECT_BROAD);
                        sendBroadcast(intent);
                    }
                }
                break;


        }
    }

    /**
     * 三方分享
     */
    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(mTagName);
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(mPublishURL);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(mTitle);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(mPublishURL);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(mPublishURL);

        // 启动分享GUI
        oks.show(this);
    }
}
//    private void openDialog(int newProgress) {
//        if (mPd == null) {
//            mPd = new ProgressDialog(ColumnWebViewDetailAty.this);
//            mPd.setTitle("正在加载中");
//            mPd.setIcon(R.mipmap.ic_launcher);
//            mPd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//            mPd.setProgress(newProgress);
//            mPd.show();
//        } else {
//            mPd.setProgress(newProgress);
//            mPd.show();
//        }
//    }
//
//    private void closeDialog() {
//        if (mPd != null && mPd.isShowing()) {
//            mPd.dismiss();
//
//        }
//    }

/**
 * 设置友好提示
 */
//        mWebView.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                super.onProgressChanged(view, newProgress);
//                if (newProgress == 100) {
//                    closeDialog();
//                } else {
//                    openDialog(newProgress);
//                }
//            }
//        });
//mFindWv.getSettings().setJavaScriptEnabled(true);
//        mFindWv.setWebViewClient(new WebViewClient() {
//@Override
//public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//        handler.proceed();
//        }
//
//@Override
//public void onPageStarted(WebView view, String url, Bitmap favicon) {
//        super.onPageStarted(view, url, favicon);
//        }
//
//@Override
//public void onPageFinished(WebView view, String url) {
//        String fun="javascript:function getClass(parent,sClass) { var aEle=parent.getElementsByTagName('div'); var aResult=[]; var i=0; for(i<0;i<aEle.length;i++) { if(aEle[i].className==sClass) { aResult.push(aEle[i]); } }; return aResult; } ";
//
//        view.loadUrl(fun);
//
//        String fun2="javascript:function hideOther() {getClass(document,'navload " +
//        "clearfix')[0].style.display='none';getClass(document,'navbar')[0].style.display='none';}";
//
//        view.loadUrl(fun2);
//
//        view.loadUrl("javascript:hideOther();");
//        super.onPageFinished(view, url);
//        }
//        });
//        mFindWv.loadUrl(mUrlIntent);