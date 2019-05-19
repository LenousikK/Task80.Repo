import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class UploadPage extends PageBasis {
    private static final String URL = "https://the-internet.herokuapp.com/upload";
    private static final String UPLOAD_PAGE_TITLE = "The Internet";
    private static final By INPUT_FILE = By.id("file-upload");
    private static final By BUTTON_UPLOAD = By.id("file-submit");
    private static final By SUCCESS_TEXT_MESSAGE = By.cssSelector("h3");
    private static final By SUCCESS_UPLOADED_FILE_NAME = By.id("uploaded-files");

    public UploadPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void open() {
        driver.get(URL);
        wait.until(titleIs(UPLOAD_PAGE_TITLE));
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_TEXT_MESSAGE));
        return successUploadedFileName().getText();
    }

    public void uploadFileWithRobot(String pathToFolder, String fileName) {
        inputFile().click();
        String fileLocation = pathToFolder + fileName;
        StringSelection stringSelection = new StringSelection(fileLocation);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(150);
        robot.keyRelease(KeyEvent.VK_ENTER);
        buttonUpload().click();
    }
}