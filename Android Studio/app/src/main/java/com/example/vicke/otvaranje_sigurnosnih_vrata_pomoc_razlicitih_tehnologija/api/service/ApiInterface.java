package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.service;

import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.AllUser;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.CrudUserDataClass;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.EventLogData;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.Login;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.ObjectOpen;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.Role;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.TriggerList;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.TriggerType;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.User;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.FacilityObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    String BASE_URL = "http://192.168.1.5:5000";

    ////////////////////// GET OBJECTS, OPEN/CLOSE OBJECTS //////////////////////

    @GET("/api/AvailableObjects")
    Call<List<FacilityObject>> getObjects(@Header("Authorization") String authToken);

    @POST("/api/triggeraccess")
    Call<ResponseBody> openObject(@Header("Authorization") String authToken,@Body ObjectOpen objectOpen);

    //TU DODATI ZA CLOSE ALL

    ////////////////////// LOGIN //////////////////////

    @POST("/api/Users/authenticate")
    Call<User> login(@Body Login login);


    ////////////////////// GET ALL USERS. CRUD USER //////////////////////

    @GET("/api/Users/All")
    Call<List<AllUser>> getUsers(@Header("Authorization") String authToken);

    @POST("api/Users/register")
    Call<Integer> crudUsersNew(@Header("Authorization") String authToken, @Body CrudUserDataClass crudUserDataClass);

    @POST("api/Users/update")
    Call<Integer> crudUsers(@Header("Authorization") String authToken, @Body CrudUserDataClass crudUserDataClass);


    ////////////////////// GET TRIGGER TYPES AND LIST OF TRIGGERS FOR SELECTED USER //////////////////////

    @GET("api/AcTriggerTypes")
    Call<ArrayList<TriggerType>> getTriggerTypes(@Header("Authorization") String authToken);

    @GET("api/Trigger")
    Call<ArrayList<TriggerList>> getTriggerList(@Header("Authorization") String authToken, @Query("id") int userID);


    ////////////////////// CRUD FOR TRIGGERS //////////////////////

    @POST("api/Trigger")
    Call<ResponseBody> crudTriggersNew(@Header("Authorization") String authToken, @Body TriggerList triggerLists);

    @POST("api/Trigger/update")
    Call<ResponseBody> crudTriggers(@Header("Authorization") String authToken, @Body TriggerList triggerLists);


    ////////////////////// GET ALL ROLES //////////////////////

    @GET("/api/Roles")
    Call<List<Role>> getRoles(@Header("Authorization") String authToken);


    ////////////////////// GET ALL EVENT LOG ENTRIES //////////////////////

    @GET("/api/EventLogs")
    Call<List<EventLogData>> getEventLogData(@Header("Authorization") String authToken);


    ////////////////////// ADD NEW GUEST //////////////////////

    //TU DODATI ZA GUESTA


    ////////////////////// GET ALL PROFILES//////////////////////
}
