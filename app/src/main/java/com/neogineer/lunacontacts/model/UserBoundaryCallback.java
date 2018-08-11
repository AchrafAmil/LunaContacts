package com.neogineer.lunacontacts.model;

import android.arch.paging.PagedList;
import android.support.annotation.NonNull;
import android.util.Log;

import com.neogineer.lunacontacts.api.RetrofitClientInstance;
import com.neogineer.lunacontacts.api.UsersServiceApi;
import com.neogineer.lunacontacts.db.User;
import com.neogineer.lunacontacts.db.UserRoomDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by AchrafAmil (@neogineer) on 11/08/2018.
 */
public class UserBoundaryCallback extends PagedList.BoundaryCallback<User> {

    private UserRoomDatabase mDb;
    private UsersServiceApi mApi;

    private int pageToRequest = 0;
    private boolean isRequestInProgress = false;

    public UserBoundaryCallback(UserRoomDatabase mDb) {
        this.mDb = mDb;
        mApi = RetrofitClientInstance.getRetrofitInstance()
                .create(UsersServiceApi.class);
    }

    @Override
    public void onZeroItemsLoaded() {
        Log.i("BoundaryCallback","onZeroItemsLoaded");
        downloadAndSaveUsers();
    }

    @Override
    public void onItemAtEndLoaded(@NonNull User itemAtFront) {
        Log.i("BoundaryCallback","onItemAtFrontLoaded "+itemAtFront.id+" "+itemAtFront.getFullName());
        downloadAndSaveUsers();
    }

    private void downloadAndSaveUsers() {
        if(isRequestInProgress){
            Log.i("BoundaryCallback", "wanted to downloadAndSaveData but request is in progress");
            return;
        }

        isRequestInProgress = true;
        Log.i("BoundaryCallback", "new request in progress, loading page number: "+ pageToRequest);

        mApi.getAllUsers(pageToRequest).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                new Thread(()->{
                    mDb.userDao().insertAll(response.body());
                    pageToRequest++;
                    isRequestInProgress = false;
                    Log.i("BoundaryCallback", "received new data from web service, size: "+response.body().size());
                }).start();
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                isRequestInProgress = false;
                Log.i("BoundaryCallback", "oh no :(");
            }
        });
    }
}
