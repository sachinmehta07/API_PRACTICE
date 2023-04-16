package com.example.api_practice;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("posts")
    @FormUrlEncoded
    Call<ResponseModel> userdetails(@Field("userId") int userId, @Field("title") String userTitle);
}
