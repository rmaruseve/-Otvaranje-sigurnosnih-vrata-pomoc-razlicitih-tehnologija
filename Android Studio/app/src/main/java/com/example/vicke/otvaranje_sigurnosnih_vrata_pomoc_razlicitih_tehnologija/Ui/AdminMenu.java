package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.R;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.Role;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.User;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.service.ApiInterface;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminMenu extends AppCompatActivity implements LogFragment.OnFragmentInteractionListener, UserFragment.OnFragmentInteractionListener, ProfileFragment.OnFragmentInteractionListener {

    String token;
    User currentUser = new User();

    List<User> listOfUsers;
    List<Role> listOfRoles;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ApiInterface apiInterface = retrofit.create(ApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            token = extras.getString("token");
            currentUser = (User) extras.getSerializable("currentUser");
        }

        Call<List<User>> call = apiInterface.getUsers(token);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                listOfUsers = response.body();
                TabLayout tabLayout = findViewById(R.id.adminMenuTabLayout);
                tabLayout.addTab(tabLayout.newTab().setText("Log"));
                tabLayout.addTab(tabLayout.newTab().setText("Users"));
                tabLayout.addTab(tabLayout.newTab().setText("Profiles"));
                tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

                final ViewPager viewPager = findViewById(R.id.adminMenuViewPager);
                final AdminMenuFragmentAdapter adminMenuFragmentAdapter = new AdminMenuFragmentAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), listOfUsers, listOfRoles);

                viewPager.setAdapter(adminMenuFragmentAdapter);
                viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

                tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        viewPager.setCurrentItem(tab.getPosition());
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
