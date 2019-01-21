package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.R;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui.Adapters.TriggerListAdapter;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.CrudUserDataClass;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.TriggerList;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.TriggerType;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.User;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.service.ApiInterface;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrudTrigger extends AppCompatActivity {

    User user;
    CrudUserDataClass crudUser;

    Button addNew;
    Button next;

    ListView listView;
    TriggerListAdapter arrayAdapter;

    private ArrayList<TriggerList> listData;
    private ArrayList<TriggerType> listofTriggerNames;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ApiInterface apiInterface = retrofit.create(ApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_trigger);

        listView = findViewById(R.id.triggerListView);

        addNew = findViewById(R.id.addNewTrigger);
        next = findViewById(R.id.triggerNext);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = (User) extras.getSerializable("user");
            crudUser = (CrudUserDataClass) extras.getSerializable("editUser");
        }

        //prvi retrofit za sve triggere
        Call<ArrayList<TriggerType>> call = apiInterface.getTriggerTypes(user.getToken());
        call.enqueue(new Callback<ArrayList<TriggerType>>() {
            @Override
            public void onResponse(Call<ArrayList<TriggerType>> call, Response<ArrayList<TriggerType>> response) {
                listofTriggerNames = response.body();

                //drugi retrofit poziv za listu trigger typeova(listData)
                Call<ArrayList<TriggerList>> call2 = apiInterface.getTriggerList(user.getToken());
                call2.enqueue(new Callback<ArrayList<TriggerList>>() {
                    @Override
                    public void onResponse(Call<ArrayList<TriggerList>> call, Response<ArrayList<TriggerList>> response) {
                        listData = response.body();
                        arrayAdapter = new TriggerListAdapter(CrudTrigger.this, listofTriggerNames, listData);
                        listView.setAdapter(arrayAdapter);
                    }
                    @Override
                        public void onFailure(Call<ArrayList<TriggerList>> call, Throwable t) {

                        }
                    });
            }

            @Override
            public void onFailure(Call<ArrayList<TriggerType>> call, Throwable t) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getBaseContext(), CrudTriggerSecondary.class);
                i.putExtra("user", user);
                i.putExtra("listDataItem", (Serializable) listData.get(position));
                i.putExtra("listOfTriggerNames", listofTriggerNames);
                startActivity(i);
            }
        });

        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listData.add(new TriggerList());
                arrayAdapter.notifyDataSetChanged();
                Intent i = new Intent(getBaseContext(), CrudTriggerSecondary.class);
                i.putExtra("user", user);
                i.putExtra("listOfTriggerNames", listofTriggerNames);
                startActivity(i);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: idi na sljedeci ekran
                //za sad ide natrag na admin meni, kasnije dodati da ide na crudProfile
                Intent i = new Intent(CrudTrigger.this, AdminMenu.class);
                i.putExtra("currentUser",user);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                TriggerList result = (TriggerList) data.getSerializableExtra("result");
                listData.get(listData.size() - 1).setTriggerId(result.getTriggerId());
                listData.get(listData.size() - 1).setTriggerValue(result.getTriggerValue());
                listData.get(listData.size() - 1).setIsTriggerActive(result.getIsTriggerActive());
                arrayAdapter.notifyDataSetChanged();
            }
        }
    }
}
