package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.R;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.Role;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.User;

import java.util.ArrayList;
import java.util.List;

public class CrudUser extends Fragment {

    //TODO: declare all xml objects
    private boolean isNew = false;
    private List<Role> listOfRoles;
    Role role;


    public static CrudUser newInstance() {
        return new CrudUser();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.crud_user, parent, false);
    }

    Spinner dropdown;
    TextView firstName;
    TextView lastName;
    TextView email;
    TextView password;
    TextView phoneNumber;

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        firstName = getActivity().findViewById(R.id.input_first_name);
        lastName = getActivity().findViewById(R.id.input_last_name);
        email = getActivity().findViewById(R.id.input_email);
        password = getActivity().findViewById(R.id.input_password);
        phoneNumber = getActivity().findViewById(R.id.input_phone_number);

        Bundle bundle = getArguments();
        listOfRoles = (List<Role>)bundle.getSerializable("listOfRoles");


        /*dropdown = getActivity().findViewById(R.id.roleDropdown);
        ArrayAdapter<Role> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, listOfRoles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);*/

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                role = null;
                role = (Role) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (bundle.getSerializable("selectedUser") != null)
        {
            User passedUser = (User) bundle.getSerializable("selectedUser");
            firstName.setText(passedUser.getFirstName());
            lastName.setText(passedUser.getLastName());
            email.setText(passedUser.getEmail());
            dropdown.setSelection(passedUser.getRole());

            Button buttonModify = getActivity().findViewById(R.id.btnSave);
            buttonModify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        else
        {
            //its a new user, everything is empty
            isNew = true;
            Button buttonAddNew = getActivity().findViewById(R.id.btnSave);
            buttonAddNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
