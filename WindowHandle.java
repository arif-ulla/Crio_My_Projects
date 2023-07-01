package demo;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowHandle {
    
    ChromeDriver driver;

    public WindowHandle() {
        System.out.println("Constructor: WindowHandle");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() throws IOException, InterruptedException {

        // Navigate to URL
        driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_win_open");

        // Switch to iframe
        driver.switchTo().frame("iframeResult");

        // Try it button
        WebElement tryItButton = driver.findElement(By.cssSelector("button"));
        tryItButton.click();

        Thread.sleep(3000);

        // Store the original window handle
        String originalWindowHandle = driver.getWindowHandle();

        // Switch to the new window
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        // Get the URL and title of the new window
        String newWindowUrl = driver.getCurrentUrl();
        String newWindowTitle = driver.getTitle();
        System.out.println("New window URL : " + newWindowUrl);
        System.out.println("New window title : " + newWindowTitle);

        // Take a screenshot
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        byte[] screenshotBytes = screenshot.getScreenshotAs(OutputType.BYTES);

        // Save the screenshot to a file (you can modify the file path and name as per your requirement)
        FileUtils.writeByteArrayToFile(new File("src\\main\\java\\Screenshots\\windowHandle.png"), screenshotBytes);

        // Close the new window
        driver.close();

        // Switch back to the original window
        driver.switchTo().window(originalWindowHandle);
    }
    
}
