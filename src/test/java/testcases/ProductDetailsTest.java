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
import pages.HomePage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class ProductDetailsTest {
    WebDriver driver;
    HomePage homePageFuns;


    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeSuite
    public void setUpReport() {
        //create the HtmlReporter in that path by the name of  MyOwnReport.html
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/Shujuan_ecom_sba_Report.html");
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
    public void launchBrowser() {

        driver = SelectBrowser.StartBrowser("Chrome");
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //Very important for wait content appears
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.get("http://automationpractice.com/index.php");
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Shopping Bag")));
    }


    /**
     * Req 3.1 - Popular product option (tab): The product detail page must open once the product image is clicked.
     * Req 3.1 - The user is able to select the size and color of the product.
     * Req 3.1 - The user is able to select (increase or decrease) the quantity of a product.
     * Req 3.1 - The user is able to add products to the cart by clicking on the “Add to Cart” option (button); but, the user must have previously selected the quantity, size, and color of the product.
     ***/

    @Test(priority = 1)
    public void checkPopularProductBtn() throws InterruptedException, IOException {
        test = extent.createTest("check_PopularProductButton_test", "Test Passed");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        //very important to pass down the driver
        homePageFuns = new HomePage(driver);
        //Click on popular product button
        homePageFuns.clickPopularBtn();
        //Click on a picture
        homePageFuns.checkProductDetail("//*[@id=\"homefeatured\"]/li[2]/div/div[1]/div/a[1]/img");

        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div")).isDisplayed());

        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //screenshot copied from buffer is saved at the mentioned path.
        FileUtils.copyFile(f, new File("C:\\Users\\shuju\\Automation Testing\\Selenium project\\POM_SBA_Exam - Version2\\POMExample\\src\\test\\resources\\screenshots\\checkPopularProductBtn.png"));
        test.addScreenCaptureFromPath("checkPopularProductBtn.png");
        test.log(Status.INFO, "Starting test case");


    }

    @Test(priority = 2)
    public void checkProductColorAndSize() throws IOException {
        test = extent.createTest("check_PopularProductButton_test", "Test Passed");
        //very important to pass down the driver
        homePageFuns = new HomePage(driver);
        //Click on a picture
        homePageFuns.checkProductDetail("//*[@id=\'homefeatured\']/li[2]/div/div[1]/div/a[1]/img");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div")));

        //Switch to inner frame
        driver.switchTo().frame(driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/iframe")));

        driver.findElement(By.id("color_8")).click();
        Select size = new Select(driver.findElement(By.id("group_1")));
        size.selectByVisibleText("M");
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\'bigpic\']")).isDisplayed());

        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //screenshot copied from buffer is saved at the mentioned path.
        FileUtils.copyFile(f, new File("C:\\Users\\shuju\\Automation Testing\\Selenium project\\POM_SBA_Exam - Version2\\POMExample\\src\\test\\resources\\screenshots\\checkProductColorAndSize.png"));
        test.addScreenCaptureFromPath("checkProductColorAndSize.png");

        test.log(Status.INFO, "Starting test case");

    }
    @Test(priority = 3)
    public void checkQty() throws IOException {
        test = extent.createTest("check_PopularProductButton_test", "Test Passed");
        //very important to pass down the driver
        homePageFuns = new HomePage(driver);
        //Click on a picture
        homePageFuns.checkProductDetail("//*[@id=\"homefeatured\"]/li[2]/div/div[1]/div/a[1]/img");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div")));

        //Switch to inner frame
        driver.switchTo().frame(driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/iframe")));

        driver.findElement(By.id("quantity_wanted")).clear();
        driver.findElement(By.id("quantity_wanted")).sendKeys("2");
        //driver.findElement(By.xpath("//*[@id=\"quantity_wanted_p\"]/label")).click();


      /*  String expectedQty = "2";
        String actualQty = driver.findElement(By.id("quantity_wanted")).getText();

        Assert.assertEquals(actualQty,expectedQty);*/
        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //screenshot copied from buffer is saved at the mentioned path.
        FileUtils.copyFile(f, new File("C:\\Users\\shuju\\Automation Testing\\Selenium project\\POM_SBA_Exam - Version2\\POMExample\\src\\test\\resources\\screenshots\\checkQty.png"));
        test.addScreenCaptureFromPath("checkQty.png");


       // Assert.assertEquals(actualQty,expectedQty);
        test.log(Status.INFO, "Starting test case");

    }


    @Test(priority = 4)
    public void addToCartAfterFilter() throws IOException {
        test = extent.createTest("check_PopularProductButton_test", "Test Passed");
        //very important to pass down the driver
        homePageFuns = new HomePage(driver);
        //Click on a picture
        homePageFuns.checkProductDetail("//*[@id=\"homefeatured\"]/li[2]/div/div[1]/div/a[1]/img");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div")));

        //Switch to inner frame
        driver.switchTo().frame(driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/iframe")));


        driver.findElement(By.id("color_8")).click();
        Select size = new Select(driver.findElement(By.id("group_1")));
        size.selectByVisibleText("M");

        driver.findElement(By.name("Submit")).click();

        String expectedURL = "http://automationpractice.com/index.php";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL,expectedURL);

        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //screenshot copied from buffer is saved at the mentioned path.
        FileUtils.copyFile(f, new File("C:\\Users\\shuju\\Automation Testing\\Selenium project\\POM_SBA_Exam - Version2\\POMExample\\src\\test\\resources\\screenshots\\addToCartAfterFilter.png"));
        test.addScreenCaptureFromPath("addToCartAfterFilter.png");



        test.log(Status.INFO, "Starting test case");

    }

    @AfterSuite
    public void tearDown()
    {
        extent.flush();
    }
}
