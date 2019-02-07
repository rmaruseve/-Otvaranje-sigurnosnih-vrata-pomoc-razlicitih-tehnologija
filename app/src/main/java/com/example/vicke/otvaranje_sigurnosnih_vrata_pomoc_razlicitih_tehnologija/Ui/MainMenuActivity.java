package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.addguest.AddGuestFragment;
import com.example.core.Module;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.R;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui.manager.ModuleManager;

import java.util.LinkedList;

public class MainMenuActivity extends AppCompatActivity implements AddGuestFragment.OnFragmentInteractionListener {

    private ModuleManager mModuleManager;
    private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mModuleManager = ModuleManager.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, AddGuestFragment.newInstance(null))
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.module_list_menu, menu);
        mMenu = menu;

        setupModulesList();

        return true;
    }

    private void setupModulesList() {
        LinkedList<Module> listOfModules = mModuleManager.getListOfModules();

        for (Module module : listOfModules) {
            mMenu.add(module.getModuleName(this));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return false;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //NOP
    }
}
