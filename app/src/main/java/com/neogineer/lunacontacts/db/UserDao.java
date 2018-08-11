package com.neogineer.lunacontacts.db;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PagedList;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<User> users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Query("SELECT * FROM user WHERE user.first_name LIKE :name")
    DataSource.Factory<Integer, User> getUsersByName(String name);

    @Query("SELECT * FROM user WHERE id= :userId")
    LiveData<User> getUserById(int userId);
}
