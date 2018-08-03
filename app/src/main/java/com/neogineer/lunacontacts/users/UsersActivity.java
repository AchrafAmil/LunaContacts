package com.neogineer.lunacontacts.users;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.neogineer.lunacontacts.R;
import com.neogineer.lunacontacts.api.RetrofitClientInstance;
import com.neogineer.lunacontacts.api.UsersServiceApi;
import com.neogineer.lunacontacts.db.User;
import com.neogineer.lunacontacts.db.UserDao;
import com.neogineer.lunacontacts.db.UserRoomDatabase;
import com.neogineer.lunacontacts.model.Repository;
import com.neogineer.lunacontacts.model.RepositoryImpl;
import com.neogineer.lunacontacts.user_details.UserDetailsActivity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersActivity extends AppCompatActivity {

    public static final String USER_ID_EXTRA = "user_id_extra";

    private UsersViewModel mUsersViewModel;
    private RecyclerView mRecycler;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        mRecycler = findViewById(R.id.recycler);
        mRecycler.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mLayoutManager);


        mUsersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        mUsersViewModel.init(this);

        new Thread(() -> {
            mUsersViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
                @Override
                public void onChanged(@Nullable List<User> users) {
                    mRecycler.setAdapter(new UsersAdapter(users, UsersActivity.this::openUserDetails));
                }
            });
        }).start();


    }

    private void openUserDetails(int userId) {
        Intent i = new Intent(this, UserDetailsActivity.class);
        i.putExtra(USER_ID_EXTRA, userId);
        startActivity(i);
    }
}
