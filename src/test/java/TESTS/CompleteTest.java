package TESTS;
import POM.*;
import UTILS.BaseDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

public class CompleteTest extends BaseDriver {
    KadinCorapPage_Elements kadinCorapPage_Elements;
    NavigateBar_Elements navigateBar_Elements;
    HomePage_Elements home_page_elements;
    CartPage_Elements cartPage_Elements;
    LoginPage_Elements loginPage_Elements;
    CheckoutPage_Elements checkoutPage_Elements;

    String expectedHomePageURL = "https://www.lcw.com/";
    String expectedKadinPageURL = "https://www.lcw.com/kadin-t-1";
    String expectedKadinKurkluCorapPageUrl = "https://www.lcw.com/3lu-kurklu-kadin-soket-corap-3lu-lacivert-o-3922750";
    String expectedAddToCartPageUrl = "https://www.lcw.com/sepetim";
    String expected_PersonalCheckoutPageUrl = "https://www.lcw.com/checkout#odeme";

    @Test(priority = 1)
    public void navigateToHomePageTest() {

        //Home Page'e giderek sayfanın doğruluğunu validate ediyorum
        //Homepage navigasyon scripti BaseDriver Sayfasında (Herbir testte kod tekrarını önlemek için)

        home_page_elements = new HomePage_Elements(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String actualHomePageCurrentURL = driver.getCurrentUrl();
        System.out.println("Home Page URL" + actualHomePageCurrentURL);

        Assert.assertEquals(actualHomePageCurrentURL, expectedHomePageURL);
        Assert.assertTrue(actualHomePageCurrentURL.contains("https://www.lcw.com/"));
    }

    @Test(priority = 2)
    public void loginTest() throws InterruptedException {
        //Valid username ve password ile Pozitif Login Testi yapıyorum

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        navigateBar_Elements = new NavigateBar_Elements(driver);
        home_page_elements = new HomePage_Elements(driver);
        loginPage_Elements = new LoginPage_Elements(driver);

        wait.until(ExpectedConditions.visibilityOf(home_page_elements.cookieBox));
        home_page_elements.cookieRejectButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(navigateBar_Elements.hesabaGirisYapButonu)).click();
        loginPage_Elements.emailInputBox.sendKeys("user_email@gmail.com");
        wait.until(ExpectedConditions.elementToBeClickable(loginPage_Elements.devamEtButton)).click();
        wait.until(ExpectedConditions.visibilityOf(loginPage_Elements.passwordInputBox)).sendKeys("user_password");
        wait.until(ExpectedConditions.elementToBeClickable(loginPage_Elements.passwordSubmitButonu)).click();

        Actions action = new Actions(driver);

        Thread.sleep(3000);
        action.moveToElement(navigateBar_Elements.hesabimDropDown).build().perform();
        Thread.sleep(3000);
        action.moveToElement(navigateBar_Elements.hosGeldinizDropdown).build().perform();

        //Login sonrası Success kontrolü yapıyorum
        String hosgeldinizText = navigateBar_Elements.hosGeldinizDropdown.getText();
        System.out.println("LOGIN MESAJI= " + hosgeldinizText);

        Assert.assertTrue(hosgeldinizText.contains("Hoş Geldiniz"));
    }

    @Test(priority = 3)
    public void navigateToKadinPageTest() {

        //Kadın Sayfasına navigate edip doğru sayfa mı validate ediyorum
        home_page_elements = new HomePage_Elements(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(home_page_elements.menuHeaderKadin)).click();

        Assert.assertEquals(driver.getCurrentUrl(), expectedKadinPageURL);
    }

    @Test(priority = 4)
    public void searchingProductBySearchBoxTest() throws InterruptedException {

        //Search boxta ürün aratıyorum
        //Search sonucu gelen tüm ürünlerin product grid içindeki "a Tag" elementlerini bulup List altında kaydediyorum
        //for loop ile gelen tüm tüm ürünlerin textini yazdırıyorum

        navigateBar_Elements = new NavigateBar_Elements(driver);
        navigateBar_Elements.searchBox.sendKeys("kürklü çorap");
        navigateBar_Elements.araButton.click();

        List<WebElement> searchSonucuGelenTumUrunlerListesi = driver.findElements(By.xpath("//div[@class='product-grid']//div//a"));
        for (int i = 0; i < searchSonucuGelenTumUrunlerListesi.size(); i++) {
            System.out.println("SEARCH SONUCU GELEN TÜM ÜRÜNLERİN LİSTESİ= " + searchSonucuGelenTumUrunlerListesi.get(i).getText());
        }
    }

    @Test(priority = 5)
    public void addToCartTest() throws InterruptedException {

        //Sececegim urunun sayfasına gidiyorum ve sayfanın doğruluğunu validate ediyorum

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        navigateBar_Elements = new NavigateBar_Elements(driver);
        kadinCorapPage_Elements = new KadinCorapPage_Elements(driver);
        navigateBar_Elements.searchPageKurkluCorap.click();

        Assert.assertEquals(driver.getCurrentUrl(), expectedKadinKurkluCorapPageUrl);

        //Corap bedenini seçtikten sonra  ürünü sepete ekliyorum
        //"Sepete 1 ADET ürün  eklendi" success mesajını validate ediyorum( Adet ve success kontrolü)
        wait.until(ExpectedConditions.elementToBeClickable(kadinCorapPage_Elements.corapBedenOption)).click();

        //LCW web sitesinde wait issue sorunları bu sayfalarda sıklaştığı için thread.sleep ile desteklemem gerekti
        wait.until(ExpectedConditions.elementToBeClickable(kadinCorapPage_Elements.addToCartButton)).click();
        Thread.sleep(3000);
        Assert.assertTrue(kadinCorapPage_Elements.addToCartSuccessMessage.isDisplayed());

    }

    @Test(priority = 6)
    public void navigateToCartPageTest() throws InterruptedException {

        //Sepet sayfasına navigate edip sayfanın doğruluğunu kontrol ediyorum

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        navigateBar_Elements = new NavigateBar_Elements(driver);

        wait.until(ExpectedConditions.elementToBeClickable(navigateBar_Elements.navigateToSepetim)).click();

        String currentAddToCartPageUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentAddToCartPageUrl.contains(expectedAddToCartPageUrl));
    }

    @Test(priority = 7)
    public void editCartPageTest() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Sepet Sayfasındaki ürün adetini 1'den 2'ye artırıyorum ve adetler doğru değişti mi validate ediyorum

        cartPage_Elements = new CartPage_Elements(driver);
        Thread.sleep(2000);

        String firstQuantity = cartPage_Elements.productQuantity.getAttribute("data-quantity");
        System.out.println("FIRST QUANTITY=" + firstQuantity);

        wait.until(ExpectedConditions.visibilityOf(cartPage_Elements.increaseQuantityButton)).click();
        Thread.sleep(3000);

        String secondQuantity = cartPage_Elements.productQuantity.getAttribute("data-quantity");
        System.out.println("SECOND QUANTITY= " + secondQuantity);

        Assert.assertEquals(firstQuantity, "1");
        Assert.assertEquals(secondQuantity, "2");
    }

    @Test(priority = 8)
    public void deleteCartPageTest() {

        //Sepet sayfasındaki ürünlerimi siliyorum ve silindiğine dair success mesajını validate ediyorum

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        cartPage_Elements = new CartPage_Elements(driver);
        wait.until(ExpectedConditions.elementToBeClickable(cartPage_Elements.firstDeleteButton));
        cartPage_Elements.firstDeleteButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(cartPage_Elements.modalBoxDeleteButton));
        cartPage_Elements.modalBoxDeleteButton.click();

        wait.until(ExpectedConditions.visibilityOf(cartPage_Elements.deleteSuccessText));
        Assert.assertTrue(cartPage_Elements.deleteSuccessText.getText().contains("Sepetinizde ürün bulunmamaktadır"));
    }

    @Test(priority = 9)
    public void loOutTest() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        navigateBar_Elements = new NavigateBar_Elements(driver);

        Actions action = new Actions(driver);
        action.moveToElement(navigateBar_Elements.hesabimDropDown).build().perform();
        Thread.sleep(3000);

        action.moveToElement(navigateBar_Elements.cikisYapButton).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(navigateBar_Elements.cikisYapButton)).click();
        Thread.sleep(3000);
    }

    @Test(priority = 10)
    public void redirectingNoneMembersToTheLoginPageTest() throws InterruptedException {

        //Bir önceki testte Logout olundu ve sistem kullanıcıyı tekrar Home Page'e yönlendirdi
        // Bu testte Login olmadan sepete ürün ekleyerek ödeme butonuna basıyorum.
        //Bu aşamada kullanıcının Login Sayfasına redirect edilmesi beklenmektedir

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        navigateBar_Elements = new NavigateBar_Elements(driver);
        kadinCorapPage_Elements = new KadinCorapPage_Elements(driver);

        wait.until(ExpectedConditions.visibilityOf(navigateBar_Elements.searchBox)).sendKeys("kürklü çorap");
        wait.until(ExpectedConditions.elementToBeClickable(navigateBar_Elements.araButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(navigateBar_Elements.searchPageKurkluCorap)).click();

        wait.until(ExpectedConditions.elementToBeClickable(kadinCorapPage_Elements.corapBedenOption)).click();
        wait.until(ExpectedConditions.elementToBeClickable(kadinCorapPage_Elements.addToCartButton)).click();
        Thread.sleep(3000);

        wait.until(ExpectedConditions.elementToBeClickable(navigateBar_Elements.navigateToSepetim)).click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(cartPage_Elements.odemeAdiminaGecButton)).click();

        String expectedRedirectedUrl = "https://www.lcw.com/giris?returnUrl=/checkout#adres";
        String currentUrl = driver.getCurrentUrl();
        System.out.println("CURRENT PAGE URL= " + currentUrl);

        //Kullanıcı  Üyelik girişi yapmadan önce ödeme butonuna bastığı için, Sistem kullanıcıyı "Return Login" sayfasına yönlendirmeli
        Assert.assertTrue(currentUrl.contains("https://www.lcw.com/giris?returnUrl=/checkout#adres"));

    }

    @Test(priority = 11)
    public void purchaseWithCreditCardTestForMembers() throws InterruptedException {

    //Üyeliği olan kullanıcı için Kredi Kartı ile satın alma testi
    //Home Page'e navigate edip Login oluyorum

    driver.navigate().to("https://www.lcw.com/");
    driver.manage().window().maximize();

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    navigateBar_Elements = new NavigateBar_Elements(driver);
    loginPage_Elements = new LoginPage_Elements(driver);
    kadinCorapPage_Elements = new KadinCorapPage_Elements(driver);
    cartPage_Elements = new CartPage_Elements(driver);
    home_page_elements = new HomePage_Elements(driver);
    checkoutPage_Elements = new CheckoutPage_Elements(driver);

    //wait.until(ExpectedConditions.visibilityOf(home_page_elements.cookieBox));
    //home_page_elements.cookieRejectButton.click();

    wait.until(ExpectedConditions.elementToBeClickable(navigateBar_Elements.hesabaGirisYapButonu)).click();

    wait.until(ExpectedConditions.elementToBeClickable(loginPage_Elements.emailInputBox)).click();
    loginPage_Elements.emailInputBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
    Thread.sleep(2000);
    loginPage_Elements.emailInputBox.sendKeys("user_email@gmail.com");
    wait.until(ExpectedConditions.elementToBeClickable(loginPage_Elements.devamEtButton)).click();

    wait.until(ExpectedConditions.visibilityOf(loginPage_Elements.passwordInputBox)).sendKeys("user_password");
    wait.until(ExpectedConditions.elementToBeClickable(loginPage_Elements.passwordSubmitButonu)).click();

    Thread.sleep(2000);
    wait.until(ExpectedConditions.visibilityOf(navigateBar_Elements.searchBox)).sendKeys("kürklü çorap");
    wait.until(ExpectedConditions.elementToBeClickable(navigateBar_Elements.araButton)).click();

    wait.until(ExpectedConditions.visibilityOf(navigateBar_Elements.searchPageKurkluCorap)).click();

    wait.until(ExpectedConditions.elementToBeClickable(kadinCorapPage_Elements.corapBedenOption)).click();
    wait.until(ExpectedConditions.elementToBeClickable(kadinCorapPage_Elements.addToCartButton)).click();
    Thread.sleep(3000);
    navigateBar_Elements.navigateToSepetim.click();

    wait.until(ExpectedConditions.elementToBeClickable(cartPage_Elements.odemeAdiminaGecButton)).click();

    wait.until(ExpectedConditions.visibilityOf(checkoutPage_Elements.cardRadioButton)).click();

    wait.until(ExpectedConditions.visibilityOf(checkoutPage_Elements.bankCardUserName)).sendKeys("name surname");
    wait.until(ExpectedConditions.visibilityOf(checkoutPage_Elements.bankCardNo)).sendKeys("1111 1111 1111 1111");

    Thread.sleep(2000);
    checkoutPage_Elements.cardExpDateMonth.sendKeys("01", Keys.ENTER);
    checkoutPage_Elements.cardExpDateYear.sendKeys("2025",Keys.ENTER);
    checkoutPage_Elements.cvvNumber.sendKeys("111");

    checkoutPage_Elements.onBilgiFormuCheckBox.click();
    wait.until(ExpectedConditions.elementToBeClickable(checkoutPage_Elements.siparisiTamamlaButton)).click();

    String orderSuccesPageUrl= driver.getCurrentUrl();
    Assert.assertTrue(orderSuccesPageUrl.contains("checkout#onay"));

    String orderSuccessText= checkoutPage_Elements.orderSuccessElement.getAttribute("value");
    Assert.assertTrue(orderSuccessText.contains("Siparişiniz alındı"));
    }
}
    //Testler bitince browser quit etmeli(AfterMethod BaseDriver sayfasında)






