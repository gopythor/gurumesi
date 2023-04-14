package com.zerobase.gurumesi.book.model.repository;

import com.zerobase.gurumesi.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
