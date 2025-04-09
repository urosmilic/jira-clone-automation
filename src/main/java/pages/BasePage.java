package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Mouse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.BoundingBox;
import com.microsoft.playwright.options.LoadState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePage {
    protected Page page;
    protected static final Logger log = LoggerFactory.getLogger(BasePage.class);
    public BasePage(Page page) {
        this.page = page;
    }

    public void waitForNetworkIdle() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void dragAndDrop(Locator source, Locator destination) {
        source.hover();
        page.mouse().down();
        BoundingBox sourceBox = source.boundingBox();
        page.mouse().move(sourceBox.x + 10, sourceBox.y + 10, new Mouse.MoveOptions().setSteps(5));
        BoundingBox targetBox = destination.boundingBox();
        page.mouse().move(targetBox.x + 10, targetBox.y + 10, new Mouse.MoveOptions().setSteps(10));
        page.mouse().up();
    }
}
