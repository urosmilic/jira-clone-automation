package pages.dashboard;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;
import pages.BasePage;

public class DashboardPage extends BasePage {
    private final Locator addIssueButton;
    @Getter
    private FilterPagelet filterPagelet;
    @Getter
    private BoardPagelet boardPagelet;

    public DashboardPage(Page page) {
        super(page);
        filterPagelet = new FilterPagelet(page);
        boardPagelet = new BoardPagelet(page);
        this.addIssueButton = page.locator("i.anticon-plus");
    }

    public void createIssue() {
        addIssueButton.click();
    }


}
