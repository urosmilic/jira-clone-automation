package tests;

import com.microsoft.playwright.*;
import common.PageContainer;
import lombok.Getter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

import java.util.List;

public class BaseTest {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    @Getter
    Page page;
    PageContainer pageContainer;
    ConfigReader configReader = new ConfigReader();

    @BeforeMethod(alwaysRun = true)
    public void playwrightSetup() {
        playwright = Playwright.create();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions();
        launchOptions.setArgs(List.of("--start-maximized"));
        if (Boolean.parseBoolean(configReader.getProperty("browserHeadless"))) {
            launchOptions.setHeadless(true);
            contextOptions.setViewportSize(1920, 1080);
        } else {
            contextOptions.setViewportSize(null);
            launchOptions.setHeadless(false);
        }
        switch (configReader.getProperty("browser")) {
            case "chrome" -> browser = playwright.chromium().launch(launchOptions.setChannel("chrome"));
            case "edge" -> browser = playwright.chromium().launch(launchOptions.setChannel("msedge"));
            case "firefox" -> browser = playwright.firefox().launch(launchOptions);
            case "webkit" -> browser = playwright.chromium().launch(launchOptions);
            default -> throw new IllegalArgumentException("Wrong browser type choosed!");
        }
        browserContext = browser.newContext(contextOptions);
        page = browserContext.newPage();
        pageContainer = new PageContainer(page);
        page.navigate(configReader.getProperty("baseUrl"));
    }

    @AfterMethod(alwaysRun = true)
    public void closePlaywright() {
        page.close();
        browserContext.close();
        browser.close();
        playwright.close();
    }
}
