package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui.Adapters;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.R;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.TriggerList;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.TriggerType;

import java.util.ArrayList;


public class TriggerListAdapter extends ArrayAdapter<String> {

    private Activity context;
    private ArrayList<TriggerType> triggerType;
    private ArrayList<TriggerList> triggerList;

    public TriggerListAdapter(Activity context, ArrayList<TriggerType> triggerType, ArrayList<TriggerList> triggerList)
    {
        super(context, R.layout.recycle_view_trigger_item);
        this.context = context;
        this.triggerType = triggerType;
        this.triggerList= triggerList;


    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        Drawable isChecked = ContextCompat.getDrawable(getContext(), R.drawable.ic_check);
        Drawable notChecked = ContextCompat.getDrawable(getContext(), R.drawable.ic_menu_gallery);

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.recycle_view_trigger_item, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.recycleTriggerText);
        txtTitle.setText("Trigger name: " + triggerType.get(position).getTriggerName() + " Trigger value: " + triggerList.get(position).getTriggerValue() + " Active: ");
        if (triggerList.get(position).getIsTriggerActive() == 1)
        {
            txtTitle.setCompoundDrawablesRelative(null,null, isChecked, null);
        }
        else
        {
            txtTitle.setCompoundDrawablesRelative(null,null, notChecked, null);
        }

        return rowView;
    }


}
