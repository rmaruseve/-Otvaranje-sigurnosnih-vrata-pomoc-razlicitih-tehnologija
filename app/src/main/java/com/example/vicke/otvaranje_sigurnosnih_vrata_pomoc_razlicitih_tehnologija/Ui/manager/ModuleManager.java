package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui.manager;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.addguest.AddGuestFragment;
import com.example.core.Module;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.R;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui.Fragments.UserFragment;

import java.util.LinkedList;

public class ModuleManager {

    public static final int MENU_ITEM_ADD_GUEST = 1;
    public static final int MENU_ITEM_MAIN_FRAGMENT = 2;
    private static ModuleManager sInstance;

    private Menu mMenu;

    private LinkedList<Module> listOfModules = new LinkedList<>();

    private ModuleManager() {
        setupListOfModules();
    }

    public static ModuleManager getInstance() {

        if (sInstance == null) {
            sInstance = new ModuleManager();
            return sInstance;
        }

        return sInstance;
    }

    private AppCompatActivity mActivity;

    public void setDrawerDependencies(
            AppCompatActivity activity,
            Menu menu) {
        this.mActivity = activity;
        this.mMenu = menu;

        setupDrawer();
    }

    public void startMainModule() {
        Module mainModule = listOfModules != null ? listOfModules.get(0) : null;
        if (mainModule != null)
            startModule(mainModule);
    }

    private void startModule(Module module) {
        FragmentManager mFragmentManager = mActivity.getSupportFragmentManager();
        mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        mFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, module.getModuleFragment(mActivity))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
        //DataManager.getInstance().sendData(module);
    }

    public boolean selectNavigationItem(MenuItem menuItem) {
        if (!menuItem.isChecked()) {

            for (int i = 0; i < mMenu.size(); i++) {
                mMenu.findItem(i).setChecked(false);
            }

            menuItem.setChecked(true);

            Module selectedItem = listOfModules.get(menuItem.getItemId());
            startModule(selectedItem);
            return true;
        }
        return false;
    }

    private void setupDrawer() {
        for (int i = 0; i < listOfModules.size(); i++) {
            Module item = listOfModules.get(i);
            mMenu.add(Menu.NONE, i, i + 1, item.getModuleName(mActivity))
                    .setCheckable(true)
                    .setIcon(item.getModuleIcon(mActivity));
        }
    }

    private void setupListOfModules() {

        //TODO add real modules
        listOfModules.add(AddGuestFragment.newInstance(null));
        listOfModules.add(UserFragment.newInstance());
    }
}
