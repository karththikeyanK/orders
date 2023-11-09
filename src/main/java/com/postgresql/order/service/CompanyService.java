package com.postgresql.order.service;

import com.postgresql.order.entity.Company;
import com.postgresql.order.entity.Organization;
import com.postgresql.order.exception.MainServiceBusinessException;
import com.postgresql.order.repo.CompanyRepository;
import com.postgresql.order.repo.OrganiztionsRepository;
import com.postgresql.order.request.CompanyRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@Slf4j
public class CompanyService {


    @Autowired
    private CompanyRepository companyRepository;

    public Company createCompany(Company company) throws MainServiceBusinessException {
        Company savedCompany;
        log.info("create company started");
        try{
            if(companyRepository.existsOrganizationByCompanyName(company.getCompanyName())){
                log.info("company already exists");
                throw new MainServiceBusinessException("Company with name " + company.getCompanyName() + " already exists");
            }
            savedCompany = companyRepository.save(company);
            log.info("company saved successfully {}", company );
        }catch (MainServiceBusinessException e){
            log.error("Exception occurred while persisting new company entry to the database, Exception message {}", e.getMessage());
            throw e;
        }
        catch (Exception e){
            log.error("Exception occurred while persisting new company entry to the database, Exception message {}", e.getMessage());
            throw new MainServiceBusinessException("Error creating company");
        }

        log.info("CompanyService:addNewCompany execution ended.");
        return savedCompany;
    }


    public Company getCompany(Long companyId){
        Company company;
        try {
            log.info("CompanyService:getCompany execution started.");
             company = companyRepository.findById(companyId)
                    .orElseThrow(() -> new MainServiceBusinessException("Company with id " + companyId + " does not exist"));
            log.info("CompanyService:getCompany retrieved company from the database: {}", company);
        } catch (MainServiceBusinessException e) {
            // This catches the custom exception for non-existent companies
            log.error("Company with id " + companyId + " does not exist in the database.");
            throw e;
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            log.error("Exception occurred while retrieving company by id from the database.", e);
            throw new RuntimeException("Exception occurred while fetching company by id from the Database", e);
        }
        return company;
    }


    public Company updateCompany(Long id, CompanyRequest companyRequest) {
        log.info("CompanyService:updateCompany execution started.");
        Company updatedCompany;
        try {
            Company company = companyRepository.findById(id).orElseThrow(() -> new MainServiceBusinessException("Company with id " + id + " not found"));

            if (companyRequest.getCompanyName() != null) {
                company.setCompanyName(companyRequest.getCompanyName());
            }
            if (companyRequest.getCompanyRegNo() != null) {
                company.setCompanyRegNo(companyRequest.getCompanyRegNo());
            }
            if (companyRequest.getCompanyAddress() != null) {
                company.setCompanyAddress(companyRequest.getCompanyAddress());
            }
            updatedCompany = companyRepository.save(company);

        }catch (MainServiceBusinessException e){
            log.error("Exception occurred while updating company with id {} from the database, Exception message {}", id, e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Exception occurred while updating company with id {} from the database, Exception message {}", id, e.getMessage());
            throw new RuntimeException("Company Service: Error updating company");
        }
        return updatedCompany;
    }


    public String deleteCompany(Long id) {
        try{
            log.info("CompanyService:deleteCompany execution started.");
            Company company = companyRepository.findById(id).orElseThrow(()->new MainServiceBusinessException("Company with id " + id + " not found"));
            companyRepository.delete(company);
            log.info("CompanyService:deleteCompany deleted company with id {} from the database", id);
            return "Company deleted successfully";
        }catch (MainServiceBusinessException e){
            log.error("There is no company with id {}", id);
            throw e;
        }catch (Exception e){
            log.error("Exception occurred while deleting company with id {} from the database, Exception message {}", id, e.getMessage());
            throw new RuntimeException("Company Service: Error deleting company : "+e.getMessage());
        }
    }
}
