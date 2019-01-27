package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TimePicker;

import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.R;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.EventLogData;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LogFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogFragment extends Fragment {

    ArrayList<EventLogData> eventLogData = new ArrayList<>();
    ListView listViewLog;


    DatePickerDialog.OnDateSetListener dateListener;
    TimePickerDialog.OnTimeSetListener timeListener;
    String date = "";

    private OnFragmentInteractionListener mListener;

    public LogFragment() {
        // Required empty public constructor
    }

    public static LogFragment newInstance() {
        return new LogFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_log, container, false);

        listViewLog = v.findViewById(R.id.logList);

        Bundle bundle = this.getArguments();

        if(bundle != null)
        {
            eventLogData = (ArrayList)bundle.getSerializable("logList");
        }

        ArrayAdapter<EventLogData> arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.log_list_item, R.id.logListItem, eventLogData);
        listViewLog.setAdapter(arrayAdapter);

        Button resetDate = v.findViewById(R.id.logResetFilter);
        Button setFilter = v.findViewById(R.id.filterLog);

        setFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calFromDate = Calendar.getInstance();
                int year = calFromDate.get(Calendar.YEAR);
                int month = calFromDate.get(Calendar.MONTH);
                int day = calFromDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialogFromDate = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateListener,
                        year, month, day);
                dialogFromDate.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogFromDate.show();
            }
        });

        dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date += year + "-" + month + "-" + dayOfMonth + " ";


                Calendar calFromTime = Calendar.getInstance();
                int hour = calFromTime.get(Calendar.HOUR_OF_DAY);
                int minute = calFromTime.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog dialogFromTime = new TimePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        timeListener,
                        hour,minute,true
                );
                dialogFromTime.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogFromTime.show();

                timeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date += hourOfDay +":"+ minute;
                    }
                };
            }
        };

        resetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = "";
            }
        });


        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);

        MenuItem searchItem = menu.findItem(R.id.searchButton);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<EventLogData> tempList = new ArrayList<>();

                for (EventLogData item: eventLogData)
                {
                    if (tempList.contains(newText))
                    {
                        tempList.add(item);
                    }
                }


                if (date == "")
                {
                    ArrayAdapter<EventLogData> arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.log_list_item, R.id.logListItem, tempList);
                    listViewLog.setAdapter(arrayAdapter);
                }
                else
                {
                    //TODO: if date is set filter by date
                    for (int i = 0; i < tempList.size(); i++)
                    {
                        if (tempList.get(i).getDate() != date)
                        {
                            tempList.remove(i);
                            i--;
                        }
                    }
                    ArrayAdapter<EventLogData> arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.log_list_item, R.id.logListItem, tempList);
                    listViewLog.setAdapter(arrayAdapter);
                }
                return true;
            }
        });

        super.onCreateOptionsMenu(menu,inflater);
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

        void onFragmentInteraction(Uri uri);
    }
}
