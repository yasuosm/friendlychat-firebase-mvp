package com.google.firebase.codelab.friendlychat.ui.signin;

/**
 * Created by annt on 6/19/16.
 */
public class SignInPresenter implements SignInContract.Presenter {
    private SignInContract.View mView;

    public SignInPresenter(SignInContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        this.mView = null;
    }
}
