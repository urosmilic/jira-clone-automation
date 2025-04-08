package tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.annotations.Test;

public class SearchTests extends BaseTest{
    @Test(groups = {"regression"})
    public void searchForTask() {
        String keyword = "Try leaving a comment on this issue.";
        pageContainer.dashboardPage.openSearchPage();
        page.getByPlaceholder("Search issues by summary, description...").locator("input")
                .pressSequentially(keyword, new Locator.PressSequentiallyOptions().setDelay(50));
        // The best way would be to check if all the items from the list have this text, but since search works for
        // the description too, that would be insufficient
        PlaywrightAssertions.assertThat(page.locator("issue-result").first()).containsText(keyword);
    }

}
