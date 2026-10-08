# Du an Kiem thu Tu dong - Dang nhap Van phong Dien tu UTC

## 1. Gioi thieu Du an
Du an nay duoc phat trien nham muc dich kiem thu tu dong (Automation Testing) chuc nang Dang nhap cua he thong Van phong Dien tu Truong Dai hoc Giao thong Van tai (UTC).
Du an ap dung cac cong nghe va mo hinh tieu chuan trong nganh kiem thu phan mem hien nay.

![Bao cao Allure Report](report.png)


## 2. Cong nghe su dung
- Ngon ngu lap trinh: Java 17
- Framework kiem thu: JUnit 5
- Cong cu tu dong hoa: Selenium WebDriver
- Cong cu quan ly buid: Gradle
- Bao cao kiem thu: Allure Report
- Mo hinh thiet ke: Page Object Model (POM)

## 3. Cau truc thu muc
- src/test/java/vn/edu/utc/base/: Chua lop BaseTest, cau hinh khoi tao va dong trinh duyet Chrome tu dong.
- src/test/java/vn/edu/utc/pages/: Chua lop LoginPage ap dung mo hinh Page Object Model, dong goi cac phan tu (locators) va cac hanh dong tren trang.
- src/test/java/vn/edu/utc/tests/: Chua lop LoginTests gom 22 kịch bản kiem thu (Test Cases).
- src/test/java/vn/edu/utc/utils/: Chua cac lop tien ich (Utilities) ho tro du an.

## 4. Danh sach Kich ban Kiem thu (22 Test Cases)
Du an bao phu toan dien cac tinh huong kiem thu tu co ban den nang cao, bao gom:
- Kiem tra giao dien (UI/UX): Hien thi thanh phan, placeholder, URL, Title, Tab thu tu di chuyen...
- Kiem tra chuc nang (Functional): Dang nhap thanh cong, tai khoan sai, mat khau sai, bo trong thong tin...
- Kiem tra bien (Boundary): Chuoi ky tu rat dai, ky tu khoang trang dau cuoi...
- Kiem tra bao mat (Security): SQL Injection, XSS, an ky tu mat khau...
- Kiem tra luu tru phien (Session): Checkbox luu dang nhap...

## 5. Huong dan cai dat va chay chuong trinh
De chay duoc du an nay tren may ca nhan, can thuc hien cac buoc sau:

Buoc 1: Mo Terminal hoac PowerShell tai thu muc goc cua du an.
Buoc 2: Chay lenh sau de thuc thi toan bo 22 kịch ban kiem thu:
  .\gradlew clean test

Buoc 3: Sau khi tien trinh test hoan tat (hien thi BUILD SUCCESSFUL), chay lenh sau de xuat va xem bao cao Allure:
  .\gradlew allureServe

Trinh duyet se tu dong mo ra trang bao cao chi tiet ve so luong test case Pass/Fail.

## 6. Luu y
- Phan mem yeu cau Chrome va thu vien Selenium tu dong tai ChromeDriver phu hop.
- De dam bao report luon hien thi dung so luong, hay luon them tham so "clean" truoc khi chay "test".
