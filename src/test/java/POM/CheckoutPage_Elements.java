package POM;

import UTILS.BaseDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage_Elements extends BaseDriver {

    public CheckoutPage_Elements(WebDriver driver){PageFactory.initElements(driver,this);}



    @FindBy(css = "label[for='PaymentButtonRadio_PaymentCardMasterpassView']")
    public WebElement cardRadioButton;

    @FindBy(css = "input[placeholder='Kart Üzerindeki Ad Soyad']")
    public WebElement bankCardUserName;

    @FindBy(css = "input[placeholder='Kart Numarası']")
    public WebElement bankCardNo;

    @FindBy(css = "input[placeholder='Ay']")
    public WebElement cardExpDateMonth;

    @FindBy(xpath = "(//span[@class='vs__selected'])[1]")
    public WebElement monthOptions;

    @FindBy(css = "input[placeholder='Yıl']")
    public WebElement cardExpDateYear;

    @FindBy(xpath = "(//span[@class='vs__selected'])[2]")
    public WebElement yearOptions;

    @FindBy(css = "input[placeholder='***']")
    public WebElement cvvNumber;

    @FindBy(id="contractContainer")
    public WebElement onBilgiFormuCheckBox;

    @FindBy(xpath = "//span[text()=' SİPARİŞİ TAMAMLA']")
    public WebElement siparisiTamamlaButton;

    @FindBy(css = "p[class='order-complete-text']")
    public WebElement orderSuccessElement;


}
