package com.zerobase.gurumesi.book.controller;

import com.zerobase.gurumesi.book.book.*;
import com.zerobase.gurumesi.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Booking Control", description = "Management Booking")
@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    //고객 부킹 생성
    @Operation(summary = "Booking by customer")
    @PostMapping("/customer")
    public ResponseEntity<?> makeBooking
            (final @Valid @RequestBody MakeBookingForm form,
             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }

        return ResponseEntity.ok(
                BookDto.from(bookService.makeBooking(form)));
    }

    //고객 부킹 취소
    @Operation(summary= "Cancel By customer")
    @PutMapping("/customer")
    public ResponseEntity<?> cancelBooking(@RequestBody CancelBookingForm form){
        return ResponseEntity.ok(bookService.cancelBooking(form));
    }

    //고객 부킹 히스토리 확인 가능
    @Operation(summary = "Customer can view his/her booking history")
    @GetMapping("/customer")
    public ResponseEntity<?> customerBookingView(Long customerID){
        return ResponseEntity.ok(bookService.checkBooking(customerID));
    }

    //점주 레스토랑 ID로 예약 조회 가능
    @Operation(summary = "Owner can view booking status")
    @GetMapping("/owner")
    public List<BookDetailDto> ownerViewBooking(Long restaurantId) {
        return bookService.ownerCheckBooking(restaurantId);
    }

    //점주 부킹 승인
    @Operation(summary = "Owner can approve a booking")
    @PutMapping("/owner/approve")
    public ResponseEntity<?> approveBooking(@RequestBody ApproveBookingForm form) {
        return ResponseEntity.ok(
                BookDto.from(bookService.approveBooking(form)));
    }

    //점주 부킹 거부
    @Operation(summary = "Owner can reject a booking")
    @PutMapping("/owner/reject")
    public ResponseEntity<?> rejectBooking(@RequestBody ApproveBookingForm form) {
        return ResponseEntity.ok(
                BookDto.from(bookService.rejectBooking(form)));
    }

    // 고객 해당 레스토랑에서 전화번호로 자신의 정보 조회, 승인된 건만 확인 가능
    @Operation(summary = "Customer can see available bookings in the restaurant " +
            "by his/her phone number")
    @GetMapping("/kiosk")
    public List<BookDetailDto> customerViewBooking(Long restaurantId, String phone) {
        return bookService.kioskCheckBooking(restaurantId, phone);
    }

    //Kiosk에서 사용 컨펌
    @Operation(summary = "Customer can confirm approved booking")
    @PutMapping("/kiosk")
    public ResponseEntity<?> customerConfirmationBooking(Long bookingId){
        return ResponseEntity.ok(
                BookDto.from(bookService.kioskConfirmBooking(bookingId)));
    }
}
