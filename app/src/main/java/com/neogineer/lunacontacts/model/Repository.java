package com.neogineer.lunacontacts.model;

import android.arch.lifecycle.LiveData;

import com.neogineer.lunacontacts.db.User;

import java.util.List;

/**
 * Main entry point to access data
 */
public interface Repository {

    /**
     * use getUsersByName("%%") instead
     * @return
     */
    @Deprecated
    LiveData<List<User>> getAllUsers();

    LiveData<List<User>> getUsersByName(String nameRegex);

    LiveData<User> getUser(int userId);
}
