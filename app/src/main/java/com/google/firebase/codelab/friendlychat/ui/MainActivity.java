package com.google.firebase.codelab.friendlychat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.codelab.friendlychat.ui.chat.ChatActivity;
import com.google.firebase.codelab.friendlychat.ui.signin.SignInActivity;

/**
 * Created by annt on 6/19/16.
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Firebase Auth
        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

        Intent intent;
        if (mFirebaseUser == null) {
            // Not signed in, launch the Sign In activity
            intent = new Intent(this, SignInActivity.class);
        } else {
            intent = new Intent(this, ChatActivity.class);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
    }
}
