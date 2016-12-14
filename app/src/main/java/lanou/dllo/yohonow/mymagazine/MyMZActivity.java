package lanou.dllo.yohonow.mymagazine;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.menu.MenuWrapperFactory;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.base.BaseActivity;
import lanou.dllo.yohonow.greendao.DBTools;
import lanou.dllo.yohonow.greendao.Magazine;
import lanou.dllo.yohonow.tools.stringtools.StringTools;

/**
 * Created by dllo on 16/12/9.
 */

public class MyMZActivity extends BaseActivity implements View.OnClickListener {

    private GridView mGridView;
    private ImageView mIvBack;
    private TextView mTvChose;
    private TextView mTvCancel;
    private List<Magazine> lists;
    private ImageView mIvRubbish;
    private RelativeLayout mRl;
    private TextView mTvNum;
    private MZGvAdapter mAdapter;
    private int num = 0;
    private boolean flag = false;
    private int mNum;
    private MyMZBroad mBroad;

    @Override
    protected int setLayout() {
        return R.layout.my_magazine_activity;
    }

    @Override
    protected void initView() {
        mGridView = bindView(R.id.grid_view_my_magazine_actyvity);
        mIvBack = bindView(R.id.iv_back_my_magazine_activity);
        mTvChose = bindView(R.id.tv_chose_my_magazine_activity);
        mTvCancel = bindView(R.id.tv_cancel_my_magazine_activity);
        mIvRubbish = bindView(R.id.iv_rubbish_my_magazine_activity);
        mRl = bindView(R.id.rl_rubbish_my_magazine_activity);
        mTvNum = bindView(R.id.tv_zero_my_magazine_activity);
    }

    @Override
    protected void initData() {
        /**
         * 点击事件
         */
        setClick(this, mIvBack, mTvChose, mTvCancel, mIvRubbish, mRl);
        /**
         * 查询数据库, 显示数据
         */
        initQueryMagazineToolsData();
        /**
         * 注册广播
         */
        mBroad = new MyMZBroad();
        IntentFilter filter = new IntentFilter();
        filter.addAction(StringTools.MY_MAGAZINE_BROAD);
        registerReceiver(mBroad, filter);
    }

    private void initQueryMagazineToolsData() {
        lists = DBTools.getInstance().queryAll();
        mAdapter = new MZGvAdapter(this);
        mAdapter.setList(lists);
        mGridView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            /**
             * 返回
             */
            case R.id.iv_back_my_magazine_activity:
                finish();
                break;
            /**
             * 选择
             */
            case R.id.tv_chose_my_magazine_activity:
                mTvChose.setVisibility(View.GONE);
                mRl.setVisibility(View.VISIBLE);
                mTvCancel.setVisibility(View.VISIBLE);
                mTvNum.setText("0");
                mAdapter.showTagSelected(true);
                mAdapter.notifyDataSetChanged();
                break;
            /**
             * 取消
             */
            case R.id.tv_cancel_my_magazine_activity:
                flag = false;
                mAdapter.showTagSelected(flag);
                mAdapter.notifyDataSetChanged();
                mTvChose.setVisibility(View.VISIBLE);
                mRl.setVisibility(View.GONE);
                mTvCancel.setVisibility(View.GONE);
                // 查询数据库, 更新数据状态
                lists = DBTools.getInstance().queryAll();
                mAdapter.setList(lists);
                mGridView.setAdapter(mAdapter);
                break;
            /**
             * 删除
             */
            case R.id.rl_rubbish_my_magazine_activity:
                showDialog();
                break;
            case R.id.iv_rubbish_my_magazine_activity:
                showDialog();
                break;
        }
    }

    /**
     * 删除过程, 显示dialog提示
     */
    private void showDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定删除" + mNum + "本杂志");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mAdapter.deleteCheckedItem();
                mAdapter.notifyDataSetChanged();
                mTvNum.setText("0");
                flag = false;
                mAdapter.showTagSelected(flag);
                mAdapter.notifyDataSetChanged();
                mTvChose.setVisibility(View.VISIBLE);
                mRl.setVisibility(View.GONE);
                mTvCancel.setVisibility(View.GONE);
                // 查询数据库, 更新数据状态
                lists = DBTools.getInstance().queryAll();
                mAdapter.setList(lists);
                mGridView.setAdapter(mAdapter);
            }
        });
        builder.create().show();
    }

    /**
     * 显示选中状态
     */
    public void showSelected(){
            if (flag){
                mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Log.d("MyMZActivity", "--" + 123);
                    }
                });
            }
    }
    /**
     * 广播接收适配器传过来的值
     */
    class MyMZBroad extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            mNum = intent.getIntExtra("a", 0);
            // 根据选中图片数量改变布局背景颜色
            if (mNum > 0){
                mRl.setBackgroundColor(Color.BLACK);
            } else {
                mRl.setBackgroundColor(Color.GRAY);
            }
            mTvNum.setText("(" + mNum + ")");
        }
    }
    /**
     * 注销广播
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroad);
    }
}
