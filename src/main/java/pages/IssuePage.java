package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class IssuePage extends BasePage {
    private static final String DROPDOWN_OPTION = "li.ant-dropdown-menu-item";

    public IssuePage(Page page) {
        super(page);
    }

    public Locator getSummary() {
        return page.locator("issue-title textarea");
    }

    public Locator getDescription() {
        return page.locator("div.ql-editor");
    }

    public Locator getPriority() {
        return page.locator("issue-priority button");
    }

    public Locator getStatus() {
        return page.locator("issue-status button");
    }

    public Locator getReporter() {
        return page.locator("issue-reporter button");
    }

    public Locator getAssignees() {
        return page.locator("issue-assignees button");
    }

    public Locator getType() {
        return page.locator("issue-type button");
    }

    public Locator getComments() {
        return page.locator("issue-comment.ng-star-inserted");
    }

    public void changeStatus(String status) {
        getStatus().click();
        page.locator(DROPDOWN_OPTION).getByText(status).click();
    }

    public void changeType(String type) {
        getType().click();
        page.locator(DROPDOWN_OPTION).getByText(type).click();
    }

    public void addAssignee(String assignee) {
        page.locator("a").getByText("Add Assignee").click();
        page.locator(DROPDOWN_OPTION).getByText(assignee).click();
        page.waitForCondition(() -> getAssignees().count() > 0);
    }

    public void removeAllAssignees() {
        page.waitForCondition(() -> getAssignees().count() > 0);
        if (page.locator("issue-assignees button").count() > 0) {
            int initialCount = getAssignees().count();
            for (int i = 0; i <= getAssignees().count(); i++) {
                getAssignees().locator("[title='Remove user'] use").first().click();
                int finalInitialCount = initialCount - i;
                page.waitForCondition(() -> getAssignees().count() < finalInitialCount);
            }
        }
        page.waitForCondition(() -> getAssignees().count() == 0);
    }

    public void changePriority(String priority) {
        getPriority().click();
        page.locator(DROPDOWN_OPTION).getByText(priority, new Locator.GetByTextOptions().setExact(true)).click();
    }

    public void insertComment(String comment) {
        page.getByPlaceholder("Add a comment").click();
        page.getByPlaceholder("Add a comment").fill(comment);
        page.getByRole(AriaRole.BUTTON).getByText("Save").click();
    }

    public void deleteIssue() {
        page.locator("[icon='trash']").click();
        page.locator("button:has-text('Delete')").click();
    }

    public void closeModal() {
        page.locator("[icon='times']").click();
    }
}
