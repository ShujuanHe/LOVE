package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AuthenticationPage {
    private WebDriver driver;

    public AuthenticationPage(WebDriver driver) {
        this.driver = driver;
    }

    // *** CREATE AN ACCOUNT Form ***
    public void createAccountForm(String email){

        //Write an email on email placeHolder
        WebElement emailAddress=driver.findElement(By.id("email_create"));
        emailAddress.sendKeys(email);

        // Click on Create an account button
        WebElement createAnAccountBtn = driver.findElement(By.id("SubmitCreate"));
        createAnAccountBtn.click();

    }

    // *** ALREADY REGISTERED? Form With Valid email and password***
    public void registrationForm(String email, String password){
        //Write email to Email address placeholder, passed from test class
        WebElement emailPlaceholder = driver.findElement(By.id("email"));
        emailPlaceholder.sendKeys(email);

        //Write password to Password placeholder, passed from test class
        WebElement passwordPlaceholder = driver.findElement(By.id("passwd"));
        passwordPlaceholder.sendKeys(password);

        WebElement signInBut = driver.findElement(By.name("SubmitLogin"));
        signInBut.click();

    }
}
