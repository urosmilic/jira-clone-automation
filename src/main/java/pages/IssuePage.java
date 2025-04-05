package pages;

import com.microsoft.playwright.Page;

import java.util.List;

public class IssuePage extends BasePage{
    private static final String SUMMARY = "issue-title textarea";
    public IssuePage(Page page) {
        super(page);
    }

    public String getSummary() {
        return page.locator(SUMMARY).inputValue();
    }

    public String getDescription() {
        return page.locator("div.ql-editor").textContent();
    }

    public String getPriority() {
        return page.locator("issue-priority button").textContent().trim();
    }

    public String getStatus() {
        return page.locator("issue-status button").textContent().trim();
    }

    public String getReporter() {
        return page.locator("issue-reporter button").textContent().trim();
    }

    public List<String> getAssignees() {
        return page.locator("issue-assignees button").allTextContents().stream().map(String::trim).toList();
    }

    public String getType() {
        return page.locator("issue-type button").textContent().trim().split("-")[0];
    }
}
