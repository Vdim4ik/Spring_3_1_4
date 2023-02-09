package com.example.spring_3_1_4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.spring_3_1_4.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u join fetch u.roles where u.username= :username")
    User findByUsername(String username);

    @Query("select distinct u from User u join fetch u.roles where u.email= :email")
    User findByEmail(String email);

}
