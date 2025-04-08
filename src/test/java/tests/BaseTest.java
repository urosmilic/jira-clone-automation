package tests;

import com.microsoft.playwright.*;
import common.PageContainer;
import lombok.Getter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.List;

public class BaseTest {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    @Getter
    Page page;
    PageContainer pageContainer;

    @BeforeMethod(alwaysRun = true)
    public void playwrightSetup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true).setArgs(List.of("--start-maximized")));
        browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        page = browserContext.newPage();
        pageContainer = new PageContainer(page);
        page.navigate("https://jira.trungk18.com/project/board");
    }

    @AfterMethod(alwaysRun = true)
    public void closePlaywright() {
        page.close();
        browserContext.close();
        browser.close();
        playwright.close();
    }
}
