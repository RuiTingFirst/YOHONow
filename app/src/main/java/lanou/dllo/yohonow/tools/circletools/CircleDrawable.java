package lanou.dllo.yohonow.tools.circletools;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * Created by dllo on 16/11/29.
 */

/**
 * 显示圆形图片的Drawable
 */
public class CircleDrawable extends Drawable{

    private Bitmap mBitmap;// 原始图片
    private Paint mPaint;// 画笔
    private int r;// 半径

    public CircleDrawable(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);// 抗锯齿

        // 图片重复时使用的模式
        // 印章图片作为画笔的花纹
        BitmapShader shader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(shader);

        // 计算出半径   取最小值
        r = Math.min(mBitmap.getHeight() / 2 , mBitmap.getWidth() / 2);
    }

    @Override
    public void draw(Canvas canvas) {

        // 画圆
        canvas.drawCircle(mBitmap.getWidth() / 2, mBitmap.getHeight() / 2, r, mPaint);
    }

    @Override
    public void setAlpha(int i) {
        mPaint.setAlpha(i);

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);

    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }

    // 负责告诉Drawable它的宽和高是多少   自动压缩
    @Override
    public int getIntrinsicWidth() {
        return 2 * r;
    }

    @Override
    public int getIntrinsicHeight() {
        return 2 * r;
    }
}
