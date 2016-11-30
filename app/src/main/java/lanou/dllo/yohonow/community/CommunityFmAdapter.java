package lanou.dllo.yohonow.community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.column.ColumnHeadBean;

/**
 * Created by dllo on 16/11/30.
 */

public class CommunityFmAdapter extends BaseAdapter {
    private CommunityBean mCommunityBean;
    private List<CommunityBean.DataBean.ListBean> mXList;
    private List<String> mUrl;
    private Context mContext;

    public void setCommunityBean(CommunityBean communityBean) {
        mCommunityBean = communityBean;
        notifyDataSetChanged();
    }

    public CommunityFmAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mCommunityBean.getData() != null && mCommunityBean.getData().getList().size() > 0 ? mCommunityBean.getData().getList().size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return mCommunityBean.getData().getList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CommunityHolder communityHolder = null;
        mUrl = new ArrayList<>();
        mXList = mCommunityBean.getData().getList();
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_lv_community_fragment, viewGroup, false);
            communityHolder = new CommunityHolder(view);
            view.setTag(communityHolder);
        } else {
            communityHolder = (CommunityHolder) view.getTag();
        }

        communityHolder.mTvNickName.setText(mXList.get(i).getAuthorInfo().getNickName());
        communityHolder.mTvComment.setText(" " + String.valueOf(mXList.get(i).getComment()));
        communityHolder.mTvCreateTime.setText(String.valueOf(mXList.get(i).getCreateTime()));
        communityHolder.mTvForumName.setText(mXList.get(i).getForumName());
        communityHolder.mTvPraise.setText(" " + String.valueOf(mXList.get(i).getPraise()));
        if (mXList.get(i).getPostsTitle() != null) {
            communityHolder.mTvPostTitle.setText(mXList.get(i).getPostsTitle());
        }
        return view;
    }

    private class CommunityHolder {

        private final ImageView mIvHeadIcon;
        private final TextView mTvNickName;
        private final TextView mTvCreateTime;
        private final TextView mTvPostTitle;
        private final TextView mTvBlocksContentData;
        private final ImageView mIvBlocksContentDataOne;
        private final ImageView mIvBlocksContentDataTwo;
        private final ImageView mIvBlocksContentDataThree;
        private final TextView mTvForumName;
        private final TextView mTvComment;
        private final TextView mTvPraise;

        public CommunityHolder(View view) {
            mIvHeadIcon = (ImageView) view.findViewById(R.id.item_iv_author_info_head_icon_community_fragment);
            mTvNickName = (TextView) view.findViewById(R.id.item_tv_author_info_nick_name_community_fragment);
            mTvCreateTime = (TextView) view.findViewById(R.id.item_tv_create_time_community_fragment);
            mTvPostTitle = (TextView) view.findViewById(R.id.item_tv_posts_title_community_fragment);
            mTvBlocksContentData = (TextView) view.findViewById(R.id.item_tv_blocks_cotent_data_community_fragment);
            mIvBlocksContentDataOne = (ImageView) view.findViewById(R.id.item_iv_one_blocks_cotent_data_community_fragment);
            mIvBlocksContentDataTwo = (ImageView) view.findViewById(R.id.item_iv_two_blocks_cotent_data_community_fragment);
            mIvBlocksContentDataThree = (ImageView) view.findViewById(R.id.item_iv_three_blocks_cotent_data_community_fragment);
            mTvForumName = (TextView) view.findViewById(R.id.item_tv_forum_name_community_fragment);
            mTvComment = (TextView) view.findViewById(R.id.item_tv_comment_community_fragment);
            mTvPraise = (TextView) view.findViewById(R.id.item_tv_praise_community_fragment);
        }
    }
}
//        for (int j = 0; j < mXList.get(i).getBlocks().size(); j++) {
//            if (mXList.get(i).getBlocks().get(j).getTemplateKey().equals("text")) {
//                communityHolder.mTvBlocksContentData.setText(mXList.get(i).getBlocks().get(j).getContentData());
//            } else {
//                int a = mXList.get(i).getBlocks().get(j).getContentData().indexOf("?");
//                String url = mXList.get(i).getBlocks().get(j).getContentData().substring(0, a);
//                mUrl.add(url);
//            }
//        }
//        if (1 == mUrl.size()) {
//            Picasso.with(mContext).load(mUrl.get(0)).into(communityHolder.mIvBlocksContentDataOne);
//        } else if (2 == mUrl.size()) {
//            Picasso.with(mContext).load(mUrl.get(0)).into(communityHolder.mIvBlocksContentDataOne);
//            Picasso.with(mContext).load(mUrl.get(1)).into(communityHolder.mIvBlocksContentDataTwo);
//        } else {
//            Picasso.with(mContext).load(mUrl.get(0)).into(communityHolder.mIvBlocksContentDataOne);
//            Picasso.with(mContext).load(mUrl.get(1)).into(communityHolder.mIvBlocksContentDataTwo);
//            Picasso.with(mContext).load(mUrl.get(2)).into(communityHolder.mIvBlocksContentDataThree);
//        }
