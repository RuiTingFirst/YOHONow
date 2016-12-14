package lanou.dllo.yohonow.column;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.base.BaseFragment;
import lanou.dllo.yohonow.column.columndetail.ColumnDetailAty;
import lanou.dllo.yohonow.tools.circletools.CircleDrawable;
import lanou.dllo.yohonow.tools.urltools.URLValues;
import lanou.dllo.yohonow.tools.volleytools.NetHelper;
import lanou.dllo.yohonow.tools.volleytools.NetListener;

/**
 * Created by dllo on 16/11/24.
 */

public class ColumnFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener {

    private ListView mListView;
    private ColumnAdapter mColumnAdapter;
    private ImageView mIvBanner;
    private TextView mTvDescription;
    private TextView mTvAnswer;
    private ImageView mIvHeadpic;
    private TextView mTvNick;
    private TextView mTvQuestion;
    private ImageView mIvDefault;
    private TextView mTvTitle;
    private TextView mTvDataAnswer;
    private List<ColumnHeadBean.DataBeanX> mXList;
    private SwipeToLoadLayout mSwipeToLoadLayout;

    private String createTime = "";
    private int a = 1;
    private Gson gson;
    private String values;
    private String num = "0";
    private HashMap<String, String> mapsure;
    private HashMap<String, String> map;

    private ColumnBean mColumnBean;

    /**
     * 绑定布局
     * @return
     */
    @Override
    protected int setLayout() {
        return R.layout.column_fragment;
    }

    /**
     * 初始化布局
     */
    @Override
    protected void initView() {
        /**
         * 上拉刷新, 下拉加载
         */
        mSwipeToLoadLayout = bindView(R.id.swipe_toload_layout_colum_fragment);
        mListView = bindView(R.id.swipe_target);
        /**
         * 头布局
         */
        View headView = LayoutInflater.from(mContext).inflate(R.layout.head_item_column_fragment, null);
        mListView.addHeaderView(headView);
        /**
         * 轮播图
         */
        mIvBanner = (ImageView) headView.findViewById(R.id.iv_banner_head_item_column_fragment);
        mTvDescription = (TextView) headView.findViewById(R.id.tv_description_head_item_fragment);
        mTvAnswer = (TextView) headView.findViewById(R.id.tv_answers_head_item_fragment);
        mIvHeadpic = (ImageView) headView.findViewById(R.id.iv_headpic_head_item_column_fragment);
        mTvNick = (TextView) headView.findViewById(R.id.tv_nick_head_item_fragment);
        mTvQuestion = (TextView) headView.findViewById(R.id.tv_question_head_item_fragment);
        mIvDefault = (ImageView) headView.findViewById(R.id.iv_daifu_head_item_column_fragment);
        circleDrawable();
        mTvTitle = (TextView) headView.findViewById(R.id.tv_title_head_item_fragment);
        mTvDataAnswer = (TextView) headView.findViewById(R.id.tv_data_answer_head_item_fragment);

        /**
         * 点击跳到二级界面
         */
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(mContext, ColumnDetailAty.class);
                intent.putExtra("headImage", mColumnBean.getData().get(i -1).getCover());
                intent.putExtra("title", mColumnBean.getData().get(i -1).getTitle());
                intent.putExtra("id", mColumnBean.getData().get(i -1).getId());
                intent.putExtra("total", mColumnBean.getData().get(i -1).getTotal());
                intent.putExtra("summary", mColumnBean.getData().get(i -1).getSummary());
                startActivity(intent);
            }
        });
    }

    /**
     * 本地图片画圆
     */
    private void circleDrawable() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.static_shoe);
        CircleDrawable circleDrawable = new CircleDrawable(bitmap);
        mIvDefault.setImageDrawable(circleDrawable);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        mColumnAdapter = new ColumnAdapter(mContext);
        columnMap();
        initVolleyPost();
        /**
         * 栏目头布局
         */
        initHeadView();
        /**
         * 上拉加载, 下拉刷新监听
         */
        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
    }

    /**
     * 栏目头布局
     */
    private void initHeadView() {
        NetHelper.MyRequest(URLValues.COLUMN_HEAD_URL, ColumnHeadBean.class, new NetListener<ColumnHeadBean>() {
            @Override
            public void successListener(ColumnHeadBean response) {
                Picasso.with(mContext).load(response.getData().getBanner()).into(mIvBanner);
                mTvAnswer.setText("以诊断了" + response.getData().getAnswers() + "位症案");
                mTvTitle.setText(response.getData().getTitle());
                mTvDescription.setText(response.getData().getDescription());
                mTvNick.setText(response.getData().getData().get(0).getNick());
                mTvQuestion.setText(" " + response.getData().getData().get(0).getQuestion());
                mTvDataAnswer.setText(" " + response.getData().getData().get(0).getAnswer());
                Glide.with(mContext).load(response.getData().getData().get(0).getHeadpic()).bitmapTransform(new CropCircleTransformation(mContext)).into(mIvHeadpic);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    /**
     * post 请求, 解析数据
     */
    private void initVolleyPost() {
        HashMap<String, String> map = new HashMap<>();
        map.put(URLValues.COLUMN_KEY, URLValues.COLUMN_VALUE);
        NetHelper.MyRequest(URLValues.COLUMN_URL, ColumnBean.class, new NetListener<ColumnBean>() {
            @Override
            public void successListener(ColumnBean response) {
                mColumnBean = response;
                mColumnAdapter.setColumnBeen(response);
                //判断数据
                if (a == 1) {
                    createTime = response.getData().get(response.getData().size() - 1).getCreate_time();
                }
                mListView.setAdapter(mColumnAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, map);
    }

    // 栏目页面的HashMap
    private void columnMap() {
        map = new HashMap<>();
        map.put("limit", "12");

        map.put("lastTime", num);

        map.put("startTime", "0");
        map.put("platform", "4");
        map.put("locale", "zh-Hans");
        map.put("language", "zh-Hans");
        map.put("udid", "00000000000000063aa461b71c4cfcf");
        map.put("curVersion", "5.0.4");

        HashMap<String, String> mm = new HashMap<>();
        mm.put("udid", "00000000000000063aa461b71c4cfcf");
        String a = new Gson().toJson(mm).toString();
        map.put("authInfo", a);

        gson = new Gson();
        values = gson.toJson(map).toString();

        mapsure = new HashMap<>();
        mapsure.put("parameters", values);
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        // 延时
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 刷新
                mSwipeToLoadLayout.setRefreshing(false);
                getRefreshPost();
            }
        }, 2000);
    }

    private void getRefreshPost() {
        NetHelper.MyRequest(URLValues.COLUMN_URL, ColumnBean.class, new NetListener<ColumnBean>() {
            @Override
            public void successListener(ColumnBean response) {
                mColumnAdapter.setColumnBeen(response);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, mapsure);
    }

    /**
     * 上拉加载
     */
    @Override
    public void onLoadMore() {
        //让其第一个接口的数据只加载一遍
        if (a == 1) {
            num = createTime;
            map.put("lastTime", num);
            values = gson.toJson(map).toString();
            mapsure.put("parameters", values);
            a = -1;
        }
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeToLoadLayout.setLoadingMore(false);
                getLoadData();
            }
        }, 3000);
    }

    private void getLoadData() {
        NetHelper.MyRequest(URLValues.COLUMN_URL, ColumnBean.class, new NetListener<ColumnBean>() {
            @Override
            public void successListener(ColumnBean response) {
                mColumnAdapter.addMore(response);
                //接口中是空的,让其重复加载
                if (response.getData().isEmpty()) {
                    num = "0";
                } else {
                    num = response.getData().get(response.getData().size() - 1).getCreate_time();
                }

                //更新map
                map.put("lastTime", num);
                // 反转化 把key value 转化成字符串
                values = gson.toJson(map).toString();
                mapsure.put("parameters", values);
                mapsure.put(URLValues.COLUMN_KEY, values);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, mapsure);
    }

    /**
     * 画圆
     */
    class CircleTransform implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }

    }
}
