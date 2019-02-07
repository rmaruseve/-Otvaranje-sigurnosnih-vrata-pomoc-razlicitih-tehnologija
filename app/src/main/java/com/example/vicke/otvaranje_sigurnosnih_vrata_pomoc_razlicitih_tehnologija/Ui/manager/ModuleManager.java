package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui.manager;

import com.example.addguest.AddGuestFragment;
import com.example.core.Module;

import java.util.LinkedList;

public class ModuleManager {

    private static ModuleManager sInstance;

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

    private void setupListOfModules() {

        listOfModules.add(AddGuestFragment.newInstance(null));
        listOfModules.add(AddGuestFragment.newInstance(null));
        listOfModules.add(AddGuestFragment.newInstance(null));
    }

    public LinkedList<Module> getListOfModules() {
        return listOfModules;
    }

}
