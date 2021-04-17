package com.tanay.alliancelogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

interface ApiHelper {
    @FormUrlEncoded
    @POST("0/up/")
    Call<String> login(@Field("user") String user,
                       @Field("pass") String pass,
                       @Field("login") String login);
}
