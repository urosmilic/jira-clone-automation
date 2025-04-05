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
        return page.locator(STATUS_COLUMN)
                .filter(new Locator.FilterOptions().setHas(page.locator(String.format("div:has-text('%s')", column))))
                .locator(TASK_CARD).filter(new Locator.FilterOptions().setHas(page.locator(String.format("p:text-is('%s')", ticketSummary))));
    }

    public Locator getTicket(String ticketSummary) {
        return page.locator(TASK_CARD)
                .filter(new Locator.FilterOptions().setHas(page.locator(String.format("p:text-is('%s')", ticketSummary))));
    }

    public void openTicket(String ticketSummary) {
        getTicket(ticketSummary).click();
    }

}
