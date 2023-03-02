package com.example.game.repository;

import com.example.game.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from user where password=:password and user_name=:username", nativeQuery = true)
    User findUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
