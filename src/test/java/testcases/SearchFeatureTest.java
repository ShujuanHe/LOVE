package testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import library.SelectBrowser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class SearchFeatureTest {
    WebDriver driver;
    HomePage itemName;


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





    /** Req 2.1 - The website has filters to search products (e.g., price range, category, brands, etc.).
    Test Data: “Printed Chiffon Dress”  ***/

    @Test(priority = 1)
    public void searchProduct() throws IOException {

        test = extent.createTest("Seach_product_test", "Test Passed");
        //very important to pass down the driver
        itemName = new HomePage(driver);
        itemName.inputOnSearchPlaceHolder("Printed Chiffon Dress");
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]/img")).isDisplayed());

        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //screenshot copied from buffer is saved at the mentioned path.
        FileUtils.copyFile(f, new File("C:\\Users\\shuju\\Automation Testing\\Selenium project\\POM_SBA_Exam - Version2\\POMExample\\src\\test\\resources\\screenshots\\searchProduct.png"));
        test.addScreenCaptureFromPath("searchProduct.png");

        test.log(Status.INFO, "Starting test case");
    }

    @AfterSuite
    public void tearDown()
    {
        extent.flush();
    }
}
