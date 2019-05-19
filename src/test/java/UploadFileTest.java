import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UploadFileTest extends BeforeAfter {
    private static final String PATH_TO_FOLDER_ON_DISK_OR_IN_PROJECT = System.getProperty("user.dir") + "\\src\\test\\resources\\";
    private static final String FILE_NAME = "image.jpg";
    private static final String EXPECTED_SUCCESS_TEXT_MESSAGE = "File Uploaded!";

    @Test
    public void successFileUpload() {
        UploadPage uploadPage = new UploadPage(driver, wait);
        uploadPage.open();
        uploadPage.upload(PATH_TO_FOLDER_ON_DISK_OR_IN_PROJECT, FILE_NAME);
        assertAll("File was successfully uploaded!",
                () -> assertEquals(EXPECTED_SUCCESS_TEXT_MESSAGE, uploadPage.getTextOfSuccessMessage()),
                () -> assertEquals(FILE_NAME, uploadPage.getTextOfSuccessUploadedFileName()));
    }
}