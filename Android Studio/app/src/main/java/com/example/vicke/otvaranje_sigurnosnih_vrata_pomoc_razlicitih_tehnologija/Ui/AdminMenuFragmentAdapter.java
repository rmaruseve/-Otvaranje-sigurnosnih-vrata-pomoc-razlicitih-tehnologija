package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.AllUser;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.Role;

import java.io.Serializable;
import java.util.List;

public class AdminMenuFragmentAdapter extends FragmentStatePagerAdapter {

    int numberOfTabs;
    private List<AllUser> userList;
    private List<Role> roleList;

    public AdminMenuFragmentAdapter(FragmentManager fm, int numberOfTabs, List<AllUser> userList, List<Role> roleList)
    {
        super(fm);
        this.numberOfTabs = numberOfTabs;
        this.userList = userList;
        this.roleList = roleList;
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
                Bundle bundle = new Bundle();
                bundle.putSerializable("listOfUsers", (Serializable) userList);
                bundle.putSerializable("listOfRoles", (Serializable) roleList);
                userFragment.setArguments(bundle);

                return userFragment;
            case 2:
                AddGuestFragment addGuestFragment = new AddGuestFragment();
                return addGuestFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
