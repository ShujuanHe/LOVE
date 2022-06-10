package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GuestCheckOutPage {

    WebDriver driver;

    public GuestCheckOutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void guestCheckOutSteps() throws InterruptedException {

       /* WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //WebElement addToCart = driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button"));
        WebElement addToCart = driver.findElement(By.xpath("//*[@id=\'add_to_cart\']/button"));
        addToCart.click();

        //Switch to inner frame
        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\'layer_cart\']/div[1]/div[2]/div[4]/a")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Proceed to checkout")));*/



        //Step3 Click on Proceed to checkout
        WebElement proceedToCheckout = driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a"));
        proceedToCheckout.click();
       // wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Proceed to checkout")));


        //Step4 Click on Proceed to checkout
        WebElement proceedToCheckout2 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]"));
        proceedToCheckout2.click();


    }


}
