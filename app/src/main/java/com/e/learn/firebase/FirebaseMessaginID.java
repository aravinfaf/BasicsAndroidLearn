package com.e.learn.firebase;


import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessagingService;

public class FirebaseMessaginID extends FirebaseMessagingService {


    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);

        Log.e("TOKEN",s);
        storeToken(s);
    }

    private void storeToken(String token) {
        //saving the token on shared preferences
        SharedPrefManager.getInstance(getApplicationContext()).saveDeviceToken(token);
    }
}
