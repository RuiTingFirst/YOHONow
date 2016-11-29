package lanou.dllo.yohonow.video.videoson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import lanou.dllo.yohonow.R;

/**
 * Created by dllo on 16/11/25.
 */

public class VideoSonFragmentAdapter extends BaseAdapter {
    Context mContext;
    private VideoSonBean mVideoSonBean;
    private List<VideoSonBean.DataBean.ContentBean> mBeanList;

    public void setVideoSonBean(VideoSonBean videoSonBean) {
        mVideoSonBean = videoSonBean;
        notifyDataSetChanged();
    }

    public VideoSonFragmentAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mVideoSonBean.getData().getContent() != null && mVideoSonBean.getData().getContent().size() > 0 ? mVideoSonBean.getData().getContent().size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return mBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        mBeanList = mVideoSonBean.getData().getContent();
        VSonHolder vSonHolder = null;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_lv_video_son_fragment, viewGroup, false);
            vSonHolder = new VSonHolder(view);
            view.setTag(vSonHolder);
        } else {
            vSonHolder = (VSonHolder) view.getTag();
        }
        /**
         * 时间
         */
        vSonHolder.mTvCreateTime.setText(mBeanList.get(i).getCreate_time());
        /**
         * tag_name
         */
        vSonHolder.mTvTagName.setText("# " + mBeanList.get(i).getTag().get(0).getTag_name());
        /**
         * 标题
         */
        vSonHolder.mTvTitle.setText(mBeanList.get(i).getTitle());
        /**
         * 图片
         */
        Picasso.with(mContext).load(mBeanList.get(i).getImage()).into(vSonHolder.mIvImage);
        return view;
    }

    /**
     * 缓存类
     */
    private class VSonHolder {

        private final TextView mTvTagName;
        private final TextView mTvCreateTime;
        private final TextView mTvTitle;
        private final ImageView mIvImage;

        public VSonHolder(View view) {
            mTvTagName = (TextView) view.findViewById(R.id.item_tv_tag_name_video_son_fragment);
            mTvCreateTime = (TextView) view.findViewById(R.id.item_tv_create_time_video_son_fragment);
            mTvTitle = (TextView) view.findViewById(R.id.item_tv_title_video_son_fragment);
            mIvImage = (ImageView) view.findViewById(R.id.item_iv_image_video_son_fragment);
        }
    }
}
