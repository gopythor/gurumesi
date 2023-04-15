package com.zerobase.gurumesi.book.controller;

import com.zerobase.gurumesi.book.book.ApproveBookingForm;
import com.zerobase.gurumesi.book.book.BookDetailDto;
import com.zerobase.gurumesi.book.book.BookDto;
import com.zerobase.gurumesi.book.book.MakeBookingForm;
import com.zerobase.gurumesi.book.model.Book;
import com.zerobase.gurumesi.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("customer/make")
    public ResponseEntity<?> makeBooking
            (final @Valid @RequestBody MakeBookingForm form,
             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }

        return ResponseEntity.ok(
                BookDto.from(bookService.makeBooking(form)));
    }

    @GetMapping("/customer/view")
    public ResponseEntity<?> customerBookingView(Long customerID){
        return ResponseEntity.ok(bookService.checkBooking(customerID));
    }

    @PutMapping("owner/approve")
    public ResponseEntity<?> approveBooking(@RequestBody ApproveBookingForm form) {
        return ResponseEntity.ok(
                BookDto.from(bookService.approveBooking(form)));
    }

    @GetMapping("owner/view")
    public List<BookDetailDto> ownerViewBooking(Long restaurantId) {
        return bookService.ownerCheckBooking(restaurantId);
    }

}
