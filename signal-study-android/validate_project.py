from pathlib import Path
from xml.etree import ElementTree as ET
import re

root = Path(__file__).parent
for path in ('settings.gradle', 'build.gradle', 'app/build.gradle'):
    assert (root / path).is_file(), path
manifest = root / 'app/src/main/AndroidManifest.xml'
doc = ET.parse(manifest).getroot()
ns = '{http://schemas.android.com/apk/res/android}'
assert any(x.get(ns + 'name') == 'android.permission.INTERNET' for x in doc.findall('uses-permission'))
java = (root / 'app/src/main/java/com/signalstudy/it2006/MainActivity.java').read_text()
assert 'https://signal-study-it2006-pqeedb.v2.appdeploy.ai/' in java
assert 'setDomStorageEnabled(true)' in java
assert 'setJavaScriptEnabled(true)' in java
assert 'setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW)' in java
assert 'shouldOverrideUrlLoading' in java
assert 'getHost()' in java
assert 'canGoBack()' in java
print('PASS: Android wrapper source, manifest and security boundary validated')
