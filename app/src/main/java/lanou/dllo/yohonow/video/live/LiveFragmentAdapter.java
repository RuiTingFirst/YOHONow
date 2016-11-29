package lanou.dllo.yohonow.video.live;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import lanou.dllo.yohonow.R;

/**
 * Created by dllo on 16/11/25.
 */

public class LiveFragmentAdapter extends BaseAdapter {

    Context mContext;
    private LiveBean mLiveBean;
    private List<LiveBean.DataBean.ContentBean> mBeanList;

    public void setLiveBean(LiveBean liveBean) {
        mLiveBean = liveBean;
        notifyDataSetChanged();
    }

    public LiveFragmentAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mLiveBean != null && mLiveBean.getData().getContent().size() > 0 ? mLiveBean.getData().getContent().size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return mLiveBean.getData().getContent().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        mBeanList = mLiveBean.getData().getContent();
        LiveHolder liveHolder = null;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_lv_video_son_fragment, viewGroup, false);
            liveHolder = new LiveHolder(view);
            view.setTag(liveHolder);
        } else {
            liveHolder = (LiveHolder) view.getTag();
        }
        /**
         * 时间
         */
        liveHolder.mTvCreateTime.setText(mBeanList.get(i).getCreate_time());
        /**
         * tag_name
         */
        liveHolder.mTvTagName.setText("# " + mBeanList.get(i).getTag().get(0).getTag_name());
        /**
         * 标题
         */
        liveHolder.mTvTitle.setText(mBeanList.get(i).getTitle());
        /**
         * 图片
         */
        Picasso.with(mContext).load(mBeanList.get(i).getImage()).into(liveHolder.mIvImage);
        /**
         * 显示隐藏布, 获取在线人数
         */
        liveHolder.mLl.setVisibility(View.VISIBLE);
        liveHolder.mTvOnlineNum.setText(String.valueOf(mBeanList.get(i).getOnlineNum()));
        return view;
    }

    private class LiveHolder {
        private final TextView mTvTagName;
        private final TextView mTvCreateTime;
        private final TextView mTvTitle;
        private final ImageView mIvImage;
        private final TextView mTvOnlineNum;
        private final LinearLayout mLl;

        public LiveHolder(View view) {
            mTvTagName = (TextView) view.findViewById(R.id.item_tv_tag_name_video_son_fragment);
            mTvCreateTime = (TextView) view.findViewById(R.id.item_tv_create_time_video_son_fragment);
            mTvTitle = (TextView) view.findViewById(R.id.item_tv_title_video_son_fragment);
            mIvImage = (ImageView) view.findViewById(R.id.item_iv_image_video_son_fragment);
            mTvOnlineNum = (TextView) view.findViewById(R.id.item_tv_online_num_video_son_fragment);
            mLl = (LinearLayout) view.findViewById(R.id.item_ll_video_son_fragment);
        }
    }
}
