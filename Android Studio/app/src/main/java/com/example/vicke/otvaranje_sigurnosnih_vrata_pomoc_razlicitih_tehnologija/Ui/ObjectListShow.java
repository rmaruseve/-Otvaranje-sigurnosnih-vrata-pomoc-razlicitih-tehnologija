package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.R;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.facilityObject;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.service.ApiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ObjectListShow extends Fragment {


    String token;
    boolean available;

    private ExpandableListView expandableListView;
    private List<facilityObject> objectDataList;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ApiInterface apiInterface = retrofit.create(ApiInterface.class);




    public static ObjectListShow newInstance() {
        return new ObjectListShow();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        token = getArguments().getString("token");
        return inflater.inflate(R.layout.object_list_show, parent, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        Call<List<facilityObject>> call = apiInterface.getObjects(token);
        call.enqueue(new Callback<List<facilityObject>>() {
            @Override
            public void onResponse(Call<List<facilityObject>> call, Response<List<facilityObject>> response) {
                List<facilityObject> facilityObjects = response.body();
                
                objectDataList = new ArrayList<>(facilityObjects);
            }

            @Override
            public void onFailure(Call<List<facilityObject>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

            expandableListView = getView().findViewById(R.id.expendableList);

            List<String> Headings = new ArrayList<>();
            Headings = addHeaderName(Headings, objectDataList);

            List<Button> ChildElements;
            HashMap<String, List<Button>> ChildList = new HashMap<>();

            for (int i = 0; i<Headings.size(); i++)
            {
                ChildElements = new ArrayList<>();
                ChildElements = addButtonAttribute(ChildElements);
                ChildList.put(Headings.get(i),ChildElements);

            }
            objectListAdapter MyAdapter = new objectListAdapter(getContext(), Headings, ChildList, token, objectDataList);
            expandableListView.setAdapter(MyAdapter);

            expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                boolean open = false;
                long parentId = -1;
                @Override
                public boolean onGroupClick(ExpandableListView parent, View v,
                                            int groupPosition, long id) {

                    if (open = false && objectDataList.get(groupPosition).isAvailable())
                    {
                        parentId = id;
                        open = true;
                    }
                    else
                    {
                        if (parentId == id && objectDataList.get(groupPosition).isAvailable())
                        {
                            parent.collapseGroup((int)parentId);
                            parentId = -1;
                            open = false;
                        }
                        if(parentId != id && objectDataList.get(groupPosition).isAvailable())
                        {
                            parent.collapseGroup((int)parentId);
                            parentId = id;
                            open = true;
                        }
                    }

                    if (!objectDataList.get(groupPosition).isAvailable())
                        return true;

                    return false;
                }
            });
    }

    private List<String> addHeaderName(List<String> headerList, List<facilityObject> nameList)
    {
        
        for (int i=0; i<10; i++)
        {
            headerList.add(nameList.get(i).getName());
        }

        return headerList;
    }

    private List<Button> addButtonAttribute(List<Button> buttonList)
    {

            Button btn1 = new Button(getContext());
            btn1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT/2, LinearLayout.LayoutParams.MATCH_PARENT));
            btn1.setText("OPEN");
            btn1.setFocusable(false); //VAŽNO

            Button btn2 = new Button(getContext());
            btn2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT/2, LinearLayout.LayoutParams.MATCH_PARENT));
            btn2.setText("SMS");
            btn2.setFocusable(false); //VAŽNO

            buttonList.add(btn1);
            buttonList.add(btn2);

        return buttonList;
    }
}