package lanou.dllo.yohonow.video.videoson;

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
 * Created by dllo on 16/11/25.
 */

public class VideoSonFragment extends BaseFragment {

    private ListView mListView;
    private VideoSonFragmentAdapter mVideoSonFragmentAdapter;

    @Override
    protected int setLayout() {
        return R.layout.video_son_fragment;
    }

    @Override
    protected void initView() {
        mListView = bindView(R.id.lv_video_son_fragment);
        mVideoSonFragmentAdapter = new VideoSonFragmentAdapter(mContext);

    }

    @Override
    protected void initData() {
        initPost();
    }

    private void initPost() {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLValues.VIDEO_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                VideoSonBean videoSonBean = gson.fromJson(response, VideoSonBean.class);
                mVideoSonFragmentAdapter.setVideoSonBean(videoSonBean);
                mListView.setAdapter(mVideoSonFragmentAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put(URLValues.VIDEO_KEY, URLValues.VIDEO_VALUE);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
