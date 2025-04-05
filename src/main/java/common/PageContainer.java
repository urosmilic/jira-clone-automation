package common;

import com.microsoft.playwright.Page;
import pages.IssuePage;
import pages.dashboard.DashboardPage;
import pages.CreateIssuePage;
import pages.SearchPage;

public class PageContainer {
    public final DashboardPage dashboardPage;
    public final CreateIssuePage createIssuePage;
    public final IssuePage issuePage;
    public final SearchPage searchPage;

    public PageContainer(Page page) {
        this.dashboardPage = new DashboardPage(page);
        this.createIssuePage = new CreateIssuePage(page);
        this.issuePage = new IssuePage(page);
        this.searchPage = new SearchPage(page);
    }

}
