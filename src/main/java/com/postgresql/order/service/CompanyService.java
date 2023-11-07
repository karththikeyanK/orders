package com.postgresql.order.service;

import com.postgresql.order.entity.Company;
import com.postgresql.order.entity.Organization;
import com.postgresql.order.exception.MainServiceBusinessException;
import com.postgresql.order.repo.CompanyRepository;
import com.postgresql.order.repo.OrganiztionsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CompanyService {
    @Autowired
    private OrganiztionsRepository organiztionsRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public Company createCompany(Company company) throws MainServiceBusinessException {
        log.info("create company started");
        try{
            if(companyRepository.existsOrganizationByCompanyName(company.getCompanyName())){
                throw new MainServiceBusinessException("Company with name " + company.getCompanyName() + " already exists");
            }
            log.info("company already exists");
            companyRepository.save(company);
            log.debug("company saved successfully {}", company );
        } catch (Exception e){
            throw new MainServiceBusinessException("Error creating company");
        }
        return company;
    }


    public Organization getOrganization(Long organizationId) throws MainServiceBusinessException {

        try{
            if(!organiztionsRepository.existsById(organizationId)){
                throw new MainServiceBusinessException("Organization with id " + organizationId + " does not exist");
            }
            return organiztionsRepository.findById(organizationId).orElse(null);
        } catch (Exception e){
            throw new MainServiceBusinessException("Error getting organization");
        }


    }

    public Company getCompany(Long companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }
}
