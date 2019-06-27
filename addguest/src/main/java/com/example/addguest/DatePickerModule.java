package com.example.addguest;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.addguest.R.layout;
import com.example.core.api.service.ApiInterface;

import java.util.Calendar;
import java.util.Locale;

public class DatePickerModule extends Fragment implements DateModule {

    private DatePickerDialog.OnDateSetListener dateFromListener;
    private DatePickerDialog.OnDateSetListener dateToListener;

    private TimePickerDialog.OnTimeSetListener timeFromListener;
    private TimePickerDialog.OnTimeSetListener timeToListener;

    private TextView dateFromText;
    private TextView dateToText;

    private String dateFromStr = "";
    private String dateToStr = "";

    private String dateFromStrShow = "";
    private String dateToStrShow = "";

    public DatePickerModule() {
        // Required empty public constructor
    }

    public static DatePickerModule newInstance() {
        return new DatePickerModule();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(layout.fragment_date_picker_module, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);


        dateFromText = v.findViewById(R.id.dateFromText);
        dateToText = v.findViewById(R.id.dateToText);

        setupPickers();
    }


    private void setupPickers() {
        /**
         * Pick start date
         */
        dateFromText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateFromStr = "";
                dateFromStrShow = "";
                dateFromText.setText("");
                Calendar calFromDate = Calendar.getInstance();
                int year = calFromDate.get(Calendar.YEAR);
                int month = calFromDate.get(Calendar.MONTH);
                int day = calFromDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialogFromDate = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateFromListener,
                        year, month, day);
                dialogFromDate.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogFromDate.show();
            }
        });

        dateFromListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateFromStr = String.format(Locale.getDefault(), "%04d-%02d-%02dT", year, month + 1, dayOfMonth);
                dateFromStrShow = String.format(Locale.getDefault(), "%04d.%02d.%02d", year, month + 1, dayOfMonth);


                Calendar calFromTime = Calendar.getInstance();
                int hour = calFromTime.get(Calendar.HOUR_OF_DAY);
                int minute = calFromTime.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog dialogFromTime = new TimePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        timeFromListener,
                        hour, minute, true
                );
                dialogFromTime.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogFromTime.show();
            }
        }

        ;

        timeFromListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                dateFromStr = String.format(Locale.getDefault(), dateFromStr + "%02d:%02d:00.0", hourOfDay, minute);
                dateFromStrShow = String.format(Locale.getDefault(), dateFromStrShow + " %02d:%02d", hourOfDay, minute);
                dateFromText.setText(dateFromStrShow);

            }
        }

        ;

        /**
         * Pick end date
         */
        dateToText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateToStr = "";
                dateToText.setText("");
                dateToStrShow = "";
                Calendar calToDate = Calendar.getInstance();
                int year = calToDate.get(Calendar.YEAR);
                int month = calToDate.get(Calendar.MONTH);
                int day = calToDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialogToDate = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateToListener,
                        year, month, day);
                dialogToDate.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogToDate.show();
            }
        });

        dateToListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateToStr = String.format(Locale.getDefault(), "%04d-%02d-%02dT", year, month + 1, dayOfMonth);
                dateToStrShow = String.format(Locale.getDefault(), "%04d.%02d.%02d", year, month + 1, dayOfMonth);

                Calendar calToTime = Calendar.getInstance();
                int hour = calToTime.get(Calendar.HOUR_OF_DAY);
                int minute = calToTime.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog dialogToTime = new TimePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        timeToListener,
                        hour, minute, true
                );
                dialogToTime.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogToTime.show();
            }
        };

        timeToListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                dateToStr = String.format(Locale.getDefault(), dateToStr + "%02d:%02d:00.0", hourOfDay, minute);
                dateToStrShow = String.format(Locale.getDefault(), dateToStrShow + " %02d:%02d", hourOfDay, minute);
                dateToText.setText(dateToStrShow);
            }
        };
    }

    @Override
    public String getDateFrom() {
        return dateFromStr;
    }

    @Override
    public String getDateTo() {
        return dateToStr;
    }

    @Override
    public String getModuleName(Context context) {
        return "Date picker";
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
