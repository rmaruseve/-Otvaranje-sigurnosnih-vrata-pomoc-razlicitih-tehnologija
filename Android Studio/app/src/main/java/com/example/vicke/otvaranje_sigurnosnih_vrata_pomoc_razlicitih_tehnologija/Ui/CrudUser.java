package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.R;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.User;

public class CrudUser extends Fragment {

    //TODO: declare all xml objects


    public static CrudUser newInstance() {
        return new CrudUser();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.crud_user, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if (!bundle.isEmpty())
        {
            User passedUser = (User) bundle.getSerializable("selectedUser");
            //TODO: set values to xml objects if bundle is not empty
        }
        else
        {
            //its a new user, everything is empty
        }


    }
}
