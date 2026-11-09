package ph.sti.studyatlas;

/**
 * Keep navigation inside the WebView for app pages and nested preview frames.
 * Only top-level navigation to another website is opened externally.
 */
public final class NavigationPolicy {
    private NavigationPolicy() {}

    public static boolean shouldOverride(boolean isMainFrame, String scheme,
                                         String host, String appHost) {
        if (!isMainFrame) {
            return false;
        }
        return !("https".equalsIgnoreCase(scheme)
                && appHost.equalsIgnoreCase(host));
    }
}
