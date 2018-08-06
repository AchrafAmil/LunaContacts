package com.neogineer.lunacontacts.user_details;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.neogineer.lunacontacts.R;
import com.neogineer.lunacontacts.db.User;
import com.neogineer.lunacontacts.users.UsersActivity;

public class UserDetailsActivity extends AppCompatActivity {

    private UserDetailsViewModel mUserDetailsViewModel;
    private int mUserId;
    private ImageView image;
    private TextView name;
    private TextView gender;
    private TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        image = findViewById(R.id.user_image);
        name = findViewById(R.id.name);
        gender = findViewById(R.id.gender);
        email = findViewById(R.id.email);

        ImageView back = findViewById(R.id.back_button);
        back.setOnClickListener(v -> onBackPressed());

        mUserId = getIntent().getIntExtra(UsersActivity.USER_ID_EXTRA, -1);

        mUserDetailsViewModel = ViewModelProviders.of(this).get(UserDetailsViewModel.class);
        mUserDetailsViewModel.init(this, mUserId);

        mUserDetailsViewModel.getUser().observe(this, user -> {
            populateUi(user);
        });
    }

    private void populateUi(User user) {
        Glide.with(this)
                .load(user.avatar)
                .into(image);

        name.setText(user.firstName + " " + user.lastName);
        gender.setText(user.gender);
        email.setText(user.email);
    }
}
