package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.service;

import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.AllUser;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.Login;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.ObjectOpen;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.Role;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.User;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.facilityObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    String BASE_URL = "http://172.16.0.25:5000";
    @GET("/api/AvailableObjects")
    Call<List<facilityObject>> getObjects(@Header("Authorization") String authToken);

    @POST("/api/triggeraccess")
    Call<ResponseBody> openObject(@Header("Authorization") String authToken,@Body ObjectOpen objectOpen);


    @POST("/api/Users/authenticate")
    Call<User> login(@Body Login login);

    @GET("/api/Users/All")
    Call<List<AllUser>> getUsers(@Header("Authorization") String authToken);

    @GET("/api/Roles")
    Call<List<Role>> getRoles(@Header("Authorization") String authToken);


}
