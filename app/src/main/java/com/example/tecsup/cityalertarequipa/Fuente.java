package com.example.tecsup.cityalertarequipa;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class Fuente extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("@string/fuente")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
