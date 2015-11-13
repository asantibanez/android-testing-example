package com.andressantibanez.testingexample.interactors;

import com.andressantibanez.testingexample.github.GithubApiService;
import com.andressantibanez.testingexample.github.User;

import java.util.List;

import rx.Scheduler;
import rx.Subscriber;

/**
 * Created by asantibanez on 11/12/15.
 */
public class GetUsersListInteractor {

    private Scheduler observeOnScheduler;
    private Scheduler subscribeOnScheduler;

    public List<User> getUsers(String sinceUserId) {
        return new GithubApiService()
                .users(sinceUserId)
                .toBlocking()
                .first();
    }

    public GetUsersListInteractor setObserveOnScheduler(Scheduler scheduler) {
        observeOnScheduler = scheduler;
        return this;
    }

    public GetUsersListInteractor setSubscribeOnScheduler(Scheduler scheduler) {
        subscribeOnScheduler = scheduler;
        return this;
    }

    public void getUsers(final Callbacks listener, String sinceUserId) {
        new GithubApiService()
                .users(sinceUserId)
                .observeOn(observeOnScheduler)
                .subscribeOn(subscribeOnScheduler)
                .subscribe(new Subscriber<List<User>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<User> users) {
                        listener.onSuccess(users);
                    }
                });
    }

    public interface Callbacks {
        void onSuccess(List<User> users);
    }

}
