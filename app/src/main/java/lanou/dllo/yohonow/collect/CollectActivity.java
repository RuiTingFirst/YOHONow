package lanou.dllo.yohonow.collect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.IntentFilter;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.base.BaseActivity;

import lanou.dllo.yohonow.column.columndetail.columnwebview.ColumnWebViewDetailAty;
import lanou.dllo.yohonow.greendao.DetailBean;
import lanou.dllo.yohonow.greendao.DetailDBTools;
import lanou.dllo.yohonow.tools.stringtools.StringTools;
import lanou.dllo.yohonow.video.videoson.videodetail.VideoDetailActivity;

/**
 * Created by dllo on 16/12/10.
 */

public class CollectActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mIvBack;
    private TextView mTvCancel;
    private ListView mListView;
    private TextView mTvEdit;
    private CollectAdapter mAdapter;
    private List<DetailBean> mList;
    private CollectBroad mCollectBroad;

    @Override
    protected int setLayout() {
        return R.layout.activity_collect_detail;
    }

    @Override
    protected void initView() {
        mIvBack = bindView(R.id.iv_menu_back_activity_collect_detail);
        mTvEdit = bindView(R.id.tv_edit_activity_collect_detail);
        mTvCancel = bindView(R.id.tv_cancel_activity_collect_detail);
        mListView = bindView(R.id.lv_activity_collect_detail);

    }

    @Override
    protected void initData() {
        /**
         * 点击事件
         */
        setClick(this, mIvBack , mTvEdit, mTvCancel);
        /**
         * 获取数据库数据, 显示数据
         */
        initToolsData();
        /**
         * item 点击事件
         */
        listViewItemClick();

        /**
         * 注册广播
         */
        mCollectBroad = new CollectBroad();
        IntentFilter filter = new IntentFilter();
        filter.addAction(StringTools.COLLECT_BROAD);
        registerReceiver(mCollectBroad, filter);

    }

    /**
     * item 点击事件
     */
    private void listViewItemClick() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int type = mList.get(i).getType();
                if (1 == type){
                    Intent intent = new Intent(CollectActivity.this, ColumnWebViewDetailAty.class);
                    intent.putExtra("publishURL", mList.get(i).getPublishURL());
                    intent.putExtra("createTime", mList.get(i).getCreateTime());
                    intent.putExtra("title", mList.get(i).getTitle());
                    intent.putExtra("tagName", mList.get(i).getTagName());
                    intent.putExtra("image", mList.get(i).getImage());
                    startActivity(intent);
                }
                if (2 == type){
                    Intent intent = new Intent(CollectActivity.this, VideoDetailActivity.class);
                    intent.putExtra("videoURL", mList.get(i).getVideoURL());
                    intent.putExtra("publishURL", mList.get(i).getPublishURL());
                    intent.putExtra("createTime", mList.get(i).getCreateTime());
                    intent.putExtra("title", mList.get(i).getTitle());
                    intent.putExtra("tagName", mList.get(i).getTagName());
                    intent.putExtra("image", mList.get(i).getImage());
                    startActivity(intent);
                }
            }
        });
    }

    private void initToolsData() {
        mAdapter = new CollectAdapter(this);
        mList = DetailDBTools.getInstance().queryAll();
        mAdapter.setList(mList);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            /**
             * 返回
             */
            case R.id.iv_menu_back_activity_collect_detail:
                finish();
                break;
            /**
             * 编辑
             */
            case R.id.tv_edit_activity_collect_detail:
                mTvCancel.setVisibility(View.VISIBLE);
                mTvEdit.setVisibility(View.GONE);
                mAdapter.showIv(true);
                break;
            /**
             * 取消
             */
            case R.id.tv_cancel_activity_collect_detail:
                mTvCancel.setVisibility(View.GONE);
                mTvEdit.setVisibility(View.VISIBLE);
                mAdapter.showIv(false);
                break;
        }
    }

    /**
     * 接收广播
     */
    class CollectBroad extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            initToolsData();
        }
    }

    /**
     * 注销广播
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mCollectBroad);
    }
}
