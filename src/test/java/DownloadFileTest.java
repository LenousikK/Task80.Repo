import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DownloadFileTest extends BeforeAfter {
    @Test
    public void successfulDownload() throws InterruptedException {
        DownloadPage downloadPage = new DownloadPage(driver, wait);
        downloadPage.open();
        String downloadedFileName = downloadPage.download();
        Thread.sleep(5000);
        File folder = new File("src/test/resources/");
        File[] arrayListOfFiles = folder.listFiles();
        boolean isFileEqualToCartName = false;
        for (int i = 0; i < arrayListOfFiles.length; i++) {
            if (arrayListOfFiles[i].isFile()) {
                String fileNameToCompare = arrayListOfFiles[i].getName();
                if (fileNameToCompare.equals(downloadedFileName)) {
                    isFileEqualToCartName = true;
                    break;
                }
            }
        }
        assertTrue(isFileEqualToCartName, ("File " + downloadedFileName + " is downloaded"));
    }
}