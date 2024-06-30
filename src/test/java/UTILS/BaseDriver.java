package UTILS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;

public class BaseDriver {
    public WebDriver driver;

    @BeforeTest
    public void setUpDriverMethod() {
        //CHROME
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();

        //BİLDİRİMLERİ HANDLE ETME
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-notifications");
        options.addArguments("--acceptInsecureCerts");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("chrome.switches","--disable-extensions");
        options.setExperimentalOption("useAutomationExtension", false);
        options.merge(desiredCapabilities);

        driver = new ChromeDriver(options);
        driver.navigate().to("https://www.lcw.com/");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterTest
    public void quitDriverMethod(){

        driver.quit();
    }
}


