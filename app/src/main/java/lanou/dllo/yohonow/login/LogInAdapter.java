package lanou.dllo.yohonow.login;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 16/12/6.
 */

public class LogInAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;

    public void setFragments(List<Fragment> fragments) {
        mFragments = fragments;
    }

    public LogInAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments != null && mFragments.size() > 0 ? mFragments.size() : 0;
    }
}
