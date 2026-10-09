package com.trunghau.bookstore.config;

import com.trunghau.bookstore.entity.Account;
import com.trunghau.bookstore.entity.Book;
import com.trunghau.bookstore.entity.Category;
import com.trunghau.bookstore.services.AccountService;
import com.trunghau.bookstore.services.BookService;
import com.trunghau.bookstore.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitilalizer implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CategoryService cateService;

    @Override
    public void run(String... args) throws Exception {
        accountService.saveAccount(new Account("ad@az.com", "ad", 1));
        accountService.saveAccount(new Account("st@az.com", "st", 2));
        accountService.saveAccount(new Account("mb@az.com", "mb", 3));

        //TẠO OBJECT, NHỜ SERVICE ĐẨY XUỐNG
        //TẠO TABLE 1 TRƯỚC, N SAU (DO KHOÁ NGOẠI THAM CHIẾU KHOÁ CHÍNH)
        Category cate1 = new Category("Chữa lành", "...");
        Category cate2 = new Category("Tuổi trẻ và thanh xuân", "...");
        Category cate3 = new Category("Tình yêu và cảm xúc", "...");
// TẠO BOOK, MỖI BOOK THUỘC VỀ MỘT CATEGORY

        cate1.addBook(new Book(
                "Hiểu Về Trái Tim",
                "Minh Niệm",
                10,
                89_000
        ));

        cate1.addBook(new Book(
                "Thương Mình Một Chút Cũng Đâu Có Sao",
                "Mộc Trầm",
                12,
                95_000
        ));

        cate2.addBook(new Book(
                "Cho Tôi Xin Một Vé Đi Tuổi Thơ",
                "Nguyễn Nhật Ánh",
                15,
                85_000
        ));

        cate2.addBook(new Book(
                "Mắt Biếc",
                "Nguyễn Nhật Ánh",
                8,
                110_000
        ));

        cate3.addBook(new Book(
                "Những Ngày Chênh Vênh",
                "Khánh Mây",
                10,
                79_000
        ));

        cate3.addBook(new Book(
                "Ngày Hôm Nay Cũng Đáng Được Dịu Dàng",
                "Mộc Trầm",
                7,
                92_000
        ));

        //xuống table hoy khi tomcat chạy, CASCADE ALL
        //NGHĨA LÀ TABLE 1 ĐI XUỐNG THÌ TABLE N XUỐNG LUÔN!!! VÀ LIST SÁCH BỊ DOMINO XUỐNG THEO LUÔN
        cateService.saveCategory(cate1);
        cateService.saveCategory(cate2);
        cateService.saveCategory(cate3);
    }
}
