package com.neogineer.lunacontacts.model;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

import com.neogineer.lunacontacts.db.User;

/**
 * Main entry point to access data
 */
public interface Repository {

    /**
     * use getUsersByName("%%") instead
     */
    @Deprecated
    LiveData<PagedList<User>> getAllUsers();

    LiveData<PagedList<User>> getUsersByName(String nameRegex);

    LiveData<User> getUser(int userId);
}
