package demo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BookMyShow {

    ChromeDriver driver;

    public BookMyShow() {
        System.out.println("Constructor: BookMyShow");
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

    public void testCase01() {
        
        // Navigate to the website
        driver.get("https://in.bookmyshow.com/explore/home/chennai");

        // Find the image URLs for all the "Recommended Movies"
        List<WebElement> recommendedMovies = driver.findElements(By.xpath("//div[@data-id='recommended_movies']//img"));
        for (WebElement movie : recommendedMovies) {
            String imageUrl = movie.getAttribute("src");
            System.out.println("Image URL: " + imageUrl);
        }

        // Print the Name & Language of the 2nd item in the "Premiere" list
        WebElement premiereMovie = driver.findElement(By.xpath("//div[@data-filter-id='premiere']//div[@class='__name']"));
        String movieName = premiereMovie.getText();
        WebElement premiereLanguage = driver.findElement(By.xpath("//div[@data-filter-id='premiere']//div[@class='__language']"));
        String movieLanguage = premiereLanguage.getText();
        System.out.println("Premiere Movie Name: " + movieName);
        System.out.println("Premiere Movie Language: " + movieLanguage);
    }
    
}
