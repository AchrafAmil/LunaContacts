package com.neogineer.lunacontacts.users;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.neogineer.lunacontacts.R;
import com.neogineer.lunacontacts.db.User;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    public interface UsersOnClickHandler {
        void onClick(int userId);
    }

    private UsersOnClickHandler mClickHandler;

    private List<User> mUsers;

    UsersAdapter(List<User> Users, UsersOnClickHandler callback) {
        mUsers = Users;
        mClickHandler = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_item, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        User u = mUsers.get(position);
        viewHolder.title.setText(u.firstName+ " "+ u.lastName);
        viewHolder.userId = u.id;

        Glide.with(viewHolder.image.getContext())
                .load(u.avatar)
                .into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return mUsers==null ? 0 : mUsers.size();
    }

    List<User> getUsers(){
        return mUsers;
    }

    void setUsers(List<User> Users){
        this.mUsers = Users;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView image;
        TextView title;
        int userId;

        ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mClickHandler.onClick(userId);
        }
    }
}
