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

public class RegistrationDemoN {
    WebDriver driver;
    Utilities utilities;

    @Before
    public void setUP() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);    //allows up to 10 seconds to find locators
        utilities = new Utilities(driver);
    }

    @Test
    public void userShouldNavigateToRegistrationFormSuccessfully() {
        utilities.clickOnElement(By.xpath("//a[@class='ico-register']"));
        WebElement text = driver.findElement(By.xpath("//h1[contains(text(),'Register')]"));
        Assert.assertTrue("Register",text.isDisplayed());
    }

    @Test
    public void userShouldRegisterSuccessfully() {
        utilities.clickOnElement(By.xpath("//a[@class='ico-register']"));
        utilities.clickOnElement(By.id("gender-female"));
        utilities.sendTextToElement(By.id("FirstName"), "My First Name");
        utilities.sendTextToElement(By.id("LastName"), "My Last Name");
        utilities.select_By_Index(By.name("DateOfBirthDay"), 5);
        utilities.select_By_Value(By.name("DateOfBirthMonth"), "1");
        utilities.select_By_Visible_Text(By.name("DateOfBirthYear"), "1960");
        utilities.sendTextToElement(By.id("Email"), "myemail"+utilities.generateRandomNumber()+"@email.com");
        utilities.sendTextToElement(By.id("Company"), "My Company");
        utilities.clickOnElement(By.id("Newsletter"));      //deselect checkbox
        utilities.sendTextToElement(By.name("Password"),"abcdefg123");
        utilities.sendTextToElement(By.name("ConfirmPassword"),"abcdefg123");
        WebElement registerBtn = driver.findElement(By.id("register-button"));
        registerBtn.click();

        String expectedMessage = "Your registration completed";
        String actualMessage = driver.findElement(By.xpath("//div[@class='result']")).getText();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}


