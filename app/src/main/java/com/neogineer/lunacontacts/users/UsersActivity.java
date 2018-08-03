package com.neogineer.lunacontacts.users;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.neogineer.lunacontacts.R;
import com.neogineer.lunacontacts.db.User;
import com.neogineer.lunacontacts.db.UserDao;
import com.neogineer.lunacontacts.db.UserRoomDatabase;
import com.neogineer.lunacontacts.model.Repository;
import com.neogineer.lunacontacts.model.RepositoryImpl;

import java.util.LinkedList;
import java.util.List;

public class UsersActivity extends AppCompatActivity {

    private UsersViewModel mUsersViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        mUsersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        mUsersViewModel.init(this);

    }
}
