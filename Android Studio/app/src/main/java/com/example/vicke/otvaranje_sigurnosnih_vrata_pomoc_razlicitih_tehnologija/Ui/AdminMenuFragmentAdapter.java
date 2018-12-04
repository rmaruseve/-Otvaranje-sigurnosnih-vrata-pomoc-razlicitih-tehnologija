package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class AdminMenuFragmentAdapter extends FragmentStatePagerAdapter {

    int numberOfTabs;

    public AdminMenuFragmentAdapter(FragmentManager fm, int numberOfTabs)
    {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i)
        {
            case 0:
                LogFragment logFragment = new LogFragment();
                return logFragment;
            case 1:
                UserFragment userFragment = new UserFragment();
                return userFragment;
            case 2:
                ProfileFragment profileFragment = new ProfileFragment();
                return profileFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
