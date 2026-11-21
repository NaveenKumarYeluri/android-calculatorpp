package org.solovyev.android.calculator.view;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.HapticFeedbackConstants;
import android.view.View;

import javax.annotation.Nonnull;

public class HapticHelper {

    public static void feedbackKeypress(@Nonnull View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            try {
                final Vibrator vibrator = (Vibrator) view.getContext().getSystemService(Context.VIBRATOR_SERVICE);
                if (vibrator != null && vibrator.hasVibrator()) {
                    vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK));
                    return;
                }
            } catch (Throwable ignored) {
            }
        }
        view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP,
                HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING | HapticFeedbackConstants.FLAG_IGNORE_VIEW_SETTING);
    }

    public static void feedbackDrag(@Nonnull View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            try {
                final Vibrator vibrator = (Vibrator) view.getContext().getSystemService(Context.VIBRATOR_SERVICE);
                if (vibrator != null && vibrator.hasVibrator()) {
                    vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_TICK));
                    return;
                }
            } catch (Throwable ignored) {
            }
        }
        view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP,
                HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING | HapticFeedbackConstants.FLAG_IGNORE_VIEW_SETTING);
    }
}
