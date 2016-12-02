package lanou.dllo.yohonow.community;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dalong.francyconverflow.FancyCoverFlow;
import com.dalong.francyconverflow.FancyCoverFlowAdapter;

import java.util.List;

import lanou.dllo.yohonow.R;

/**
 * Created by dllo on 16/12/1.
 */

public class CommunityVpAdapter extends FancyCoverFlowAdapter {
    Context mContext;
    private CommunityTrunBean mCommunityTrunBean;
    private List<CommunityTrunBean.DataBean.ForumInfoBean> mList;

    public void setCommunityTrunBean(CommunityTrunBean communityTrunBean) {
        mCommunityTrunBean = communityTrunBean;
        notifyDataSetChanged();
    }

    public CommunityVpAdapter(Context context) {
        mContext = context;
    }

    @Override
    public View getCoverFlowItem(int position, View reusableView, ViewGroup parent) {
        MyViewHolder viewHolder = null;
        mList = mCommunityTrunBean.getData().getForumInfo();
        if (reusableView == null){
            reusableView = LayoutInflater.from(mContext).inflate(R.layout.item_community_head_vp, parent, false);
            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth()+600;
            reusableView.setLayoutParams(new FancyCoverFlow.LayoutParams(width / 3 ,FancyCoverFlow.LayoutParams.WRAP_CONTENT));
            viewHolder = new MyViewHolder(reusableView);
            reusableView.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) reusableView.getTag();
        }
        viewHolder.mTvCommentNum.setText(String.valueOf(mList.get(position).getCommentsNum()));
        viewHolder.mTvForumName.setText(String.valueOf(mList.get(position).getForumName()));
        viewHolder.mTvHoyPostTitle.setText(mList.get(position).getHotPost().getPostsTitle());
        viewHolder.mTvNewPostTitle.setText(mList.get(position).getNewPost().getPostsTitle());
        viewHolder.mTvPostNum.setText(String.valueOf(mList.get(position).getPostsNum()));
        viewHolder.mTvOneDayAddNum.setText(String.valueOf(mList.get(position).getOneDayAddNum()));
        viewHolder.mTvPraiseNum.setText(String.valueOf(mList.get(position).getPraiseNum()));
        Glide.with(mContext).load(mList.get(position).getForumPic()).into(viewHolder.mIvForumPic);

        Glide.with(mContext).load(mList.get(position).getHotPost().getUser().getHeadIcon());

        return reusableView;
    }

    @Override
    public int getCount() {
        return mCommunityTrunBean != null && mCommunityTrunBean.getData().getForumInfo().size() > 0 ? mCommunityTrunBean.getData().getForumInfo().size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return mCommunityTrunBean.getData().getForumInfo().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class MyViewHolder {

        private final ImageView mIvForumPic;
        private final TextView mTvForumName;
        private final TextView mTvPostNum;
        private final TextView mTvCommentNum;
        private final TextView mTvPraiseNum;
        private final ImageView mIvHotPostUserHeadIcon;
        private final TextView mTvHoyPostTitle;
        private final ImageView mIvNewPostUserHeadIcon;
        private final TextView mTvNewPostTitle;
        private final TextView mTvOneDayAddNum;

        public MyViewHolder(View reusableView) {
            mIvForumPic = (ImageView) reusableView.findViewById(R.id.item_iv_forim_pic_community_head_vp);
            mTvForumName = (TextView) reusableView.findViewById(R.id.item_tv_forum_name_community_head_vp);
            mTvPostNum = (TextView) reusableView.findViewById(R.id.item_tv_posts_num_community_head_vp);
            mTvCommentNum = (TextView) reusableView.findViewById(R.id.item_tv_comments_num_community_head_vp);
            mTvPraiseNum = (TextView) reusableView.findViewById(R.id.item_tv_praise_num_community_head_vp);
            mIvHotPostUserHeadIcon = (ImageView) reusableView.findViewById(R.id.item_iv_hot_post_user_head_icon_community_head_vp);
            mTvHoyPostTitle = (TextView) reusableView.findViewById(R.id.item_tv_hot_post_posts_title_community_head_vp);
            mIvNewPostUserHeadIcon = (ImageView) reusableView.findViewById(R.id.item_iv_new_post_user_head_icon_community_head_vp);
            mTvNewPostTitle = (TextView) reusableView.findViewById(R.id.item_tv_new_post_posts_title_community_head_vp);
            mTvOneDayAddNum = (TextView) reusableView.findViewById(R.id.item_tv_one_day_add_num_community_head_vp);
        }
    }
}
