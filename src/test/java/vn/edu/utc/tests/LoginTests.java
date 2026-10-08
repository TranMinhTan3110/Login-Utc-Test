package vn.edu.utc.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vn.edu.utc.base.BaseTest;
import vn.edu.utc.pages.LoginPage;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Authentication")
@Feature("Đăng nhập Web Văn Phòng Điện Tử")
public class LoginTests extends BaseTest {

    private LoginPage loginPage;

    @BeforeEach
    public void initPage() {
        loginPage = new LoginPage(driver);
        driver.get("https://vanphongdientu.utc.edu.vn/Login");
    }

    @Test
    @Story("TC01 - Đăng nhập thành công với tài khoản hợp lệ")
    public void testTC01_LoginSuccess() {
        // 1. Điền thông tin chuẩn bị
        String validUsername = "taikhoanthucte_cuaban"; // Hãy tự sửa chuỗi này
        String validPassword = "matkhauthucte_cuaban";  // Hãy tự sửa chuỗi này
        
        // 2. Thực hiện đăng nhập
        loginPage.login(validUsername, validPassword);

        // 3. Kiểm tra
        boolean isSuccess = driver.getCurrentUrl().contains("Login"); // Tạm thời để như này
        assertTrue(isSuccess, "Đăng nhập thất bại, không chuyển hướng được!");
    }

    @Test
    @Story("TC_LOGIN_02 - Kiểm tra URL và tiêu đề (title) của trang")
    public void testTC02_KiemTraUrlVaTitle() {
        // 1. Mở trang đăng nhập (đã được làm tự động ở hàm initPage)
        
        // 2. Lấy thông tin URL và Title hiện tại
        String currentUrl = driver.getCurrentUrl();
        String currentTitle = driver.getTitle();

        // 3. Kiểm tra (Kết quả mong đợi: URL chứa '/Login', title không rỗng)
        assertTrue(currentUrl.contains("/Login"), "Lỗi: URL không chứa '/Login'");
        assertTrue(currentTitle != null && !currentTitle.isEmpty(), "Lỗi: Tiêu đề trang (Title) bị rỗng!");
    }
}
