package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;
import java.util.regex.Pattern;

public class CreateIssuePage extends BasePage {
    private static final String FORM_GROUP_DIV = "div.form-group";
    private static final String DROPDOWN_ITEM = "nz-option-item";

    public CreateIssuePage(Page page) {
        super(page);
    }

    public void clickOnDropdown(String label) {
        page.locator(FORM_GROUP_DIV)
                .filter(new Locator.FilterOptions().setHas(page.locator(String.format("label:has-text('%s')", label))))
                .locator(".ant-select").click();
    }

    public void selectDropdownItem(String item) {
        page.locator(DROPDOWN_ITEM + String.format(":has-text('%s')", item)).first().click();
    }

    public void selectIssueType(String type) {
        clickOnDropdown("Issue type");
        selectDropdownItem(type);
    }

    public void choosePriority(String priority) {
        clickOnDropdown("Issue priority");
        page.locator(DROPDOWN_ITEM).filter(new Locator.FilterOptions().setHasText(Pattern.compile(String.format("^%s$", priority)))).click();
    }

    public void enterSummary(String summary) {
        page.locator("input[formcontrolname='title']").fill(summary);
    }

    public void enterDescription(String description) {
        page.locator(".ql-editor").fill(description);
    }

    public void createIssue() {
        page.locator("button").filter(new Locator.FilterOptions().setHasText("Create Issue")).click();
    }

    public void selectReporter(String reporter) {
        clickOnDropdown("Reporter");
        selectDropdownItem(reporter);
    }

    public void addAssignees(List<String> assignees) {
        clickOnDropdown("Assignees");
        for (String assignee : assignees) {
            selectDropdownItem(assignee);
        }
        // Clicking again to close the dropdown
        clickOnDropdown("Assignees");
    }

}
