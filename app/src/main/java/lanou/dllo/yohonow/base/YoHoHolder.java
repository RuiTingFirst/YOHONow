package lanou.dllo.yohonow.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by dllo on 16/12/3.
 */

public class YoHoHolder extends RecyclerView.ViewHolder{
    private View mView;
    // 存放视图的数组, 类似map, key 值固定int 类型
    private SparseArray<View> mViewSparseArray;
    private Context mContext;

    public YoHoHolder(View itemView, Context context) {
        super(itemView);
        mContext = context;
        mView = itemView;
        mViewSparseArray = new SparseArray<>();
    }

    /**
     * 绑定布局, 如果布局是一样, 则返回该布局, 如果布局不一样
     * 则把布局加到集合
     */
    public <T extends View> T getView(int layoutId){
        //通过layout作为key值取出视图
        View view = mViewSparseArray.get(layoutId);
        if (view == null){
            //如果没有视图的话 我们就将自己的视图放入集合之中
            view = mView.findViewById(layoutId);
            mViewSparseArray.put(layoutId, view);
        }
        return (T) view;
    }

    // 对外提供静态方法
    public static YoHoHolder createViewHolder(Context context, ViewGroup parent, int layoutId){
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        // context 获取当前的itemView
        YoHoHolder yoHoHolder = new YoHoHolder(itemView, context);
        return yoHoHolder;
    }

    // listView 的 viewHolder
    public static YoHoHolder listViewHolder(View itemView, ViewGroup viewGroup, int layoutId){
        YoHoHolder yoHoHolder = null;
        if (itemView == null){
            // viewGroup.getContext() 防止自定义的context 对象变成静态的
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false);
            // viewGroup.getContext() 获取当前的itemView
            yoHoHolder = new YoHoHolder(itemView, viewGroup.getContext());
            itemView.setTag(yoHoHolder);
        } else {
            yoHoHolder = (YoHoHolder) itemView.getTag();
        }
        return yoHoHolder;
    }

    //根据不同的视图 获取不同的组件 对组件进行某些设置的操作
    public YoHoHolder setText(int id, String str){
        TextView textView = getView(id);
        textView.setText(str);
        return this;
    }

    // Picasso 获取网络图片
    public YoHoHolder setImage(int id, String s){
        ImageView imageView = getView(id);
        if (s != null){
            Picasso.with(mContext).load(s).into(imageView);
        }
        return this;
    }

    // glide 画圆
    public YoHoHolder setGlideImage(int id, String s){
        ImageView imageView = getView(id);
        if (s != null){
            Glide.with(mContext).load(s).bitmapTransform(new CropCircleTransformation(mContext)).into(imageView);
        }
        return this;
    }

    public View getItemView(){
        return mView;
    }
}
