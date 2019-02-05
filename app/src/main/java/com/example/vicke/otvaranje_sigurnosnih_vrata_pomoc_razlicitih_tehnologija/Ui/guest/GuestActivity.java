package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui.guest;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.core.Module;
import com.example.core.api.service.ApiInterface;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.R;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui.Fragments.AddGuestFragment;

public class GuestActivity extends AppCompatActivity implements Module, AddGuestFragment.OnFragmentInteractionListener {

    public static Intent getIntent(Context callingActivity) {
        return new Intent(callingActivity, GuestActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.add_guest);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, AddGuestFragment.newInstance())
                .commit();
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

    @Override
    public String getModuleName() {
        return getResources().getString(R.string.add_guest);
    }

    @Override
    public Drawable getModuleIcon() {
        return getResources().getDrawable(R.drawable.nav_add_guest, null);
    }

    @Override
    public void ApiModule(ApiInterface apiInterface) {

    }
}
