# StudyAtlas Android wrapper

This is an Android WebView wrapper around the deployed StudyAtlas web app:
https://studyatlas-gq3inc.v2.appdeploy.ai/

- Android 7.0+ (API 24), requires internet to load the web app.
- Supports JavaScript, DOM storage/IndexedDB, Android PDF file picker and external web links.
- The actual PDF files and study progress are stored in the APK's own Android WebView data profile, separate from Chrome and the web site as viewed in Chrome.
- APK is debug-signed for personal sideload; do not publish to an app store.
- In-app PDF iframe previews may not render in Android System WebView on all versions. For a reliable PDF viewer, use Chrome to open the web app or add a native PDF renderer in a future build.
- The package is not a fully offline application and does not automatically sync the user's eLMS / Google Drive PDFs.
- Signing identity should be stabilized before distributing upgrades to preserve existing installs without uninstall/reinstall.

Build: `gradle :app:assembleDebug`
