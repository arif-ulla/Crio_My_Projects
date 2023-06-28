package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

public class LinkedInAutomation {

    ChromeDriver driver;

    public LinkedInAutomation() {

        System.out.println("Constructor: LinkedInAutomation");
        WebDriverManager.chromedriver().timeout(30).setup();        
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void testCase01() {

        // Navigate to the LinkedIn login page
        driver.get("https://in.linkedin.com/");

        // Enter your email/phone and password in the login form
        WebElement emailInput = driver.findElement(By.id("session_key"));
        emailInput.sendKeys("your_email_or_phone");

        WebElement passwordInput = driver.findElement(By.id("session_password"));
        passwordInput.sendKeys("your_password");

        // Submit the login form
        WebElement signInButton = driver.findElement(By.xpath("//button[@type='submit']"));
        signInButton.click();

        // Wait for the home page to load
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Print the count of "Who's viewed your profile"
        WebElement profileViewsElement = driver.findElement(By.xpath("//a[contains(@href,'/in/')]/div/div/h4[contains(text(),'Who')]"));
        String profileViewsCount = profileViewsElement.getText();
        System.out.println("Who's viewed your profile: " + profileViewsCount);

        // Print the count of "Impressions of your post"
        WebElement postImpressionsElement = driver.findElement(By.xpath("//a[contains(@href,'/in/')]/div/div/h4[contains(text(),'Impressions')]"));
        String postImpressionsCount = postImpressionsElement.getText();
        System.out.println("Impressions of your post: " + postImpressionsCount);

        // Create a post
        WebElement createPostInput = driver.findElement(By.xpath("//span[text()='Start a post']"));
        createPostInput.sendKeys("This is my post text.");

        // Share with "Connections only"
        WebElement shareButton = driver.findElement(By.xpath("//button[contains(@data-control-name,'share')]"));
        shareButton.click();

        WebElement connectionsOnlyOption = driver.findElement(By.xpath("//span[contains(text(),'Connections only')]"));
        connectionsOnlyOption.click();

        // Confirm if the post is created
        WebElement postConfirmationElement = driver.findElement(By.xpath("//span[contains(text(),'Post')]"));
        String postConfirmationText = postConfirmationElement.getText();

        if (postConfirmationText.contains("Posted")) {
            System.out.println("Post successfully created and shared.");
        } else {
            System.out.println("Post creation and sharing failed.");
        }  
    }

    public void endTest() {
        System.out.println("End Test case: LinkedInAutomation");
        driver.close();
        driver.quit();
    }    
}
