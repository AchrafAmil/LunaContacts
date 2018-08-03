package com.neogineer.lunacontacts.model;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.neogineer.lunacontacts.api.RetrofitClientInstance;
import com.neogineer.lunacontacts.api.UsersServiceApi;
import com.neogineer.lunacontacts.db.User;
import com.neogineer.lunacontacts.db.UserRoomDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryImpl implements Repository {

    Context mContext;
    LiveData<List<User>> mCachedUsers;

    public RepositoryImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public LiveData<List<User>> getAllUsers() {
        UserRoomDatabase db = UserRoomDatabase.getDatabase(mContext);

        if(mCachedUsers==null)
            mCachedUsers = db.userDao().getAllUsers();

        UsersServiceApi api = RetrofitClientInstance.getRetrofitInstance()
                .create(UsersServiceApi.class);
        Call<List<User>> call = api.getAllUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                new Thread(()->{
                    db.userDao().insertAll(response.body());
                    Log.i("RepoImpl", "received new data from web service, size: "+response.body().size());
                }).start();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.i("RepoImpl", "oh no :(");
            }
        });

        return mCachedUsers;
    }

    @Override
    public LiveData<User> getUser(int userId) { // not yet impl
        return null;
    }
}
