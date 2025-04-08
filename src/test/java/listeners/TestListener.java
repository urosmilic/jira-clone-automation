package listeners;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;
import utils.DateTimeHelper;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        Page page = ((BaseTest) currentClass).getPage();

        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));

        // Allure attachment
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));

        // Save into target package
        try {
            String testName = result.getMethod().getMethodName();
            File screenshotDir = new File("target/screenshots");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }
            File screenshotFile = new File(screenshotDir, testName + DateTimeHelper.getCurrentDateTimeString() + ".png");
            try (FileOutputStream fos = new FileOutputStream(screenshotFile)) {
                fos.write(screenshot);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
