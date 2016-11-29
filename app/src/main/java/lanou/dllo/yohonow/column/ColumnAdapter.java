package lanou.dllo.yohonow.column;

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
 * Created by dllo on 16/11/24.
 */

public class ColumnAdapter extends BaseAdapter {
    private ColumnBean mColumnBeen;
    private Context mContext;

    public ColumnAdapter(Context context) {
        mContext = context;
    }

    public void setColumnBeen(ColumnBean columnBeen) {
        mColumnBeen = columnBeen;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mColumnBeen != null && mColumnBeen.getData().size() > 0 ? mColumnBeen.getData().size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return mColumnBeen.getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ColumnHolder columnHolder = null;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.lv_column_fragment_item, viewGroup, false);
            columnHolder = new ColumnHolder(view);
            view.setTag(columnHolder);
        } else {
            columnHolder = (ColumnHolder) view.getTag();
        }
        /**
         * 图片
         */
        Picasso.with(mContext).load(mColumnBeen.getData().get(i).getCover()).into(columnHolder.mIvCover);
        /**
         * 标题
         */
        columnHolder.mTvTitle.setText(mColumnBeen.getData().get(i).getTitle());
        /**
         * summary
         */
        columnHolder.mTvSummary.setText(mColumnBeen.getData().get(i).getSummary());

        /**
         * 更新篇数
         */
        columnHolder.mTvTotal.setText(mColumnBeen.getData().get(i).getTotal());
        return view;
    }

    /**
     * 缓存类
     */
    private class ColumnHolder {

        private final ImageView mIvCover;
        private final TextView mTvTitle;
        private final TextView mTvSummary;
        private final TextView mTvTotal;

        public ColumnHolder(View view) {
            mIvCover = (ImageView) view.findViewById(R.id.iv_cover_lv_column_fragment_item);
            mTvTitle = (TextView) view.findViewById(R.id.tv_title_lv_column_fragment);
            mTvSummary = (TextView) view.findViewById(R.id.tv_summary_column_fragment_item);
            mTvTotal = (TextView) view.findViewById(R.id.tv_total_column_fragment);
        }
    }
}
