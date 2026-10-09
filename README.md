bash -lc cat > /mnt/data/README.md <<'EOF'
<div align="center">

# 📚 Book Management System

### Ứng dụng web quản lý sách được xây dựng với Spring Boot

Quản lý thông tin sách, danh mục và quyền truy cập trong một giao diện web đơn giản, dễ sử dụng.

<p>
  <img src="https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white" alt="Java 21">
  <img src="https://img.shields.io/badge/Spring%20Boot-4.1.1-6DB33F?logo=springboot&logoColor=white" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Thymeleaf-Template%20Engine-005F0F?logo=thymeleaf&logoColor=white" alt="Thymeleaf">
  <img src="https://img.shields.io/badge/Database-SQL%20Server-CC2927?logo=microsoftsqlserver&logoColor=white" alt="SQL Server">
  <img src="https://img.shields.io/badge/Build-Maven-C71A36?logo=apachemaven&logoColor=white" alt="Maven">
</p>

</div>

---

## ✨ Giới thiệu

**Book Management System** là ứng dụng web quản lý sách được phát triển bằng Java và Spring Boot. Dự án tập trung vào các chức năng quản lý dữ liệu cơ bản, kết nối cơ sở dữ liệu quan hệ và tổ chức mã nguồn theo các lớp Controller, Service và Repository.

Ứng dụng cho phép người dùng xem và tìm kiếm sách; tài khoản có quyền quản trị có thể thêm, cập nhật và xóa sách. Mỗi cuốn sách được liên kết với một danh mục để việc phân loại và hiển thị dữ liệu rõ ràng hơn.

## 🚀 Chức năng chính

| Chức năng | Mô tả |
|---|---|
| 📖 Quản lý sách | Xem danh sách, thêm mới, chỉnh sửa và xóa sách |
| 🔎 Tìm kiếm | Tìm sách theo tên sách hoặc tên tác giả |
| 🗂️ Quản lý danh mục | Gắn sách với danh mục tương ứng và chọn danh mục khi tạo/sửa sách |
| 🔐 Đăng nhập | Lưu thông tin đăng nhập bằng session để kiểm tra quyền truy cập |
| 👥 Phân quyền | Phân biệt quyền quản trị và người dùng; giới hạn thao tác quản lý sách theo vai trò |
| ✅ Kiểm tra dữ liệu | Sử dụng Jakarta Bean Validation để kiểm tra dữ liệu khi gửi biểu mẫu |
| 🧩 Kiểm tra mã sách | Phát hiện ID sách trùng khi tạo mới và hiển thị thông báo cho người dùng |
| 🖥️ Giao diện web | Hiển thị dữ liệu bằng Thymeleaf, HTML và CSS |

## 🛠️ Công nghệ sử dụng

- **Java 21** — ngôn ngữ lập trình chính.
- **Spring Boot** — xây dựng và cấu hình ứng dụng web.
- **Spring Web MVC** — tiếp nhận request và điều hướng giữa các trang.
- **Spring Data JPA / Hibernate** — ánh xạ đối tượng Java với dữ liệu quan hệ và thực hiện thao tác dữ liệu.
- **Thymeleaf** — kết xuất giao diện HTML động.
- **Microsoft SQL Server** — lưu trữ dữ liệu sách, danh mục và tài khoản.
- **Jakarta Bean Validation** — kiểm tra dữ liệu đầu vào.
- **Maven** — quản lý dependency và build dự án.

## 🧱 Cách tổ chức ứng dụng

Dự án áp dụng cách tổ chức nhiều lớp để tách phần nhận yêu cầu, xử lý nghiệp vụ và truy cập dữ liệu.

```mermaid
flowchart TD
    U[Người dùng trên trình duyệt] --> V[Thymeleaf View]
    V <--> C[Controller]
    C --> S[Service]
    S --> R[Repository / Spring Data JPA]
    R --> D[(SQL Server)]
