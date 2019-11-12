package com.techflitter.assignments

import android.content.Context
import android.util.Log

import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser

object ObjectBox {

    private var boxStore: BoxStore? = null

    internal fun init(context: Context) {

        boxStore = MyObjectBox.builder()
            .androidContext(context.applicationContext)
            .build()

        if (BuildConfig.DEBUG) {
            Log.d(
                App.TAG, String.format(
                    "Using ObjectBox %s (%s)",
                    BoxStore.getVersion(), BoxStore.getVersionNative()
                )
            )
            AndroidObjectBrowser(boxStore).start(context.applicationContext)
        }
    }

    fun get(): BoxStore? {
        return boxStore
    }
}
