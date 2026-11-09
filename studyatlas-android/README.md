# StudyAtlas Android wrapper — Reader edition

This is an Android WebView wrapper around the deployed StudyAtlas web app:
https://studyatlas-gq3inc.v2.appdeploy.ai/

- Android 7.0+ (API 24), requires internet to load the web app.
- Supports JavaScript, DOM storage/IndexedDB, Android PDF file picker and external web links.
- The actual PDF files and study progress are stored in the APK's own Android WebView data profile, separate from Chrome and the web site as viewed in Chrome.
- APK is debug-signed for personal sideload; do not publish to an app store.
- In-app PDF iframe previews may not render in Android System WebView on all versions. For a reliable PDF viewer, use Chrome to open the web app or add a native PDF renderer in a future build.
- The package id is ph.sti.studyatlas.modules, so this launcher-icon release installs alongside the original debug-signed v1.0.0. It opens the fixed nine-subject course catalog, but no 2026–2027 module PDFs were available in the user's new Google Drive archive at build time. The package is not a fully offline application and does not automatically sync the user's eLMS / Google Drive PDFs.
- Signing identity should be stabilized before distributing upgrades to preserve existing installs without uninstall/reinstall.

Build: `gradle :app:assembleDebug`

## Version 1.2.0
- Fixed nested Google Drive PDF preview navigation so the WebView does not automatically launch the system browser for iframe requests.
- External main-page Google Drive links intentionally open in the browser for sign-in / fallback.
- Includes JUnit regression tests for subframe and main-frame navigation.
- A distinct package ID `ph.sti.studyatlas.reader` protects existing v1.1 installs and local study data from debug-signing incompatibilities. Existing data is not migrated automatically.
- App content is loaded from the live StudyAtlas URL and requires internet; private Drive embeds may still require browser login or fallback.
