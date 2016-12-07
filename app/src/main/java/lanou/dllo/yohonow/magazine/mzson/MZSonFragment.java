package lanou.dllo.yohonow.magazine.mzson;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.base.BaseFragment;
import lanou.dllo.yohonow.tools.urltools.URLValues;
import lanou.dllo.yohonow.tools.volleytools.NetHelper;
import lanou.dllo.yohonow.tools.volleytools.NetListener;

/**
 * Created by dllo on 16/11/26.
 */

public class MZSonFragment extends BaseFragment implements View.OnClickListener {

    /**
     * boy
     */
    private TextView mTvJournalBoyOne;
    private TextView mTvJournalBoyTwo;
    private TextView mTvJournalBoyThree;
    private ImageView mIvCoverBoyOne;
    private ImageView mIvCoverBoyTwo;
    private ImageView mIvCoverBoyThree;
    /**
     * girl
     */
    private TextView mTvJournalGirlOne;
    private TextView mTvJournalGirlTwo;
    private TextView mTvJournalGirlThree;
    private ImageView mIvCoverGirlOne;
    private ImageView mIvCoverGirlTwo;
    private ImageView mIvCoverGirlThree;
    /**
     * special
     */
    private TextView mTvJournalSpecialOne;
    private TextView mTvJournalSpecialTwo;
    private TextView mTvJournalSpecialThree;
    private ImageView mIvCoverSpecialOne;
    private ImageView mIvCoverSpecialTwo;
    private ImageView mIvCoverSpecialThree;

    private List<MSNumBean.DataBean> mList;
    private List<MSNumGirlBean.DataBean> mGirlBeanList;
    private List<MSNumSpecialBean.DataBean> mSpecialBeenList;
    private PopupWindow mPopupWindow;
    private View mView;
    private RelativeLayout mRl;

    /**
     * 绑定布局
     *
     * @return
     */
    @Override
    protected int setLayout() {
        return R.layout.magazine_son_fragment;
    }

    /**
     * 初始化布局
     */
    @Override
    protected void initView() {
        /**
         * boy
         */
        mTvJournalBoyOne = bindView(R.id.item_tv_journal_boy_one_magazine_son_fragment);
        mTvJournalBoyTwo = bindView(R.id.item_tv_journal_boy_two_magazine_son_fragment);
        mTvJournalBoyThree = bindView(R.id.item_tv_journal_boy_three_magazine_son_fragment);
        mIvCoverBoyOne = bindView(R.id.item_iv_cover_sum_boy_one_magazine_son_fragment);
        mIvCoverBoyTwo = bindView(R.id.item_iv_cover_sum_boy_two_magazine_son_fragment);
        mIvCoverBoyThree = bindView(R.id.item_iv_cover_sum_boy_three_magazine_son_fragment);
        /**
         * girl
         */
        mTvJournalGirlOne = bindView(R.id.item_tv_journal_girl_one_magazine_son_fragment);
        mTvJournalGirlTwo = bindView(R.id.item_tv_journal_girl_two_magazine_son_fragment);
        mTvJournalGirlThree = bindView(R.id.item_tv_journal_girl_three_magazine_son_fragment);
        mIvCoverGirlOne = bindView(R.id.item_iv_cover_sum_girl_one_magazine_son_fragment);
        mIvCoverGirlTwo = bindView(R.id.item_iv_cover_sum_girl_two_magazine_son_fragment);
        mIvCoverGirlThree = bindView(R.id.item_iv_cover_sum_girl_three_magazine_son_fragment);
        /**
         * sepcial
         */
        mTvJournalSpecialOne = bindView(R.id.item_tv_journal_special_one_magazine_son_fragment);
        mTvJournalSpecialTwo = bindView(R.id.item_tv_journal_special_two_magazine_son_fragment);
        mTvJournalSpecialThree = bindView(R.id.item_tv_journal_special_three_magazine_son_fragment);
        mIvCoverSpecialOne = bindView(R.id.item_iv_cover_sum_special_one_magazine_son_fragment);
        mIvCoverSpecialTwo = bindView(R.id.item_iv_cover_sum_special_two_magazine_son_fragment);
        mIvCoverSpecialThree = bindView(R.id.item_iv_cover_sum_special_three_magazine_son_fragment);


    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        /**
         * get解析 请求boy 页数据
         */
        initUrlData();
        /**
         * get解析 请求girl页数据
         */
        initURLGirlData();
        /**
         * get解析, 请求special页数据
         */
        initUrlSpecialData();
        /**
         * 点击事件
         */
        setClick(this, mIvCoverBoyOne, mIvCoverBoyTwo, mIvCoverBoyThree, mIvCoverGirlOne, mIvCoverGirlTwo, mIvCoverGirlThree,
                mIvCoverSpecialOne, mIvCoverSpecialTwo, mIvCoverSpecialThree);
    }

    /**
     * get解析, 请求special页数据
     */
    private void initUrlSpecialData() {
        NetHelper.MyRequest(URLValues.YOHO_SPECIAL_URL, MSNumSpecialBean.class, new NetListener<MSNumSpecialBean>() {
            @Override
            public void successListener(MSNumSpecialBean response) {
                mSpecialBeenList = response.getData();
                /**
                 * special
                 */
                mTvJournalSpecialOne.setText(mSpecialBeenList.get(0).getJournal());
                mTvJournalSpecialTwo.setText(mSpecialBeenList.get(1).getJournal());
                mTvJournalSpecialThree.setText(mSpecialBeenList.get(2).getJournal());
                Picasso.with(mContext).load(mSpecialBeenList.get(0).getCover()).into(mIvCoverSpecialOne);
                Picasso.with(mContext).load(mSpecialBeenList.get(1).getCover()).into(mIvCoverSpecialTwo);
                Picasso.with(mContext).load(mSpecialBeenList.get(2).getCover()).into(mIvCoverSpecialThree);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    /**
     * get解析 请求girl页数据
     */
    private void initURLGirlData() {
        NetHelper.MyRequest(URLValues.YOHO_GIRL_URL, MSNumGirlBean.class, new NetListener<MSNumGirlBean>() {
            @Override
            public void successListener(MSNumGirlBean response) {
                mGirlBeanList = response.getData();
                /**
                 * girl
                 */
                mTvJournalGirlOne.setText(mGirlBeanList.get(0).getJournal());
                mTvJournalGirlTwo.setText(mGirlBeanList.get(1).getJournal());
                mTvJournalGirlThree.setText(mGirlBeanList.get(2).getJournal());
                Picasso.with(mContext).load(mGirlBeanList.get(0).getCover()).into(mIvCoverGirlOne);
                Picasso.with(mContext).load(mGirlBeanList.get(1).getCover()).into(mIvCoverGirlTwo);
                Picasso.with(mContext).load(mGirlBeanList.get(2).getCover()).into(mIvCoverGirlThree);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    /**
     * get解析 请求boy 页数据
     */
    private void initUrlData() {
        NetHelper.MyRequest(URLValues.YOHO_BOY_URL, MSNumBean.class, new NetListener<MSNumBean>() {
            @Override
            public void successListener(MSNumBean response) {
                mList = response.getData();
                /**
                 * boy
                 */
                mTvJournalBoyOne.setText(mList.get(0).getJournal());
                mTvJournalBoyTwo.setText(mList.get(1).getJournal());
                mTvJournalBoyThree.setText(mList.get(2).getJournal());
                Picasso.with(mContext).load(mList.get(0).getCover()).into(mIvCoverBoyOne);
                Picasso.with(mContext).load(mList.get(1).getCover()).into(mIvCoverBoyTwo);
                Picasso.with(mContext).load(mList.get(2).getCover()).into(mIvCoverBoyThree);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            /**
             * boy 图片1
             */
            case R.id.item_iv_cover_sum_boy_one_magazine_son_fragment:
                /**
                 * POP 布局显示放大图片
                 */
                String urlBoyOne = mList.get(0).getCover();
                String contentBoyOne = mList.get(0).getJournal();
                initPopWindow(urlBoyOne, contentBoyOne);
                break;
            /**
             * boy 图片2
             */
            case R.id.item_iv_cover_sum_boy_two_magazine_son_fragment:
                /**
                 * POP 布局显示放大图片
                 */
                String urlBoyTwo = mList.get(1).getCover();
                String contentBoyTwo = mList.get(1).getJournal();
                initPopWindow(urlBoyTwo, contentBoyTwo);
                break;
            /**
             * boy 图片3
             */
            case R.id.item_iv_cover_sum_boy_three_magazine_son_fragment:
                /**
                 * POP 布局显示放大图片
                 */
                String urlBoyThree = mList.get(2).getCover();
                String contentBoyThree = mList.get(2).getJournal();
                initPopWindow(urlBoyThree, contentBoyThree);
                break;
            /**
             * girl 图片1
             */
            case R.id.item_iv_cover_sum_girl_one_magazine_son_fragment:
                /**
                 * POP 布局显示放大图片
                 */
                String urlGirlOne = mGirlBeanList.get(0).getCover();
                String contentGirlOne = mGirlBeanList.get(0).getJournal();
                initPopWindow(urlGirlOne, contentGirlOne);
                break;
            /**
             * girl 图片2
             */
            case R.id.item_iv_cover_sum_girl_two_magazine_son_fragment:
                /**
                 * POP 布局显示放大图片
                 */
                String urlGirlTwo = mGirlBeanList.get(1).getCover();
                String contentGirlTwo = mGirlBeanList.get(1).getJournal();
                initPopWindow(urlGirlTwo, contentGirlTwo);
                break;
            /**
             * girl 图片3
             */
            case R.id.item_iv_cover_sum_girl_three_magazine_son_fragment:
                /**
                 * POP 布局显示放大图片
                 */
                String urlGirlThree = mGirlBeanList.get(2).getCover();
                String contentGirlThree = mGirlBeanList.get(2).getJournal();
                initPopWindow(urlGirlThree, contentGirlThree);
                break;
            /**
             * special 图片1
             */
            case R.id.item_iv_cover_sum_special_one_magazine_son_fragment:
                /**
                 * POP 布局显示放大图片
                 */
                String urlSpecialOne = mSpecialBeenList.get(0).getCover();
                String contentSpecialOne = mSpecialBeenList.get(0).getJournal();
                initPopWindow(urlSpecialOne, contentSpecialOne);
                break;
            /**
             * special 图片2
             */
            case R.id.item_iv_cover_sum_special_two_magazine_son_fragment:
                /**
                 * POP 布局显示放大图片
                 */
                String urlSpecialTwo = mSpecialBeenList.get(1).getCover();
                String contentSpecialTwo = mSpecialBeenList.get(1).getJournal();
                initPopWindow(urlSpecialTwo, contentSpecialTwo);
                break;
            /**
             * special 图片3
             */
            case R.id.item_iv_cover_sum_special_three_magazine_son_fragment:
                /**
                 * POP 布局显示放大图片
                 */
                String urlSpecialThree = mSpecialBeenList.get(2).getCover();
                String contentSpecialThree = mSpecialBeenList.get(2).getJournal();
                initPopWindow(urlSpecialThree, contentSpecialThree);
                break;
        }
    }

    /**
     * POP 布局显示放大图
     */
    private void initPopWindow(String url, String content) {
        mPopupWindow = new PopupWindow(mContext);
        // 铺满屏幕
        mPopupWindow = new PopupWindow(mView, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        mView = LayoutInflater.from(mContext).inflate(R.layout.pop_mzson_fragment, null);
        // 设置动画效果
        mPopupWindow.setAnimationStyle(R.style.AnimationFade);
        mPopupWindow.setFocusable(true);
        ImageView imageView = (ImageView) mView.findViewById(R.id.iv_pop_mzson_fragment);

        Picasso.with(mContext).load(url).into(imageView);
        TextView textView = (TextView) mView.findViewById(R.id.tv_yoho_pop_mzson_fragment);
        mRl = (RelativeLayout) mView.findViewById(R.id.rl_pop_mzson_fragment);
        textView.setText(content);
        // 隐藏窗口标题栏
        mRl.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        mPopupWindow.setContentView(mView);
        // 点击pop消失
        mRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
        if (!mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(mIvCoverBoyOne, Gravity.BOTTOM, 0, 0);
        } else {
            mPopupWindow.dismiss();
        }

    }
}
