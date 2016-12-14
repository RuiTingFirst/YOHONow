package lanou.dllo.yohonow.tools.animview;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

import lanou.dllo.yohonow.R;

/**
 * Created by dllo on 16/12/8.
 */

public class LoadMoreFooterView extends ImageView implements SwipeLoadMoreTrigger, SwipeTrigger {
    public LoadMoreFooterView(Context context) {
        super(context);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onLoadMore() {
        setImageResource(R.drawable.anim);
        AnimationDrawable animationDrawable = (AnimationDrawable) getDrawable();
        animationDrawable.start();
    }

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
        } else {
            setImageResource(R.mipmap.yoho_sdk_view_dialog_loadingdialog_yohonow_00001);
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
