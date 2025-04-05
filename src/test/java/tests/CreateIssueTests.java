package tests;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import models.Issue;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static common.IssueType.*;
import static common.Priority.*;
import static utils.DateTimeHelper.getCurrentDateTimeString;

public class CreateIssueTests extends BaseTest {

    @BeforeMethod
    public void navigateToWebsite() {
        page.navigate("https://jira.trungk18.com/project/board");
    }

    @Test
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

    @Test
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

    @Test
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
        Assertions.assertThat(pageContainer.issuePage.getType()).isEqualTo(String.valueOf(issue.getType()));
        Assertions.assertThat(pageContainer.issuePage.getSummary()).isEqualTo(issue.getSummary());
        Assertions.assertThat(pageContainer.issuePage.getDescription()).isEqualTo(issue.getDescription());
        Assertions.assertThat(pageContainer.issuePage.getPriority()).isEqualTo(String.valueOf(issue.getPriority()));
        Assertions.assertThat(pageContainer.issuePage.getStatus()).isEqualTo("Backlog");
        Assertions.assertThat(pageContainer.issuePage.getReporter()).isEqualTo(issue.getReporter());
        Assertions.assertThat(pageContainer.issuePage.getAssignees()).isEqualTo(issue.getAssignees());
    }
}
