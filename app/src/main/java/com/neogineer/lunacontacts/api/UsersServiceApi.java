package com.neogineer.lunacontacts.api;

import com.neogineer.lunacontacts.db.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UsersServiceApi {

    @GET("/techtest/users")
    Call<List<User>> getAllUsers();
}
