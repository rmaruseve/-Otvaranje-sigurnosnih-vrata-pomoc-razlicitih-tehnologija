package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.R;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.AllUser;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.Role;

import java.util.List;

public class CrudUser extends AppCompatActivity {

    private boolean isNew = false;
    private List<Role> listOfRoles;
    Role role;

    Spinner dropdown;
    TextView firstName;
    TextView lastName;
    TextView email;
    TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crud_user);

        firstName = findViewById(R.id.input_first_name);
        lastName = findViewById(R.id.input_last_name);
        email = findViewById(R.id.input_email);
        password = findViewById(R.id.input_password);


        Bundle bundle = getIntent().getExtras();
        listOfRoles = (List<Role>)bundle.getSerializable("userRole");


        dropdown = findViewById(R.id.roleDropdown);
        ArrayAdapter<Role> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listOfRoles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

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

        if (bundle.getSerializable("currentUser") != null)
        {
            AllUser passedUser = (AllUser) bundle.getSerializable("currentUser");
            firstName.setText(passedUser.getUsrName());
            lastName.setText(passedUser.getUsrSurname());
            email.setText(passedUser.getUsrEmail());
            dropdown.setSelection(passedUser.getUsrRolId()-1);

            Button buttonModify = findViewById(R.id.btnSave);
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
            Button buttonAddNew = findViewById(R.id.btnSave);
            buttonAddNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
