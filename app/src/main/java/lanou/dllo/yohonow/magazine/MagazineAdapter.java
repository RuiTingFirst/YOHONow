package lanou.dllo.yohonow.magazine;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/26.
 */

public class MagazineAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> mFragments;
    String title[] = {"杂志", "壁纸"};
    public void setFragments(ArrayList<Fragment> fragments) {
        mFragments = fragments;
    }

    public MagazineAdapter(FragmentManager fm) {
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

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
