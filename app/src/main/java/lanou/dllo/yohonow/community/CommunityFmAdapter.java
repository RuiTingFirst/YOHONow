package lanou.dllo.yohonow.community;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.column.ColumnHeadBean;
import lanou.dllo.yohonow.tools.circletools.CircleDrawable;
import lanou.dllo.yohonow.tools.timetools.StringTime;

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
        for (int j = 0; j < mXList.get(i).getBlocks().size(); j++) {
            if (mXList.get(i).getBlocks().get(j).getTemplateKey().equals("text")) {
                communityHolder.mTvBlocksContentData.setText(mXList.get(i).getBlocks().get(j).getContentData());
            } else {
                int a = mXList.get(i).getBlocks().get(j).getContentData().indexOf("?");
                String url = mXList.get(i).getBlocks().get(j).getContentData().substring(0, a);
                mUrl.add(url);
            }
        }
        Glide.with(mContext).load(subStrings(mXList.get(i).getAuthorInfo().getHeadIcon())).bitmapTransform(new CropCircleTransformation(mContext)).into(communityHolder.mIvHeadIcon);
        if (1 == mUrl.size()) {
            Picasso.with(mContext).load(mUrl.get(0)).into(communityHolder.mIvBlocksContentDataOne);
            /**
             * 隐藏并占位 setVisibility(View.INVISIBLE)
             */
            communityHolder.mIvBlocksContentDataOne.setVisibility(View.VISIBLE);
            communityHolder.mIvBlocksContentDataTwo.setVisibility(View.INVISIBLE);
            communityHolder.mIvBlocksContentDataThree.setVisibility(View.INVISIBLE);
        } else if (2 == mUrl.size()) {
            Picasso.with(mContext).load(mUrl.get(0)).into(communityHolder.mIvBlocksContentDataOne);
            Picasso.with(mContext).load(mUrl.get(1)).into(communityHolder.mIvBlocksContentDataTwo);
            communityHolder.mIvBlocksContentDataTwo.setVisibility(View.VISIBLE);
            communityHolder.mIvBlocksContentDataOne.setVisibility(View.VISIBLE);
            communityHolder.mIvBlocksContentDataThree.setVisibility(View.INVISIBLE);
        } else if (2 < mUrl.size()){
            Picasso.with(mContext).load(mUrl.get(0)).into(communityHolder.mIvBlocksContentDataOne);
            Picasso.with(mContext).load(mUrl.get(1)).into(communityHolder.mIvBlocksContentDataTwo);
            Picasso.with(mContext).load(mUrl.get(2)).into(communityHolder.mIvBlocksContentDataThree);
            communityHolder.mIvBlocksContentDataOne.setVisibility(View.VISIBLE);
            communityHolder.mIvBlocksContentDataTwo.setVisibility(View.VISIBLE);
            communityHolder.mIvBlocksContentDataThree.setVisibility(View.VISIBLE);
        }

        communityHolder.mTvNickName.setText(mXList.get(i).getAuthorInfo().getNickName());
        communityHolder.mTvComment.setText(" " + String.valueOf(mXList.get(i).getComment()));
        communityHolder.mTvCreateTime.setText(StringTime.IntoTime(String.valueOf(mXList.get(i).getCreateTime())));
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

//    int mJ;
//for (mJ = 0; mJ < mXList.get(i).getBlocks().size(); mJ++) {
//        if (mXList.get(i).getBlocks().get(mJ).getTemplateKey().equals("text")) {
//        communityHolder.mTvBlocksContentData.setText(mXList.get(i).getBlocks().get(mJ).getContentData());
//        Log.d("aaaa", mXList.get(i).getBlocks().get(mJ).getContentData());
//        } else {
//        int a = mXList.get(i).getBlocks().get(mJ).getContentData().indexOf("?");
//        String url = mXList.get(i).getBlocks().get(mJ).getContentData().substring(0, a);
//        mUrl.add(url);
//        }
//        }
//if (1 == mUrl.size()) {
//        Picasso.with(mContext).load(mUrl.get(0)).memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE)
//        .networkPolicy(NetworkPolicy.NO_CACHE,NetworkPolicy.NO_STORE).into(communityHolder.mIvBlocksContentDataOne);
//        } else if (2 == mUrl.size()) {
//        Picasso.with(mContext).load(mUrl.get(0)).memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE)
//        .networkPolicy(NetworkPolicy.NO_CACHE,NetworkPolicy.NO_STORE)
//        .into(communityHolder.mIvBlocksContentDataOne);
//        Picasso.with(mContext).load(mUrl.get(1)).memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE)
//        .networkPolicy(NetworkPolicy.NO_CACHE,NetworkPolicy.NO_STORE).into(communityHolder.mIvBlocksContentDataTwo);
//        } else {
//        Picasso.with(mContext).load(mUrl.get(0)).memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE)
//        .networkPolicy(NetworkPolicy.NO_CACHE,NetworkPolicy.NO_STORE).into(communityHolder.mIvBlocksContentDataOne);
//        Picasso.with(mContext).load(mUrl.get(1)).memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE)
//        .networkPolicy(NetworkPolicy.NO_CACHE,NetworkPolicy.NO_STORE).into(communityHolder.mIvBlocksContentDataTwo);
//        Picasso.with(mContext).load(mUrl.get(2)).memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE)
//        .networkPolicy(NetworkPolicy.NO_CACHE,NetworkPolicy.NO_STORE).into(communityHolder.mIvBlocksContentDataThree);
//        }


//    int type = mListBeen.get(position).getBlocks().size();
//    or (int i = 0; i < type; i++) {
//        if (mListBeen.get(position).getBlocks().get(i).getTemplateKey().equals("text")) {
//            if (mListBeen.get(position).getBlocks().get(i).getContentData() != null) {
//                viewHolder.setText(R.id.content_data, mListBeen.get(position).getBlocks().get(i).getContentData());
//            } else {
//                viewHolder.getView(R.id.content_data).setVisibility(View.GONE);
//            }
//        } else {
//            urls.add(getSubstring(mListBeen.get(position).getBlocks().get(i).getContentData()));
//        }
//    }
//    ImageView imageView2 = viewHolder.getView(R.id.community_image_two);
//    ImageView imageView3 = viewHolder.getView(R.id.community_image_three);
//if (urls.size() == PIC_SIZE_ONE) {
//        Log.d("aaaa", "第一种" + urls.size());
//
//        mOneUrl = urls.get(0);
//
//        viewHolder.setImage(R.id.community_image_one, mOneUrl);
//
//        imageView2.setVisibility(View.INVISIBLE);
//        imageView3.setVisibility(View.INVISIBLE);
//        viewHolder.setImage(R.id.community_image_two, mOneUrl);
//
//
//        } else if (urls.size() == PIC_SIZE_TWO) {
//        Log.d("aaaa", "第二种" + urls.size());
//        mOneUrl = urls.get(0);
//        mTwoUrl = urls.get(1);
//        imageView2.setVisibility(View.VISIBLE);
//        imageView3.setVisibility(View.INVISIBLE);
//        viewHolder.setImage(R.id.community_image_one, mOneUrl);
//        viewHolder.setImage(R.id.community_image_two, mTwoUrl);
//        } else {
//        Log.d("aaaa", "第三种" + urls.size());
//        mOneUrl = urls.get(0);
//        mTwoUrl = urls.get(1);
//        mThreeUrl = urls.get(2);
//
//        viewHolder.setImage(R.id.community_image_one, mOneUrl);
//        viewHolder.setImage(R.id.community_image_two, mTwoUrl);
//        viewHolder.setImage(R.id.community_image_three, mThreeUrl);
//        imageView2.setVisibility(View.VISIBLE);
//        imageView3.setVisibility(View.VISIBLE);