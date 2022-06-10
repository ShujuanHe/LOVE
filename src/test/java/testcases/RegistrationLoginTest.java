package testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import library.SelectBrowser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.AuthenticationPage;
import pages.GuestCheckOutPage;
import pages.HomePage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class RegistrationLoginTest {


    WebDriver driver;

    HomePage signinBtn;
    HomePage A_Pic;
    HomePage creatAccount;
    AuthenticationPage creatAccountForm;
    AuthenticationPage signInForm;
    GuestCheckOutPage guestCheckOut;

    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeSuite
    public void setUpReport()
    {
        //create the HtmlReporter in that path by the name of  MyOwnReport.html
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/Shujuan_ecom_sba_Report.html");
        extent = new ExtentReports();

        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "shujuan.home-server.local");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Shujuan He");
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("AutomationTesting Google download pictures report");
        htmlReporter.config().setReportName("Google Search and Download Pictures Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);

    }
    @BeforeTest
    public void launchBrowser(){

        driver = SelectBrowser.StartBrowser("Chrome");
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //Very important for wait content appears
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
        //WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.get("http://automationpractice.com/index.php");
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Shopping Bag")));
    }





    // *** Check Sign in Button functionality ***
    @Test(priority = 1)
    public void checkSignInBtnFuc() throws IOException {

        test = extent.createTest("check_SignInButton_test", "Test Passed");
        //very important to pass down the driver
        signinBtn = new HomePage(driver);
        signinBtn.clickSignInBtn();
        String actureURL =  driver.getCurrentUrl();
        String expectedURL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
        Assert.assertEquals(actureURL,expectedURL);

        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //screenshot copied from buffer is saved at the mentioned path.
        FileUtils.copyFile(f, new File("C:\\Users\\shuju\\Automation Testing\\Selenium project\\POM_SBA_Exam - Version2\\POMExample\\src\\test\\resources\\screenshots\\checkSignInBtnFuc.png"));
        test.addScreenCaptureFromPath("checkSignInBtnFuc.png");



        test.log(Status.INFO,"Starting test case");
    }



    /*** Check Create Account Button
     * Req 1.2 - Anyone can register on the website easily.
     * Test Data: email: heshujuan9988@gmail.com    ***/
    @Test(priority = 2)
    public void checkCreateAccountFrom() throws IOException {

        test = extent.createTest("check_createAccountBtn_test", "Test Passed");

        creatAccount = new HomePage(driver);
        creatAccount.clickSignInBtn();

        //very important to pass down the driver
        creatAccountForm = new AuthenticationPage(driver);
        creatAccountForm.createAccountForm("heshujuan9988@gmail.com");
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"account-creation_form\"]/div[1]/h3")).isDisplayed());

        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //screenshot copied from buffer is saved at the mentioned path.
        FileUtils.copyFile(f, new File("C:\\Users\\shuju\\Automation Testing\\Selenium project\\POM_SBA_Exam - Version2\\POMExample\\src\\test\\resources\\screenshots\\checkCreateAccountFrom.png"));
        test.addScreenCaptureFromPath("checkCreateAccountFrom.png");
        test.log(Status.INFO,"Starting test case");
    }

    /***
     * Req 1.3 - Once registered, the user should be able to log in successfully:
     * Test data, email: shujuanhe1@gmail.com / password: 12345  ***/
    @Test(priority = 3)
    public void checkRegistrationForm() throws IOException {
        test = extent.createTest("check_registrationForm_test", "Test Passed");

        signinBtn = new HomePage(driver);
        signinBtn.clickSignInBtn();
        //very important to pass down the driver
        signInForm = new AuthenticationPage(driver);
        signInForm.registrationForm("shujuanhe1@gmail.com","12345");
        Assert.assertTrue(driver.findElement(By.className("icon-list-ol")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.className("icon-ban-circle")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.className("icon-building")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.className("icon-user")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.className("icon-heart")).isDisplayed());

        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //screenshot copied from buffer is saved at the mentioned path.
        FileUtils.copyFile(f, new File("C:\\Users\\shuju\\Automation Testing\\Selenium project\\POM_SBA_Exam - Version2\\POMExample\\src\\test\\resources\\screenshots\\checkRegistrationForm.png"));
        test.addScreenCaptureFromPath("checkRegistrationForm.png");

        test.log(Status.INFO,"Starting test case");
    }

    /**
     * Req 1.4 - The registered user is now able to view all the products listed on the website.
     */

    @Test(priority = 4)
    public void checkProducts() throws IOException {
        test = extent.createTest("check_registrationForm_test", "Test Passed");

        signinBtn = new HomePage(driver);
        signinBtn.clickSignInBtn();
        //very important to pass down the driver
        signInForm = new AuthenticationPage(driver);
        signInForm.registrationForm("shujuanhe1@gmail.com","12345");

        WebElement goToHomeBtn = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/a/span"));
        goToHomeBtn.click();

        //class ="replace-2x img-responsive" are all img in the home page
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"home-page-tabs\"]/li[1]/a")).isDisplayed());
        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //screenshot copied from buffer is saved at the mentioned path.
        FileUtils.copyFile(f, new File("C:\\Users\\shuju\\Automation Testing\\Selenium project\\POM_SBA_Exam - Version2\\POMExample\\src\\test\\resources\\screenshots\\checkProducts.png"));
        test.addScreenCaptureFromPath("checkProducts.png");

        test.log(Status.INFO,"Starting test case");
    }


    /***
     * Req 1.1 - A guest user can purchase a product as a guest user.
     */
    @Test(priority = 5)
    public void checkGuestPurchaseProductFuc() throws InterruptedException, IOException {

        test = extent.createTest("check_PopularProductButton_test", "Test Passed");
        //very important to pass down the driver
        A_Pic = new HomePage(driver);
        //Click on a picture
        A_Pic .checkProductDetail("//*[@id=\"homefeatured\"]/li[2]/div/div[1]/div/a[1]/img");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div")));

        //Switch to inner frame
        driver.switchTo().frame(driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/iframe")));


        driver.findElement(By.id("color_8")).click();
        Select size = new Select(driver.findElement(By.id("group_1")));
        size.selectByVisibleText("M");

        driver.findElement(By.name("Submit")).click();

        guestCheckOut = new GuestCheckOutPage(driver);
        guestCheckOut.guestCheckOutSteps();


        String expectedURL = "http://automationpractice.com/index.php?controller=order";
        String actualURL = driver.getCurrentUrl();

        Assert.assertEquals(actualURL,expectedURL);
        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //screenshot copied from buffer is saved at the mentioned path.
        FileUtils.copyFile(f, new File("C:\\Users\\shuju\\Automation Testing\\Selenium project\\POM_SBA_Exam - Version2\\POMExample\\src\\test\\resources\\screenshots\\checkGuestPurchaseProductFuc.png"));
        test.addScreenCaptureFromPath("checkGuestPurchaseProductFuc.png");
        test.log(Status.INFO,"Starting test case");

    }

    @AfterSuite
    public void tearDown()
    {
        extent.flush();
    }

}
