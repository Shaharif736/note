# Arif Notes - Android APK

Full native notes app built with Kotlin, Jetpack Compose, and Room.

## Features
- Create / Edit / Delete notes
- Local save with Room Database (offline, persistent)
- Search instantly
- Dark Mode (follows system)
- Material 3 design

## Build APK
1. Open in Android Studio Hedgehog+
2. Wait for Gradle sync (KSP + Room will download)
3. Build > Build APK(s) > Build Debug APK
4. APK: app/build/outputs/apk/debug/app-debug.apk

## Customize
- App name: AndroidManifest.xml -> android:label
- Package: app/build.gradle.kts -> applicationId / namespace
- Colors: MainActivity.kt -> ArifNotesTheme
- Add features: PIN lock, export to txt, categories - ask Meta AI to add them.

Built for Shamsur Rahman Arif
