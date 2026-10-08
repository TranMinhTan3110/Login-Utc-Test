package vn.edu.utc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // Locators
    private By usernameLocator = By.xpath("//input[@placeholder='Tên đăng nhập' or @type='text']");
    private By passwordLocator = By.xpath("//input[@placeholder='Mật khẩu' or @type='password']");
    private By loginButtonLocator = By.xpath("//button[contains(text(), 'Đăng nhập') or @type='submit'] | //input[@type='submit' and @value='Đăng nhập']");

    public LoginPage(WebDriver driver) {
        super(driver); // Gọi constructor của BasePage
    }

    public void enterUsername(String username) {
        enterText(usernameLocator, username); // Kế thừa hàm enterText từ BasePage
    }

    public void enterPassword(String password) {
        enterText(passwordLocator, password); // Kế thừa hàm enterText từ BasePage
    }

    public void clickLoginButton() {
        clickElement(loginButtonLocator); // Kế thừa hàm clickElement từ BasePage
    }
    
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}
