package com.example.demonorn.repository;

import com.example.demonorn.dao.enity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {
  @Query(value = "select * from role where name= :name",nativeQuery = true)
  Role findByName(@Param("name") String name);
}
