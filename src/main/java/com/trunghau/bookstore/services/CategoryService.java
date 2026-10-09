package com.trunghau.bookstore.services;

import com.trunghau.bookstore.entity.Category;
import com.trunghau.bookstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository cateRepo;

    public CategoryService(CategoryRepository cateRepo){
        this.cateRepo = cateRepo;
    }

    public void saveCategory(Category o) {
        cateRepo.save(o);  //hàm tự sinh
    }
    public List<Category> getAllCategories() {
        return cateRepo.findAll(); //hàm tự sinh, câu JPQL: SELECT c FROM CATEGORY c;  //ngầm sẽ là SELECT * FROM CATEGORY
    }
}
