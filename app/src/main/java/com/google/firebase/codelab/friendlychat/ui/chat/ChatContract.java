package com.google.firebase.codelab.friendlychat.ui.chat;

import com.google.firebase.codelab.friendlychat.ui.base.BasePresenter;
import com.google.firebase.codelab.friendlychat.ui.base.BaseView;

/**
 * Created by annt on 6/19/16.
 */
public class ChatContract {
    interface Presenter extends BasePresenter {
        void sendMessage(String text, String name, String photoUrl);

        // Fetch the config to determine the allowed length of messages.
        void fetchConfig();
    }

    interface View extends BaseView<Presenter> {
        void onMessageSent();

        /**
         * Apply retrieved length limit to edit text field.
         * This result may be fresh from the server or it may be from cached values.
         */
        void applyRetrievedLengthLimit();
    }
}
