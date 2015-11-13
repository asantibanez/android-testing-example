package com.andressantibanez.testingexample.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.andressantibanez.testingexample.R;
import com.andressantibanez.testingexample.github.User;
import com.andressantibanez.testingexample.interactors.GetUsersListInteractor;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UsersActivity extends AppCompatActivity implements GetUsersListInteractor.Callbacks {

    private static final String LOG_TAG = UsersActivity.class.getSimpleName();

    @Bind(R.id.users_list) RecyclerView usersListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        ButterKnife.bind(this);

        usersListView.setLayoutManager(new LinearLayoutManager(this));

        new GetUsersListInteractor()
                .setObserveOnScheduler(AndroidSchedulers.mainThread())
                .setSubscribeOnScheduler(Schedulers.io())
                .getUsers(this, "");
    }


    @Override
    public void onSuccess(List<User> users) {
        Log.d(LOG_TAG, "Users: " + users.size());

        UsersAdapter adapter = new UsersAdapter(users);
        usersListView.setAdapter(adapter);
    }
}
