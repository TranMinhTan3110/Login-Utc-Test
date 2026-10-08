package vn.edu.utc;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    static void setupClass() {
        // Tự động tải xuống và thiết lập ChromeDriver
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        ChromeOptions options = new ChromeOptions();
        // Tự động chạy ngầm (không hiện giao diện Chrome) nếu đang chạy trên GitHub Actions
        if (System.getenv("CI") != null) {
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        // Mở trang đăng nhập
        driver.get("https://vanphongdientu.utc.edu.vn/Login");
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testSuccessfulLogin() {
        // Tìm và nhập Tên đăng nhập
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Tên đăng nhập' or @type='text']")));
        usernameInput.sendKeys("tentaikhoan");

        // Tìm và nhập Mật khẩu
        WebElement passwordInput = driver.findElement(By.xpath("//input[@placeholder='Mật khẩu' or @type='password']"));
        passwordInput.sendKeys("matkhau123");

        // Click ô Giữ tôi luôn đăng nhập (Tùy chọn)
        WebElement rememberMeCheckbox = driver.findElement(By.xpath("//input[@type='checkbox']"));
        if (!rememberMeCheckbox.isSelected()) {
            rememberMeCheckbox.click();
        }

        // Bấm nút Đăng nhập
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Đăng nhập') or @type='submit'] | //input[@type='submit' and @value='Đăng nhập']"));
        loginButton.click();

        // Kiểm tra sau khi đăng nhập thành công
        // Lưu ý: Phần này cần điều chỉnh locator cho phù hợp với trang web thực tế sau khi đăng nhập
        // assertTrue(driver.getCurrentUrl().contains("Dashboard"));
    }

    @Test
    void testEmptyUsernameAndPassword() {
        // Bấm nút Đăng nhập mà không điền gì cả
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Đăng nhập') or @type='submit'] | //input[@type='submit' and @value='Đăng nhập']")));
        loginButton.click();

        // Kiểm tra thông báo lỗi
        // Lưu ý: Thay thế locator này bằng locator thực tế của thông báo lỗi trên trang
        // WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));
        // assertTrue(errorMessage.isDisplayed());
    }

    @Test
    void testInvalidCredentials() {
        // Nhập thông tin sai
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Tên đăng nhập' or @type='text']")));
        usernameInput.sendKeys("sai_ten_dang_nhap");

        WebElement passwordInput = driver.findElement(By.xpath("//input[@placeholder='Mật khẩu' or @type='password']"));
        passwordInput.sendKeys("sai_mat_khau");

        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Đăng nhập') or @type='submit'] | //input[@type='submit' and @value='Đăng nhập']"));
        loginButton.click();

        // Kiểm tra thông báo lỗi
        // WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));
        // assertTrue(errorMessage.isDisplayed());
    }
    
    @Test
    void testLoginWithUtcEmail() {
        // Kiểm tra nút Đăng nhập bằng e-mail UTC có hoạt động không
        WebElement emailUtcButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Đăng nhập bằng e-mail UTC')] | //button[contains(text(), 'Đăng nhập bằng e-mail UTC')]")));
        emailUtcButton.click();
        
        // Kiểm tra xem có được chuyển hướng đến trang đăng nhập của Google/Microsoft không
        // wait.until(ExpectedConditions.urlContains("accounts.google.com")); // hoặc login.microsoftonline.com
        // assertTrue(driver.getCurrentUrl().contains("accounts"));
    }
}
