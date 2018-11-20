package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ObjectListShow extends Fragment {

    private ExpandableListView expandableListView;
    private GetObjects objectsClass = new GetObjects();

    public static ObjectListShow newInstance() {
        return new ObjectListShow();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.object_list_show, parent, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        expandableListView = getView().findViewById(R.id.expendableList);

        List<String> Headings = new ArrayList<>();
        Headings = probnaHeaders(Headings);//objectsClass.getList

        List<Button> ChildElements;
        HashMap<String, List<Button>> ChildList = new HashMap<>();

        for (int i = 0; i<Headings.size(); i++)
        {
            ChildElements = new ArrayList<>();
            ChildElements = probnaGumbi(ChildElements);
            ChildList.put(Headings.get(i),ChildElements);

        }
        objectListAdapter MyAdapter = new objectListAdapter(getContext(), Headings, ChildList);
        expandableListView.setAdapter(MyAdapter);
    }

    private List<String> probnaHeaders(List<String> probnaLista)
    {

        for (int i=0; i<10; i++)
        {
            probnaLista.add("objekt "+i);
        }

        return probnaLista;
    }

    private List<Button> probnaGumbi(List<Button> listaGumbi)
    {

            Button btn1 = new Button(getContext());
            btn1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT/2, LinearLayout.LayoutParams.MATCH_PARENT));
            btn1.setText("OPEN");
            btn1.setFocusable(false); //VAŽNO

            Button btn2 = new Button(getContext());
            btn2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT/2, LinearLayout.LayoutParams.MATCH_PARENT));
            btn2.setText("SMS");
            btn2.setFocusable(false); //VAŽNO

            listaGumbi.add(btn1);
            listaGumbi.add(btn2);

        return listaGumbi;
    }
}