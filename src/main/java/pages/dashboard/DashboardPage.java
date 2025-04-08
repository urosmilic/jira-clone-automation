package pages.dashboard;

import com.microsoft.playwright.Page;
import lombok.Getter;
import pages.BasePage;

public class DashboardPage extends BasePage {
    @Getter
    private FilterPagelet filterPagelet;
    @Getter
    private BoardPagelet boardPagelet;

    public DashboardPage(Page page) {
        super(page);
        filterPagelet = new FilterPagelet(page);
        boardPagelet = new BoardPagelet(page);
    }

    public void createIssue() {
        page.locator("i.anticon-plus").click();
    }
    public void openSearchPage() {
        page.locator("i.anticon-search").click();
    }

}
