package com.techflitter.assignments

import android.app.Application

class App : Application() {

    override fun onCreate() {
        // Optional: if you distribute your app as App Bundle, provides detection of incomplete
        // installs due to sideloading and helps users reinstall the app from Google Play.
        // https://docs.objectbox.io/android/app-bundle-and-split-apk
        /*if (MissingSplitsManagerFactory.create(this).disableAppIfMissingRequiredSplits()) {
            return; // Skip app initialization.
        }*/
        super.onCreate()
        ObjectBox.init(this)
    }

    companion object {
        val TAG = "ObjectBoxExample"
    }
}
