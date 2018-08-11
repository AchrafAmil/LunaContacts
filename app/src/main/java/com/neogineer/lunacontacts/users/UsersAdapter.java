package com.neogineer.lunacontacts.users;

import android.annotation.SuppressLint;
import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.neogineer.lunacontacts.R;
import com.neogineer.lunacontacts.db.User;

public class UsersAdapter extends PagedListAdapter<User,UsersAdapter.ViewHolder> {

    private static final String PLACEHOLDER_IMAGE = "http://riosparadalaw.com/en/wp-content/uploads/2014/02/placeholder-100x100.jpg";

    private static final DiffUtil.ItemCallback<User> USER_COMPARATOR
            = new DiffUtil.ItemCallback<User>() {

        @Override
        public boolean areItemsTheSame(User oldItem, User newItem) {
            return  (oldItem.id == newItem.id);
        }

        @Override
        public boolean areContentsTheSame(User oldItem, User newItem) {
            return oldItem.toString().equals(newItem.toString()); // since toString() includes all class fields
        }
    };

    public interface UsersOnClickHandler {
        void onClick(int userId);
    }

    private UsersOnClickHandler mClickHandler;

    UsersAdapter(UsersOnClickHandler callback) {
        super(USER_COMPARATOR);
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
    public void onBindViewHolder(@NonNull UsersAdapter.ViewHolder viewHolder, int position) {
        User user = getItem(position);

        if(user!=null){
            viewHolder.bind(user);
        } else {
            viewHolder.clear();
        }
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

        // puts a placeholder
        void clear() {
            title.setText("...");
            userId = -1;
            Glide.with(this.image.getContext())
                    .load(PLACEHOLDER_IMAGE)
                    .into(this.image);
        }

        void bind(User user) {
            this.title.setText(user.getFullName());
            this.userId = user.id;
            Glide.with(this.image.getContext())
                    .load(user.avatar)
                    .into(this.image);
        }
    }
}
