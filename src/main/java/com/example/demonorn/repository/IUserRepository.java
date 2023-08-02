package com.example.demonorn.repository;

import com.example.demonorn.dao.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
  @Query(value = "select * from user where username= :username",nativeQuery = true)
  User findByUsername(@Param("username") String username);

  boolean existsByUsernameAndEmail(@Param("username") String username , @Param("email") String email);
}
