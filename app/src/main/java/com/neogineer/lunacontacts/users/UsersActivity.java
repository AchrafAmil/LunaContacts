package com.neogineer.lunacontacts.users;

import android.app.SearchManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import com.neogineer.lunacontacts.R;
import com.neogineer.lunacontacts.user_details.UserDetailsActivity;

public class UsersActivity extends AppCompatActivity {

    public static final String USER_ID_EXTRA = "user_id_extra";

    private UsersViewModel mUsersViewModel;
    private RecyclerView mRecycler;
    private LinearLayoutManager mLayoutManager;
    private SearchView mSearchView;

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

        loadUsers();
    }

    private void loadUsers() {
        mUsersViewModel.getUsers().observe(this, users ->{
            UsersAdapter adapter = (UsersAdapter) mRecycler.getAdapter();
            if(adapter==null)
                mRecycler.setAdapter(new UsersAdapter(users, UsersActivity.this::openUserDetails));
            else
                adapter.setUsers(users);
        });
    }

    private void onNewFilter(String filter) {
        mUsersViewModel.getUsers().removeObservers(this);
        mUsersViewModel.setFilterByName("%"+filter+"%");
        loadUsers();
    }

    private void openUserDetails(int userId) {
        Intent i = new Intent(this, UserDetailsActivity.class);
        i.putExtra(USER_ID_EXTRA, userId);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        mSearchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        mSearchView.setMaxWidth(Integer.MAX_VALUE);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                onNewFilter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                onNewFilter(query);
                return false;
            }
        });
        return true;
    }
}
