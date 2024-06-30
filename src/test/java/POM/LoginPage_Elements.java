package POM;

import UTILS.BaseDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage_Elements extends BaseDriver {

        public LoginPage_Elements(WebDriver driver){PageFactory.initElements(driver,this);}

        @FindBy(css = "input[name='emailAndPhone']")
        public WebElement emailInputBox;

        @FindBy()
        public WebElement loginButton;

        @FindBy(xpath = "//button[text()='Devam Et']")
        public WebElement devamEtButton;

        @FindBy(css = "input[name='password']")
        public WebElement passwordInputBox;

        @FindBy(css = "button[type='submit']")
        public WebElement passwordSubmitButonu;

    }

