# Calculator++ (Modernized & Privacy Edition)

[![Latest Release](https://img.shields.io/github/v/release/NaveenKumarYeluri/android-calculatorpp?color=blue&label=Latest%20Release)](https://github.com/NaveenKumarYeluri/android-calculatorpp/releases/latest)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)
[![Android](https://img.shields.io/badge/Android-15%20(API%2035)-green.svg)](https://developer.android.com)
[![Privacy](https://img.shields.io/badge/Privacy-100%25%20Offline%20(Zero%20Permissions)-brightgreen.svg)]()
[![Build Status](https://github.com/NaveenKumarYeluri/android-calculatorpp/actions/workflows/build.yml/badge.svg)](https://github.com/NaveenKumarYeluri/android-calculatorpp/actions)

**Calculator++ (Modernized & Privacy Edition)** is a fast, elegant, and exceptionally powerful scientific calculator for Android. It combines intuitive gesture-based drag buttons, an on-screen floating calculator window, embedded symbolic mathematics (JSCL), 2D/3D function plotting, numeral base conversion, digital storage conversion, memory functions, and Material 3 / Material You dynamic theming into a **100% offline, privacy-first** application with **zero ads, zero tracking, and zero in-app purchases**.

---

## 📑 Table of Contents

- [🌟 Acknowledgements & Credits](#-acknowledgements--credits)
- [📥 Download & Installation](#-download--installation)
- [🚀 Major Modernizations in v3.0.0](#-major-modernizations-in-v300)
- [✨ Features](#-features)
- [🧮 Symbolic Computation Engine](#-symbolic-computation-engine)
- [🎨 Themes & Customization](#-themes--customization)
- [🛠️ Building from Source](#️-building-from-source)
- [📄 License](#-license)

---

## 🌟 Acknowledgements & Credits

This project stands on the shoulders of giants. We express our deepest gratitude to the original creator, core library authors, and contributors:

### 👑 Original Creator & Architect
- **[Sergey Solovyev (serso)](https://github.com/serso)** (`se.solovyev@gmail.com`, [http://se.solovyev.org](http://se.solovyev.org))
  - Original architect and developer of Calculator++ for Android.
  - Invented the signature drag-button gesture interactions.
  - Architected the comprehensive calculator engine, multi-window floating overlay, function plotting, and underlying modular design.
  - Original repository: [serso/android-calculatorpp](https://github.com/serso/android-calculatorpp).

### 🛠️ Modernization, Privacy Architecture & Fork Maintainer
- **[Naveen Kumar Yeluri](https://github.com/NaveenKumarYeluri)** (`naveenk0513@zohomail.in`)
  - Updated to **Android 15 (API 35)** with edge-to-edge system window insets and modern runtime permissions.
  - Re-architected for **100% offline privacy**: excised all network permissions, Google Ads, Firebase Analytics/Crashlytics, and Google Play Billing.
  - Designed **Material Design 3 rounded styling** and **Material You (Monet) dynamic theming** with AMOLED True Black and rich accent palettes.
  - Added **Indian Numbering System** formatting (Lakhs and Crores: `10,00,000`).
  - Implemented **Memory calculator functions** (`MC`, `MR`, `M+`, `M-`, `MS`) with on-display indicator.
  - Added **Digital Storage unit converter** (bits to petabytes, binary and decimal prefixes).
  - Built **Quick Settings Tile integration** and **Display Quick Copy** for the floating calculator.
  - Implemented **History export and sharing** (plain text & CSV) and **modern haptic feedback** (`VibrationEffect`).
  - Modernized Gradle build scripts, dependencies, and automated GitHub Actions CI/CD workflows.

### 📚 Open Source Libraries & Technologies
- **[JSCL (Java Symbolic Computation Library)](https://github.com/serso/android-calculatorpp/tree/master/jscl)**: Core algebraic and calculus engine enabling symbolic simplification, differentiation, and equation solving.
- **[Square Otto](https://github.com/square/otto)**: Event bus architecture by Square, Inc.
- **[Google Dagger](https://github.com/google/dagger)**: High-performance dependency injection framework by Google LLC.
- **[Google Guava](https://github.com/google/guava)**: Fundamental Google core libraries for Java.
- **[Simple XML](http://simple.sourceforge.net/)**: Robust XML serialization framework.
- **[Android Jetpack & Material Components](https://github.com/material-components/material-components-android)**: Modern UI foundations and backwards compatibility by Google.

### 🌍 Localization & Community
- Sincere appreciation to all community contributors and translators on Crowdin who localized Calculator++ into dozens of languages across the world.

---

## 📥 Download & Installation

Pre-built APKs for Android 5.0+ (Lollipop) through Android 15 are published on the releases page:

### 📦 [Download the Latest Release (v3.0.0)](https://github.com/NaveenKumarYeluri/android-calculatorpp/releases/latest)

| Package | Description | Recommended For |
|:---|:---|:---|
| **`CalculatorPlusPlus-v3.0.0.apk`** | Standard signed release APK | **All Users** |
| **`CalculatorPlusPlus-v3.0.0-release.apk`** | Signed release build | General Installation |
| **`CalculatorPlusPlus-v3.0.0-debug.apk`** | Debug build with extra diagnostic logs | Developers / Testers |

### 📲 Installation Instructions
1. Download **`CalculatorPlusPlus-v3.0.0.apk`** from the [GitHub Releases](https://github.com/NaveenKumarYeluri/android-calculatorpp/releases/latest).
2. Tap the downloaded file in your browser or file manager.
3. If prompted, grant permission to *"Install unknown apps"* for your browser or file manager.
4. Tap **Install** and enjoy a private, ad-free, full-featured scientific calculator!

---

## 🚀 Major Modernizations in v3.0.0

### 🔒 1. 100% Offline & Absolute Privacy
- **Zero Network Permissions**: Completely removed `android.permission.INTERNET` and `android.permission.ACCESS_NETWORK_STATE`. The application is physically incapable of connecting to the internet or transmitting any data.
- **Zero Ads & Trackers**: Completely removed Google Mobile Ads SDK (`play-services-ads`), Firebase Crashlytics, and Firebase Analytics. No telemetry, no background beacons, no profiling.
- **Zero In-App Purchases**: Completely stripped Google Play Billing (`checkout`) library, billing permissions, and payment prompts. The entire application is 100% free and open source.

### 📱 2. Target Android 15 (API 35) & Modern Frameworks
- Upgraded project toolchains to **compileSdk 35** and **targetSdk 35** (Android 15) using **Java 17** and Gradle 8.6+.
- **Edge-to-Edge System Bar Insets**: Handles system window insets across all activities, ensuring seamless layout rendering behind the status bar and gesture navigation bar.
- **Modern Overlay Support**: Full compatibility with Android 14/15 `TYPE_APPLICATION_OVERLAY` for the floating calculator window.
- **Themed Monochrome Launcher Icon**: Full support for Android 13+ **Material You dynamic themed icons** on compatible launchers.

### 🎨 3. Material Design 3 UI & Rich Theming
- **Material You Dynamic Theming (Monet)**: Adapts calculator colors dynamically to match your device wallpaper and system theme on Android 12+.
- **Tailored Theme Options**:
  - **White (Material Light)**: Clean, high-contrast light theme with 7 accent choices.
  - **Black (Material Dark)**: Modern slate dark theme with `#121212` surfaces.
  - **True Black (AMOLED)**: Pitch black `#000000` background for maximum OLED battery savings and razor-sharp contrast.
  - **Grey (Material Neutral)**: Eye-friendly slate grey (`#2B2D30`) designed for long sessions.
- **7 Vibrant Accents**: Teal, Blue, Green, Purple, Amber, Red, and Pink.
- **Theme-Aligned Android 12+ Splash Screen**: Smooth cold-start launch animation matching your selected theme.
- **Material 3 Rounded Keypad**: Contemporary squircle/pill button geometry with interactive touch ripples.

### 💾 4. Memory Calculator Functions (MC, MR, M+, M-, MS)
- Dedicated memory operations accessible directly on the keypad:
  - **`MS` (Memory Store)**: Save the current value into memory.
  - **`MR` (Memory Recall)**: Recall the stored value into the current calculation.
  - **`M+` (Memory Add)**: Add the current value to the value in memory.
  - **`M-` (Memory Subtract)**: Subtract the current value from the value in memory.
  - **`MC` (Memory Clear)**: Reset the memory register.
- Visual **`M`** indicator on the display header whenever a value is retained in memory.

### 🔢 5. Flexible Number Formatting (Indian & International)
- **Indian Numbering Format**: Groups digits using Lakhs and Crores (e.g., `10,00,000` and `1,00,00,000`).
- **International / Western Format**: Groups digits using Thousands and Millions (e.g., `1,000,000`).
- **Configurable Grouping Separators**: Choose between Space (` `), Apostrophe (`'`), Comma (`,`), or None (`OFF`), with instant live preview in settings.

### 💱 6. Unit & Base Converter with Digital Storage
- **Digital Storage Dimension**: Convert effortlessly between Bits, Bytes, KB, MB, GB, TB, PB, KiB, MiB, GiB, TiB, and PiB.
- **Numeral Base Conversions**: Instant real-time conversions across Hexadecimal (HEX), Decimal (DEC), Octal (OCT), and Binary (BIN).
- **Physical Dimensions**: Length, Mass/Weight, Volume, Temperature, and Time with customizable precision.

### 🪟 7. Floating Calculator & Quick Settings Integration
- **Quick Settings Tile**: Add the `Floating Calculator` tile to your Android notification shade to launch the floating window from any screen or app instantly.
- **Display Quick Copy**: Long-press on the calculation display in the floating window to instantly copy the expression or result to the clipboard.
- **App Shortcuts**: Quick launcher actions to directly jump to the Floating Calculator or Calculation History.

### 📤 8. History Export & Sharing
- Export your calculation history log to other apps (Notes, Messaging, Email, Drive).
- Support for plain text and CSV export formats.

### 📳 9. Modern Haptic Feedback
- High-fidelity tactile feedback utilizing Android's modern `VibrationEffect` API, offering crisp click and tick feedback upon key presses.

---

## ✨ Features

- **Scientific Calculator**:
  - Arithmetic, square roots, cubic roots, powers, factorials, permutations, combinations.
  - Trigonometric, inverse trigonometric, hyperbolic, and inverse hyperbolic functions.
  - Natural logarithm (`ln`), base-10 logarithm (`log`), and arbitrary base logarithms.
  - Complex numbers support ($i$, imaginary units, polar forms).
  - Degree, Radian, and Gradian angle modes with on-screen toggle.
- **Gesture Drag-Button System**:
  - Swipe up or down on calculator buttons to trigger secondary and tertiary functions without switching screens or toggling shift layers.
- **Symbolic Mathematics (JSCL)**:
  - Expression simplification.
  - Symbolic differentiation (derivatives).
  - Algebraic equation solving.
- **2D & 3D Function Plotter**:
  - Graph multiple functions simultaneously with interactive pan and zoom.
- **Floating Overlay Calculator**:
  - Resizable, foldable, draggable window that floats over other apps.
- **Custom Variables & Constants**:
  - Define custom user constants and variables for repeated calculations.
- **Calculation History**:
  - Timestamped history of all calculations with customizable history depth and search.

---

## 🛠️ Building from Source

### Prerequisites
- **JDK 17** (Temurin, Corretto, or OpenJDK recommended)
- **Android SDK** with Platform API 35 and Build-Tools 35.0.0
- **Gradle 8.6+** (the repository includes the Gradle Wrapper)

### Build Steps

Clone the repository:
```bash
git clone https://github.com/NaveenKumarYeluri/android-calculatorpp.git
cd android-calculatorpp
```

Assemble Debug APK:
```bash
./gradlew assembleDebug
```
The output APK is generated at: `app/build/outputs/apk/debug/app-debug.apk`

Assemble Release APK:
```bash
./gradlew assembleRelease
```
The output APK is generated at: `app/build/outputs/apk/release/app-release.apk`

Run Unit Tests:
```bash
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
