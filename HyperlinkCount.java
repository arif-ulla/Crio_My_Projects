package demo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HyperlinkCount {
    ChromeDriver driver;

    public HyperlinkCount() {
        System.out.println("Constructor: HyperlinkCount");
        WebDriverManager.chromedriver().timeout(30).setup();        
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }    

    public void testCase01() {
        
        System.out.println("Start Test case: testCase01");
        driver.get("https://in.bookmyshow.com/explore/home/chennai");
        
        // Wait for the search results to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Find all hyperlink elements on the page
        List<WebElement> hyperlinks = driver.findElements(By.tagName("a"));

        // Print the count of hyperlinks
        System.out.println("Total number of hyperlinks: " + hyperlinks.size());
        
    }

    public void endTest() {
        System.out.println("End Test case: HyperlinkCount");
        driver.close();
        driver.quit();
    }    
}
