package com.andressantibanez.testingexample;

import com.andressantibanez.testingexample.github.User;
import com.andressantibanez.testingexample.interactors.GetUsersListInteractor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import rx.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by asantibanez on 11/13/15.
 */
public class GetUsersListUnitTest {

    GetUsersListInteractor getUsersListInteractor;

    @Mock
    GetUsersListInteractor.Callbacks getUsersListInteractorCallbacksListener;

    @Before
    public void init() {
        getUsersListInteractor = new GetUsersListInteractor();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUsersList() {
        System.out.println("Executing in sync");
        List<User> users = getUsersListInteractor.getUsers("");
        assertTrue(users.size() > 0);
    }

    @Test
    public void getUsersListAsync() {
        System.out.println("Executing in async");
        getUsersListInteractor.setObserveOnScheduler(Schedulers.immediate());
        getUsersListInteractor.setSubscribeOnScheduler(Schedulers.immediate());
        getUsersListInteractor.getUsers(getUsersListInteractorCallbacksListener, "");
        verify(getUsersListInteractorCallbacksListener, times(1)).onSuccess(anyList());
    }

}
