package com.neogineer.lunacontacts.model;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.content.Context;
import android.support.annotation.Nullable;
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

    private static final int DATABASE_PAGE_SIZE = 20;

    private UserRoomDatabase mDb;

    public RepositoryImpl(Context context) {
        mDb = UserRoomDatabase.getDatabase(context);
    }

    @Override
    public LiveData<PagedList<User>> getAllUsers() {
        return this.getUsersByName("%%");
    }

    @Override
    public LiveData<User> getUser(int userId) {
        return mDb.userDao().getUserById(userId);
    }

    @Override
    public LiveData<PagedList<User>> getUsersByName(String nameRegex){
        DataSource.Factory<Integer, User> dataSourceFactory = mDb.userDao().getUsersByName(nameRegex);

        return new LivePagedListBuilder<>(dataSourceFactory,
                new PagedList.Config.Builder()
                        .setPageSize(DATABASE_PAGE_SIZE)
                        .setInitialLoadSizeHint(DATABASE_PAGE_SIZE)
                        .build())
                .setBoundaryCallback(new UserBoundaryCallback(mDb))
                .build();
    }
}
