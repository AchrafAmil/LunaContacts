package com.neogineer.lunacontacts.model;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.neogineer.lunacontacts.db.User;
import com.neogineer.lunacontacts.db.UserRoomDatabase;

import java.util.List;

public class RepositoryImpl implements Repository {

    Context mContext;

    public RepositoryImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public LiveData<List<User>> getAllUsers() {
        UserRoomDatabase db = UserRoomDatabase.getDatabase(mContext);
        return db.userDao().getAllUsers();
    }

    @Override
    public LiveData<User> getUser(int userId) { // not yet impl
        return null;
    }
}
