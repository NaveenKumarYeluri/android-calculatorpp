package org.solovyev.android.calculator.ga;

import android.app.Application;
import android.content.SharedPreferences;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Offline analytics tracker placeholder.
 * All Firebase Analytics and remote telemetry have been completely removed.
 */
@Singleton
public final class Ga implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Inject
    public Ga(@Nonnull Application application, @Nonnull SharedPreferences preferences) {
        preferences.registerOnSharedPreferenceChangeListener(this);
    }

    public void onButtonPressed(@Nullable String text) {
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences preferences, String key) {
    }

    public void reportInitially(@Nonnull SharedPreferences preferences) {
    }

    public void onFloatingCalculatorOpened() {
    }
}
