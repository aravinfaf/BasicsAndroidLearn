package com.e.learn;

import retrofit2.Call;
import retrofit2.http.POST;

public interface LoginService {

    @POST("get_key")
    Call<User> basicLogin();
}
