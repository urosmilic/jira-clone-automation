package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SearchPage extends BasePage{
    public SearchPage(Page page) {
        super(page);
    }

    public void searchingUsingKeyword(String keyword) {
        page.getByPlaceholder("Search issues by summary, description...").locator("input")
                .pressSequentially(keyword, new Locator.PressSequentiallyOptions().setDelay(100));
    }

    public Locator getListOfIssuesFromTheMenu() {
        return page.locator("issue-result");
    }

}
