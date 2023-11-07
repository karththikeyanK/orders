package com.postgresql.order.repo;

import com.postgresql.order.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsOrganizationByCompanyName(String name);

}
