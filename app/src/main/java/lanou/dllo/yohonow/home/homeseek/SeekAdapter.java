package lanou.dllo.yohonow.home.homeseek;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/12/2.
 */
//复用时候 更改 适配器 (将 fragment 完全 销毁)
public class SeekAdapter extends FragmentStatePagerAdapter {
    // 静态方法需要用静态类, 不过静态类不容易回收, 所以尽量不要用静态类
    private SeekTitleBean mSeekTitleBean;

    public void setSeekTitleBean(SeekTitleBean seekTitleBean) {
        mSeekTitleBean = seekTitleBean;
        notifyDataSetChanged();
    }

    public SeekAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return mSeekTitleBean != null && mSeekTitleBean.getData().get(0).getSubNav().size() > 0 ? mSeekTitleBean.getData().get(0).getSubNav().size(): 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mSeekTitleBean.getData().get(0).getSubNav().get(position).getChannel_name_cn();
    }

    //根据 position的 不同 加载数据
    @Override
    public Fragment getItem(int position) {
        String s = mSeekTitleBean.getData().get(0).getSubNav().get(position).getId();
        return SeekFragment.newInstance(s);
    }
}
