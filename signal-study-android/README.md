# Signal Study IT2006 Android APK

This Android project wraps https://signal-study-it2006-pqeedb.v2.appdeploy.ai/ in a WebView.

Package: com.signalstudy.it2006. Android 7.0 and higher. No STI eLMS sign-in, account privileges or sensitive Android permissions.

A successful GitHub Actions run builds a debug-signed APK named SignalStudy-IT2006-v1.0.0-debug.apk. GitHub holds that artifact for 30 days. Download the ZIP artifact and extract the APK inside. The app needs an internet connection for initial loading; offline WebView use is not guaranteed. Progress is local to the WebView and separate from Chrome.

The branch is build/signal-study-it2006-apk in the existing kael-ai-downloads repo. It is isolated; do not merge this branch just to download the APK.

Build locally with Android SDK 35, Java 17 and Gradle 8.9:
gradle --no-daemon :app:assembleDebug

This debug build is for personal installation testing, not a Play Store signed release.
