package com.example.elif.eliff;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String adsoy;
    public String bolum;


    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String adsoy, String bolum) {
        this.adsoy = adsoy;
        this.bolum = bolum;

    }

}

