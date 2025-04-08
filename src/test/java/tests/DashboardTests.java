package tests;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import models.Issue;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;

import static common.IssueType.BUG;
import static common.Priority.*;
import static utils.DateTimeHelper.getCurrentDateTimeString;

public class DashboardTests extends BaseTest {
    @Test(groups = {"regression", "smoke"})
    public void changeIssueStatusAndVerifyPositionOnBoard() {
        Issue issue = createIssue();

        PlaywrightAssertions.assertThat(pageContainer.dashboardPage.getBoardPagelet().findTicketWithinColumn(
                issue.getSummary(), "Backlog")).isVisible();

        pageContainer.dashboardPage.getBoardPagelet().openTicket(issue.getSummary());
        pageContainer.issuePage.changeStatus("In progress");
        pageContainer.issuePage.closeModal();
        PlaywrightAssertions.assertThat(pageContainer.dashboardPage.getBoardPagelet().findTicketWithinColumn(
                issue.getSummary(), "In progress")).isVisible();

        pageContainer.dashboardPage.getBoardPagelet().openTicket(issue.getSummary());
        pageContainer.issuePage.changeStatus("Done");
        pageContainer.issuePage.closeModal();
        PlaywrightAssertions.assertThat(pageContainer.dashboardPage.getBoardPagelet().findTicketWithinColumn(
                issue.getSummary(), "Done")).isVisible();

        pageContainer.dashboardPage.getBoardPagelet().openTicket(issue.getSummary());
        pageContainer.issuePage.changeStatus("Selected for Development");
        pageContainer.issuePage.closeModal();
        PlaywrightAssertions.assertThat(pageContainer.dashboardPage.getBoardPagelet().findTicketWithinColumn(
                issue.getSummary(), "Selected for Development")).isVisible();
    }

    @Test(groups = {"regression"})
    public void changeIssueStatusByDragAndDrop() {
        Issue issue = createIssue();

        PlaywrightAssertions.assertThat(pageContainer.dashboardPage.getBoardPagelet().findTicketWithinColumn(
                issue.getSummary(), "Backlog")).isVisible();

        pageContainer.dashboardPage.getBoardPagelet().moveIssueToColumn(issue.getSummary(), "In Progress");
        PlaywrightAssertions.assertThat(pageContainer.dashboardPage.getBoardPagelet().findTicketWithinColumn(
                issue.getSummary(), "In progress")).isVisible();

        pageContainer.dashboardPage.getBoardPagelet().moveIssueToColumn(issue.getSummary(), "Done");
        PlaywrightAssertions.assertThat(pageContainer.dashboardPage.getBoardPagelet().findTicketWithinColumn(
                issue.getSummary(), "Done")).isVisible();

        pageContainer.dashboardPage.getBoardPagelet().moveIssueToColumn(issue.getSummary(), "Selected for Development");
        PlaywrightAssertions.assertThat(pageContainer.dashboardPage.getBoardPagelet().findTicketWithinColumn(
                issue.getSummary(), "Selected for Development")).isVisible();
    }

    @Test(groups = {"regression"})
    public void filteringByIssueSummary() {
        pageContainer.dashboardPage.getFilterPagelet().filterIssuesByKeyword("Angular");
        Assertions.assertThat(pageContainer.dashboardPage.getBoardPagelet().getTicket().count())
                .isEqualTo(pageContainer.dashboardPage.getBoardPagelet().getTicket("Angular").count());
    }

    public Issue createIssue() {
        Issue issue = Issue.builder()
                .type(BUG)
                .summary(String.format("AQA-%s - Short summary text!", getCurrentDateTimeString()))
                .description("AQA description for the test!")
                .priority(LOWEST)
                .reporter("Iron Man")
                .assignees(List.of("Captain"))
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
