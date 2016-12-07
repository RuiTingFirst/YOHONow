package lanou.dllo.yohonow.home.homeseek;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.base.YoHoHolder;
import lanou.dllo.yohonow.tools.timetools.StringTime;

/**
 * Created by dllo on 16/12/2.
 */

public class SeekFragmentAdapter extends BaseAdapter {
    private Context mContext;
    private SeekFragmentBean mSeekBean;
    private List<SeekFragmentBean.DataBean.ContentBean> mList;

    public void setSeekBean(SeekFragmentBean seekBean) {
        mSeekBean = seekBean;
        notifyDataSetChanged();
    }

    public SeekFragmentAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mSeekBean != null && mSeekBean.getData().getContent().size() > 0 ? mSeekBean.getData().getContent().size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        mList = mSeekBean.getData().getContent();
        YoHoHolder hoHolder = YoHoHolder.listViewHolder(view, viewGroup, R.layout.item_lv_seek_fragment);
        hoHolder.setText(R.id.item_tv_title_seek_fragment, mList.get(i).getTitle());
        hoHolder.setText(R.id.item_tv_tag_name_seek_fragment, "# " + mList.get(i).getTag().get(0).getTag_name());
        hoHolder.setText(R.id.item_tv_create_time_seek_fragment, StringTime.IntoTime(String.valueOf(mList.get(i).getCreate_time())));
        hoHolder.setImage(R.id.item_iv_image_seek_fragment, mList.get(i).getImage());
        if (mList.get(i).getImgNum() > 0) {
            hoHolder.getView(R.id.item_ll_lv_seek_fragment).setVisibility(View.VISIBLE);
            hoHolder.setText(R.id.item_image_num_tv_lv_seek_fragment, String.valueOf(mList.get(i).getImgNum()));
        } else {
            hoHolder.getView(R.id.item_ll_lv_seek_fragment).setVisibility(View.INVISIBLE);
        }
        return hoHolder.getItemView();
    }
}
