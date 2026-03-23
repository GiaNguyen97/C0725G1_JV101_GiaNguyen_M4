# HƯỚNG DẪN SỬA DỰ ÁN KHI ĐI THI (VÍ DỤ: QUẢN LÝ HỌC SINH / BẤT ĐỘNG SẢN)

Khi đề bài thay đổi từ **Quản lý Sản phẩm** sang **Quản lý Học sinh** (hoặc Bất động sản), bạn cần thay đổi theo trình tự sau để tránh bỏ sót lỗi.

## 1. Thay đổi Database & Entity (MODEL)
Đây là bước quan trọng nhất. Nếu sai ở đây, toàn bộ code sau sẽ lỗi.

*   **File:** `model/Product.java` -> Đổi tên thành `model/Student.java` (hoặc `RealEstate.java`).
*   **Fields (Trường):**
    *   `code` (Mã SP) -> `studentCode` (Mã HS), `propertyCode` (Mã BĐS).
    *   `name` (Tên SP) -> `fullName` (Tên HS), `location` (Vị trí).
    *   `price` -> `score` (Điểm số), `area` (Diện tích), `price` (Giá bán).
    *   `manufactureDate` -> `dateOfBirth` (Ngày sinh), `listingDate` (Ngày đăng bán).
    *   `category` (Loại SP) -> `classroom` (Lớp học), `propertyType` (Loại nhà đất).
*   **Validation (@Annotations):** Quan trọng!
    *   `@Min(1000)` cho Giá -> `@Min(0), @Max(10)` cho Điểm số.
    *   `@Past` cho Ngày sinh (Ngày sinh phải trong quá khứ) thay vì logic Hạn sử dụng.
    *   Kiểm tra kỹ Regex cho Mã (Ví dụ: Mã HS phải là `HS-XXXX`).

## 2. Thay đổi Repository (DATA LAYER)
*   **File:** `repository/ProductRepository.java` -> `StudentRepository.java`.
*   **Query (@Query):** Sửa lại câu JPQL.
    *   Sửa `SELECT p FROM Product p` thành `SELECT s FROM Student s`.
    *   Sửa các điều kiện `WHERE`:
        *   Thay `p.name` -> `s.fullName`.
        *   Thay `p.price` -> `s.score` ...
    *   **Lưu ý:** Các tham số `@Param("...")` trong câu Query phải khớp với tên biến trong phương thức bên dưới.

## 3. Thay đổi Service (BUSINESS LOGIC)
*   **File:** `service/ProductService.java` (Interface & Impl) -> `StudentService.java`.
*   **Logic nghiệp vụ:**
    *   Xóa logic "Ngày SX phải trước Hạn SD".
    *   Thêm logic mới (nếu đề yêu cầu): Ví dụ "Tuổi học sinh phải > 6", "Giá đất không được thấp hơn 100tr".
*   **Tìm kiếm:**
    *   Cập nhật các tham số truyền vào hàm `search()` cho phù hợp với đề mới (VD: tìm theo Khoảng Điểm thay vì Khoảng Giá).

## 4. Thay đổi Controller (THE GLUE)
*   **File:** `controller/ProductController.java` -> `StudentController.java`.
*   **RequestMapping:** Đổi `@RequestMapping("/products")` thành `/students`.
*   **ModelAttribute:** Trong các hàm `create`, `edit`, đổi tên attribute:
    *   `model.addAttribute("product", ...)` -> `model.addAttribute("student", ...)`
    *   Điều này ảnh hưởng trực tiếp đến file HTML (th:object="${...}").

## 5. Thay đổi HTML Views (FRONTEND)
Cần dùng công cụ **Find & Replace (Ctrl + Shift + R)** để thay thế nhanh, nhưng phải cẩn thận.

*   **Thư mục:** Đổi tên thư mục `templates/product` -> `templates/student`.
*   **Thay thế từ khóa:**
    *   `product` -> `student`.
    *   `products` -> `students` (đường dẫn URL).
*   **Form Input:**
    *   Sửa `type="number"` (giá) thành `type="text"` (tên) hoặc `date` tùy trường mới.
    *   Sửa các `th:field="*{...}"` cho khớp với tên trường mới trong Entity `Student`.
*   **Bảng (Table):** Sửa tiêu đề cột (Header) và dữ liệu (Body).

## ⚠️ CÁC LƯU Ý SỐNG CÒN KHI ĐI THI
1.  **Chạy thử liên tục:** Sửa xong Entity -> Chạy thử. Sửa xong Repo -> Chạy thử. Đừng sửa hết 1 lượt mới chạy, rất khó tìm lỗi.
2.  **DTO? Không cần thiết:** Với đề thi module 4 thường không yêu cầu DTO quá phức tạp, dùng trực tiếp Entity cho nhanh trừ khi đề bắt buộc.
3.  **Validation:**
    *   Chú ý `@NotBlank` (Bắt buộc nhập chuỗi).
    *   Chú ý `@NotNull` (Bắt buộc nhập số/ngày - `@NotEmpty` không dùng cho số).
    *   Xử lý lỗi `BindingResult` trong Controller để không bị trang trắng (White Label Error) khi người dùng nhập sai.
4.  **Phân trang:** Nếu đề không yêu cầu tìm kiếm phức tạp, hãy làm **Hiển thị danh sách** trước -> **Thêm mới** -> **Sửa/Xóa**. Làm Tìm kiếm và Phân trang cuối cùng nếu còn giờ.
5.  **Backup:** Trước khi sửa lớn (như đổi tên hàng loạt), hãy copy backup project ra một thư mục khác.

## CHECKLIST TRƯỚC KHI NỘP BÀI
- [ ] CRUD cơ bản (Thêm, Sửa, Xóa, Xem) hoạt động tốt.
- [ ] Validate dữ liệu đầu vào (Không được để trống, đúng định dạng).
- [ ] Hiển thị thông báo (Toast/Alert) sau khi thao tác.
- [ ] Dữ liệu tìm kiếm được giữ lại trên Form sau khi bấm "Tìm kiếm".
- [ ] Code không còn comment thừa, import thừa (Ctrl + Alt + O trong IntelliJ).
