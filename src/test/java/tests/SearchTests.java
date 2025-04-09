package tests;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

public class SearchTests extends BaseTest{
    @Test(groups = {"regression"})
    @Description("Verify that user can search for the issue using search menu")
    @Feature("Searching")
    @Severity(SeverityLevel.NORMAL)
    public void searchingForIssue() {
        String keyword = "Try leaving a comment on this issue.";
        pageContainer.dashboardPage.openSearchPage();
        pageContainer.searchPage.searchingUsingKeyword(keyword);
        // The best way would be to check if all the items from the list have this text, but since search works for
        // the description too, that would be insufficient
        PlaywrightAssertions.assertThat(pageContainer.searchPage.getListOfIssuesFromTheMenu().first()).containsText(keyword);
    }

}
