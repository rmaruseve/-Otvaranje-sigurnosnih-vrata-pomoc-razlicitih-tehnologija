package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.Ui.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.R;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.GuestData;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.TriggerType;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.User;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.facilityObject;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.service.ApiInterface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddGuestFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddGuestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddGuestFragment extends Fragment {


    DatePickerDialog.OnDateSetListener dateFromListener;
    DatePickerDialog.OnDateSetListener dateToListener;

    TimePickerDialog.OnTimeSetListener timeFromListener;
    TimePickerDialog.OnTimeSetListener timeToListener;

    String dateFromStr = "";
    String dateToStr = "";
    String phoneNumberStr = "";
    int objectId = 0;

    facilityObject selectedObject;

    GuestData guestData;

    User user;

    ArrayList<facilityObject> listOfObjects = new ArrayList<>();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ApiInterface apiInterface = retrofit.create(ApiInterface.class);


    private OnFragmentInteractionListener mListener;

    public AddGuestFragment() {

    }

    public static AddGuestFragment newInstance() {
        return new AddGuestFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_guest, container, false);

        Bundle bundle = this.getArguments();

        if(bundle != null)
        {
            user = (User)bundle.getSerializable("user");
            listOfObjects = (ArrayList)bundle.getSerializable("listOfObjects");
        }

        final EditText phoneNumber = v.findViewById(R.id.inputGuestPhone);
        final Spinner objectDropdown = v.findViewById(R.id.objectDropdown);
        final Button dateFrom = v.findViewById(R.id.dateFrom);
        Button dateTo = v.findViewById(R.id.dateTo);
        Button save = v.findViewById(R.id.btnGuestSave);


        ArrayAdapter<facilityObject> adapter = new ArrayAdapter<facilityObject>(getContext(), android.R.layout.simple_spinner_dropdown_item, listOfObjects);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        objectDropdown.setAdapter(adapter);

        dateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                dateFromStr += year + "-" + month + "-" + dayOfMonth + " ";


                Calendar calFromTime = Calendar.getInstance();
                int hour = calFromTime.get(Calendar.HOUR_OF_DAY);
                int minute = calFromTime.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog dialogFromTime = new TimePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        timeFromListener,
                        hour,minute,true
                );
                dialogFromTime.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogFromTime.show();

                timeFromListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        dateFromStr += hourOfDay +":"+ minute;
                    }
                };
            }
        };


        dateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                dateToStr += year + "-" + month + "-" + dayOfMonth + " ";


                Calendar calToTime = Calendar.getInstance();
                int hour = calToTime.get(Calendar.HOUR_OF_DAY);
                int minute = calToTime.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog dialogToTime = new TimePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        timeToListener,
                        hour,minute,true
                );
                dialogToTime.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogToTime.show();

                timeToListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        dateToStr += hourOfDay +":"+ minute;
                    }
                };
            }
        };


        objectDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedObject = null;
                selectedObject = (facilityObject) parent.getSelectedItem();
                objectId = selectedObject.getObjId();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                phoneNumberStr = phoneNumber.getText().toString();

                if (phoneNumberStr != "" && objectId !=0  && dateFromStr != "" && dateToStr != "")
                {
                    guestData = new GuestData();
                    guestData.setObjectId(objectId);
                    guestData.setPhoneNumber(phoneNumberStr);
                    guestData.setDateFrom(dateFromStr);
                    guestData.setDateTo(dateToStr);
                    Call<ResponseBody> call = apiInterface.setGuest(user.getToken(), guestData);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            phoneNumberStr = "";
                            dateFromStr = "";
                            dateToStr = "";
                            objectId = 0;

                            Toast.makeText(getActivity(), "Guest added" , Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }
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

        void onFragmentInteraction(Uri uri);
    }
}
