package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // *** Get sign in button ***
    public void clickSignInBtn() {
        WebElement signInBtn = driver.findElement(By.className("login"));
        signInBtn.click();
    }

    // *** Search product in search bar ***
    public void inputOnSearchPlaceHolder(String productMessage){
        //*** Write product message on the search bar ***
        WebElement searchPlaceHolder = driver.findElement(By.id("search_query_top"));
        searchPlaceHolder.sendKeys(productMessage);

        //*** Click on search button ***
        WebElement submitSearch = driver.findElement(By.name("submit_search"));
        submitSearch.click();

    }

    // *** Check picture quick view for more details
    public void checkProductDetail(String xpathOfPic){
        WebElement picture = driver.findElement(By.xpath(xpathOfPic));
        picture.click();
    }

    // *** Check POPULAR button ***
    public void clickPopularBtn(){
        WebElement popularBtn = driver.findElement(By.className("homefeatured"));
        popularBtn.click();

    }

    // *** Check POPULAR button ***
    public void clickBestsellerBtn(){
        WebElement popularBtn = driver.findElement(By.className("blockbestsellers"));
        popularBtn.click();

    }

    // *** Click Add to cart for different products ***
    public void clickAddToCartBtn(String xpathOfAddBtn){
        WebElement addToCartBtn = driver.findElement(By.xpath(xpathOfAddBtn));
        addToCartBtn.click();

    }

    // *** Click More to cart for different products ***
    public void clickMoreBtn(String xpathOfMoreBtn){
        WebElement addToCartBtn = driver.findElement(By.xpath(String.valueOf(xpathOfMoreBtn)));
        addToCartBtn.click();
    }

    // *** Hover over on Cart button ***
    public void hoverCartBtn(){
        //Actions class is for hover over function
        Actions actions = new Actions(driver);
        WebElement cartBtn = driver.findElement(By.className("shopping_cart"));
        //move mouse hover Cart button
        actions.moveToElement(cartBtn).perform();

    }

    // *** Click on Cart button ***
    public void clickCartBtn(){
        WebElement cartBtn = driver.findElement(By.className("shopping_cart"));
        cartBtn.click();
    }
}
