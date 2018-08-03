package com.neogineer.lunacontacts.user_details;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.neogineer.lunacontacts.db.User;
import com.neogineer.lunacontacts.model.Repository;
import com.neogineer.lunacontacts.model.RepositoryImpl;

import java.util.List;

public class UserDetailsViewModel extends ViewModel {

    private Repository mRepository;
    private LiveData<User> mUser;

    public void init(Context context, int userId) {
        this.mRepository = new RepositoryImpl(context);
        mUser = mRepository.getUser(userId);
    }

    LiveData<User> getUser(){
        return this.mUser;
    }
}
