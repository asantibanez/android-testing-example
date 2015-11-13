package com.andressantibanez.testingexample.github;

import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * Created by asantibanez on 11/12/15.
 */
public class GithubApiService {

    GithubApi mApi;

    public GithubApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mApi = retrofit.create(GithubApi.class);
    }

    public Observable<List<User>> users(String sinceUserId) {
        return mApi.users(sinceUserId);
    }

}
