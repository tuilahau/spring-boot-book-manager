package com.trunghau.bookstore.controller;

import com.trunghau.bookstore.entity.Account;
import com.trunghau.bookstore.entity.Book;
import com.trunghau.bookstore.services.BookService;
import com.trunghau.bookstore.services.CategoryService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller //1. Chịu trách nhiệm hứng các url từ trình duyệt (Get, Post..)
               //hứng data gửi lên server
               //2. Gọi hàm xử lý
               //3. Return lại tên trang web, hoặc 1 url khác (redirect)
public class BookController {

    //tiêm bookService qua nhiều cách: field, contructor,setter
    @Autowired
    private BookService bookService;

    //tiêm thêm CateService để lấy danh sách Cates để show trong màn hình book-form
    //ta xài full data của bảng 1 trong mối quan hệ 1-N, để show ra drop-down
    @Autowired
    private CategoryService categoryService; //tự IoC

   @GetMapping("/books") //CONTROLLER
    public String showList(@RequestParam(name = "keyword", required = false, defaultValue = "") String keyword, Model model, HttpSession session){
       //MODEL, thùng chứa info sẽ gửi cho trang trước khi render
       Account acc = (Account) session.getAttribute("loggedInUser");

       if(acc ==  null){
           //chưa login, vì tìm ko thấy info đã login nằm trong quảng trường, thùng dài lâu session
           return "redirect:/login";
       }

       //kiểm tra keyword có hay ko, c thì search gần đúng - LIKE, RELATIVE SEARCH
       if (!keyword.equals("")) {
           model.addAttribute("books", bookService.searchBooksByNameOrAuthor(keyword));
       }
       else {
           //ko có thì show full
           //                List<Book> books = bookService.getAllBooks()
           model.addAttribute("books", bookService.getAllBooks());  //gửi ds book đọc từ table, vào thùng cho view dùng
       }

       return "books";  //.html  VIEW
    }

    @GetMapping("/books/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model, HttpSession session) {
        Account acc = (Account) session.getAttribute("loggedInUser");
        if (acc == null) {
            return "redirect:/login";
        }

        if (acc.getRole() == 2) {
            return "redirect:/books";
        }

        model.addAttribute("selectedOne", bookService.getBookById(id));

        model.addAttribute("cates", categoryService.getAllCategories());

        //GỬI THÊM EM PHẤT CỜ TRẠNG THÁI/BIẾN FLAG LƯU CÁI MODE/TRẠNG THÁI, CHẾ ĐỘ MỞ MÀN HÌNH
        model.addAttribute("formMode", "edit");
        return "book-form";
    }

    @GetMapping("/books/create")
    public String create(Model model, HttpSession ss) {
        Account acc = (Account) ss.getAttribute("loggedInUser");
        if (acc == null) {
            return "redirect:/login";
        }

        if (acc.getRole() == 2) {
            return "redirect:/books";
        }

        //TẠO MỚI VẪN PHẢI GỬI 1 SELECTED OBJECT CHÍNH LÀ 1 OBJ book BÊN TRONG DEFAULT, CHUỖI KO GÌ CẢ, SỐ LÀ 0, BOOLEAN LÀ SAI
        model.addAttribute("selectedOne", new Book());  //gửi ds book đọc từ table, vào thùng cho view dùng

        model.addAttribute("cates", categoryService.getAllCategories() );

        model.addAttribute("formMode", "new");
        return "book-form";
    }

    //link save
    @PostMapping("/books/save")
    public String save(@Valid @ModelAttribute("selectedOne") Book book, BindingResult result, Model model, @RequestParam("formMode") String formMode) {
        //save xong trả về trang books, show danh sách có book mới, hoặc book đã đc cập nhật!!!
        //NẾU CÓ LỖI NHẬP TRONG QUÁ TRÌNH BINDING, THÌ VÒNG LẠI MÀN HÌNH NHẬP
        if (result.hasErrors()) {
            //gởi thêm cates khi ở màn hình show error
            model.addAttribute("cates", categoryService.getAllCategories() );

            //gửi lại formMode nhen
            model.addAttribute("formMode", formMode);
            //                                       "new", "edit" đc gửi khi nhấn [Save]

            return "book-form";
        }

        //nhờ Service save: key mới thì là insert, key cũ thì là update, tự Hibernate nó tính cho mình qua thằng Repo
        if (formMode.equals("new")) {
            //check key trùng...
            if (bookService.existsBook(book.getId())) {
                //trùng key rồi, ko save đc, vòng lại màn hình nhập
                //gởi thêm cates khi ở màn hình show error
                model.addAttribute("cates", categoryService.getAllCategories() );

                //gửi lại formMode nhen
                model.addAttribute("formMode", formMode);
                model.addAttribute("duplicated", "Duplicated Id. Input another one!");
                return "book-form";
            }
        }

        //ko trùng id thì save bình thường
        //và còn dùng lun cho edit
        bookService.saveBook(book);

        return "redirect:/books";
    }

    @GetMapping("/books/delete/{id}")
    public String delete(@PathVariable("id") Long id, HttpSession ss) {
        Account acc = (Account) ss.getAttribute("loggedInUser");
        if (acc == null) {
            return "redirect:/login";
        }

        if (acc.getRole() == 2) {
            return "redirect:/books";
        }
        bookService.deleteBook(id);
        return "redirect:/books";
    }


}
