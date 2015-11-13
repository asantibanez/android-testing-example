package com.andressantibanez.testingexample.github;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by asantibanez on 11/12/15.
 */
public interface GithubApi {

    @GET("/users")
    Observable<List<User>> users(@Query("since") String sinceUserId);

}
