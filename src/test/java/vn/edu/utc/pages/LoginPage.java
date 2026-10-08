package vn.edu.utc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // 1. Quản lý các Locators (Địa chỉ của các thẻ trên web) ở một nơi duy nhất
    private By usernameLocator = By.xpath("//input[@placeholder='Tên đăng nhập' or @type='text']");
    private By passwordLocator = By.xpath("//input[@placeholder='Mật khẩu' or @type='password']");
    private By loginButtonLocator = By.xpath("//button[contains(text(), 'Đăng nhập') or @type='submit'] | //input[@type='submit' and @value='Đăng nhập']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 2. Viết các hàm chức năng tương ứng
    public void enterUsername(String username) {
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameLocator));
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordInput = driver.findElement(passwordLocator);
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(loginButtonLocator));
        loginButton.click();
    }
    
    // Gộp các bước thành 1 hàm tiện lợi
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}
