package com.neogineer.lunacontacts.users;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersActivity extends AppCompatActivity {

    private UsersViewModel mUsersViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        TextView textView = findViewById(R.id.textView);

        mUsersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        mUsersViewModel.init(this);

        new Thread(() -> {
            mUsersViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
                @Override
                public void onChanged(@Nullable List<User> users) {
                    textView.setText(""+users.size());
                    Log.i("MainActivity", "data change! "+ users);
                }
            });
        }).start();
    }
}
