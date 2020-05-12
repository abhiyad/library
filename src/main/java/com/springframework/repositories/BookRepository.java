package com.springframework.repositories;
import com.springframework.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,String> {
    List<Book> findAll();
    Book findBookByName(String name);

    @Transactional
    @Modifying
    @Query("update Book c set c.copies = c.copies - 1 WHERE c.name = :book_name")
    void issue(@Param("book_name")String book_name);

    @Transactional
    @Modifying
    @Query("update Book c set c.copies = c.copies + 1 WHERE c.name = :book_name")
    void return_book(@Param("book_name")String book_name);
}
