package lanou.dllo.yohonow.column;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
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
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.base.BaseFragment;
import lanou.dllo.yohonow.tools.circletools.CircleDrawable;
import lanou.dllo.yohonow.tools.urltools.URLValues;
import lanou.dllo.yohonow.tools.volleytools.NetHelper;
import lanou.dllo.yohonow.tools.volleytools.NetListener;

/**
 * Created by dllo on 16/11/24.
 */

public class ColumnFragment extends BaseFragment {

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

    /**
     * 绑定布局
     *
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
        mListView = (ListView) getView().findViewById(R.id.lv_column_fragment);
        View headView = LayoutInflater.from(mContext).inflate(R.layout.head_item_column_fragment, null);
        mListView.addHeaderView(headView);
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
    }

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
        initVolleyPost();
        /**
         * 栏目头布局
         */
        initHeadView();
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
                Picasso.with(mContext).load(response.getData().getData().get(0).getHeadpic()).transform(new CircleTransform()).into(mIvHeadpic);
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
                mColumnAdapter.setColumnBeen(response);
                mListView.setAdapter(mColumnAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, map);
    }

    /**
     * 画圆
     */
    class CircleTransform implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight()) ;

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
