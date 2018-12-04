package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.R;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.User;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String token;
    User currentUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            token = extras.getString("token");
            currentUser = (User) extras.getSerializable("currentUser");
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*if (!currentUser.isAdmin)
        {
           //TODO: hide admin menu
        }*/


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction t = fragmentManager.beginTransaction();
        ObjectListShow objectListShow = new ObjectListShow();
        Bundle bundle = new Bundle();
        bundle.putString("token", token);
        objectListShow.setArguments(bundle);
        t.add(R.id.objectListShowFrame, objectListShow);
        t.commit();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = getResources().getString(R.string.phoneNumber);
                dialNumber(String.valueOf(phoneNumber));
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //TODO: open setting
            return true;
        }
        if (id == R.id.action_refresh) {
            //TODO: refresh object list
            finish();
            startActivity(getIntent());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home)
        {

        }
        else if (id == R.id.nav_profile)
        {

        }
        else if (id == R.id.nav_map)
        {

        }
        else if (id == R.id.nav_admin_options)
        {
            Intent i = new Intent(MainActivity.this, AdminMenu.class);
            i.putExtra("token",token);
            i.putExtra("currentUser",currentUser);
            startActivity(i);
        }

        //TODO: add logout

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void dialNumber(String phoneNumber) {

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("tel:"+phoneNumber));
        this.startActivity(intent);
    }

}
