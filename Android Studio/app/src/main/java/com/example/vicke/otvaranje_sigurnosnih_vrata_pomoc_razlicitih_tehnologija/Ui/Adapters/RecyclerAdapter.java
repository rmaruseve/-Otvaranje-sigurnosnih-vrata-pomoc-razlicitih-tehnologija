package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui.Adapters;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.R;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.TriggerList;

import java.util.ArrayList;


class RecyclerViewHolder extends RecyclerView.ViewHolder{

    public Spinner triggerTypes;
    public TextInputLayout inputText;
    public CheckBox isActiveTrigger;



    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        triggerTypes = itemView.findViewById(R.id.triggerTypeDropdown);
        inputText = itemView.findViewById(R.id.input_trigger_value);
        isActiveTrigger = itemView.findViewById(R.id.triggerIsActive);
    }
}




public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{


    private ArrayList<TriggerList> listData;
    private ArrayList<String> listOfTriggerTypes;

    public RecyclerAdapter(ArrayList<TriggerList> listData, ArrayList<String> listOfTriggerTypes) {
        this.listData = listData;
        this.listOfTriggerTypes = listOfTriggerTypes;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.crud_trigger_list_item, viewGroup, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(recyclerViewHolder.inputText.getContext(), android.R.layout.simple_spinner_item, this.listOfTriggerTypes);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        recyclerViewHolder.triggerTypes.setAdapter(spinnerArrayAdapter);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
