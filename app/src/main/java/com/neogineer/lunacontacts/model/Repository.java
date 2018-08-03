package com.neogineer.lunacontacts.model;

import android.arch.lifecycle.LiveData;

import com.neogineer.lunacontacts.db.User;

import java.util.List;

public interface Repository {

    LiveData<List<User>> getAllUsers();

    LiveData<User> getUser(int userId);
}
