import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utility.Utilities;
import java.util.concurrent.TimeUnit;

public class ChromeDriverDemo {

    private WebDriver driver;
    Utilities utilities;

    @Before
    public void setUP() {

        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);    //allows up to 10 seconds to find locators
        utilities = new Utilities(driver);
    }

    @Test
    public void userShouldNavigateToLoginPageSuccessfully() {
    utilities.clickOnElement(By.linkText("Log in"));
    WebElement text = driver.findElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"));
    Assert.assertTrue("Welcome Text Not Displayed",text.isDisplayed());
    }

    @Test
    public void loginShouldFailAndErrorMessageShouldAppearWhenLoginIsInvalid(){
        // save locator of login link
        By loginLink = By.linkText("Log in");
        // click on Login Link element using locator
        utilities.clickOnElement(loginLink);
        utilities.sendTextToElement(By.id("Email"), "abcdefg@yahoo.com");
        utilities.sendTextToElement(By.id("Password"), "password");
       // Assert.assertTrue(driver.findElement(By.id("Email")).getText()!=null);
       // Assert.assertTrue(driver.findElement(By.id("Password")).getText()!=null);
        utilities.clickOnElement(By.xpath("//input[@value='Log in']"));
        WebElement text = driver.findElement(By.xpath("//li[contains(text(),'No customer account found')]"));
        Assert.assertTrue("No customer account found",text.isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

