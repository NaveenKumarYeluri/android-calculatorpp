# Calculator++ (Modernized & Privacy Edition)

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)
[![Android](https://img.shields.io/badge/Android-15%20(API%2035)-green.svg)](https://developer.android.com)
[![Privacy](https://img.shields.io/badge/Privacy-100%25%20Offline%20(Zero%20Permissions)-brightgreen.svg)]()

A fast, elegant, and powerful scientific calculator application for Android, featuring an on-screen floating calculator window, advanced symbolic computation (JSCL), 2D/3D function plotting, numeral base conversions, and high-precision arithmetic.

---

## 🌟 Acknowledgement & Attribution

This project is a modernized, privacy-first fork of **[Calculator++](https://github.com/serso/android-calculatorpp)** originally created and architected by **[Sergey Solovyev (serso)](https://github.com/serso)** (`se.solovyev@gmail.com`, [http://se.solovyev.org](http://se.solovyev.org)).

All core symbolic computation, drag-button gestures, and calculator architecture are the foundational work of Sergey Solovyev. This fork honors the original work and extends it with modern Android platform support, complete removal of ads and network tracking, and new usability features.

Distributed under the **[Apache License 2.0](LICENSE)**.

---

## 🚀 Key Modernizations & Changes

### 🔒 1. 100% Offline & Privacy First
- **Zero Network Permissions**: Removed `android.permission.INTERNET` and `android.permission.ACCESS_NETWORK_STATE`.
- **Zero Ads & Trackers**: Removed Google Mobile Ads SDK (`play-services-ads`), Firebase Crashlytics, and Firebase Analytics.
- **Zero In-App Purchases**: Removed Google Play Billing library (`checkout`), `BILLING` permission, and purchase prompts. The entire application is 100% free, open source, and offline.

### 🎨 2. Material UI Themes & Rich Accents
Custom tailored Material themes for all lighting preferences and display types:
- **White (Material Light)**: Clean, high-contrast light theme with 7 distinct accent options (Teal, Blue, Green, Purple, Amber, Red, Pink).
- **Black (Material Dark)**: Modern Material Dark theme with #121212 surfaces and 7 accent colorways.
- **True Black (AMOLED)**: Pitch black #000000 background offering zero pixel power consumption with vibrant accents (Blue, Cyan, Teal, Green, Purple, Amber, Red).
- **Grey (Material Neutral)**: Soft slate grey theme (#2B2D30) comfortable for extended sessions.
- **Theme-Aligned Android 12+ Splash Screen**: Cold-start splash screen background and vector icon automatically match the user's picked theme (pure white on light themes, dark on dark/AMOLED themes).

### 🔢 3. Flexible Number Formatting (Indian & International)
- **Indian Numbering Format**: Formats numbers using Lakhs and Crores (e.g. `10,00,000` / `1,00,00,000`).
- **International / Western Format**: Formats numbers using Thousands and Millions (e.g. `1,000,000` / `10,000,000`).
- Configurable grouping separators (apostrophe, space, none) with instant live preview in settings.

### 🪟 4. Floating Window Enhancements
- **Quick Settings Tile**: Launch the floating calculator directly from the Android Quick Settings shade (`FloatingCalculatorTileService`).
- **Android App Shortcuts**: Long-press app icon to instantly jump to Floating Calculator or Calculation History.
- **Display Quick Copy**: Long-press the floating calculator display to instantly copy the current result to the clipboard with toast feedback.
- **Android 14/15 Overlay Compatibility**: Full support for `TYPE_APPLICATION_OVERLAY` and runtime notification permission checks.

### 📱 4. Latest Android Compatibility & Icons
- Updated to **compileSdk 35** and **targetSdk 35** (Android 15).
- **Edge-to-Edge System Bar Handling**: Handled window insets across all activities for seamless drawing behind status and navigation bars.
- **Adaptive Vector Launcher Icons**: Native vector adaptive icons with support for Android 13+ **Material You themed icons** (`monochrome` layer) for both the main app and the floating window.
- **New Package ID**: `com.naveenkumar.calculatorpp` (version 3.0.0).

---

## ✨ Features

- **Standard & Scientific Calculator**: Fractions, square roots, trigonometry, logarithms, powers, factorials, permutations, combinations.
- **Symbolic Calculations**: Powered by the embedded Java Symbolic Computation Library (JSCL) - simplify expressions, differentiate, and solve equations.
- **Drag Button Gestures**: Swipe up/down on calculator keys to quickly access secondary functions without switching keyboards.
- **Floating Calculator**: Resizable, draggable, foldable overlay window that floats over any application.
- **Unit & Base Converter**: Seamlessly convert between Decimal, Hexadecimal, Binary, and Octal, plus physical units (length, weight, volume, temperature, time).
- **Function Plotter**: Graph 2D mathematical functions.
- **Calculation History**: Keep track of previous calculations, add comments, and reuse past results.

---

## 🛠️ Building from Source

### Prerequisites
- JDK 17
- Android SDK (API 35, Build-Tools 35.0.0+)
- Gradle 8.7+

### Build Commands
```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease

# Run unit tests
./gradlew test
```

---

## 📄 License

```text
Copyright 2009-2024 Sergey Solovyev (serso)
Copyright 2026 Naveen Kumar Yeluri

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
