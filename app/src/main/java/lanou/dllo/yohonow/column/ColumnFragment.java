package lanou.dllo.yohonow.column;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.base.BaseFragment;
import lanou.dllo.yohonow.tools.urltools.URLValues;

/**
 * Created by dllo on 16/11/24.
 */

public class ColumnFragment extends BaseFragment {

    private ListView mListView;
    private ColumnAdapter mColumnAdapter;

    /**
     * 绑定布局
     * @return
     */
    @Override
    protected int setLayout() {
        return R.layout.column_fragment;
    }

    /**
     * 初始化布局
     */
    @Override
    protected void initView() {
        mListView = (ListView) getView().findViewById(R.id.lv_column_fragment);
        View headView = LayoutInflater.from(mContext).inflate(R.layout.head_item_column_fragment, null);
        mListView.addHeaderView(headView);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        mColumnAdapter = new ColumnAdapter(mContext);
        initVolleyPost();
    }

    /**
     * post 请求, 解析数据
     */
    private void initVolleyPost() {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLValues.COLUMN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                ColumnBean columnBean = gson.fromJson(response, ColumnBean.class);
                mColumnAdapter.setColumnBeen(columnBean);
                mListView.setAdapter(mColumnAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put(URLValues.COLUMN_KEY, URLValues.COLUMN_VALUE);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
