package pages.dashboard;

import com.microsoft.playwright.Page;
import pages.BasePage;

public class FilterPagelet extends BasePage {
    public FilterPagelet(Page page) {
        super(page);
    }

    public void filterIssuesByKeyword(String keyword) {
        page.getByLabel("search").locator("input").fill(keyword);
        waitForNetworkIdle();
    }
}
