package com.neogineer.lunacontacts.users;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.neogineer.lunacontacts.db.User;
import com.neogineer.lunacontacts.model.Repository;
import com.neogineer.lunacontacts.model.RepositoryImpl;

import java.util.List;

public class UsersViewModel extends ViewModel {

    private Repository mRepository;
    private LiveData<List<User>> mUsers;

    public void init(Context context) {
        this.mRepository = new RepositoryImpl(context);
        mUsers = mRepository.getAllUsers();
    }

    LiveData<List<User>> getAllUsers(){
        return this.mUsers;
    }
}
