package lanou.dllo.yohonow.video.videoson;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.VolleyError;


import java.util.HashMap;

import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.base.BaseFragment;
import lanou.dllo.yohonow.tools.urltools.URLValues;
import lanou.dllo.yohonow.tools.volleytools.NetHelper;
import lanou.dllo.yohonow.tools.volleytools.NetListener;
import lanou.dllo.yohonow.video.videoson.videodetail.VideoDetailActivity;

/**
 * Created by dllo on 16/11/25.
 */

public class VideoSonFragment extends BaseFragment {

    private ListView mListView;
    private VideoSonFragmentAdapter mVideoSonFragmentAdapter;
    private VideoSonBean mVideoSonBean;

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
        /**
         * 获取网络数据
         */
        initPost();
        /**
         * 跳转传值
         */
        initSetItemData();
    }

    /**
     * 跳转传值
     */
    private void initSetItemData() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(mContext, VideoDetailActivity.class);
                    intent.putExtra("publishURL", mVideoSonBean.getData().getContent().get(i).getPublishURL());
                    intent.putExtra("videoURL", mVideoSonBean.getData().getContent().get(i).getVideoUrl());
                    intent.putExtra("createTime", mVideoSonBean.getData().getContent().get(i).getCreate_time());
                    intent.putExtra("title", mVideoSonBean.getData().getContent().get(i).getTitle());
                    intent.putExtra("tagName", mVideoSonBean.getData().getContent().get(i).getTag().get(0).getTag_name());
                    intent.putExtra("image", mVideoSonBean.getData().getContent().get(i).getImage());
                Log.d("---", mVideoSonBean.getData().getContent().get(i).getPublishURL());
                    startActivity(intent);
            }
        });
    }

    private void initPost() {
        HashMap<String, String> map =  new HashMap<>();
        map.put(URLValues.VIDEO_KEY, URLValues.VIDEO_VALUE);
        NetHelper.MyRequest(URLValues.VIDEO_URL, VideoSonBean.class, new NetListener<VideoSonBean>() {
            @Override
            public void successListener(VideoSonBean response) {
                mVideoSonBean = response;
                mVideoSonFragmentAdapter.setVideoSonBean(response);
                mListView.setAdapter(mVideoSonFragmentAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        },map);
    }
}
