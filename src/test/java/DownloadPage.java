import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class DownloadPage extends PageBasis {
    private static final String URL = "https://the-internet.herokuapp.com/download";
    private static final String DOWNLOAD_PAGE_TITLE = "The Internet";
    private static final By LINK_THE_FIRST_FILE_TO_DOWNLOAD = By.cssSelector("h3 ~ a");

    public DownloadPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void open() {
        driver.get(URL);
        wait.until(titleIs(DOWNLOAD_PAGE_TITLE));
    }

    private WebElement linkToTheFirstFileToDownload() {
        WebElement linkToTheFirstFileToDownload = driver.findElements(LINK_THE_FIRST_FILE_TO_DOWNLOAD).get(0);
        return linkToTheFirstFileToDownload;
    }

    public String download() {
        linkToTheFirstFileToDownload().click();
        return linkToTheFirstFileToDownload().getText();
    }
}