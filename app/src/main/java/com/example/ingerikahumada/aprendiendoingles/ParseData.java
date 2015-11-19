package com.example.ingerikahumada.aprendiendoingles;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Ing. Erik Ahumada on 19/11/2015.
 */
public class ParseData extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "6AVz0s4E50OcfioCwCAKM9MNg7RP59ZeSiuEyGpH", "an5jRB30HmwryIw3Ws0AYQXTAjYUFlL085eCwfL4");
    }
}
