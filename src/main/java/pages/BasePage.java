package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

public class BasePage {
    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    public void waitForLocator(String selector, WaitForSelectorState state, int timeout) {
        page.locator(selector).waitFor(new Locator.WaitForOptions().setState(state).setTimeout(timeout));
    }

    public void waitForLocator(String selector) {
        page.locator(selector).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
    }

    public void waitForPageLoad() {
        page.waitForLoadState(LoadState.LOAD);
    }

    public void waitForDomContentLoad() {
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    public void waitForNetworkIdle() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }
}
