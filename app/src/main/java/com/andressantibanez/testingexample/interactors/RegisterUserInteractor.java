package com.andressantibanez.testingexample.interactors;


import rx.Observable;
import rx.Subscriber;

/**
 * Created by asantibanez on 11/12/15.
 */
public class RegisterUserInteractor {

    public RegisterUserInteractor() {
    }

    public boolean execute(String email, String password) {
        return email.equals("santibanez.andres@gmail.com") && password.equals("ocelot");
    }

    public void executeAsync(final Callbacks listener, final String email, final String password) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                if( execute(email, password) )
                    subscriber.onNext(null);
                else
                    subscriber.onError(new Throwable());
                
                subscriber.onCompleted();

            }
        }).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure();
            }

            @Override
            public void onNext(Object o) {
                listener.onSuccess();
            }
        });
    }

    /**
     * Callbacks
     */
    public interface Callbacks {
        void onSuccess();
        void onFailure();
    }

}
