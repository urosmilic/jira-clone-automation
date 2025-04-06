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

    @BeforeMethod
    public void playwrightSetup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true).setArgs(List.of("--start-maximized")));
        browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        page = browserContext.newPage();
        pageContainer = new PageContainer(page);
    }

    @AfterMethod
    public void closePlaywright() {
        browser.close();
        playwright.close();
    }
}
