package POM;
import UTILS.BaseDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage_Elements extends BaseDriver {

    public CartPage_Elements(WebDriver driver){PageFactory.initElements(driver,this);}

    @FindBy(xpath = "(//a[@class='main-button mt-15'])[1]")
    public WebElement odemeAdiminaGecButton;

    @FindBy(css = "div[class='item-quantity-editor option-quantity']>input")
    public WebElement productQuantity;

    @FindBy(css = "a[class='oq-up plus']")
    public WebElement increaseQuantityButton;

    @FindBy(xpath = "(//a[@class='cart-square-link'])[2]")
    public WebElement firstDeleteButton;

    @FindBy(css = "div[class='col-xs-12']>a")
    public WebElement modalBoxDeleteButton;

    @FindBy(css = "div[class='col-sm-12']>p")
    public WebElement deleteSuccessText;






}

