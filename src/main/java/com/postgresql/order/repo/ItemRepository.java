package com.postgresql.order.repo;

import com.postgresql.order.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean existsByName(String name);
}
