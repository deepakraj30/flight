package training.training;
//import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class FlightSearchTest {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\a819202\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver =new ChromeDriver();
     try {
          driver.get("https://www.makemytrip.com");
          closePopUpIfPresent(driver);
       WebElement fromCity = driver.findElement(By.xpath("//input[@id='fromCity']"));
           fromCity.click();
            fromCity.sendKeys("Mumbai");
       WebElement fromCityOption = driver.findElement(By.xpath("//p[contains(text(), 'Mumbai, India')]"));
          fromCityOption.click();
            WebElement toCity = driver.findElement(By.xpath("//input[@id='toCity']"));
           toCity.click();
           toCity.sendKeys("Bangalore");
           WebElement toCityOption = driver.findElement(By.xpath("//p[contains(text(), 'Bangalore, India')]"));
            toCityOption.click();
         selectTodaysDate(driver);
      WebElement searchButton = driver.findElement(By.xpath("//a[@class='primaryBtn font24 latoBold widgetSearchBtn ']"));
         searchButton.click();       
      WebDriverWait wait = new WebDriverWait(driver, 5);
       List<WebElement> flights = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='splitVw-sctn pull-left']")));
     List<WebElement> flights1 = driver.findElements(By.xpath("//div[@class='splitVw-sctn pull-left']"));
         for (WebElement flight : flights1) {
           System.out.println(flight.getText());
           }
        } finally {
           driver.quit();
        }
    }

    private static void closePopUpIfPresent(WebDriver driver) {
        try {
            WebElement popup = driver.findElement(By.xpath("//p[@data-cy='outsideModal']"));
            popup.click();
        } catch (Exception e) {
        	  e.printStackTrace();
        }
    }
 private static void selectTodaysDate(WebDriver driver) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, d MMM, yyyy");
        String formattedDate = today.format(formatter);
        WebElement dateInput = driver.findElement(By.xpath("//div[@class='DayPicker-Day' and @aria-label='" + formattedDate + "']"));
        dateInput.click();
    }

}


