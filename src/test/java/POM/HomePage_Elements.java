package POM;
import UTILS.BaseDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage_Elements extends BaseDriver{

        public HomePage_Elements(WebDriver driver){
            PageFactory.initElements(driver,this);}

        @FindBy(xpath = "//button[text()='Tüm Çerezlere İzin Ver']")
        public WebElement acceptAllCookiesButton;

        @FindBy(xpath ="(//a[text()='KADIN'])[1]" )
        public WebElement menuHeaderKadin;

        @FindBy(id = "cookieseal-banner")
        public WebElement cookieBox;

        @FindBy(id = "cookieseal-banner-reject")
        public WebElement cookieRejectButton;

    }
