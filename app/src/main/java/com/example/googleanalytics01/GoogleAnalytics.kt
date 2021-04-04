package com.example.googleanalytics01

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import java.util.*


class GoogleAnalytics{
    var mFirebaseAnalytics: FirebaseAnalytics? = null

    fun time(total:Long,id: String, pageName: String) {

        val bundle = Bundle()
        bundle.putString("id", id)
        bundle.putString("name", pageName)
        mFirebaseAnalytics!!.logEvent(total.toString(), bundle)
    }

    fun trackScreen(screenName: String) {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity")
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }

}