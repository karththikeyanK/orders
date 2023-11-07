package com.postgresql.order.repo;

import com.postgresql.order.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    boolean existsUsersByEmail(String email);
}
