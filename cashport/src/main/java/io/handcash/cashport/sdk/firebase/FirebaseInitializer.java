package io.handcash.cashport.sdk.firebase;

import android.app.Activity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FirebaseInitializer {

    public static final String APPLICATION_ID = "1:89393363979:android:0fd2593a9a28a947";
    public static final String API_KEY = "AIzaSyCtTK1H4UOdP9Ib12eN_CKS1jOgQ1a0KsI";
    public static final String PROJECT_ID = "handcash-188517";
    public static final String DATABASE_URL = "https://handcash-188517.firebaseio.com";

    public static FirebaseInitializer create() {
        return new FirebaseInitializer();
    }

    public void initializeFirebase(Activity activity) {
        try {
            FirebaseApp.getInstance();
        } catch ( Exception ex ) {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setApplicationId( APPLICATION_ID )
                    .setApiKey( API_KEY )
                    .setProjectId( PROJECT_ID )
                    .setDatabaseUrl( DATABASE_URL )
                    .build();
            FirebaseApp.initializeApp( activity, options );
        }
    }

}
