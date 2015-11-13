package com.andressantibanez.testingexample;

import com.andressantibanez.testingexample.interactors.RegisterUserInteractor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by asantibanez on 11/12/15.
 */
public class RegisterUserUnitTest {

    String validEmail = "santibanez.andres@gmail.com";
    String validPassword = "ocelot";

    //Interactors
    RegisterUserInteractor registerUserInteractor;

    //Mocks
    @Mock
    RegisterUserInteractor.Callbacks registerUserInteractorListener;

    @Before
    public void setupInteractors() {
        registerUserInteractor = new RegisterUserInteractor();
    }

    @Before
    public void setupMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void registerUserWithValidCredentials() {
        boolean registrationOk = registerUserInteractor.execute(validEmail, validPassword);
        assertThat(registrationOk, is(true));
    }

    @Test
    public void registerUserWithValidCredentialsAsync() {
        registerUserInteractor.executeAsync(registerUserInteractorListener, validEmail, validPassword);
        verify(registerUserInteractorListener, times(1)).onSuccess();
        verify(registerUserInteractorListener, times(0)).onFailure();
    }

}
