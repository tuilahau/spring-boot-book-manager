package com.trunghau.bookstore.controller;

import com.trunghau.bookstore.entity.Account;
import com.trunghau.bookstore.services.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private AccountService acconutService;

    @GetMapping({"/", "/login"})
    public String showLogin(){
        return "login";
    }

    @GetMapping("/logout")
    public String doLogout(HttpSession session){
        session.invalidate(); //xóa biến looggedInUer tahy vì phải chờ 30p hết seesion default
        return "redirect:/login";
    }

    @PostMapping("/auth")
    public String loLogin(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session){
        Account acc = acconutService.authenticate(email, password);
        //hoặc null hoặc khác null - tức là match với email và pass

        if(acc == null){
            //vòng về gửi lại login và message
            model.addAttribute("errors", "Invalid crendentials!!!");
            return "login";
        }
        //MỖI USER SẼ CÓ 1 SESSION, SERVER CÓ NHIỀU QUẢNG TRƯỜNG
        session.setAttribute("loggedInUser", acc); //thùng model lâu dài hơn, dành cho từng acc
        //ram tomcat: Account loggedInUser = acc;
        return "redirect:/books";
    }
}
