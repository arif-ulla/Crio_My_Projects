package demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NestedFrames {

    ChromeDriver driver;

    public NestedFrames() {
        System.out.println("Constructor: NestedFrames");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: NestedFrames");
        driver.close();
        driver.quit();

    }

    public void testCase01() {
        
        // Visit the website
        driver.get("https://the-internet.herokuapp.com/nested_frames");

        // Switch to the top frame
        driver.switchTo().frame("frame-top");
        
        // Switch to the left frame
        driver.switchTo().frame("frame-left");

        // Print the text in the left frame
        WebElement leftFrameText = driver.findElement(By.tagName("body"));
        System.out.println("Left Frame Text: " + leftFrameText.getText());

        driver.switchTo().parentFrame();

        // Switch to the middle frame
        driver.switchTo().frame("frame-middle");

        // Print the text in the middle frame
        WebElement middleFrameText = driver.findElement(By.tagName("body"));
        System.out.println("Middle Frame Text: " + middleFrameText.getText());

        driver.switchTo().parentFrame();

        // Switch to the right frame
        driver.switchTo().frame("frame-right");

        // Print the text in the right frame
        WebElement rightFrameText = driver.findElement(By.tagName("body"));
        System.out.println("Right Frame Text: " + rightFrameText.getText());

        // Switch back to the default content
        driver.switchTo().defaultContent();

        // Switch to the bottom frame
        driver.switchTo().frame("frame-bottom");

        // Print the text in the bottom frame
        WebElement bottomFrameText = driver.findElement(By.tagName("body"));
        System.out.println("Bottom Frame Text: " + bottomFrameText.getText());
    }
    
}
