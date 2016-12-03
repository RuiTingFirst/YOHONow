package lanou.dllo.yohonow.home.homeseek;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/12/2.
 */

public class SeekAdapter extends FragmentPagerAdapter {
    private static ArrayList<Fragment> mFragments;
    String title[] = {"主页", "时尚", "球鞋", "生活", "运动", "美容"};

    public SeekAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(ArrayList<Fragment> fragments) {
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return mFragments != null && mFragments.size() > 0 ? mFragments.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
    public static Fragment getMessage(int pos){
        return mFragments.get(pos);
    }
}
