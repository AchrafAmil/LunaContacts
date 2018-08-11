package com.neogineer.lunacontacts.api;

import com.neogineer.lunacontacts.db.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UsersServiceApi {

    @GET("/techtest/users")
    Call<List<User>> getAllUsers();

    @GET("/techtest/users")
    Call<List<User>> getAllUsers(@Query("page") int page);
}
