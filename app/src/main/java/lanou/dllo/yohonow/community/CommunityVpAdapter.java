package lanou.dllo.yohonow.community;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dalong.francyconverflow.FancyCoverFlow;
import com.dalong.francyconverflow.FancyCoverFlowAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
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
            reusableView.setLayoutParams(new FancyCoverFlow.LayoutParams(width * 2 / 5 ,FancyCoverFlow.LayoutParams.WRAP_CONTENT));
            viewHolder = new MyViewHolder(reusableView);
            reusableView.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) reusableView.getTag();
        }
        if (position == 0){
            viewHolder.mLlLine.setVisibility(View.GONE);
            viewHolder.mLl.setBackgroundResource(R.mipmap.yohocommid1);
        }else if (position == mList.size() + 1){
            viewHolder.mLlLine.setVisibility(View.GONE);
            viewHolder.mLl.setBackgroundResource(R.mipmap.yohocommid1);
        } else {
            viewHolder.mTvCommentNum.setText("回复" + String.valueOf(mList.get(position -1).getCommentsNum()));
            viewHolder.mTvForumName.setText(String.valueOf(mList.get(position -1).getForumName()));
            viewHolder.mTvHoyPostTitle.setText(mList.get(position -1).getHotPost().getPostsTitle());
            viewHolder.mTvNewPostTitle.setText(mList.get(position -1).getNewPost().getPostsTitle());
            viewHolder.mTvPostNum.setText("帖子" + String.valueOf(mList.get(position -1).getPostsNum()));
            viewHolder.mTvOneDayAddNum.setText(String.valueOf(mList.get(position -1).getOneDayAddNum()) + "条更新 >");
            if (10000 < mList.get(position -1).getPraiseNum()) {
                viewHolder.mTvPraiseNum.setText("赞" + mList.get(position -1).getPraiseNum() / 10000 + "W+");
            } else {
                viewHolder.mTvPraiseNum.setText("| 赞" + String.valueOf(mList.get(position -1).getPraiseNum()));
            }
            Picasso.with(mContext).load(mList.get(position -1).getForumPic()).into(viewHolder.mIvForumPic);

            Glide.with(mContext).load(subStrings(mList.get(position -1).getHotPost().getUser().getHeadIcon())).bitmapTransform(new CropCircleTransformation(mContext)).into(viewHolder.mIvHotPostUserHeadIcon);
            Glide.with(mContext).load(subStrings(mList.get(position -1).getNewPost().getUser().getHeadIcon())).bitmapTransform(new CropCircleTransformation(mContext)).into(viewHolder.mIvNewPostUserHeadIcon);
        }
            return reusableView;
    }

    @Override
    public int getCount() {
        return mCommunityTrunBean != null && mCommunityTrunBean.getData().getForumInfo().size() > 0 ? mCommunityTrunBean.getData().getForumInfo().size() + 2 : 0;
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
        private final LinearLayout mLl;
        private final LinearLayout mLlLine;

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
            mLl = (LinearLayout) reusableView.findViewById(R.id.item_ll_community_head_vp);
            mLlLine = (LinearLayout) reusableView.findViewById(R.id.item_ll_line_community_head_vp);
        }
    }
    public String subStrings(String str){
        if(str.indexOf("?")!=-1){
            String result = str.substring(0,str.indexOf("?"));
            return result;
        }else{
            //返回一个字符串类型
            return "";
        }
    }
}
