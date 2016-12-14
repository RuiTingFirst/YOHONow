package lanou.dllo.yohonow.mymagazine;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import lanou.dllo.yohonow.R;
import lanou.dllo.yohonow.greendao.DBTools;
import lanou.dllo.yohonow.greendao.DetailDBTools;
import lanou.dllo.yohonow.greendao.Magazine;
import lanou.dllo.yohonow.tools.stringtools.StringTools;

/**
 * Created by dllo on 16/12/9.
 */

public class MZGvAdapter extends BaseAdapter {
    private Context mContext;
    List<Magazine> mList;
    private int a = 0;
    private boolean flag = false;
    private MZGvHolder mMzGvHolder;

    public MZGvAdapter(Context context) {
        mContext = context;
    }

    public void setList(List<Magazine> list) {
        mList = list;
    }

    @Override
    public int getCount() {
        return mList != null && mList.size() > 0 ? mList.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return mList != null && mList.size() > 0 ? mList.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        mMzGvHolder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_gv_my_magazine, viewGroup, false);
            mMzGvHolder = new MZGvHolder(view);
            view.setTag(mMzGvHolder);
        } else {
            mMzGvHolder = (MZGvHolder) view.getTag();
        }
        Glide.with(mContext).load(mList.get(i).getImageUrl()).into(mMzGvHolder.mImage);
        mMzGvHolder.mTvJournal.setText(mList.get(i).getJournal());
        /**
         * 把选中状态存到集合
         */
        mMzGvHolder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox checkBox = (CheckBox) view;
                mList.get(i).setIsChecked(checkBox.isChecked());
                if (checkBox.isChecked()) {
                    a += 1;
                } else {
                    if (a > 0) {
                        a -= 1;
                    }
                }
                Intent intent = new Intent(StringTools.MY_MAGAZINE_BROAD);
                intent.putExtra("a", a);
                mContext.sendBroadcast(intent);
            }
        });
        /**
         * 显示选中状态
         */
        if (flag) {
            a = 0;
            mMzGvHolder.mCheckBox.setVisibility(View.VISIBLE);
            showAnimation();
        } else {
            mMzGvHolder.mCheckBox.setVisibility(View.GONE);
            showAnimation();
        }
        return view;
    }

    /**
     * 点击按钮删除被勾选的item
     */
    public void deleteCheckedItem() {
        for (int i = mList.size() - 1; i >= 0; i--) {
            Magazine bean = mList.get(i);
            if (bean.getIsChecked()) {
                DBTools.getInstance().deleteMagazine(bean);
                mList.remove(i);
                flag = false;
            }
        }
    }

    public void showTagSelected(boolean flag) {
        this.flag = flag;
        notifyDataSetChanged();
    }

    private class MZGvHolder {

        private final ImageView mImage;
        private final TextView mTvJournal;
        private final CheckBox mCheckBox;

        public MZGvHolder(View view) {
            mImage = (ImageView) view.findViewById(R.id.item_iv_gv_my_magazine);
            mTvJournal = (TextView) view.findViewById(R.id.item_tv_gv_my_magazine);
            mCheckBox = (CheckBox) view.findViewById(R.id.item_checkbox_gv_my_magazine);
        }
    }

    /**
     * 抖动动画
     */
    public void showAnimation() {
        RotateAnimation ra = new RotateAnimation(-1, 1, Animation.RELATIVE_TO_SELF,
                0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
        ra.setDuration(60);
        // -1 是让动画一直抖动
        ra.setRepeatCount(-1);
        ra.setRepeatMode(Animation.REVERSE);
        if (flag) {
            mMzGvHolder.mImage.startAnimation(ra);
        } else {
            // 0 是让动画停止
            ra.setRepeatCount(0);
            mMzGvHolder.mImage.startAnimation(ra);
        }
    }
}
