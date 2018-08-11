package com.neogineer.lunacontacts.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit mRetrofitInstance;
    private static final String BASE_URL = "http://server.lunabee.studio:11111/";

    public static Retrofit getRetrofitInstance() {
        if (mRetrofitInstance == null) {
            mRetrofitInstance = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofitInstance;
    }
}