package vn.edu.utc.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import vn.edu.utc.base.BaseTest;
import vn.edu.utc.pages.LoginPage;

@Epic("Authentication")
@Feature("Login Feature")
public class LoginExcelTest extends BaseTest { // Kế thừa BaseTest để lấy tự động WebDriver

    private LoginPage loginPage;

    @BeforeEach
    public void initPage() {
        loginPage = new LoginPage(driver);
        driver.get("https://vanphongdientu.utc.edu.vn/Login");
    }

    @ParameterizedTest
    @MethodSource("vn.edu.utc.utils.ExcelUtils#getLoginData")
    void testLoginFromExcel(String username, String password, String expectedMessage) {
        
        loginPage.login(username, password);

        System.out.println("Đã thử đăng nhập với user: " + username + " | pass: " + password);
        System.out.println("Kết quả mong muốn ghi trong Excel: " + expectedMessage);
    }
}
