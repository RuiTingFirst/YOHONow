package lanou.dllo.yohonow.magazine.mzson;

import android.widget.ImageView;
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

/**
 * Created by dllo on 16/11/26.
 */

public class MZSonFragment extends BaseFragment {

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
    /**
     * 绑定布局
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
    }

    private void initUrlSpecialData() {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(URLValues.YOHO_SPECIAL_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                MSNumSpecialBean msNumSpecialBean = gson.fromJson(response, MSNumSpecialBean.class);
                mSpecialBeenList = msNumSpecialBean.getData();
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
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    /**
     * get解析 请求girl页数据
     */
    private void initURLGirlData() {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(URLValues.YOHO_GIRL_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                MSNumGirlBean msNumGirlBean = gson.fromJson(response, MSNumGirlBean.class);
                mGirlBeanList = msNumGirlBean.getData();
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
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    /**
     * get解析 请求boy 页数据
     */
    private void initUrlData() {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(URLValues.YOHO_BOY_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                MSNumBean msNumBean = gson.fromJson(response, MSNumBean.class);
                mList = msNumBean.getData();
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
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
}
