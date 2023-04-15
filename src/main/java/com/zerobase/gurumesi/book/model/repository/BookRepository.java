package com.zerobase.gurumesi.book.model.repository;

import com.zerobase.gurumesi.book.book.BookDetailDto;
import com.zerobase.gurumesi.book.model.Book;
import com.zerobase.gurumesi.book.type.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    int countByBookingTimeBetweenAndStatusAndRestaurantId
            (LocalDateTime start, LocalDateTime end, Status status, Long id);
    Optional<Book> findByIdAndStatus(Long id, Status status);
    List<Book> findByCustomerId(Long CustomerId);

    @Query(value = "select b.id, b.booking_Time, b.customer_Id, b.restaurant_Id, c.name, c.phone " +
            "from Book b inner join Customer c on b.customer_Id = c.id " +
            "where b.restaurant_Id =?1", nativeQuery = true)
    List<BookDetailDto> checkBookingOwner(Long restaurantId);
}
