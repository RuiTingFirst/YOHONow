package lanou.dllo.yohonow.home;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.tools.timetools.StringTime;

/**
 * Created by dllo on 16/11/23.
 */

public class HomeAdapter extends BaseAdapter {

    Context mContext;
    private HomeBean mHomeBean;
    private static final int TYPE_ZERO = 0;
    private static final int TYPE_TWO = 2;
    private static final int TYPE_THREE = 3;
    private static final int TYPE_COUNT = 20;
    private List<HomeBean.DataBean.ParamsBean> mBean;

    public void setHomeBean(HomeBean homeBean) {
        mHomeBean = homeBean;
        notifyDataSetChanged();
    }

    public HomeAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mHomeBean != null && mHomeBean.getData().size() > 0 ? mHomeBean.getData().size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return mHomeBean.getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHomeBean.getData().get(position).getType() == 0) {
            Log.d("类型", "mHomeBean.getData().get(position).getType():" + mHomeBean.getData().get(position).getType());
            return TYPE_ZERO;
        } else if (mHomeBean.getData().get(position).getType() == 2) {
            return TYPE_TWO;
        } else {
            return -1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    // 获取三种不同行布局数据
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        mBean = mHomeBean.getData().get(i).getParams();
        switch (getItemViewType(i)) {
            // type == 0, 获取三个布局数据
            case TYPE_ZERO:
                ViewHolderOne viewHolderOne = null;
                if (view == null) {
                    // 注入布局
                    view = LayoutInflater.from(mContext).inflate(R.layout.zero_home_fragment_item, viewGroup, false);
                    viewHolderOne = new ViewHolderOne(view);
                    view.setTag(viewHolderOne);
                } else {
                    viewHolderOne = (ViewHolderOne) view.getTag();
                }

                // type == 0, 布局1
                /**
                 * type == 0, 布局1 标题
                 */
                viewHolderOne.mTvTitleZero.setText(mBean.get(0).getTitle());
                /**
                 * type == 0, 布局1 时间
                 */
                viewHolderOne.mTvCreateTimeZero.setText(StringTime.IntoTime(mBean.get(0).getCreate_time()));
                /**
                 * type == 0, 布局1 tag_name
                 */
                viewHolderOne.mTvTagNameZero.setText(mBean.get(0).getTag().get(0).getTag_name());
                /**
                 * type == 0, 布局1 图片个数
                 * listView 复用, 每当设置GONE的时候, 下回用就是GONE, 所以每次用的时候必须让它显示(VISIBLE)
                 */
                viewHolderOne.mLlOne.setVisibility(View.VISIBLE);
                if (mBean.get(0).getImgNum() > 0) {
                    // 布局显示
                    viewHolderOne.mTvImgNumZeroOne.setText("  " + String.valueOf(mBean.get(0).getImgNum()));
                } else {
                    viewHolderOne.mLlOne.setVisibility(View.INVISIBLE);
                }
                /**
                 * type == 0, 布局1 图片
                 */
                Picasso.with(mContext).load(mBean.get(0).getImage()).into(viewHolderOne.mImageViewZero);

                // type == 0, 布局2
                /**
                 * type == 0, 布局2 标题
                 */
                viewHolderOne.mTvTitleZeroTwo.setText(mBean.get(1).getTitle());
                /**
                 * type == 0, 布局2 时间
                 */
                viewHolderOne.mTvCreateTimeZeroTwo.setText(StringTime.IntoTime(mBean.get(1).getCreate_time()));

                /**
                 * type == 0, 布局2 tag_name
                 */
                viewHolderOne.mTvTagNameZeroTwo.setText(mBean.get(1).getTag().get(0).getTag_name());
                /**
                 * type == 0, 布局2 图片个数
                 * listView 复用, 每当设置GONE的时候, 下回用就是GONE, 所以每次用的时候必须让它显示(VISIBLE)
                 */
                viewHolderOne.mLlTwo.setVisibility(View.VISIBLE);
                if (mBean.get(1).getImgNum() > 0) {
                    // 布局显示
                    viewHolderOne.mTvImgNumZeroTwo.setText("  " + String.valueOf(mBean.get(1).getImgNum()));
                } else {
                    // 占位
                    viewHolderOne.mLlTwo.setVisibility(View.INVISIBLE);
                }
                /**
                 * type == 0, 布局2 图片
                 */
                Picasso.with(mContext).load(mBean.get(1).getImage()).into(viewHolderOne.mImageViewZeroTwo);

                // type == 0, 布局3
                /**
                 * type == 0, 布局3 标题
                 */
                viewHolderOne.mTvTitleZeroThree.setText(mBean.get(2).getTitle());

                /**
                 * type == 0, 布局3 时间
                 */
                viewHolderOne.mTvCreateTimeZeroThree.setText(StringTime.IntoTime(mBean.get(2).getCreate_time()));
                /**
                 * type == 0, 布局3 tag_name
                 */
                viewHolderOne.mTvTagNameZeroThree.setText(mBean.get(2).getTag().get(0).getTag_name());

                /**
                 * type == 0, 布局3 图片个数
                 * listView 复用, 每当设置GONE的时候, 下回用就是GONE, 所以每次用的时候必须让它显示(VISIBLE)
                 */
                viewHolderOne.mLlThree.setVisibility(View.VISIBLE);
                if (mBean.get(2).getImgNum() > 0) {
                    // 显示隐藏布局
                    viewHolderOne.mTvImgNumZeroThree.setText("  " + String.valueOf(mBean.get(2).getImgNum()));
                } else {
                    viewHolderOne.mLlThree.setVisibility(View.INVISIBLE);
                }

                /**
                 * type == 0, 布局3 图片
                 */
                Picasso.with(mContext).load(mBean.get(2).getImage()).into(viewHolderOne.mImageViewZeroThree);
                break;

            // type == 2, 获取布局数据
            case TYPE_TWO:
                ViewHolderTwo viewHolderTwo = null;
                if (view == null) {
                    // 注入布局
                    view = LayoutInflater.from(mContext).inflate(R.layout.two_home_fragment, viewGroup, false);
                    viewHolderTwo = new ViewHolderTwo(view);
                    view.setTag(viewHolderTwo);
                } else {
                    viewHolderTwo = (ViewHolderTwo) view.getTag();
                }
                Log.d("HomeAdapter", mBean.get(0).getTag().get(0).getTag_name() + "--");
                /**
                 * type == 2, tag_name
                 */
                viewHolderTwo.mTvTagNameTwo.setText(mBean.get(0).getTag().get(0).getTag_name());
//                String timeTwo = initTime(String.valueOf(mBean.get(i).getCreate_time()));
//                viewHolderTwo.mTvCreateTimeTwo.setText(timeTwo);

                /**
                 * type == 2, 时间
                 */
                viewHolderTwo.mTvCreateTimeTwo.setText(StringTime.IntoTime(mBean.get(0).getCreate_time()));
                /**
                 * type == 2, 标题
                 */
                viewHolderTwo.mTvTitleTwo.setText(mBean.get(0).getTitle());

                /**
                 * type == 2, 图片
                 */
                Picasso.with(mContext).load(mBean.get(0).getImage()).into(viewHolderTwo.mIvImageTwo);

                break;

            case -1:

                ViewHolderOne viewHolderOne1 = null;
                if (view == null) {
                    // 注入布局
                    view = LayoutInflater.from(mContext).inflate(R.layout.zero_home_fragment_item, viewGroup, false);
                    viewHolderOne1 = new ViewHolderOne(view);
                    view.setTag(viewHolderOne1);
                } else {
                    viewHolderOne = (ViewHolderOne) view.getTag();
                }

                break;

            // type == 3, 获取布局数据
//            case TYPE_THREE:
//                if (view == null){
//                    // 注入布局
//                    view = LayoutInflater.from(mContext).inflate(R.layout.three_home_fragment_item, viewGroup, false);
//                }
//                break;
        }
        return view;
    }

    /**
     * type == 0, 缓存类
     */
    private class ViewHolderOne {

        private final ImageView mImageViewZero;
        private final TextView mTvTitleZero;
        private final TextView mTvTagNameZero;
        private final TextView mTvCreateTimeZero;
        private final TextView mTvImgNumZeroOne;
        private final LinearLayout mLlOne;

        private final ImageView mImageViewZeroTwo;
        private final TextView mTvTitleZeroTwo;
        private final TextView mTvTagNameZeroTwo;
        private final TextView mTvCreateTimeZeroTwo;
        private final LinearLayout mLlTwo;

        private final ImageView mImageViewZeroThree;
        private final TextView mTvTitleZeroThree;
        private final TextView mTvTagNameZeroThree;
        private final TextView mTvCreateTimeZeroThree;
        private final TextView mTvImgNumZeroTwo;
        private final TextView mTvImgNumZeroThree;
        private final LinearLayout mLlThree;

        public ViewHolderOne(View view) {
            mImageViewZero = (ImageView) view.findViewById(R.id.iv_params_image_zero_home_fragment_item);
            mTvTitleZero = (TextView) view.findViewById(R.id.tv_params_one_title_zero_home_fragment_item);
            mTvTagNameZero = (TextView) view.findViewById(R.id.tv_params_one_tag_name_zero_home_fragment_item);
            mTvCreateTimeZero = (TextView) view.findViewById(R.id.tv_params_one_create_time_zero_home_fragment_item);
            mTvImgNumZeroOne = (TextView) view.findViewById(R.id.tv_params_one_img_num_zero_home_fragment_item);
            mLlOne = (LinearLayout) view.findViewById(R.id.ll_one_zero_home_fragment_item);

            mImageViewZeroTwo = (ImageView) view.findViewById(R.id.iv_params_two_image_zero_home_fragment_item);
            mTvTitleZeroTwo = (TextView) view.findViewById(R.id.tv_params_two_title_zero_home_fragment_item);
            mTvTagNameZeroTwo = (TextView) view.findViewById(R.id.tv_params_two_tag_name_zero_home_fragment_item);
            mTvCreateTimeZeroTwo = (TextView) view.findViewById(R.id.tv_params_two_create_time_zero_home_fragment_item);
            mTvImgNumZeroTwo = (TextView) view.findViewById(R.id.tv_params_two_img_num_zero_home_fragment_item);
            mLlTwo = (LinearLayout) view.findViewById(R.id.ll_two_zero_home_fragment_item);

            mImageViewZeroThree = (ImageView) view.findViewById(R.id.iv_params_three_image_zero_home_fragment_item);
            mTvTitleZeroThree = (TextView) view.findViewById(R.id.tv_params_three_title_zero_home_fragment_item);
            mTvTagNameZeroThree = (TextView) view.findViewById(R.id.tv_params_three_tag_name_zero_home_fragment_item);
            mTvCreateTimeZeroThree = (TextView) view.findViewById(R.id.tv_params_three_create_time_zero_home_fragment_item);
            mTvImgNumZeroThree = (TextView) view.findViewById(R.id.tv_params_three_img_num_zero_home_fragment_item);
            mLlThree = (LinearLayout) view.findViewById(R.id.ll_three_zero_home_fragment_item);
        }
    }

    /**
     * type == 2 缓存类
     */
    private class ViewHolderTwo {

        private final ImageView mIvImageTwo;
        private final TextView mTvTitleTwo;
        private final TextView mTvCreateTimeTwo;
        private final TextView mTvTagNameTwo;

        public ViewHolderTwo(View view) {
            mIvImageTwo = (ImageView) view.findViewById(R.id.iv_params_image_two_home_fragment);
            mTvTitleTwo = (TextView) view.findViewById(R.id.tv_params_title_two_home_fragment);
            mTvCreateTimeTwo = (TextView) view.findViewById(R.id.tv_params_create_time_two_home_fragment);
            mTvTagNameTwo = (TextView) view.findViewById(R.id.tv_params_tag_name_two_home_fragment);
        }
    }
}
