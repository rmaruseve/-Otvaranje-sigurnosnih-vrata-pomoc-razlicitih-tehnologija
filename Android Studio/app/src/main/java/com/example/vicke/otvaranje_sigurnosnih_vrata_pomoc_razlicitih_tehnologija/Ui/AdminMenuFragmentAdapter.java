package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.Role;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdminMenuFragmentAdapter extends FragmentStatePagerAdapter {

    int numberOfTabs;
    private List<User> userList;
    private List<Role> roleList;

    public AdminMenuFragmentAdapter(FragmentManager fm, int numberOfTabs, List<User> userList, List<Role> roleList)
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
                /*ArrayList<User> alListOfUsers = new ArrayList<>(userList.size());
                alListOfUsers.addAll(userList);

                ArrayList<Role> alListOfRoles = new ArrayList<>(roleList.size());
                alListOfRoles.addAll(roleList);*/


                UserFragment userFragment = new UserFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("listOfUsers", (Serializable) userList);
                bundle.putSerializable("listOfRoles", (Serializable) roleList);
                userFragment.setArguments(bundle);

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
