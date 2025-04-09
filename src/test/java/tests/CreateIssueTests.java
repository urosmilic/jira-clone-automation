package tests;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import models.Issue;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;

import static common.IssueType.*;
import static common.Priority.*;
import static utils.DateTimeHelper.getCurrentDateTimeString;

public class CreateIssueTests extends BaseTest {
    @Test(groups = {"regression", "smoke"})
    @Description("Verify that user can create a bug")
    @Feature("Issue creation")
    @Severity(SeverityLevel.CRITICAL)
    public void createBug() {
        Issue issue = Issue.builder()
                .type(BUG)
                .summary(String.format("AQA-%s - Short summary text!", getCurrentDateTimeString()))
                .description("AQA description for the test!")
                .priority(HIGHEST)
                .reporter("Iron Man")
                .assignees(List.of("Spider Man", "Captain", "Iron Man", "Thor"))
                .build();

        pageContainer.dashboardPage.createIssue();
        pageContainer.createIssuePage.selectIssueType(String.valueOf(issue.getType()));
        pageContainer.createIssuePage.choosePriority(String.valueOf(issue.getPriority()));
        pageContainer.createIssuePage.enterSummary(issue.getSummary());
        pageContainer.createIssuePage.enterDescription(issue.getDescription());
        pageContainer.createIssuePage.selectReporter(issue.getReporter());
        pageContainer.createIssuePage.addAssignees(issue.getAssignees());
        pageContainer.createIssuePage.createIssue();
        assertThatIssueIsCreated(issue);
        pageContainer.dashboardPage.getBoardPagelet().openTicket(issue.getSummary());
        assertCreatedIssueAttributes(issue);
    }

    @Test(groups = {"regression", "smoke"})
    @Description("Verify that user can create a task")
    @Feature("Issue creation")
    @Severity(SeverityLevel.CRITICAL)
    public void createTask() {
        Issue issue = Issue.builder()
                .type(TASK)
                .summary(String.format("AQA-%s - Short summary text!", getCurrentDateTimeString()))
                .description("AQA description for the test!")
                .priority(LOW)
                .reporter("Iron Man")
                .assignees(List.of("Spider Man", "Captain"))
                .build();

        pageContainer.dashboardPage.createIssue();
        pageContainer.createIssuePage.selectIssueType(String.valueOf(issue.getType()));
        pageContainer.createIssuePage.choosePriority(String.valueOf(issue.getPriority()));
        pageContainer.createIssuePage.enterSummary(issue.getSummary());
        pageContainer.createIssuePage.enterDescription(issue.getDescription());
        pageContainer.createIssuePage.selectReporter(issue.getReporter());
        pageContainer.createIssuePage.addAssignees(issue.getAssignees());
        pageContainer.createIssuePage.createIssue();
        assertThatIssueIsCreated(issue);
        pageContainer.dashboardPage.getBoardPagelet().openTicket(issue.getSummary());
        assertCreatedIssueAttributes(issue);
    }

    @Test(groups = {"regression", "smoke"})
    @Description("Verify that user can create a story")
    @Feature("Issue creation")
    @Severity(SeverityLevel.CRITICAL)
    public void createStory() {
        Issue issue = Issue.builder()
                .type(STORY)
                .summary(String.format("AQA-%s - Short summary text!", getCurrentDateTimeString()))
                .description("AQA description for the test!")
                .priority(HIGHEST)
                .reporter("Spider Man")
                .assignees(List.of("Spider Man"))
                .build();

        pageContainer.dashboardPage.createIssue();
        pageContainer.createIssuePage.selectIssueType(String.valueOf(issue.getType()));
        pageContainer.createIssuePage.choosePriority(String.valueOf(issue.getPriority()));
        pageContainer.createIssuePage.enterSummary(issue.getSummary());
        pageContainer.createIssuePage.enterDescription(issue.getDescription());
        pageContainer.createIssuePage.selectReporter(issue.getReporter());
        pageContainer.createIssuePage.addAssignees(issue.getAssignees());
        pageContainer.createIssuePage.createIssue();
        assertThatIssueIsCreated(issue);
        pageContainer.dashboardPage.getBoardPagelet().openTicket(issue.getSummary());
        assertCreatedIssueAttributes(issue);
    }

    private void assertThatIssueIsCreated(Issue issue) {
        PlaywrightAssertions.assertThat(pageContainer.dashboardPage.getBoardPagelet()
                .findTicketWithinColumn(issue.getSummary(), "Backlog")).isVisible();
    }

    public void assertCreatedIssueAttributes(Issue issue) {
        PlaywrightAssertions.assertThat(pageContainer.issuePage.getType()).containsText(String.valueOf(issue.getType()));
        PlaywrightAssertions.assertThat(pageContainer.issuePage.getSummary()).hasValue(issue.getSummary());
        PlaywrightAssertions.assertThat(pageContainer.issuePage.getDescription()).hasText(issue.getDescription());
        PlaywrightAssertions.assertThat(pageContainer.issuePage.getPriority()).hasText(String.valueOf(issue.getPriority()));
        PlaywrightAssertions.assertThat(pageContainer.issuePage.getStatus()).hasText("Backlog");
        PlaywrightAssertions.assertThat(pageContainer.issuePage.getReporter()).hasText(issue.getReporter());
        Assertions.assertThat(pageContainer.issuePage.getAssignees().allTextContents()
                .stream().map(String::trim).toList()).isEqualTo(issue.getAssignees());
    }
}
