package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.R;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui.Fragments.AddGuestFragment;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui.Fragments.UserFragment;

public class MainMenuActivity extends AppCompatActivity implements UserFragment.OnFragmentInteractionListener {

    //private ModuleManager mModuleManager;
    //private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //mModuleManager = ModuleManager.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, AddGuestFragment.newInstance())
                .commit();
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.module_list_menu, menu);
//        mMenu = menu;
//
//        mModuleManager.setDrawerDependencies(this, menu, R.id.fragment_container);
//        mModuleManager.startMainModule();
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        if (item.getItemId() == android.R.id.home) {
//            finish();
//            return true;
//        } else {
//            if (mModuleManager.selectNavigationItem(item)) return true;
//        }
//
//        return false;
//    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //NOP
    }
}
