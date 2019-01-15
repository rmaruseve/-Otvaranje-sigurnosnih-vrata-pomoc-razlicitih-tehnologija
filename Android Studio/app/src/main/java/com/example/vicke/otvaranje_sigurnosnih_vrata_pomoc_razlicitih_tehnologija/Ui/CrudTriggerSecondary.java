package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.R;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.service.ApiInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrudTriggerSecondary extends AppCompatActivity {


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ApiInterface apiInterface = retrofit.create(ApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trigger_crud_screen);




        Bundle extras = getIntent().getExtras();
        if (extras != null) {

        }
        if (extras.getSerializable("listDataItem") != null)
        {

        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You cannot go back", Toast.LENGTH_SHORT).show();
    }
}
