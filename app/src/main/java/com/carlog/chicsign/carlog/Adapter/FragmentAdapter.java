package com.carlog.chicsign.carlog.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by chicsign on 2018-06-20.
 */

final class FragmentAdapter extends FragmentStatePagerAdapter {
    Fragment[] mFragments;

    // ------------------------------------------------------------
    FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        if (mFragments == null) {
            return 0;
        }
        return mFragments.length;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments[i];
    }
}