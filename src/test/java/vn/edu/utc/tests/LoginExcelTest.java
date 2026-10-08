package vn.edu.utc.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import vn.edu.utc.pages.LoginPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Epic("Authentication")
@Feature("Login Feature")
public class LoginExcelTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeAll
    void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        ChromeOptions options = new ChromeOptions();
        if (System.getenv("CI") != null) {
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        // Khởi tạo đối tượng đại diện cho trang web
        loginPage = new LoginPage(driver);
        driver.get("https://vanphongdientu.utc.edu.vn/Login");
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Liên kết với hàm đọc Excel từ class ExcelUtils
    @ParameterizedTest
    @MethodSource("vn.edu.utc.utils.ExcelUtils#getLoginData")
    void testLoginFromExcel(String username, String password, String expectedMessage) {
        
        // Gọi hàm từ LoginPage, code test sẽ cực kỳ ngắn gọn và dễ đọc
        loginPage.login(username, password);

        System.out.println("Đã thử đăng nhập với user: " + username + " | pass: " + password);
        System.out.println("Kết quả mong muốn ghi trong Excel: " + expectedMessage);
        
        // Tương lai: Thêm hàm kiểm tra assert ở đây tuỳ thuộc vào expectedMessage
    }
}
