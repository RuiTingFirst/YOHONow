package lanou.dllo.yohonow.video.videoson.videodetail;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.base.BaseActivity;
import lanou.dllo.yohonow.column.columndetail.columnwebview.ColumnWebViewDetailAty;
import lanou.dllo.yohonow.greendao.DetailBean;
import lanou.dllo.yohonow.greendao.DetailDBTools;
import lanou.dllo.yohonow.tools.stringtools.StringTools;

public class VideoDetailActivity extends BaseActivity implements View.OnClickListener {

    private WebView mWebView;
    private String mPublishURL;
    private ProgressDialog mPd;
    private String mVideoURL;
    private VideoView mVideoView;
    private ImageView mIvShare;
    private ImageView mIvBack;
    private ImageView mIvComment;
    private ImageView mIvCollect;
    private boolean collect = true;
    private String mTitle;
    private String mImage;
    private String mCreateTime;
    private String mTagName;

    @Override
    protected int setLayout() {
        return R.layout.activity_vedeo_detail;
    }

    @Override
    protected void initView() {
        mWebView = bindView(R.id.webview_activity_video_detail);
        mVideoView = bindView(R.id.video_view_activity_video_detail);
        mIvShare = bindView(R.id.iv_share_activity_video_detail);
        mIvBack = bindView(R.id.iv_back_w_activity_video_detail);
        mIvComment = bindView(R.id.iv_comment_b_activity_video_webview_detail);
        mIvCollect = bindView(R.id.iv_love_b_activity_video_webview_detail);
    }

    @Override
    protected void initData() {
        /**
         * getIntentData 数据
         */
        initGetIntentData();
        /**
         * 获取webView 数据, 并显示
         */
        initGetWebViewData();
        /**
         * 播放视频
         */
        initVideoPlay();
        /**
         * 点击事件
         */
        setClick(this, mIvBack, mIvCollect, mIvShare);
        /**
         * 根据数据库是否已经收藏本条数据, 显示对应收藏图标
         */
        initSetMipmap();
    }

    /**
     * 播放视频
     */
    private void initVideoPlay() {
        Uri uri = Uri.parse(mVideoURL);
        mVideoView.setVideoURI(uri);
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.start();
        /**
         * 准备播放监听
         */
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            }
        });
        /**
         * 播放结束的监听
         */
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mVideoView.setVideoPath(mVideoURL);
                mVideoView.start();
            }
        });

    }

    private void initGetIntentData() {
        Intent intent = getIntent();
        mPublishURL = intent.getStringExtra("publishURL");
        mVideoURL = intent.getStringExtra("videoURL");
        mTitle = intent.getStringExtra("title");
        mImage = intent.getStringExtra("image");
        mCreateTime = intent.getStringExtra("createTime");
        mTagName = intent.getStringExtra("tagName");
    }

    /**
     * 获取webView 数据, 并显示
     */
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

    /**
     * 点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back_w_activity_video_detail:
                finish();
                break;
            case R.id.iv_share_activity_video_detail:
                showShare();
                break;

            case R.id.iv_love_b_activity_video_webview_detail:
                if (collect) {
                    collect = !collect;
                    mIvCollect.setImageResource(R.mipmap.love_b_s);
                    if (!DetailDBTools.getInstance().isHaveTheTitle(mTitle)) {
                        List<DetailBean> list = new ArrayList<>();
                        DetailBean detailBean = new DetailBean(null, mPublishURL, mTitle, mCreateTime, mTagName, mImage, mVideoURL, 2);
                        list.add(0, detailBean);
                        DetailDBTools.getInstance().insertList(list);
                        Intent intent = new Intent(StringTools.COLLECT_BROAD);
                        sendBroadcast(intent);
                    }
                } else {
                    collect = !collect;
                    mIvCollect.setImageResource(R.mipmap.love_b);
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
     * 根据数据库是否已经收藏本条数据, 显示对应收藏图标
     */
    private void initSetMipmap() {
        if (DetailDBTools.getInstance().isHaveTheTitle(mTitle)) {
            mIvCollect.setImageResource(R.mipmap.love_b_s);
            collect = false;
        } else {
            mIvCollect.setImageResource(R.mipmap.love_b);
            collect = true;
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
//一、查看元素
//        在浏览器中打开需要加载的网页，鼠标右键，选择检查，找到需要隐藏的元素的div标签的class/id名称，如图：
//
//        二、编写js代码
//        在assets文件夹下，新建一个html文件，在里面写js代码(assets文件夹和java代码文件夹是同一级别的)，如图：
//
//        具体代码如下：
//        //根据class名称获取div数组
//        function getClass(parent,sClass)
//        {
//        var aEle=parent.getElementsByTagName('div');
//        var aResult=[];
//        var i=0;
//        for(i<0;i<aEle.length;i++)
//        {
//        if(aEle[i].className==sClass)
//        {
//        aResult.push(aEle[i]);
//        }
//        };
//        return aResult;
//        }
////更改特定div的css属性
//        function hideOther()
//        {
//        getClass(document,'nav-sides')[0].style.display='none';
//        getClass(document,'side-bar')[0].style.display='none';
//        getClass(document,'area-main')[0].style.display='none';
//        getClass(document,'home-foot')[0].style.display='none';
//        getClass(document,'enter')[0].style.display='none';
//        getClass(document,'crumb')[0].style.display='none';
//        getClass(document,'date-tab clearfix')[0].style.display='none';
//        document.getElementById('id_sidebar').style.display='none';
//        document.getElementById('top_nav').style.display='none';
//        document.getElementById('fix-personal').style.display='none';
//        document.getElementById('waterlogo').style.display='none';
//
//        getClass(document,'wrap')[0].style.minWidth=0;
//        getClass(document,'game')[0].style.paddingTop=0;
//        }
//
//        三、加载WebView
//        webSettings.setJavaScriptEnabled(true);// 设置支持javascript脚本
//
//        webview.setWebViewClient(new WebViewClient() {
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
//        String fun="javascript:function getClass(parent,sClass) { var aEle=parent.getElementsByTagName('div'); var aResult=[]; var i=0;                 for(i<0;i<aEle.length;i++) { if(aEle[i].className==sClass) { aResult.push(aEle[i]); } }; return aResult; } ";
//        view.loadUrl(fun);
//
//        /**
//         * 'navload clearfix' : 需要隐藏的类名
//         * 'navbar' : 需要隐藏的类名
//         */
//        String fun2="javascript:function hideOther() {getClass(document,'navload clearfix')[0].style.display='none';
//        getClass(document,'navbar')[0].style.display='none';}";
//        view.loadUrl(fun2);
//
//        view.loadUrl("javascript:hideOther();");
//        super.onPageFinished(view, url);
//        }
//        });
//
//        webview.loadUrl(url_web);
//
//        四、效果
//
//
//        五、不足
//        问题：只有在网页加载完后才会执行我们的js代码，隐藏掉我们需要隐藏的元素。
//        解决方法：可以在onPageStarted的时候显示正在加载的动画，加载完后再显示页面