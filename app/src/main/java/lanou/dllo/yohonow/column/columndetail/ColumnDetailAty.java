package lanou.dllo.yohonow.column.columndetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.HashMap;

import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.base.BaseActivity;
import lanou.dllo.yohonow.column.columndetail.columnwebview.ColumnWebViewDetailAty;
import lanou.dllo.yohonow.tools.urltools.URLValues;
import lanou.dllo.yohonow.tools.volleytools.NetHelper;
import lanou.dllo.yohonow.tools.volleytools.NetListener;

public class ColumnDetailAty extends BaseActivity implements View.OnClickListener {

    private HashMap<String, String> mMapSure;
    private ColumnDetailBean mColumnDetailBean;
    private ColumnDetailAdapter mAdapter;
    private ListView mListView;
    private TextView mTvTitle;
    private ImageView mIvBack;
    private String mId;
    private String mTitle;
    private ImageView mIvHead;
    private TextView mTvSummary;
    private TextView mTvTotal;

    @Override
    protected int setLayout() {
        return R.layout.activity_column_detail_aty;
    }

    @Override
    protected void initView() {
        mListView = bindView(R.id.lv_activity_column_detail);
        /**
         * 添加头布局
         */
        View headView = LayoutInflater.from(this).inflate(R.layout.head_item_column_detail_activity, null);
        mListView.addHeaderView(headView);
        mIvHead = (ImageView) headView.findViewById(R.id.iv_head_item_column_detail_activity);
        /**
         * 添加尾布局
         */
        View footerView = LayoutInflater.from(this).inflate(R.layout.footer_item_column_detail_activity, null);
        mListView.addFooterView(footerView);
        mTvSummary = (TextView) headView.findViewById(R.id.tv_summary_head_item_column_detail_activity);
        mTvTotal = (TextView) headView.findViewById(R.id.tv_total_head_item_column_detail_activity);
        mTvTitle = bindView(R.id.tv_title_activity_column_detail);
        mIvBack = bindView(R.id.iv_activity_column_detail);
    }

    @Override
    protected void initData() {

        /**
         * 获取intent 传过来的数据
         */
        initGetIntentData();
        /**
         * map
         */
        initColumnDetailMap(mId);
        /**
         * 解析获取数据
         */
        mAdapter = new ColumnDetailAdapter(this);
        initUrlData();
        /**
         * 点击事件
         */
        setClick(this, mIvBack);
        /**
         * item 点击事件
         */
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    Intent intent = new Intent(ColumnDetailAty.this, ColumnWebViewDetailAty.class);
                    intent.putExtra("publishURL", mColumnDetailBean.getData().getContent().get(i - 1).getPublishURL());
                    intent.putExtra("createTime", mColumnDetailBean.getData().getContent().get(i - 1).getCreate_time());
                    intent.putExtra("title", mColumnDetailBean.getData().getContent().get(i - 1).getTitle());
                    intent.putExtra("tagName", mColumnDetailBean.getData().getContent().get(i - 1).getTag().get(0).getTag_name());
                    intent.putExtra("image", mColumnDetailBean.getData().getContent().get(i - 1).getImage());
                    startActivity(intent);
                }

            }
        });
    }

    private void initGetIntentData() {
        Intent intent = getIntent();
        mId = intent.getStringExtra("id");
        mTitle = intent.getStringExtra("title");
        mTvTitle.setText(mTitle);
        mTvSummary.setText(intent.getStringExtra("summary"));
        mTvTotal.setText("更新至" + intent.getStringExtra("total") + "篇");
        Glide.with(this).load(intent.getStringExtra("headImage")).into(mIvHead);
    }

    private void initUrlData() {
        NetHelper.MyRequest(URLValues.COLUMN_DETAIL_URL, ColumnDetailBean.class, new NetListener<ColumnDetailBean>() {
            @Override
            public void successListener(ColumnDetailBean response) {
                mColumnDetailBean = response;
                mAdapter.setColumnDetailBean(response);
                mListView.setAdapter(mAdapter);

            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, mMapSure);
    }

    private void initColumnDetailMap(String id) {
        HashMap<String,String> map = new HashMap<>();
        map.put("id",id);
        map.put("page","1");
        map.put("limit","20");
        map.put("platform","4");
        map.put("locale","zh-Hans");
        map.put("language","zh-Hans");
        map.put("udid","00000000000000063aa461b71c4cfcf");
        map.put("curVersion","5.0.4");

        HashMap<String,String> mapnew  = new HashMap<>();
        mapnew.put("udid","00000000000000063aa461b71c4cfcf");
        Gson gson = new Gson();
        String a = gson.toJson(mapnew).toString();
        map.put("authInfo",a);

        String value = gson.toJson(map).toString();
        mMapSure = new HashMap<>();
        mMapSure.put("parameters",value);
        Log.d("--", value);
    }

    /**
     * 点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            /**
             * 返回
             */
            case R.id.iv_activity_column_detail:
                finish();
                break;
            /**
             *
             */
        }
    }
}
//    String URLVIDEOVIDEOSON = "http://new.yohoboys.com/yohoboyins/v5/channel/getContentDetail";
//    //map
//    HashMap<String, String> map = new HashMap<>();
//map.put("cid", cidVideoSon);
//        map.put("id", idVideoSon);
//        map.put("app", "1");
//        map.put("platform", "4");
//        map.put("locale", "zh-Hans");
//        map.put("language", "zh-Hans");
//        map.put("udid", "00000000000000063aa461b71c4cfcf");
//        map.put("curVersion", "5.0.4");
//        HashMap<String, String> mm = new HashMap<>();
//        mm.put("udid", "00000000000000063aa461b71c4cfcf");
//        Gson gson = new Gson();
//        String a = gson.toJson(mm).toString();
//        map.put("authInfo", a);
//        HashMap<String, String> mapSure = new HashMap<>();
//        String value = gson.toJson(map).toString();
//        mapSure.put("parameters", value);
//
//        NetHelper.MyRequest(URLVIDEOVIDEOSON, BeanVideoVideoTwice.class, new NetListener<BeanVideoVideoTwice>() {
//@Override
//public void successListener(BeanVideoVideoTwice response) {
//        videoView.setMediaController(new MediaController(VideoVideoTwiceActivity.this));
//        videoView.setVideoURI(Uri.parse(response.getData().getContents().getVideoUrl()));
//        videoView.start();
//
//        //文字
//        tvTop.setText(response.getData().getContents().getTitle());
//        tvLeft.setText(response.getData().getContents().getTag().get(0).getTag_name());
//        tvRight.setText(MyStringTool.intoTime(response.getData().getContents().getCreate_time()));
//
//        webView.loadDataWithBaseURL("about:blank", response.getData().getContents().getContent(), "text/html"
//        , "utf-8", null);
//
//        //id是22971   cid是5131
//        }
//
//@Override
//public void errorListener(VolleyError error) {
//
//        }
//        },mapSure);