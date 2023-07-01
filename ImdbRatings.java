package demo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ImdbRatings {

    ChromeDriver driver;

    public ImdbRatings() {
        System.out.println("Constructor: ImdbRatings");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: ImdbRatings");
        driver.close();
        driver.quit();

    }

    public void testCase01() {
        
        // Visit the website
        driver.get("https://www.imdb.com/chart/top");

        //*** What is the highest rated movie on IMDb?

        // Get highest rated movie on IMDb
        WebElement highestRatedMovieElement = driver.findElement(By.xpath("//tbody[@class='lister-list']/tr[1]/td[2]/a"));

        // Get the text of the highest rated movie
        String highestRatedMovie = highestRatedMovieElement.getText();

        // Print the highest rated movie
        System.out.println("Highest rated movie: " + highestRatedMovie);

        //*** How many movies are included in the table?

        // Find all the rows in the table
        WebElement table = driver.findElement(By.xpath("//tbody[@class='lister-list']"));
        int movieCount = table.findElements(By.tagName("tr")).size();

        // Print the number of movies in the table
        System.out.println("Number of movies: " + movieCount);

        // *** What is the oldest movie on the list?

        // Find the table rows
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        // Initialize variables to store oldest movie information
        String oldestMovieTitle = "";
        int oldestMovieYear = Integer.MAX_VALUE;

        // Iterate through the rows to find the oldest movie
        for (WebElement row : rows) {
            // Find the release year element for each row
            WebElement yearElement = row.findElement(By.xpath(".//span[@class='secondaryInfo']"));
            String yearText = yearElement.getText().replaceAll("[()]", "");
            int year = Integer.parseInt(yearText);

            // Find the movie title for each row
            WebElement titleElement = row.findElement(By.tagName("a"));
            String title = titleElement.getText();

            // Compare release year with the oldest movie found so far
            if (year < oldestMovieYear) {
                oldestMovieYear = year;
                oldestMovieTitle = title;
            }
        }

        // Print the oldest movie
        System.out.println("Oldest movie: " + oldestMovieTitle + " (" + oldestMovieYear + ")");

        //*** What is the most recent movie on the list?
        
        // Initialize variables to store most recent movie information
        String recentMovieTitle = "";
        int recentMovieYear = Integer.MIN_VALUE;

        // Iterate through the rows to find the most recent movie
        for (WebElement row : rows) {
            // Find the release year element for each row
            WebElement yearElement = row.findElement(By.xpath(".//span[@class='secondaryInfo']"));
            String yearText = yearElement.getText().replaceAll("[()]", "");
            int year = Integer.parseInt(yearText);

            // Find the movie title for each row
            WebElement titleElement = row.findElement(By.tagName("a"));
            String title = titleElement.getText();

            // Compare release year with the most recent movie found so far
            if (year > recentMovieYear) {
                recentMovieYear = year;
                recentMovieTitle = title;
            }
        }

        // Print the most recent movie
        System.out.println("Most recent movie: " + recentMovieTitle + " (" + recentMovieYear + ")");

        //*** Which movie has the most user ratings?
        // Find the table rows
        // WebElement table = driver.findElement(By.xpath("//tbody[@class='lister-list']"));
        // List<WebElement> rows = table.findElements(By.tagName("tr"));

        // Initialize variables to store movie with most user ratings
        String movieWithMostRatings = "";
        int mostRatings = Integer.MIN_VALUE;

        // Iterate through the rows to find the movie with the most user ratings
        for (WebElement row : rows) {
            // Find the user ratings element for each row
            WebElement ratingsElement = row.findElement(By.xpath(".//strong/span[@name='ir']"));
            int ratings = Integer.parseInt(ratingsElement.getAttribute("data-value"));

            // Find the movie title for each row
            WebElement titleElement = row.findElement(By.tagName("a"));
            String title = titleElement.getText();

            // Compare user ratings with the most ratings found so far
            if (ratings > mostRatings) {
                mostRatings = ratings;
                movieWithMostRatings = title;
            }
        }

        // Print the movie with the most user ratings
        System.out.println("Movie with the most ratings: " + movieWithMostRatings + " (" + mostRatings + " ratings)");

    }
    
}
