package com.trunghau.bookstore.repository;

import com.trunghau.bookstore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

 }
