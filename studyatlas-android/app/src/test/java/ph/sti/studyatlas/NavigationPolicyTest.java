package ph.sti.studyatlas;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NavigationPolicyTest {
    private static final String HOST = "studyatlas-gq3inc.v2.appdeploy.ai";

    @Test
    public void nestedDrivePdfPreviewStaysInsideWebView() {
        assertFalse(NavigationPolicy.shouldOverride(
                false, "https", "drive.google.com", HOST));
    }

    @Test
    public void nestedOtherOriginStaysInsideWebView() {
        assertFalse(NavigationPolicy.shouldOverride(
                false, "https", "accounts.google.com", HOST));
    }

    @Test
    public void applicationNavigationStaysInsideWebView() {
        assertFalse(NavigationPolicy.shouldOverride(
                true, "https", HOST, HOST));
    }

    @Test
    public void externalMainFrameOpensInBrowser() {
        assertTrue(NavigationPolicy.shouldOverride(
                true, "https", "drive.google.com", HOST));
    }

    @Test
    public void anExternalHttpLinkAlsoOpensInBrowser() {
        assertTrue(NavigationPolicy.shouldOverride(
                true, "http", "example.com", HOST));
    }
}
