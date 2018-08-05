package com.neogineer.lunacontacts.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAll(List<User> users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(User user);

    @Query("SELECT * FROM user")
    public LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM user WHERE user.first_name LIKE :name")
    public LiveData<List<User>> getUsersByName(String name);

    @Query("SELECT * FROM user WHERE id= :userId")
    public LiveData<User> getUserById(int userId);
}
