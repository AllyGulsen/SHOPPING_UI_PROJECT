package POM;

import UTILS.BaseDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigateBar_Elements extends BaseDriver {

        public NavigateBar_Elements(WebDriver driver){PageFactory.initElements(driver,this);}

        @FindBy(id = "search-form__input-field__search-input")
        public WebElement searchBox;

        @FindBy(xpath = "//button[text()='Ara']")
        public WebElement araButton;

        @FindBy(xpath = "(//div[@class='product-card product-card--one-of-4'])[2]")
        public WebElement searchPageKurkluCorap;

        @FindBy(xpath = "(//a[@class='header-dropdown-toggle'])[1]")
        public WebElement hesabaGirisYapButonu;

        @FindBy(css = "li[class='list-content__item list-content__item--welcome']")
        public WebElement hosGeldinizDropdown;

        @FindBy(xpath = "//a[text()='Giriş Yap']")
        public WebElement girisYapDropDown;

        @FindBy(xpath = "path[id='Path_3857']")
        public WebElement userAccountPath;

        @FindBy(css = "button[class='header-dropdown-toggle striped-button']")
        public WebElement hesabimDropDown;

        @FindBy(xpath = "//button[text()='Çıkış Yap']")
        public WebElement cikisYapButton;

        @FindBy(xpath = "//span[text()='Sepetim']")
        public WebElement navigateToSepetim;




    }


