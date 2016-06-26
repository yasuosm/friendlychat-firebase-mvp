package com.google.firebase.codelab.friendlychat.ui.chat;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.codelab.friendlychat.model.FriendlyMessage;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/**
 * Created by annt on 6/19/16.
 */
public class ChatPresenter implements ChatContract.Presenter {
    private static final String TAG = "ChatPresenter";
    private ChatContract.View mView;

    // Firebase instance variables
    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseRemoteConfig mFirebaseRemoteConfig;

    public ChatPresenter(ChatContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {
        // Fetch remote config.
        fetchConfig();
    }

    @Override
    public void stop() {
        this.mView = null;
    }

    @Override
    public void sendMessage(String text, String name, String photoUrl) {
        FriendlyMessage friendlyMessage = new FriendlyMessage(text, name, photoUrl);
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseDatabaseReference.child(ChatActivity.MESSAGES_CHILD).push()
                .setValue(friendlyMessage);
        mView.onMessageSent();
    }

    @Override
    public void fetchConfig() {
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        long cacheExpiration = 3600; // 1 hour in seconds
        // If developer mode is enabled reduce cacheExpiration to 0 so that
        // each fetch goes to the server. This should not be used in release
        // builds.
        if (mFirebaseRemoteConfig.getInfo().getConfigSettings()
                .isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }
        mFirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Make the fetched config available via
                        // FirebaseRemoteConfig get<type> calls.
                        mFirebaseRemoteConfig.activateFetched();
                        mView.applyRetrievedLengthLimit();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // There has been an error fetching the config
                        Log.w(TAG, "Error fetching config: " +
                                e.getMessage());
                        mView.applyRetrievedLengthLimit();
                    }
                });
    }
}
