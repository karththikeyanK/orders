package com.postgresql.order.repo;

import com.postgresql.order.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganiztionsRepository extends JpaRepository<Organization, Long> {

    boolean existsOrganizationByName(String name);
}
