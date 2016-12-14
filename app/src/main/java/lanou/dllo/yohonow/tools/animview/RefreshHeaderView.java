package lanou.dllo.yohonow.tools.animview;

/**
 * Created by dllo on 16/12/8.
 */

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

import lanou.dllo.yohonow.R;

/**
 * 刷新动画
 */
public class RefreshHeaderView extends ImageView implements SwipeRefreshTrigger, SwipeTrigger{

    public RefreshHeaderView(Context context) {
        super(context);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 刷新过程执行
     */
    @Override
    public void onRefresh() {
        setImageResource(R.drawable.anim);
        AnimationDrawable animationDrawable = (AnimationDrawable) getDrawable();
        animationDrawable.start();
    }

    /**
     * 刷新前执行
     */
    @Override
    public void onPrepare() {
        setImageResource(R.drawable.anim);
    }

    @Override
    public void onMove(int i, boolean b, boolean b1) {
        if (!b) {
            if (i >= getHeight()) {
                setImageResource(R.mipmap.yoho_sdk_view_dialog_loadingdialog_yohonow_00001);
            }
        }else {
            setImageResource(R.mipmap.yoho_sdk_view_dialog_loadingdialog_yohonow_00022);
        }
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onReset() {

    }
}
