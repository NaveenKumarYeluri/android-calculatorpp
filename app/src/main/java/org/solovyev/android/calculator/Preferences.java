/*
 * Copyright 2013 serso aka se.solovyev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Contact details
 *
 * Email: se.solovyev@gmail.com
 * Site:  http://se.solovyev.org
 */

package org.solovyev.android.calculator;

import static org.solovyev.android.prefs.IntegerPreference.DEF_VALUE;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.provider.Settings;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import android.text.TextUtils;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;

import org.solovyev.android.Check;
import org.solovyev.android.calculator.about.AboutActivity;
import org.solovyev.android.calculator.functions.FunctionsActivity;
import org.solovyev.android.calculator.history.HistoryActivity;
import org.solovyev.android.calculator.language.Languages;
import org.solovyev.android.calculator.operators.OperatorsActivity;
import org.solovyev.android.calculator.preferences.PreferenceEntry;
import org.solovyev.android.calculator.preferences.PreferencesActivity;
import org.solovyev.android.calculator.variables.VariablesActivity;
import org.solovyev.android.calculator.wizard.WizardActivity;
import org.solovyev.android.prefs.BooleanPreference;
import org.solovyev.android.prefs.IntegerPreference;
import org.solovyev.android.prefs.NumberToStringPreference;
import org.solovyev.android.prefs.Preference;
import org.solovyev.android.prefs.StringPreference;

import java.util.EnumMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class Preferences {

    private static final Preference<Integer> version = IntegerPreference.of("version", 3);

    private Preferences() {
        throw new AssertionError();
    }

    static void init(@Nonnull Application application, @Nonnull SharedPreferences preferences) {
        final int currentVersion = getVersion(preferences);
        if (currentVersion == 0) {
            final SharedPreferences.Editor editor = preferences.edit();
            setInitialDefaultValues(application, preferences, editor);
            editor.apply();
        } else if (currentVersion == 1) {
            final SharedPreferences.Editor editor = preferences.edit();
            if (!Gui.vibrateOnKeypress.isSet(preferences)) {
                Gui.vibrateOnKeypress.putPreference(editor, Deleted.hapticFeedback.getPreference(preferences) > 0);
            }
            migratePreference(preferences, editor, Gui.keepScreenOn, Deleted.preventScreenFromFading);
            migratePreference(preferences, editor, Gui.theme, Deleted.theme);
            migratePreference(preferences, editor, Gui.useBackAsPrevious, Deleted.usePrevAsBack);
            migratePreference(preferences, editor, Gui.showReleaseNotes, Deleted.showReleaseNotes);
            migratePreference(preferences, editor, Gui.rotateScreen, Deleted.autoOrientation);
            final String layout = Deleted.layout.getPreference(preferences);
            if (TextUtils.equals(layout, "main_calculator")) {
                Gui.mode.putPreference(editor, Gui.Mode.engineer);
            } else if (TextUtils.equals(layout, "simple")) {
                Gui.mode.putPreference(editor, Gui.Mode.simple);
            } else if (!Gui.mode.isSet(preferences)) {
                Gui.mode.putDefault(editor);
            }
            version.putDefault(editor);
            editor.apply();
        } else if (currentVersion == 2) {
            final SharedPreferences.Editor editor = preferences.edit();
            Gui.highContrast.tryPutDefault(preferences, editor);
            version.putDefault(editor);
            editor.apply();
        }
    }

    private static int getVersion(@Nonnull SharedPreferences preferences) {
        if (version.isSet(preferences)) {
            return version.getPreference(preferences);
        } else if (Deleted.appVersion.isSet(preferences)) {
            return 1;
        }
        return 0;
    }

    private static <T> void migratePreference(@Nonnull SharedPreferences preferences, @NonNull SharedPreferences.Editor editor, @NonNull Preference<T> to, @NonNull Preference<T> from) {
        if (!to.isSet(preferences)) {
            to.putPreference(editor, from.getPreferenceNoError(preferences));
        }
    }

    private static void setInitialDefaultValues(@Nonnull Application application, @Nonnull SharedPreferences preferences, @Nonnull SharedPreferences.Editor editor) {
        Gui.theme.tryPutDefault(preferences, editor);
        Gui.mode.tryPutDefault(preferences, editor);
        Gui.showReleaseNotes.tryPutDefault(preferences, editor);
        Gui.useBackAsPrevious.tryPutDefault(preferences, editor);
        Gui.rotateScreen.tryPutDefault(preferences, editor);
        Gui.keepScreenOn.tryPutDefault(preferences, editor);
        Gui.language.tryPutDefault(preferences, editor);
        Gui.highContrast.tryPutDefault(preferences, editor);

        Calculations.calculateOnFly.tryPutDefault(preferences, editor);

        Onscreen.showAppIcon.tryPutDefault(preferences, editor);
        Onscreen.theme.tryPutDefault(preferences, editor);

        Widget.theme.tryPutDefault(preferences, editor);
        version.putDefault(editor);

        final ContentResolver cr = application.getContentResolver();
        if (cr != null) {
            final boolean vibrateOnKeyPress = Settings.System.getInt(cr, Settings.System.HAPTIC_FEEDBACK_ENABLED, 0) != 0;
            Gui.vibrateOnKeypress.putPreference(editor, vibrateOnKeyPress);
        }
    }

    public enum SimpleTheme {

        default_theme(0, 0, null),
        metro_blue_theme(R.layout.onscreen_layout, R.layout.widget_layout, Gui.Theme.metro_blue_theme),
        material_theme(R.layout.onscreen_layout_material, R.layout.widget_layout_material, Gui.Theme.material_theme),
        material_grey_theme(R.layout.onscreen_layout_material_grey, R.layout.widget_layout_material_grey, Gui.Theme.material_grey_theme),
        material_black_theme(R.layout.onscreen_layout_material_true_black, R.layout.widget_layout_material_true_black, Gui.Theme.material_black_theme),
        material_light_theme(R.layout.onscreen_layout_material_light, R.layout.widget_layout_material_light, Gui.Theme.material_light_theme, true);

        @LayoutRes
        private final int onscreenLayout;

        @LayoutRes
        private final int widgetLayout;

        @Nullable
        private final Gui.Theme appTheme;

        public final boolean light;

        @Nonnull
        private final Map<Gui.Theme, SimpleTheme> cache = new EnumMap<>(Gui.Theme.class);

        SimpleTheme(int onscreenLayout, int widgetLayout, @Nullable Gui.Theme appTheme) {
            this(onscreenLayout, widgetLayout, appTheme, false);
        }

        SimpleTheme(int onscreenLayout, int widgetLayout, @Nullable Gui.Theme appTheme, boolean light) {
            this.onscreenLayout = onscreenLayout;
            this.widgetLayout = widgetLayout;
            this.appTheme = appTheme;
            this.light = light;
        }

        public int getOnscreenLayout(@Nonnull Gui.Theme appTheme) {
            return resolveThemeFor(appTheme).onscreenLayout;
        }

        public int getWidgetLayout(@Nonnull Gui.Theme appTheme) {
            return resolveThemeFor(appTheme).widgetLayout;
        }

        @Nonnull
        public SimpleTheme resolveThemeFor(@Nonnull Gui.Theme appTheme) {
            if (this == default_theme) {
                SimpleTheme theme = cache.get(appTheme);
                if (theme == null) {
                    theme = lookUpThemeFor(appTheme);
                    cache.put(appTheme, theme);
                }
                return theme;
            }
            return this;
        }

        @Nonnull
        private SimpleTheme lookUpThemeFor(@Nonnull Gui.Theme appTheme) {
            Check.isTrue(this == default_theme);
            if (appTheme.light) {
                return material_light_theme;
            }
            if (appTheme.isAmoled()) {
                return material_black_theme;
            }
            if (appTheme == Gui.Theme.material_grey_theme) {
                return material_grey_theme;
            }
            return material_theme;
        }

        @Nullable
        public Gui.Theme getAppTheme() {
            return appTheme;
        }

        @ColorRes
        public int getDisplayTextColor(boolean error) {
            if (error) {
                return light ? R.color.cpp_text_inverse_error : R.color.cpp_text_error;
            }
            return light ? R.color.cpp_text_inverse : R.color.cpp_text;
        }
    }

    public static class Widget {
        public static final Preference<SimpleTheme> theme = StringPreference.ofEnum("widget.theme", SimpleTheme.default_theme, SimpleTheme.class);

        @Nonnull
        public static SimpleTheme getTheme(@Nonnull SharedPreferences preferences) {
            return theme.getPreferenceNoError(preferences);
        }

    }

    public static class Onscreen {
        public static final Preference<Boolean> showAppIcon = BooleanPreference.of("onscreen_show_app_icon", true);
        public static final Preference<SimpleTheme> theme = StringPreference.ofEnum("onscreen.theme", SimpleTheme.default_theme, SimpleTheme.class);

        @Nonnull
        public static SimpleTheme getTheme(@Nonnull SharedPreferences preferences) {
            return theme.getPreferenceNoError(preferences);
        }
    }

    public static class Calculations {
        public static final Preference<Boolean> calculateOnFly = BooleanPreference.of("calculations_calculate_on_fly", true);
    }

    public static class App {
    }

    public static class Gui {

        public static final Preference<Theme> theme = StringPreference.ofEnum("gui.theme", Theme.material_theme, Theme.class);
        public static final Preference<Mode> mode = StringPreference.ofEnum("gui.mode", Mode.simple, Mode.class);
        public static final Preference<String> language = StringPreference.of("gui.language", Languages.SYSTEM_LANGUAGE_CODE);
        public static final Preference<Boolean> showReleaseNotes = BooleanPreference.of("gui.showReleaseNotes", true);
        public static final Preference<Boolean> useBackAsPrevious = BooleanPreference.of("gui.useBackAsPrevious", false);
        public static final Preference<Boolean> rotateScreen = BooleanPreference.of("gui.rotateScreen", true);
        public static final Preference<Boolean> keepScreenOn = BooleanPreference.of("gui.keepScreenOn", true);
        public static final Preference<Boolean> highContrast = BooleanPreference.of("gui.highContrast", false);
        public static final Preference<Boolean> vibrateOnKeypress = BooleanPreference.of("gui.vibrateOnKeypress", true);

        @Nonnull
        public static Theme getTheme(@Nonnull SharedPreferences preferences) {
            return theme.getPreferenceNoError(preferences);
        }

        @Nonnull
        public static Mode getMode(@Nonnull SharedPreferences preferences) {
            return mode.getPreferenceNoError(preferences);
        }

        public enum Theme implements PreferenceEntry {

            // White Themes (Light)
            material_light_theme(R.string.cpp_theme_white_teal, R.style.Cpp_Theme_Material_Light, R.style.Cpp_Theme_Material_Light_Calculator, true, false),
            material_light_blue(R.string.cpp_theme_white_blue, R.style.Cpp_Theme_Material_Light_Blue, R.style.Cpp_Theme_Material_Light_Blue_Calculator, true, false),
            material_light_green(R.string.cpp_theme_white_green, R.style.Cpp_Theme_Material_Light_Green, R.style.Cpp_Theme_Material_Light_Green_Calculator, true, false),
            material_light_purple(R.string.cpp_theme_white_purple, R.style.Cpp_Theme_Material_Light_Purple, R.style.Cpp_Theme_Material_Light_Purple_Calculator, true, false),
            material_light_amber(R.string.cpp_theme_white_amber, R.style.Cpp_Theme_Material_Light_Amber, R.style.Cpp_Theme_Material_Light_Amber_Calculator, true, false),
            material_light_red(R.string.cpp_theme_white_red, R.style.Cpp_Theme_Material_Light_Red, R.style.Cpp_Theme_Material_Light_Red_Calculator, true, false),
            material_light_pink(R.string.cpp_theme_white_pink, R.style.Cpp_Theme_Material_Light_Pink, R.style.Cpp_Theme_Material_Light_Pink_Calculator, true, false),

            // Black Themes (Dark)
            material_theme(R.string.cpp_theme_black_blue, R.style.Cpp_Theme_Material, R.style.Cpp_Theme_Material_Calculator, false, false),
            material_dark_teal(R.string.cpp_theme_black_teal, R.style.Cpp_Theme_Material_Dark_Teal, R.style.Cpp_Theme_Material_Dark_Teal_Calculator, false, false),
            material_dark_green(R.string.cpp_theme_black_green, R.style.Cpp_Theme_Material_Dark_Green, R.style.Cpp_Theme_Material_Dark_Green_Calculator, false, false),
            material_dark_purple(R.string.cpp_theme_black_purple, R.style.Cpp_Theme_Material_Dark_Purple, R.style.Cpp_Theme_Material_Dark_Purple_Calculator, false, false),
            material_dark_amber(R.string.cpp_theme_black_amber, R.style.Cpp_Theme_Material_Dark_Amber, R.style.Cpp_Theme_Material_Dark_Amber_Calculator, false, false),
            material_dark_red(R.string.cpp_theme_black_red, R.style.Cpp_Theme_Material_Dark_Red, R.style.Cpp_Theme_Material_Dark_Red_Calculator, false, false),
            material_dark_pink(R.string.cpp_theme_black_pink, R.style.Cpp_Theme_Material_Dark_Pink, R.style.Cpp_Theme_Material_Dark_Pink_Calculator, false, false),

            // True Black (AMOLED) Themes
            material_black_theme(R.string.cpp_theme_amoled_blue, R.style.Cpp_Theme_Material_Black, R.style.Cpp_Theme_Material_Black_Calculator, false, true),
            material_amoled_cyan(R.string.cpp_theme_amoled_cyan, R.style.Cpp_Theme_Material_Amoled_Cyan, R.style.Cpp_Theme_Material_Amoled_Cyan_Calculator, false, true),
            material_amoled_teal(R.string.cpp_theme_amoled_teal, R.style.Cpp_Theme_Material_Amoled_Teal, R.style.Cpp_Theme_Material_Amoled_Teal_Calculator, false, true),
            material_amoled_green(R.string.cpp_theme_amoled_green, R.style.Cpp_Theme_Material_Amoled_Green, R.style.Cpp_Theme_Material_Amoled_Green_Calculator, false, true),
            material_amoled_purple(R.string.cpp_theme_amoled_purple, R.style.Cpp_Theme_Material_Amoled_Purple, R.style.Cpp_Theme_Material_Amoled_Purple_Calculator, false, true),
            material_amoled_amber(R.string.cpp_theme_amoled_amber, R.style.Cpp_Theme_Material_Amoled_Amber, R.style.Cpp_Theme_Material_Amoled_Amber_Calculator, false, true),
            material_amoled_red(R.string.cpp_theme_amoled_red, R.style.Cpp_Theme_Material_Amoled_Red, R.style.Cpp_Theme_Material_Amoled_Red_Calculator, false, true),

            // Grey Theme
            material_grey_theme(R.string.cpp_theme_grey, R.style.Cpp_Theme_Material_Grey, R.style.Cpp_Theme_Material_Grey_Calculator, false, false),

            // Legacy fallbacks
            default_theme(R.style.Cpp_Theme_Material),
            violet_theme(R.style.Cpp_Theme_Material),
            light_blue_theme(R.style.Cpp_Theme_Material),
            metro_blue_theme(R.string.cpp_theme_black_blue, R.style.Cpp_Theme_Material, R.style.Cpp_Theme_Material_Calculator, false, false),
            metro_purple_theme(R.string.cpp_theme_black_purple, R.style.Cpp_Theme_Material_Dark_Purple, R.style.Cpp_Theme_Material_Dark_Purple_Calculator, false, false),
            metro_green_theme(R.string.cpp_theme_black_green, R.style.Cpp_Theme_Material_Dark_Green, R.style.Cpp_Theme_Material_Dark_Green_Calculator, false, false);

            private static final SparseArray<TextColor> textColors = new SparseArray<>();

            @StringRes
            protected final int name;
            @StyleRes
            public final int theme;
            @StyleRes
            public final int calculatorTheme;
            @StyleRes
            public final int wizardTheme;
            @StyleRes
            public final int dialogTheme;
            @StyleRes
            public final int alertDialogTheme;
            public final boolean light;
            public final boolean amoled;

            Theme(@StyleRes int theme) {
                this(R.string.cpp_theme_black_blue, theme, theme, false, false);
            }

            Theme(@StringRes int name, @StyleRes int theme, @StyleRes int calculatorTheme, boolean light, boolean amoled) {
                this(name, theme, calculatorTheme,
                        light ? R.style.Cpp_Theme_Wizard_Light : R.style.Cpp_Theme_Wizard,
                        light ? R.style.Cpp_Theme_Material_Light_Dialog : R.style.Cpp_Theme_Material_Dialog,
                        light ? R.style.Cpp_Theme_Material_Light_Dialog_Alert : R.style.Cpp_Theme_Material_Dialog_Alert,
                        light, amoled);
            }

            Theme(@StringRes int name, @StyleRes int theme, @StyleRes int calculatorTheme, @StyleRes int wizardTheme, @StyleRes int dialogTheme, @StyleRes int alertDialogTheme, boolean light, boolean amoled) {
                this.name = name;
                this.theme = theme;
                this.calculatorTheme = calculatorTheme;
                this.wizardTheme = wizardTheme;
                this.dialogTheme = dialogTheme;
                this.alertDialogTheme = alertDialogTheme;
                this.light = light;
                this.amoled = amoled;
            }

            public boolean isAmoled() {
                return amoled;
            }

            public int getThemeFor(@Nonnull Context context) {
                if (context instanceof CalculatorActivity) {
                    return calculatorTheme;
                }
                if (context instanceof WizardActivity) {
                    return wizardTheme;
                }
                if (context instanceof FunctionsActivity.Dialog) {
                    return dialogTheme;
                }
                if (context instanceof PreferencesActivity.Dialog) {
                    return dialogTheme;
                }
                if (context instanceof VariablesActivity.Dialog) {
                    return dialogTheme;
                }
                if (context instanceof OperatorsActivity.Dialog) {
                    return dialogTheme;
                }
                if (context instanceof HistoryActivity.Dialog) {
                    return dialogTheme;
                }
                if (context instanceof AboutActivity.Dialog) {
                    return dialogTheme;
                }
                return theme;
            }

            @Nonnull
            public TextColor getTextColorFor(@Nonnull Context context) {
                final int themeId = getThemeFor(context);
                TextColor textColor = textColors.get(themeId);
                if (textColor == null) {
                    final ContextThemeWrapper themeContext = new ContextThemeWrapper(context, themeId);
                    final TypedArray a = themeContext.obtainStyledAttributes(themeId, new int[]{R.attr.cpp_text_color, R.attr.cpp_text_color_error});
                    final int normal = a.getColor(0, Color.BLACK);
                    final int error = a.getColor(1, Color.WHITE);
                    a.recycle();
                    textColor = new TextColor(normal, error);
                    textColors.append(themeId, textColor);
                }
                return textColor;
            }

            @ColorInt
            public int getScrimColorFor(@Nonnull Context context) {
                final int themeId = getThemeFor(context);
                final ContextThemeWrapper themeContext = new ContextThemeWrapper(context, themeId);
                final TypedArray a = themeContext.obtainStyledAttributes(themeId, new int[]{android.R.attr.background});
                final int color = a.getColor(0, Color.BLACK);
                a.recycle();
                return color;
            }

            @Override
            @NonNull
            public String getName(@NonNull Context context) {
                return context.getString(name);
            }

            @NonNull
            @Override
            public CharSequence getId() {
                return name();
            }
        }

        public enum Mode {
            engineer(R.string.cpp_mode_engineer),
            simple(R.string.cpp_mode_simple);

            @StringRes
            public final int name;

            Mode(@StringRes int name) {
                this.name = name;
            }
        }

        public static final class TextColor {
            public final int normal;
            public final int error;

            TextColor(int normal, int error) {
                this.normal = normal;
                this.error = error;
            }
        }
    }

    @SuppressWarnings("unused")
    static class Deleted {
        static final Preference<Integer> appVersion = IntegerPreference.of("application.version", DEF_VALUE);
        static final Preference<Boolean> feedbackWindowShown = BooleanPreference.of("feedback_window_shown", false);
        static final Preference<Integer> appOpenedCounter = IntegerPreference.of("app_opened_counter", 0);
        static final Preference<Long> hapticFeedback = NumberToStringPreference.of("hapticFeedback", 60L, Long.class);
        static final Preference<Boolean> colorDisplay = BooleanPreference.of("org.solovyev.android.calculator.CalculatorModel_color_display", true);
        static final Preference<Boolean> preventScreenFromFading = BooleanPreference.of("preventScreenFromFading", true);
        static final Preference<Gui.Theme> theme = StringPreference.ofEnum("org.solovyev.android.calculator.CalculatorActivity_calc_theme", Gui.Theme.material_theme, Gui.Theme.class);
        static final StringPreference<String> layout = StringPreference.of("org.solovyev.android.calculator.CalculatorActivity_calc_layout", "simple");
        static final Preference<Boolean> showReleaseNotes = BooleanPreference.of("org.solovyev.android.calculator.CalculatorActivity_show_release_notes", true);
        static final Preference<Boolean> usePrevAsBack = BooleanPreference.of("org.solovyev.android.calculator.CalculatorActivity_use_back_button_as_prev", false);
        static final Preference<Boolean> showEqualsButton = BooleanPreference.of("showEqualsButton", true);
        static final Preference<Boolean> autoOrientation = BooleanPreference.of("autoOrientation", true);
        static final Preference<Boolean> startOnBoot = BooleanPreference.of("onscreen_start_on_boot", false);
        static final Preference<Boolean> plotImag = BooleanPreference.of("graph_plot_imag", false);
    }
}
