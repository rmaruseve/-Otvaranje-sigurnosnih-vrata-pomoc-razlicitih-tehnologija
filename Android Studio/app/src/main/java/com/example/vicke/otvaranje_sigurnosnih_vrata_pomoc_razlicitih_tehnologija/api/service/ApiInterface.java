package com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.service;

import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.Login;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.ObjectOpen;
import com.example.vicke.otvaranje_sigurnosnih_vrata_pomoc_razlicitih_tehnologija.api.model.ObjectOpenResponse;
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

    String BASE_URL = "https://172.16.0.88:5000";

    @GET("/api/")
    Call<List<facilityObject>> getObjects(@Header("Authorization") String authToken);



    @POST("/api/Users/authenticate")
    Call<User> login(@Body Login login);

    @GET("/api/")
    Call<ResponseBody> getData(@Header("Authorization") String authToken);



    @POST("/api/ClickedObject")
    Call<ObjectOpenResponse> openObject(@Body ObjectOpen objectOpen);
}
