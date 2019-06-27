package com.example.addguest;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.core.api.service.ApiInterface;

public class DateTextInputModule extends Fragment implements DateModule {

    private EditText dateFromEditText;
    private EditText dateToEditText;

    public DateTextInputModule() {
        // Required empty public constructor
    }

    public static DateTextInputModule newInstance() {
        return new DateTextInputModule();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_date_text_input_module, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dateFromEditText = view.findViewById(R.id.date_from_edit_text);
        dateToEditText = view.findViewById(R.id.date_to_edit_text);
    }

    @Override
    public String getDateFrom() {
        return dateFromEditText.getText().toString();
    }

    @Override
    public String getDateTo() {
        return dateToEditText.getText().toString();
    }

    @Override
    public String getModuleName(Context context) {
        return "Edit text date";
    }

    @Override
    public Drawable getModuleIcon(Context context) {
        return null;
    }

    @Override
    public Fragment getModuleFragment(Context context) {
        return this;
    }

    @Override
    public void ApiModule(ApiInterface apiInterface) {

    }
}
