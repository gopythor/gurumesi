package com.zerobase.gurumesi.book.service;

import com.zerobase.gurumesi.book.model.Book;
import com.zerobase.gurumesi.book.book.MakeBookingForm;
import com.zerobase.gurumesi.book.model.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;

    @Transactional
    public Book addRestaurant(MakeBookingForm form){
        return bookRepository.save(Book.of(form));
    }
}
