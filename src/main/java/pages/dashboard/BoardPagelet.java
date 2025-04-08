package pages.dashboard;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.BasePage;

public class BoardPagelet extends BasePage {
    private static final String STATUS_COLUMN = "div.status-list";
    private static final String TASK_CARD = "div.issue";

    public BoardPagelet(Page page) {
        super(page);
    }

    public Locator findTicketWithinColumn(String ticketSummary, String column) {
        page.waitForCondition(() -> getTicket(ticketSummary).count() <= 1);
        return getColumn(column).locator(TASK_CARD)
                .filter(new Locator.FilterOptions().setHas(page.locator(String.format("p:text-is('%s')", ticketSummary))));
    }

    public Locator getTicket(String ticketSummary) {
        return getTicket()
                .filter(new Locator.FilterOptions().setHas(page.locator(String.format("p:has-text('%s')", ticketSummary))));
    }

    public Locator getTicket() {
        return page.locator(TASK_CARD);
    }

    public void openTicket(String ticketSummary) {
        getTicket(ticketSummary).click();
    }

    public Locator getColumn(String column) {
        return page.locator(STATUS_COLUMN)
                .filter(new Locator.FilterOptions().setHas(page.locator(String.format("div:has-text('%s')", column))))
                .locator(".cdk-drop-list");
    }

    public void moveIssueToColumn(String issueSummary, String column) {
        dragAndDrop(getTicket(issueSummary), getColumn(column));
    }
}
