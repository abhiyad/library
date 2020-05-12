package com.springframework.repositories;
import com.springframework.domain.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser,String> {
    List<MyUser> findAll();
    MyUser findMyUserByUsername(String username);
    @Transactional
    @Modifying
    @Query("update MyUser c set c.issued_book = :book_name WHERE c.username = :username")
    void issue(@Param("username") String username, @Param("book_name")String book_name);

    @Transactional
    @Modifying
    @Query("update MyUser c set c.issued_book = null WHERE c.username = :username")
    void return_book(@Param("username") String username);
}
