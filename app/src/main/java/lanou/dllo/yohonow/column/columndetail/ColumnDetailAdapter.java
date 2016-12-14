package lanou.dllo.yohonow.column.columndetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.tools.timetools.StringTime;

/**
 * Created by dllo on 16/12/9.
 */

public class ColumnDetailAdapter extends BaseAdapter {
    private ColumnDetailBean mColumnDetailBean;
    private Context mContext;
    private List<ColumnDetailBean.DataBean.ContentBean> mList;

    public void setColumnDetailBean(ColumnDetailBean columnDetailBean) {
        mColumnDetailBean = columnDetailBean;
    }

    public ColumnDetailAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mColumnDetailBean != null && mColumnDetailBean.getData().getContent().size() > 0 ? mColumnDetailBean.getData().getContent().size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return mColumnDetailBean != null && mColumnDetailBean.getData().getContent().size() > 0 ? mColumnDetailBean.getData().getContent().get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        mList = mColumnDetailBean.getData().getContent();
        ColumnDetailHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_lv_colimn_detail, viewGroup, false);
            holder = new ColumnDetailHolder(view);
            view.setTag(holder);
        } else {
            holder = (ColumnDetailHolder) view.getTag();
        }
            holder.mTvCreateTime.setText(StringTime.IntoTime(mList.get(i).getCreate_time()));
            holder.mTvTagName.setText("#  " + mList.get(i).getTag().get(0).getTag_name());
            holder.mTvTitle.setText(mList.get(i).getTitle());
            Glide.with(mContext).load(mList.get(i).getImage()).into(holder.mImage);
        return view;
    }

    private class ColumnDetailHolder {

        private final ImageView mImage;
        private final TextView mTvCreateTime;
        private final TextView mTvTitle;
        private final TextView mTvTagName;

        public ColumnDetailHolder(View view) {
            mImage = (ImageView) view.findViewById(R.id.item_iv_image_lv_column_detail);
            mTvCreateTime = (TextView) view.findViewById(R.id.item_tv_create_time_lv_column_detail);
            mTvTitle = (TextView) view.findViewById(R.id.item_tv_title_lv_column_detail);
            mTvTagName = (TextView) view.findViewById(R.id.item_tv_tag_name_lv_column_detail);
        }
    }
}
