package lanou.dllo.yohonow.magazine.mzson;

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
 * Created by dllo on 16/11/26.
 */

public class MSFAdapter extends BaseAdapter {
    private Context mContext;
    private MSNumBean mMSNumBean;
    private List<MSNumBean.DataBean> mList;
    private MSNumGirlBean mMSNumGirlBean;
    private List<MSNumGirlBean.DataBean> mGirlBeanList;
    private MSNumSpecialBean mMSNumSpecialBean;
    private List<MSNumSpecialBean.DataBean> mSpecialBeenList;
    public void setMSNumSpecialBean(MSNumSpecialBean MSNumSpecialBean) {
        mMSNumSpecialBean = MSNumSpecialBean;
        notifyDataSetChanged();
    }

    public void setMSNumGirlBean(MSNumGirlBean MSNumGirlBean) {
        mMSNumGirlBean = MSNumGirlBean;
        notifyDataSetChanged();
    }

    public void setMSNumBean(MSNumBean MSNumBean) {
        mMSNumBean = MSNumBean;
        notifyDataSetChanged();
    }

    public MSFAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mMSNumBean != null && mMSNumBean.getData().size() > 0 ? mMSNumBean.getData().size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return mMSNumBean != null && mMSNumBean.getData().size() > 0 ? mMSNumBean.getData().get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * 杂志页获取数据
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        MSNumHolder msNumHolder = null;
//        mList = mMSNumBean.getData();
//        mGirlBeanList = mMSNumGirlBean.getData();
//        mSpecialBeenList = mMSNumSpecialBean.getData();
//        if (view == null){
//            view = LayoutInflater.from(mContext).inflate(R.layout.lv_magazine_son_fragment_item, viewGroup, false);
//            msNumHolder = new MSNumHolder(view);
//            view.setTag(msNumHolder);
//        } else {
//            msNumHolder = (MSNumHolder) view.getTag();
//        }
//        /**
//         * boy
//         */
//        msNumHolder.mTvJournalBoyOne.setText(mList.get(0).getJournal());
//        msNumHolder.mTvJournalBoyTwo.setText(mList.get(1).getJournal());
//        msNumHolder.mTvJournalBoyThree.setText(mList.get(2).getJournal());
//        Picasso.with(mContext).load(mList.get(0).getCover()).into(msNumHolder.mIvCoverBoyOne);
//        Picasso.with(mContext).load(mList.get(1).getCover()).into(msNumHolder.mIvCoverBoyTwo);
//        Picasso.with(mContext).load(mList.get(2).getCover()).into(msNumHolder.mIvCoverBoyThree);
//        /**
//         * girl
//         */
//        msNumHolder.mTvJournalGirlOne.setText(mGirlBeanList.get(0).getJournal());
//        msNumHolder.mTvJournalGirlTwo.setText(mGirlBeanList.get(1).getJournal());
//        msNumHolder.mTvJournalGirlThree.setText(mGirlBeanList.get(2).getJournal());
//        Picasso.with(mContext).load(mGirlBeanList.get(0).getCover()).into(msNumHolder.mIvCoverGirlOne);
//        Picasso.with(mContext).load(mGirlBeanList.get(1).getCover()).into(msNumHolder.mIvCoverGirlTwo);
//        Picasso.with(mContext).load(mGirlBeanList.get(2).getCover()).into(msNumHolder.mIvCoverGirlThree);
//        /**
//         * special
//         */
//        msNumHolder.mTvJournalSpecialOne.setText(mSpecialBeenList.get(0).getJournal());
//        msNumHolder.mTvJournalSpecialTwo.setText(mSpecialBeenList.get(1).getJournal());
//        msNumHolder.mTvJournalSpecialThree.setText(mSpecialBeenList.get(2).getJournal());
//        Picasso.with(mContext).load(mSpecialBeenList.get(0).getCover()).into(msNumHolder.mIvCoverSpecialOne);
//        Picasso.with(mContext).load(mSpecialBeenList.get(1).getCover()).into(msNumHolder.mIvCoverSpecialTwo);
//        Picasso.with(mContext).load(mSpecialBeenList.get(2).getCover()).into(msNumHolder.mIvCoverSpecialThree);
        return null;
    }
//
//    /**
//     * 缓存类
//     */
//    private class MSNumHolder {
//
//        /**
//         * boy
//         */
//        private final TextView mTvJournalBoyOne;
//        private final TextView mTvJournalBoyTwo;
//        private final TextView mTvJournalBoyThree;
//        private final ImageView mIvCoverBoyOne;
//        private final ImageView mIvCoverBoyTwo;
//        private final ImageView mIvCoverBoyThree;
//        /**
//         * girl
//         */
//        private final TextView mTvJournalGirlOne;
//        private final TextView mTvJournalGirlTwo;
//        private final TextView mTvJournalGirlThree;
//        private final ImageView mIvCoverGirlOne;
//        private final ImageView mIvCoverGirlTwo;
//        private final ImageView mIvCoverGirlThree;
//        /**
//         * special
//         */
//        private final TextView mTvJournalSpecialOne;
//        private final TextView mTvJournalSpecialTwo;
//        private final TextView mTvJournalSpecialThree;
//        private final ImageView mIvCoverSpecialOne;
//        private final ImageView mIvCoverSpecialTwo;
//        private final ImageView mIvCoverSpecialThree;
//
//        public MSNumHolder(View view) {
//            /**
//             * boy
//             */
//            mTvJournalBoyOne = (TextView) view.findViewById(R.id.item_tv_journal_boy_one_magazine_son_fragment);
//            mTvJournalBoyTwo = (TextView) view.findViewById(R.id.item_tv_journal_boy_two_magazine_son_fragment);
//            mTvJournalBoyThree = (TextView) view.findViewById(R.id.item_tv_journal_boy_three_magazine_son_fragment);
//            mIvCoverBoyOne = (ImageView) view.findViewById(R.id.item_iv_cover_sum_boy_one_magazine_son_fragment);
//            mIvCoverBoyTwo = (ImageView) view.findViewById(R.id.item_iv_cover_sum_boy_two_magazine_son_fragment);
//            mIvCoverBoyThree = (ImageView) view.findViewById(R.id.item_iv_cover_sum_boy_three_magazine_son_fragment);
//            /**
//             * girl
//             */
//            mTvJournalGirlOne = (TextView) view.findViewById(R.id.item_tv_journal_girl_one_magazine_son_fragment);
//            mTvJournalGirlTwo = (TextView) view.findViewById(R.id.item_tv_journal_girl_two_magazine_son_fragment);
//            mTvJournalGirlThree = (TextView) view.findViewById(R.id.item_tv_journal_girl_three_magazine_son_fragment);
//            mIvCoverGirlOne = (ImageView) view.findViewById(R.id.item_iv_cover_sum_girl_one_magazine_son_fragment);
//            mIvCoverGirlTwo = (ImageView) view.findViewById(R.id.item_iv_cover_sum_girl_two_magazine_son_fragment);
//            mIvCoverGirlThree = (ImageView) view.findViewById(R.id.item_iv_cover_sum_girl_three_magazine_son_fragment);
//            /**
//             * sepcial
//             */
//            mTvJournalSpecialOne = (TextView) view.findViewById(R.id.item_tv_journal_special_one_magazine_son_fragment);
//            mTvJournalSpecialTwo = (TextView) view.findViewById(R.id.item_tv_journal_special_two_magazine_son_fragment);
//            mTvJournalSpecialThree = (TextView) view.findViewById(R.id.item_tv_journal_special_three_magazine_son_fragment);
//            mIvCoverSpecialOne = (ImageView) view.findViewById(R.id.item_iv_cover_sum_special_one_magazine_son_fragment);
//            mIvCoverSpecialTwo = (ImageView) view.findViewById(R.id.item_iv_cover_sum_special_two_magazine_son_fragment);
//            mIvCoverSpecialThree = (ImageView) view.findViewById(R.id.item_iv_cover_sum_special_three_magazine_son_fragment);
//        }
//    }
}
