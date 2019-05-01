import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utility.Utilities;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ComputerBasket {

    WebDriver driver;

    @Before
    public void setUP() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Archita\\IdeaProjects\\seleniumdemo\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
    }

    @Test
    public void userShouldNavigateToComputers() {
        Utilities utilities = new Utilities(driver);
        By by = By.xpath("//ul[@class='top-menu']//a[contains(text(),'Computers')]");
        utilities.mouseOver(by);
        WebElement computers = driver.findElement(by);
        utilities.waitUntilPresenceOfElementClickable(by, 10);
        //code to click on 1st item from top-menu list (List has xpath using descendant)
        List<WebElement> list = driver.findElements(By.xpath("//ul[@class='top-menu']/descendant::li"));
        list.get(0).click();
        //check new page has text 'Computers'
        WebElement text = driver.findElement(By.xpath("//h1[contains(text(),'Computers')]"));
        Assert.assertTrue("Computers", text.isDisplayed());
    }

    @Test
    public void userShouldNavigateToDesktop() {
        Utilities utilities = new Utilities(driver);
        By by = By.xpath("//ul[@class='top-menu']//a[contains(text(),'Computers')]");
        utilities.clickOnElement(by);
        By by1 = By.xpath("//div[@class='item-grid']//div[1]//div[1]//div[1]//a[1]//img[1]");
        utilities.clickOnElement(by1);
        utilities.waitUntilPresenceOfElementClickable(by1, 30);
        //check current URL to confirm that user is navigated to Desktop Page
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contentEquals("https://demo.nopcommerce.com/desktops"));
    }

    @Test
    public void userShouldNavigateToBuildYourOwnComputer() throws InterruptedException {
        Utilities utilities = new Utilities(driver);
        By computerlink = By.xpath("//ul[@class='top-menu']//a[contains(text(),'Computers')]");
        utilities.clickOnElement(computerlink);
        By desktopimage = By.xpath("//div[@class='item-grid']//div[1]//div[1]//div[1]//a[1]//img[1]");
        utilities.clickOnElement(desktopimage);
        utilities.waitUntilPresenceOfElementClickable(desktopimage, 30);
        utilities.scrollUpDown(600);

        By addToCartBtn = By.xpath("//div[@class='item-grid']//div[1]//div[1]//div[2]//div[3]//div[2]//input[1]");
        utilities.waitUntilVisibilityOfElementLocated(addToCartBtn, 30);
        utilities.waitUntilPresenceOfElementClickable(addToCartBtn, 30);
        utilities.clickOnElement(addToCartBtn);

        Thread.sleep(3000);
        WebElement text2 = driver.findElement(By.xpath("//h1[contains(text(),'Build your own computer')]"));
        Assert.assertTrue("Build your own computer", text2.isDisplayed());
    }

    @Test
    public void userShouldBeAbleToAddDesktopItemToBasket() throws InterruptedException{
        Utilities utilities = new Utilities(driver);
        By computerlink = By.xpath("//ul[@class='top-menu']//a[contains(text(),'Computers')]");
        utilities.clickOnElement(computerlink);
        By desktopimage = By.xpath("//div[@class='item-grid']//div[1]//div[1]//div[1]//a[1]//img[1]");
        utilities.clickOnElement(desktopimage);
        utilities.waitUntilPresenceOfElementClickable(desktopimage, 30);
        utilities.scrollUpDown(600);

        By addToCartBtn = By.xpath("//div[@class='item-grid']//div[1]//div[1]//div[2]//div[3]//div[2]//input[1]");
        utilities.waitUntilVisibilityOfElementLocated(addToCartBtn, 30);
        utilities.waitUntilPresenceOfElementClickable(addToCartBtn, 30);
        utilities.clickOnElement(addToCartBtn);
        Thread.sleep(3000);

        utilities.scrollUpDown(800);
        By addToCart2 = By.xpath("//input[@id='add-to-cart-button-1']");
        utilities.waitUntilVisibilityOfElementLocated(addToCart2,10);
        utilities.clickOnElement(By.id("product_attribute_3_6"));
        utilities.clickOnElement(addToCart2);
        Thread.sleep(3000);

        String cartQty = driver.findElement(By.xpath("//span[@class='cart-qty']")).getText();

        Assert.assertTrue(cartQty.equals("(1)"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
        /*  WebElement radioHDD = driver.findElement(By.id("product_attribute_3_6"));
            radioHDD.click();
            WebElement addToCart = driver.findElement(By.id("add-to-cart-button-1"));
            addToCart.click();
            WebElement shoppingcart = driver.findElement(By.className("cart-label"));
            shoppingcart.click();
        */


