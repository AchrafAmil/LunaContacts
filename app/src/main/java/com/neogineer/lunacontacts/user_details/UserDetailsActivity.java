package com.neogineer.lunacontacts.user_details;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.neogineer.lunacontacts.R;
import com.neogineer.lunacontacts.db.User;
import com.neogineer.lunacontacts.users.UsersActivity;
import com.neogineer.lunacontacts.users.UsersViewModel;

public class UserDetailsActivity extends AppCompatActivity {

    private UserDetailsViewModel mUserDetailsViewModel;
    private int mUserId;
    private ImageView image;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        image = findViewById(R.id.userImage);
        textView = findViewById(R.id.details);


        mUserId = getIntent().getIntExtra(UsersActivity.USER_ID_EXTRA, -1);

        mUserDetailsViewModel = ViewModelProviders.of(this).get(UserDetailsViewModel.class);
        mUserDetailsViewModel.init(this, mUserId);

        mUserDetailsViewModel.getUser().observe(this, user -> {
            Glide.with(this)
                    .load(user.avatar)
                    .into(image);

            textView.setText(user.toString());
        });
    }
}
