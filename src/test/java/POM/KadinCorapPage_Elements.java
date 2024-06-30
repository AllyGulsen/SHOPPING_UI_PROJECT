package POM;

import UTILS.BaseDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KadinCorapPage_Elements extends BaseDriver{

        public KadinCorapPage_Elements(WebDriver driver){PageFactory.initElements(driver, this);}

        @FindBy(xpath = "(//a[@size='36-40'])[1]")
        public WebElement corapBedenOption;

        @FindBy(id = "pd_add_to_cart")
        public WebElement addToCartButton;

        @FindBy(xpath = "//div[text()='Sepetinize 1 adet ürün eklenmiştir.']")
        public WebElement addToCartSuccessMessage;





    }

