package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.R;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.ObjectOpen;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.ObjectOpenResponse;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.facilityObject;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.service.ApiInterface;
import com.ncorti.slidetoact.SlideToActView;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class objectListAdapter extends BaseExpandableListAdapter {

    private List<String> header_titles;
    private HashMap<String, SlideToActView> child_titles;
    private Context ctx;
    private String token;
    private List<facilityObject> objectDataList;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(ApiInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    ApiInterface apiInterface = retrofit.create(ApiInterface.class);


    objectListAdapter(Context ctx, List<String> header_titles, HashMap<String, SlideToActView> child_titles, String token, List<facilityObject> objectDataList)
    {
        this.ctx = ctx;
        this.child_titles = child_titles;
        this.header_titles = header_titles;
        this.token = token;
        this.objectDataList = objectDataList;
    }

    @Override
    public int getGroupCount() {
        return header_titles.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;

    }

    @Override
    public Object getGroup(int groupPosition) {
        return header_titles.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child_titles;
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
            for(facilityObject fO: objectDataList)
            {
                if (fO.isAvailable())
                {
                    convertView.setBackgroundResource(R.color.colorPrimary);
                }
            }
        }

        TextView textView = convertView.findViewById(R.id.heading_item);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setText(title);




        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.child_layout, null);
        }

        final SlideToActView value = child_titles.get(header_titles.get(groupPosition)); //object name

        SlideToActView slideToActView = convertView.findViewById(R.id.slide_to_open);

        slideToActView.setOnSlideCompleteListener(new SlideToActView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(@NotNull SlideToActView slideToActView) {

                Log.d("click", "tag: " + value.toString());
                ObjectOpen objectOpen = new ObjectOpen(value.toString());

                Call<ObjectOpenResponse> call = apiInterface.openObject(objectOpen);
                call.enqueue(new Callback<ObjectOpenResponse>() {
                    @Override
                    public void onResponse(Call<ObjectOpenResponse> call, Response<ObjectOpenResponse> response) {
                        if (response.body().isExecuted())
                        {
                            Log.d("Response: ", "open");
                        }
                        else
                        {
                            Log.d("Response: ", "not open");
                        }
                    }

                    @Override
                    public void onFailure(Call<ObjectOpenResponse> call, Throwable t) {
                        Log.d("Response: ", "cannot connect to API");
                    }
                });

                slideToActView.resetSlider();
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
