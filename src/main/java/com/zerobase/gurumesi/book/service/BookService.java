package com.zerobase.gurumesi.book.service;

import com.zerobase.gurumesi.book.book.ApproveBookingForm;
import com.zerobase.gurumesi.book.book.BookDetailDto;
import com.zerobase.gurumesi.book.model.Book;
import com.zerobase.gurumesi.book.book.MakeBookingForm;
import com.zerobase.gurumesi.book.model.repository.BookRepository;
import com.zerobase.gurumesi.book.type.Status;
import com.zerobase.gurumesi.exception.CustomException;
import com.zerobase.gurumesi.exception.ErrorCode;
import com.zerobase.gurumesi.restaurant.domain.model.repository.RestaurantRepository;
import com.zerobase.gurumesi.user.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static com.zerobase.gurumesi.exception.ErrorCode.*;


@RequiredArgsConstructor
@Service
@Slf4j
public class BookService {
    private final BookRepository bookRepository;
    private final RestaurantRepository restaurantRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public Book makeBooking(MakeBookingForm form){
        LocalDateTime localDateTime = LocalDateTime.of
                (form.getYear(),form.getMonth(),form.getDay(),
                        form.getHour(), form.getMinute());

        //오늘 날짜 기준 30분 이후 예약부터 접수 가능
         if(localDateTime.isBefore(LocalDateTime.now().plusMinutes(30))){
            throw new CustomException(ErrorCode.PAST_BOOKING);
        }
        return bookRepository.save(Book.of(form));
    }

    @Transactional
    public Book approveBooking(ApproveBookingForm form){
        //부킹 Id 조회, 요청 상태가 아니면 에러.
        Book book = bookRepository.findByIdAndStatus(form.getBookingId(), Status.Requested)
                .orElseThrow(() -> new CustomException(NOT_FOUND_BOOKING));


        LocalDateTime start = book.getBookingTime().minusMinutes(50);
        LocalDateTime end = book.getBookingTime().plusMinutes(50);
        Long restaurantId = book.getRestaurantId();

        //승인 당시 예약 완료 된 테이블 수 계산(+-50분)
        int count = bookRepository.countByBookingTimeBetweenAndStatusAndRestaurantId(
                start,end, Status.Approved,restaurantId);

        //식당 테이블 수
        int limitTable = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_RESTAURANT))
                .getFree();

        //시간 +-50분 총합 테이블보다 많으면 승인 불가
        if (count >= limitTable){
            throw new CustomException(ErrorCode.FULLY_BOOKED);
        }

        //승인으로 상태 변경
        book.setStatus(Status.Approved);
        return book;
    }

    @Transactional
    public List<Book> checkBooking(Long customerId){
        return bookRepository.findByCustomerId(customerId);
    }

    @Transactional
    public List<BookDetailDto> ownerCheckBooking(Long restaurantId){
        return bookRepository.checkBookingOwner(restaurantId);
    }

}
