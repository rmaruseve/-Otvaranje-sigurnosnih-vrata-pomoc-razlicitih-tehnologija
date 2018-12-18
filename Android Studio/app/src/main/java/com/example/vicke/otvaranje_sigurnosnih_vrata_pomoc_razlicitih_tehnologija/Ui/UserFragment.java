package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.R;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.AllUser;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.Role;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {




    private OnFragmentInteractionListener mListener;

    public UserFragment() {
    }

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_user, container, false);

        ArrayList<AllUser> listOfUsers = new ArrayList<>();
        ArrayList<Role> listOfRoles = new ArrayList<>();
        final ListView listView = v.findViewById(R.id.adminUserList);

        Bundle bundle = this.getArguments();

        if(bundle != null){
            listOfUsers = (ArrayList)bundle.getSerializable("listOfUsers");
            listOfRoles = (ArrayList)bundle.getSerializable("listOfRoles");
        }

        ArrayAdapter<AllUser> arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.admin_user_list_item, R.id.adminUserListItem, listOfUsers);
        listView.setAdapter(arrayAdapter);


        final ArrayList<AllUser> finalListOfUsers = listOfUsers;
        final ArrayList<Role> finalListOfRoles = listOfRoles;

        Button button = v.findViewById(R.id.addNewUser);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), CrudUser.class);
                i.putExtra("userRole", finalListOfRoles);
                startActivity(i);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AllUser clickedUser = finalListOfUsers.get(position);

                Intent i = new Intent(getContext(), CrudUser.class);
                i.putExtra("userRole", finalListOfRoles);
                i.putExtra("currentUser", clickedUser);
                startActivity(i);
            }
        });

            return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
