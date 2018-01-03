package br.com.starstore.util;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;


/**
 * Created by filipenunes on 31/12/17.
 */
public class AnalyticsManager {

    private FirebaseAnalytics mFirebaseAnalytics;

    public AnalyticsManager(Context context) {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }

    public void trackSearchAction(String query) {
        Bundle params = new Bundle();
        params.putString("search", query);
        mFirebaseAnalytics.logEvent("search", params);
    }

    public void trackAddAction(String item) {
        Bundle params = new Bundle();
        params.putString("add", item);
        mFirebaseAnalytics.logEvent("add", params);
    }

}
