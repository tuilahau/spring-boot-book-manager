package com.trunghau.bookstore.repository;

import com.trunghau.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    public List<Book> getAllByOrderByCreatedAtDesc();

    List<Book> searchAllByNameContainingIgnoreCaseOrAuthorNameContainingIgnoreCase(String keyword, String keyword1);
}
