package com.andressantibanez.testingexample.github;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asantibanez on 11/12/15.
 */
public class User {

    public long id;
    public String login;

    @SerializedName("avatar_url")
    public String avatarUrl;
}
