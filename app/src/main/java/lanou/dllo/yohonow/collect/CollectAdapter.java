package lanou.dllo.yohonow.collect;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import lanou.dllo.yohonow.R;

import lanou.dllo.yohonow.greendao.DetailBean;
import lanou.dllo.yohonow.greendao.DetailDBTools;
import lanou.dllo.yohonow.tools.timetools.StringTime;

/**
 * Created by dllo on 16/12/10.
 */

public class CollectAdapter extends BaseAdapter {
    private Context mContext;
    List<DetailBean> mList;
    private CollectHolder mHolder;
    private boolean flag = false;

    public void setList(List<DetailBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public CollectAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList != null && mList.size() > 0 ? mList.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return mList != null && mList.size() > 0 ? mList.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        mHolder = null;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_collect_detail_lv, viewGroup, false);
            mHolder = new CollectHolder(view);
            view.setTag(mHolder);
        } else {
            mHolder = (CollectHolder) view.getTag();
        }

        mHolder.mTvCreateTime.setText(StringTime.IntoTime(mList.get(i).getCreateTime()));
        mHolder.mTvTagNmae.setText("#  " + mList.get(i).getTagName());
        mHolder.mTvTitle.setText(mList.get(i).getTitle());
        /**
         * 显示 删除图标
         */
        if (flag){
            mHolder.mRl.setVisibility(View.VISIBLE);
            mHolder.mIvClose.setVisibility(View.VISIBLE);
        } else {
            mHolder.mRl.setVisibility(View.GONE);
            mHolder.mIvClose.setVisibility(View.GONE);
        }
        mHolder.mIvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DetailDBTools.getInstance().isHaveTheTitle(mList.get(i).getTitle())) {
                    DetailDBTools.getInstance().deleteByTitle(mList.get(i).getTitle());
                    mHolder.mRl.setVisibility(View.GONE);
                    mHolder.mIvClose.setVisibility(View.GONE);
                    mList.remove(i);
                    notifyDataSetChanged();
                }
            }
        });
        /**
         * 视频时显示隐藏图片
         */
        if (mList.get(i).getType() == 2){
            mHolder.mIvPlay.setVisibility(View.VISIBLE);
        } else {
            mHolder.mIvPlay.setVisibility(View.GONE);
        }
        Picasso.with(mContext).load(mList.get(i).getImage()).into(mHolder.mImage);
        return view;
    }

    /**
     * 显示 删除图标
     */
    public void showIv(boolean flag){
        this.flag = flag;
        notifyDataSetChanged();
    }

    private class CollectHolder {

        private final TextView mTvTitle;
        private final TextView mTvTagNmae;
        private final TextView mTvCreateTime;
        private final ImageView mIvPlay;
        private  ImageView mImage;
        private final RelativeLayout mRl;
        private final ImageView mIvClose;

        public CollectHolder(View view) {
            mTvTitle = (TextView) view.findViewById(R.id.item_tv_title_collect_detail_lv);
            mTvTagNmae = (TextView) view.findViewById(R.id.item_tv_tag_name_collect_detail_lv);
            mTvCreateTime = (TextView) view.findViewById(R.id.item_create_time_collect_detail_lv);
            mIvPlay = (ImageView) view.findViewById(R.id.item_iv_video_item_play_collect_detail_lv);
            mImage = (ImageView) view.findViewById(R.id.item_iv_image_collect_detail_lv);
            mRl = (RelativeLayout) view.findViewById(R.id.item_rl_icon_close_collect_detail_lv);
            mIvClose = (ImageView) view.findViewById(R.id.item_iv_icon_close_collect_detail_lv);
        }
    }
}
