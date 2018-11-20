package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class objectListAdapter extends BaseExpandableListAdapter {

    private List<String> header_titles;
    private HashMap<String, List<Button>> child_titles;
    private Context ctx;

    objectListAdapter(Context ctx, List<String> header_titles, HashMap<String, List<Button>> child_titles)
    {
        this.ctx = ctx;
        this.child_titles = child_titles;
        this.header_titles = header_titles;

    }

    @Override
    public int getGroupCount() {
        return header_titles.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //header_titles.size();
        return 1;
        //return value.size();
        //return child_titles.get(header_titles.get(groupPosition));
    }

    @Override
    public Object getGroup(int groupPosition) {
        return header_titles.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child_titles.get(header_titles.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String title = (String) this.getGroup(groupPosition);
        if (convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.parent_layout, null);
        }


        TextView textView = (TextView) convertView.findViewById(R.id.heading_item);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setText(title);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        //Button title = (Button) this.getChild(groupPosition, childPosition);
        if (convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.child_layout, null);
        }

        List<Button> value = child_titles.get(header_titles.get(groupPosition));
        //((LinearLayout)convertView).addView(value.get(0));
        Button btn1 = (Button) convertView.findViewById(R.id.child_item1);
        String buttonText = header_titles.get(groupPosition);
        Log.d("Clicked", "text:  " + buttonText);
        btn1.setText(value.get(0).getText());
        btn1.setTag(buttonText);

        //test
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("click", "tag: " + v.getTag());
            }
        });

        Button btn2 = (Button) convertView.findViewById(R.id.child_item2);
        //String buttonText = header_titles.get(groupPosition);
        Log.d("Clicked", "text:  " + buttonText);
        btn2.setText(value.get(1).getText());
        btn2.setTag(buttonText);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
