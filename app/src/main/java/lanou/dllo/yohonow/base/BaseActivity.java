package lanou.dllo.yohonow.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dllo on 16/11/23.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        initView();
        initData();

    }
    // 绑定布局
    protected abstract int setLayout();
    // 初始化布局
    protected abstract void initView();
    // 初始化数据
    protected abstract void initData();
    // 简化findViewById 省去强转过程
    public <T extends View> T bindView(int id){
        return (T) findViewById(id);
    }
    // 点击事件
    public void setClick(View.OnClickListener clickListener, View ... views){
        for (View view : views){
            view.setOnClickListener(clickListener);
        }
    }
}
