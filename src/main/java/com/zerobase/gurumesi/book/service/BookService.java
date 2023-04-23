package com.zerobase.gurumesi.book.service;

import com.zerobase.gurumesi.book.book.ApproveBookingForm;
import com.zerobase.gurumesi.book.book.BookDetailDto;
import com.zerobase.gurumesi.book.book.CancelBookingForm;
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
import org.springframework.scheduling.annotation.Scheduled;
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

    // 고객 식당 부킹
    @Transactional
    public Book makeBooking(Long customerId, MakeBookingForm form){

        //레스토랑은 유효한가
        restaurantRepository.findById(form.getRestaurantId())
                .orElseThrow(() -> new CustomException(NOT_FOUND_RESTAURANT));

        //시간
        LocalDateTime localDateTime = LocalDateTime.of
                (form.getYear(),form.getMonth(),form.getDay(),
                        form.getHour(), form.getMinute());

        //오늘 날짜 기준 30분 이후 예약부터 접수 가능
         if(localDateTime.isBefore(LocalDateTime.now().plusMinutes(30))){
            throw new CustomException(ErrorCode.PAST_BOOKING);
        }
        return bookRepository.save(Book.of(customerId, form));
    }

    //고객 부킹 확인하기
    @Transactional
    public List<Book> checkBooking(Long customerId){
        return bookRepository.findByCustomerId(customerId);
    }

    //고객 부킹 취소하기
    @Transactional
    public Book cancelBooking(Long customerId, CancelBookingForm form){
        //부킹 찾아보기
        Book book = bookRepository.findByIdAndCustomerId
                        (form.getBookingId(), customerId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_AVAILABLE_BOOKING));

        //요청중이거나 승인 된 건인가?
        if(book.getStatus()==Status.Requested ||
                book.getStatus()==Status.Approved){
            book.setStatus(Status.User_Cancelled);
        } else {

        // 이미 취소되었거나 만료된 예약은 취소 안됨
            throw new CustomException(NOT_FOUND_AVAILABLE_BOOKING);
        }
            return book;
    }

    // 점주 부킹 승인
    @Transactional
    public Book approveBooking(Long ownerId, ApproveBookingForm form){
        //점주 확인
        restaurantRepository.findByOwnerIdAndId(ownerId, form.getRestaurantId()).
                orElseThrow(() -> new CustomException(NOT_AUTHORIZED_OWNER));

        //부킹 Id 조회, 요청 상태가 아니면 에러.
        Book book = bookRepository.findByIdAndStatus(form.getBookingId(), Status.Requested)
                .orElseThrow(() -> new CustomException(NOT_FOUND_BOOKING));


        //예약 시간 전후 50을 위한 기준 설정
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

        //시간 +-50분 기준 총합 테이블보다 많으면 승인 불가
        if (count >= limitTable){
            throw new CustomException(ErrorCode.FULLY_BOOKED);
        }
        //승인으로 상태 변경
        book.setStatus(Status.Approved);
        return book;
    }

    //점주 부킹 거절
    @Transactional
    public Book rejectBooking(Long ownerId, ApproveBookingForm form) {
        //점주 확인
        restaurantRepository.findByOwnerIdAndId(ownerId, form.getRestaurantId()).
                orElseThrow(() -> new CustomException(NOT_AUTHORIZED_OWNER));

        //부킹이 요청중이 아니면 거절 불가(정책 확인 필요 -> 승인건 도 취소가 되는지)
        Book book = bookRepository.findByIdAndStatus(form.getBookingId(), Status.Requested)
                .orElseThrow(() -> new CustomException(NOT_FOUND_BOOKING));

        //거절로 상태 변경
        book.setStatus(Status.Rejected);
        return book;
    }


    //점주 레스로랑 부킹 확인하기
    @Transactional
    public List<BookDetailDto> ownerCheckBooking(Long ownerId, Long restaurantId){
        //레스토랑의 주인인지 확인
        restaurantRepository.findByOwnerIdAndId(ownerId, restaurantId).
                orElseThrow(() -> new CustomException(NOT_AUTHORIZED_OWNER));

        return bookRepository.checkBookingOwner(restaurantId);
    }

    //키오스크 부킹 확인하기(레스토랑 / 전화번호)
    @Transactional
    public List<BookDetailDto> kioskCheckBooking(Long restaurantId, String phone){
        return bookRepository.kioskCustomer(restaurantId, phone);
    }

    //키오스크에서 컨펌하기
    @Transactional
    public Book kioskConfirmBooking(Long bookingId){
        //부킹 번호 확인
        Book book = bookRepository.findById(bookingId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_AVAILABLE_BOOKING));
        //주인의 승인 되었다면 완료 처리
        if(book.getStatus()==Status.Approved){
            book.setStatus(Status.Complete);
        } else {
            throw new CustomException(ErrorCode.NOT_FOUND_AVAILABLE_BOOKING);
        }

        return book;
    }


    //30초마다 현재 기준 10분남은 예약 노쇼/취소 처리
    @Scheduled(cron="0/30 * * * * ?")
    @Transactional
    public void noShowUpdateSchedule(){
        bookRepository.noShowUpdate(LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10));
        log.info("스케쥴테스트");
    }
}
