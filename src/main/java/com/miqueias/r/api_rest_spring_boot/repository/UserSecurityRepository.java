package com.miqueias.r.api_rest_spring_boot.repository;

import com.miqueias.r.api_rest_spring_boot.model.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserSecurityRepository extends JpaRepository<UserSecurity, Long> {

    @Query("SELECT u FROM UserSecurity WHERE u.userName =: userName")
    UserSecurity findByUsername(@Param("username") String username);

}
