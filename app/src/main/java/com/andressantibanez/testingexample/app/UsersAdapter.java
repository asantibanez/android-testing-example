package com.andressantibanez.testingexample.app;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andressantibanez.testingexample.R;
import com.andressantibanez.testingexample.github.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by asantibanez on 11/13/15.
 */
public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private List<User> users;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.login) TextView login;
        @Bind(R.id.avatar) ImageView avatar;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public UsersAdapter(List<User> users) {
        this.users = users;
    }

    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = users.get(position);
        holder.login.setText(user.login);

        if(user.avatarUrl.length() > 0)
            Picasso.with(holder.avatar.getContext()).load(user.avatarUrl).into(holder.avatar);
        else
            holder.avatar.setImageBitmap(null);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
