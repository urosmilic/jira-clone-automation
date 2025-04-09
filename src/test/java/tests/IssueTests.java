package tests;

import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import models.Issue;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;

import static common.IssueType.BUG;
import static common.IssueType.STORY;
import static common.Priority.HIGHEST;
import static common.Priority.LOW;
import static utils.DateTimeHelper.getCurrentDateTimeString;

public class IssueTests extends BaseTest {
    @Test(groups = {"regression", "smoke"})
    @Description("Verify that user can delete an issue")
    @Feature("Edit/Delete issue")
    @Severity(SeverityLevel.CRITICAL)
    public void deleteIssue() {
        Issue issue = createIssue();
        pageContainer.dashboardPage.getBoardPagelet().openTicket(issue.getSummary());
        pageContainer.issuePage.deleteIssue();
        PlaywrightAssertions.assertThat(pageContainer.dashboardPage.getBoardPagelet().getTicket(issue.getSummary())).isHidden();
    }

    @Test(groups = {"regression"})
    @Description("Verify that user can change issue type")
    @Feature("Edit/Delete issue")
    @Severity(SeverityLevel.NORMAL)
    public void changeIssueType() {
        Issue issue = createIssue();
        pageContainer.dashboardPage.getBoardPagelet().openTicket(issue.getSummary());
        pageContainer.issuePage.changeType(BUG.toString());
        PlaywrightAssertions.assertThat(pageContainer.issuePage.getType()).containsText(BUG.toString());
    }

    @Test(groups = {"regression", "smoke"})
    @Description("Verify that user can change issue status")
    @Feature("Edit/Delete issue")
    @Severity(SeverityLevel.CRITICAL)
    public void changeIssueStatus() {
        Issue issue = createIssue();
        pageContainer.dashboardPage.getBoardPagelet().openTicket(issue.getSummary());
        pageContainer.issuePage.changeStatus("In Progress");
        PlaywrightAssertions.assertThat(pageContainer.issuePage.getStatus())
                .hasText("In Progress", new LocatorAssertions.HasTextOptions().setIgnoreCase(true));
    }

    @Test(groups = {"regression"})
    @Description("Verify that user can change issue priority")
    @Feature("Edit/Delete issue")
    @Severity(SeverityLevel.NORMAL)
    public void changeIssuePriority() {
        Issue issue = createIssue();
        pageContainer.dashboardPage.getBoardPagelet().openTicket(issue.getSummary());
        pageContainer.issuePage.changePriority(LOW.toString());
        PlaywrightAssertions.assertThat(pageContainer.issuePage.getPriority())
                .hasText(LOW.toString(), new LocatorAssertions.HasTextOptions().setIgnoreCase(true));
    }

    @Test(groups = {"regression"})
    @Description("Verify that user can change issue assignee")
    @Feature("Edit/Delete issue")
    @Severity(SeverityLevel.NORMAL)
    public void changeIssueAssignee() {
        Issue issue = createIssue();
        pageContainer.dashboardPage.getBoardPagelet().openTicket(issue.getSummary());
        pageContainer.issuePage.removeAllAssignees();
        pageContainer.issuePage.addAssignee("Iron Man");
        Assertions.assertThat(pageContainer.issuePage.getAssignees().allTextContents()
                .stream().map(String::trim).toList()).isEqualTo(List.of("Iron Man"));
    }

    @Test(groups = {"regression", "smoke"})
    @Description("Verify that user can add a comment on the issue")
    @Feature("Edit/Delete issue")
    @Severity(SeverityLevel.NORMAL)
    public void addComment() {
        Issue issue = createIssue();
        pageContainer.dashboardPage.getBoardPagelet().openTicket(issue.getSummary());
        pageContainer.issuePage.insertComment("First comment, automation test!");
        PlaywrightAssertions.assertThat(pageContainer.issuePage.getComments().last()).containsText("First comment, automation test!");
        pageContainer.issuePage.insertComment("Second comment, automation test!");
        PlaywrightAssertions.assertThat(pageContainer.issuePage.getComments().last()).containsText("Second comment, automation test!");
    }

    public Issue createIssue() {
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
        return issue;
    }
}
