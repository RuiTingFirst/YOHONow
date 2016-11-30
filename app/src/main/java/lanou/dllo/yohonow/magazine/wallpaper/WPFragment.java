package lanou.dllo.yohonow.magazine.wallpaper;

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
import lanou.dllo.yohonow.tools.volleytools.NetHelper;
import lanou.dllo.yohonow.tools.volleytools.NetListener;

/**
 * Created by dllo on 16/11/26.
 */

public class WPFragment extends BaseFragment {

    private ListView mListView;
    private WPFragmentAdapter mAdapter;

    @Override
    protected int setLayout() {
        return R.layout.wall_paper_fragment;
    }

    @Override
    protected void initView() {
        mListView = bindView(R.id.lv_wall_paper_fragment);
    }

    @Override
    protected void initData() {
        mAdapter = new WPFragmentAdapter(mContext);
        /**
         * get 请求解析网络数据
         */
        initUrlWallpaperDate();
    }

    /**
     * get 请求解析网络数据
     */
    private void initUrlWallpaperDate() {
        NetHelper.MyRequest(URLValues.WALLPAPER_URL, WallPaperBean.class, new NetListener<WallPaperBean>() {
            @Override
            public void successListener(WallPaperBean response) {
                mAdapter.setWallPaperBean(response);
                mListView.setAdapter(mAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }
}
