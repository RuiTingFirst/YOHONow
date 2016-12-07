package lanou.dllo.yohonow.home.homeseek;

import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.VolleyError;

import java.util.HashMap;

import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.base.BaseFragment;
import lanou.dllo.yohonow.tools.urltools.PostBody;
import lanou.dllo.yohonow.tools.urltools.URLValues;
import lanou.dllo.yohonow.tools.volleytools.NetHelper;
import lanou.dllo.yohonow.tools.volleytools.NetListener;

/**
 * Created by dllo on 16/12/2.
 */

public class SeekFragment extends BaseFragment {

    private ListView mLv;
    private SeekFragmentAdapter mSeekFragmentAdapter;

    @Override
    protected int setLayout() {
        return R.layout.seek_fragment;
    }

    @Override
    protected void initView() {
        mLv = bindView(R.id.lv_seek_fragment);
    }

    @Override
    protected void initData() {

        /**
         * post 解析, 获取网络数据
         */
        mSeekFragmentAdapter = new SeekFragmentAdapter(mContext);
        initGetSeekData();

    }

    /**
     * post 解析, 获取网络数据
     */
    private void initGetSeekData() {
        //在这里进行 赋值操作
        Bundle bundle = getArguments();
        //msg就是接受我们传过来的ID
        String msg = bundle.get("key").toString();
        HashMap<String,String> map = new HashMap<>();
        PostBody postBody = new PostBody();
        postBody.setChannelId(msg);
        map.put("parameters",postBody.m(postBody));
        NetHelper.MyRequest(URLValues.HOME_SEEK_BODY_URL, SeekFragmentBean.class, new NetListener<SeekFragmentBean>() {
            @Override
            public void successListener(SeekFragmentBean response) {
                mSeekFragmentAdapter.setSeekBean(response);
                mLv.setAdapter(mSeekFragmentAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, map);
    }

    //为其他类使用的  其他类给数值传过来
    public static SeekFragment newInstance(String pos){
        //传值(位置)
        Bundle bundle = new Bundle();
        //获取适配器里的数据(最开始从Activity来的)
        bundle.putString("key", pos);
        SeekFragment seekFragment = new SeekFragment();
        seekFragment.setArguments(bundle);
        return seekFragment;
    }
}
