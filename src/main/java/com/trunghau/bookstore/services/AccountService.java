package com.trunghau.bookstore.services;

import com.trunghau.bookstore.entity.Account;
import com.trunghau.bookstore.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepo;

    public Account authenticate(String email, String password){
        Account acc = accountRepo.findByEmail(email);

        if(acc == null){
            return null; //TODO: return DTO đặc biệt mang ý nghĩa 1 email ko tồn tại, ném ngoại lệ X nào đó đại diện email ko tồn tại
        }

        if(!acc.getPassword().equals(password)){
            //TODO: decode password
            return null;
            //TODO: return DTO đb mang ý nghĩa sai pass, ngoại lệ Y
        }

        //chặn luôn role 3 - role member bon chen vào login
        if (acc.getRole() == 3) {
            return null; //TODO: 1 DTO hoặc ngoại lệ Z
        }

        return acc; //account với role = 1, 2 ĐƯỢC TRẢ VỀ
    }

    //DÀNH CHO CODE FIRST, VÀ DATA INITIALIZER, KO CẦN LÀM MÀN HÌNH TẠO MỚI ACCOUNT
    public void saveAccount(Account account) {
        accountRepo.save(account);
    }

}
