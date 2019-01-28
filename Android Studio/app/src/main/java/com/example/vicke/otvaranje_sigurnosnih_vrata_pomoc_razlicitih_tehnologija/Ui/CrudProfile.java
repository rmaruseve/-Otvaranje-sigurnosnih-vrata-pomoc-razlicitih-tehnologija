package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.R;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.CrudTriggerListItemData;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.CrudUserDataClass;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.Profile;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.TriggerList;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.TriggerType;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.User;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.UserAccess;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.facilityObject;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.service.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrudProfile extends AppCompatActivity {

    User user;
    CrudUserDataClass crudUser;

    ArrayAdapter<CrudTriggerListItemData> profileListAdapter;
    ListView listViewProfile;

    Button addNew;
    Button next;

    ArrayList<facilityObject> listOfObjects = new ArrayList<>();
    ArrayList<UserAccess> listOfAccess = new ArrayList<>();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ApiInterface apiInterface = retrofit.create(ApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_profile);

        addNew = findViewById(R.id.addNewProfile);
        next = findViewById(R.id.profileFinish);

        listViewProfile = findViewById(R.id.triggerListView);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = (User) extras.getSerializable("user");
            crudUser = (CrudUserDataClass) extras.getSerializable("editUser");
            listOfObjects = (ArrayList<facilityObject>) extras.getSerializable("listOfObjects");
        }

        //TODO: isto ko i crud trigger, treba dodati i secondary screen, vecinu kopirati, to nakon testiranja

        //ovo ide u CrudProfileSecondary
        /*Call<ArrayList<Profile>> call = apiInterface.getProfiles(user.getToken());
        call.enqueue(new Callback<ArrayList<Profile>>() {
            @Override
            public void onResponse(Call<ArrayList<Profile>> call, Response<ArrayList<Profile>> response) {
                listOfProfiles = response.body();
            }

            @Override
            public void onFailure(Call<ArrayList<Profile>> call, Throwable t) {

            }
        });*/

        Call<ArrayList<UserAccess>> call = apiInterface.getAccess(user.getToken());
        call.enqueue(new Callback<ArrayList<UserAccess>>() {
            @Override
            public void onResponse(Call<ArrayList<UserAccess>> call, Response<ArrayList<UserAccess>> response) {
                listOfAccess = response.body();

                //triggerListAdapter = new ArrayAdapter<>(getBaseContext(), R.layout.trigger_list_item, R.id.triggerListItem, triggerDatForList);
                //listViewTrigger.setAdapter(triggerListAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<UserAccess>> call, Throwable t) {

            }
        });

        listViewProfile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getBaseContext(), CrudTriggerSecondary.class); //promjeniti
                i.putExtra("user", user);
                i.putExtra("listDataItem", listOfAccess.get(position));
                i.putExtra("editUser", crudUser);
                startActivityForResult(i, 2);
            }
        });

        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listOfAccess.add(new UserAccess());
                profileListAdapter.notifyDataSetChanged();
                Intent i = new Intent(getBaseContext(), CrudTriggerSecondary.class); //promjeniti
                i.putExtra("user", user);
                i.putExtra("editUser", crudUser);
                startActivityForResult(i, 2);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            UserAccess result = (UserAccess) data.getSerializableExtra("result");
            listOfAccess.get(listOfAccess.size() - 1).setDateFrom(result.getDateFrom());
            listOfAccess.get(listOfAccess.size() - 1).setDateTo(result.getDateTo());
            listOfAccess.get(listOfAccess.size() - 1).setObjectId(result.getObjectId());
            listOfAccess.get(listOfAccess.size() - 1).setProfileId(result.getProfileId());
            listOfAccess.get(listOfAccess.size() - 1).setUserId(result.getUserId());
            listViewProfile.setAdapter(null);
            listViewProfile.setAdapter(profileListAdapter);
        }
    }
}