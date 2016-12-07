package lanou.dllo.yohonow.magazine.wallpaper;

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

public class WPFragmentAdapter extends BaseAdapter {
    private Context mContext;
    private WallPaperBean mWallPaperBean;
    private List<WallPaperBean.DataBean.WallpaperListBean> mPaperBeanList;

    public void setWallPaperBean(WallPaperBean wallPaperBean) {
        mWallPaperBean = wallPaperBean;
    }

    public WPFragmentAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mWallPaperBean.getData() != null && mWallPaperBean.getData().getWallpaperList().size() > 0 ? mWallPaperBean.getData().getWallpaperList().size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return mWallPaperBean.getData().getWallpaperList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        WallpaperHolder wallpaperHolder = null;
        mPaperBeanList = mWallPaperBean.getData().getWallpaperList();
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_lv_wallpaper_fragment, viewGroup, false);
            wallpaperHolder = new WallpaperHolder(view);
            view.setTag(wallpaperHolder);
        } else {
            wallpaperHolder = (WallpaperHolder) view.getTag();
        }
        wallpaperHolder.mTvJournal.setText(mPaperBeanList.get(i).getJournal());
        wallpaperHolder.mTvMonth.setText(mPaperBeanList.get(i).getMonth());
        Picasso.with(mContext).load(mPaperBeanList.get(i).getImages().get(0).getThumbImage()).into(wallpaperHolder.mIvThumbImageOne);
        Picasso.with(mContext).load(mPaperBeanList.get(i).getImages().get(1).getThumbImage()).into(wallpaperHolder.mIvThumbImageThree);
        Picasso.with(mContext).load(mPaperBeanList.get(i).getImages().get(2).getThumbImage()).into(wallpaperHolder.mIvThumbImageTwo);
        if (mPaperBeanList.get(i).getImages().size() == 4) {
            Picasso.with(mContext).load(mPaperBeanList.get(i).getImages().get(3).getThumbImage()).into(wallpaperHolder.mIvThumbImageFour);
        }
        return view;
    }

    private class WallpaperHolder {

        private final TextView mTvJournal;
        private final TextView mTvMonth;
        private final ImageView mIvThumbImageOne;
        private final ImageView mIvThumbImageTwo;
        private final ImageView mIvThumbImageThree;
        private final ImageView mIvThumbImageFour;

        public WallpaperHolder(View view) {
            mTvJournal = (TextView) view.findViewById(R.id.item_tv_journal_lv_wallpaper_fragment);
            mTvMonth = (TextView) view.findViewById(R.id.item_tv_month_lv_wallpaper_fragment);
            mIvThumbImageOne = (ImageView) view.findViewById(R.id.item_iv_one_thumbImage_lv_wallpaper_fragment);
            mIvThumbImageTwo = (ImageView) view.findViewById(R.id.item_iv_two_thumbImage_lv_wallpaper_fragment);
            mIvThumbImageThree = (ImageView) view.findViewById(R.id.item_iv_three_thumbImage_lv_wallpaper_fragment);
            mIvThumbImageFour = (ImageView) view.findViewById(R.id.item_iv_four_thumbImage_lv_wallpaper_fragment);

        }
    }
}
