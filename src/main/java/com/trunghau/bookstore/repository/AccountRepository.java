package com.trunghau.bookstore.repository;

import com.trunghau.bookstore.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByEmail(String email);
}
