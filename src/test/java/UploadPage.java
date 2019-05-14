import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class UploadPage extends PageBasis {
    private static final String URL = "https://the-internet.herokuapp.com/upload";
    private static final String LOGIN_TITLE = "The Internet";
    private static final By INPUT_FILE = By.id("file-upload");
    private static final By BUTTON_UPLOAD = By.id("file-submit");
    private static final By SUCCESS_TEXT_MESSAGE = By.cssSelector("h3");
    private static final By SUCCESS_UPLOADED_FILE_NAME = By.id("uploaded-files");

    public UploadPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void open() {
        driver.get(URL);
        wait.until(titleIs(LOGIN_TITLE));
    }

    private WebElement inputFile() {
        WebElement inputFile = driver.findElement(INPUT_FILE);
        return inputFile;
    }

    private WebElement buttonUpload() {
        WebElement buttonUpload = driver.findElement(BUTTON_UPLOAD);
        return buttonUpload;
    }

    public void upload(String pathToFolder, String fileName) {
        inputFile().sendKeys(pathToFolder + fileName);
        buttonUpload().click();
    }

    private WebElement successTextMessage() {
        WebElement successTextMessage = driver.findElement(SUCCESS_TEXT_MESSAGE);
        return successTextMessage;
    }

    public String getTextOfSuccessMessage() {
        return successTextMessage().getText();
    }

    private WebElement successUploadedFileName() {
        WebElement successUploadedFileName = driver.findElement(SUCCESS_UPLOADED_FILE_NAME);
        return successUploadedFileName;
    }

    public String getTextOfSuccessUploadedFileName() {
        return successUploadedFileName().getText();
    }
}