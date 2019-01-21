package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.R;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui.Adapters.AdminMenuFragmentAdapter;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.AllUser;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.EventLogData;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.Role;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.User;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.service.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminMenu extends AppCompatActivity implements LogFragment.OnFragmentInteractionListener, UserFragment.OnFragmentInteractionListener, AddGuestFragment.OnFragmentInteractionListener {

    User currentUser = new User();

    List<AllUser> listOfUsers;
    List<Role> listOfRoles;
    List<EventLogData> eventLogData;

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
            currentUser = (User) extras.getSerializable("currentUser");
        }

        Call<List<AllUser>> call = apiInterface.getUsers(currentUser.getToken());
        call.enqueue(new Callback<List<AllUser>>() {
            @Override
            public void onResponse(Call<List<AllUser>> call, Response<List<AllUser>> response) {
                listOfUsers = response.body();

                Call<List<Role>> callRoles = apiInterface.getRoles(currentUser.getToken());
                callRoles.enqueue(new Callback<List<Role>>() {
                    @Override
                    public void onResponse(Call<List<Role>> call, Response<List<Role>> response) {
                        listOfRoles = response.body();

                        Call<List<EventLogData>> callEventLog = apiInterface.getEventLogData(currentUser.getToken());
                        callEventLog.enqueue(new Callback<List<EventLogData>>() {
                            @Override
                            public void onResponse(Call<List<EventLogData>> call, Response<List<EventLogData>> response) {
                                eventLogData = response.body();

                                TabLayout tabLayout = findViewById(R.id.adminMenuTabLayout);
                                tabLayout.addTab(tabLayout.newTab().setText("Log"));
                                tabLayout.addTab(tabLayout.newTab().setText("Users"));
                                tabLayout.addTab(tabLayout.newTab().setText("Add guest"));
                                tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

                                final ViewPager viewPager = findViewById(R.id.adminMenuViewPager);
                                final AdminMenuFragmentAdapter adminMenuFragmentAdapter = new AdminMenuFragmentAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), listOfUsers, listOfRoles, currentUser, eventLogData);

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
                            public void onFailure(Call<List<EventLogData>> call, Throwable t) {

                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<List<Role>> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onFailure(Call<List<AllUser>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
