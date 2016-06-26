package com.google.firebase.codelab.friendlychat.ui.signin;

import com.google.firebase.codelab.friendlychat.ui.base.BasePresenter;
import com.google.firebase.codelab.friendlychat.ui.base.BaseView;

/**
 * Created by annt on 6/19/16.
 */
public class SignInContract {
    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<Presenter> {
    }
}
