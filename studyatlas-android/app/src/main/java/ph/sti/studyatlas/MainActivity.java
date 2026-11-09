package ph.sti.studyatlas;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public final class MainActivity extends Activity {
    private static final String APP_HOST = "studyatlas-gq3inc.v2.appdeploy.ai";
    private static final String APP_URL = "https://" + APP_HOST + "/";
    private static final int FILE_CHOOSER_REQUEST = 41;

    private WebView webView;
    private ValueCallback<Uri[]> chooserCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.rgb(28, 48, 52));
        getWindow().setNavigationBarColor(Color.rgb(248, 249, 245));

        webView = new WebView(this);
        webView.setBackgroundColor(Color.rgb(248, 249, 245));
        setContentView(webView);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(false);
        settings.setSupportMultipleWindows(false);
        settings.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri uri = request.getUrl();
                if ("https".equalsIgnoreCase(uri.getScheme()) && APP_HOST.equalsIgnoreCase(uri.getHost())) {
                    return false;
                }
                String scheme = uri.getScheme();
                if (!"https".equalsIgnoreCase(scheme) && !"http".equalsIgnoreCase(scheme)) {
                    return true;
                }
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                } catch (ActivityNotFoundException exception) {
                    Toast.makeText(MainActivity.this, "No app can open this link.", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onShowFileChooser(WebView view, ValueCallback<Uri[]> callback,
                                             FileChooserParams params) {
                if (chooserCallback != null) {
                    chooserCallback.onReceiveValue(null);
                }
                chooserCallback = callback;
                try {
                    Intent chooseFiles = params.createIntent();
                    chooseFiles.addCategory(Intent.CATEGORY_OPENABLE);
                    startActivityForResult(chooseFiles, FILE_CHOOSER_REQUEST);
                    return true;
                } catch (ActivityNotFoundException exception) {
                    chooserCallback = null;
                    Toast.makeText(MainActivity.this, "No file picker is installed.", Toast.LENGTH_LONG).show();
                    return false;
                }
            }
        });

        if (savedInstanceState != null) {
            webView.restoreState(savedInstanceState);
        } else {
            webView.loadUrl(APP_URL);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_CHOOSER_REQUEST && chooserCallback != null) {
            chooserCallback.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, data));
            chooserCallback = null;
        }
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        if (webView != null) {
            webView.saveState(bundle);
        }
        super.onSaveInstanceState(bundle);
    }

    @Override
    protected void onDestroy() {
        if (chooserCallback != null) {
            chooserCallback.onReceiveValue(null);
            chooserCallback = null;
        }
        if (webView != null) {
            webView.destroy();
        }
        super.onDestroy();
    }
}
