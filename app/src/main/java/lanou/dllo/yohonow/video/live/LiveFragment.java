package lanou.dllo.yohonow.video.live;

import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.base.BaseFragment;
import lanou.dllo.yohonow.tools.urltools.URLValues;

/**
 * Created by dllo on 16/11/25.
 */

public class LiveFragment extends BaseFragment {

    private ListView mListView;
    private LiveFragmentAdapter mLiveFragmentAdapter;

    /**
     * 初始化布局
     * @return
     */
    @Override
    protected int setLayout() {
        return R.layout.live_fragment;
    }

    /**
     * 初始化视图
     */
    @Override
    protected void initView() {
        mListView = bindView(R.id.lv_live_fragment);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        mLiveFragmentAdapter = new LiveFragmentAdapter(mContext);
        /**
         * 获取网络解析数据
         */
        initUrlData();
    }

    private void initUrlData() {
        /**
         * 请求队列
         */
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(URLValues.LIVE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                LiveBean liveBean = gson.fromJson(response, LiveBean.class);
                mLiveFragmentAdapter.setLiveBean(liveBean);
                mListView.setAdapter(mLiveFragmentAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
}
