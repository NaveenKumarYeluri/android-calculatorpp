package org.solovyev.android.calculator.ads;

import android.view.View;
import androidx.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * No-op ad UI controller.
 * Google Play billing and advertising dependencies have been completely removed.
 */
@Singleton
public class AdUi {

    @Inject
    public AdUi() {
    }

    public void onCreate() {
    }

    public void onResume() {
    }

    public void onCreateView(@NonNull View view) {
    }

    public void onPause() {
    }

    public void onDestroyView() {
    }

    public void onDestroy() {
    }
}
