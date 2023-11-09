package com.postgresql.order.controller;

import com.postgresql.order.entity.Company;
import com.postgresql.order.entity.Department;
import com.postgresql.order.request.CompanyRequest;
import com.postgresql.order.request.DepartmentRequest;
import com.postgresql.order.service.CompanyService;
import com.postgresql.order.service.DepartmentService;
import com.postgresql.order.service.OrganizationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("api/v1")
@Tag(name = "Company Controller", description = "Company Controller")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private OrganizationService organizationService;

    @PostMapping("create/company")
    public Company createCompany(@RequestBody CompanyRequest companyRequest) {
        Company company = new Company();
        company.setCompanyName(companyRequest.getCompanyName());
        company.setCompanyRegNo(companyRequest.getCompanyRegNo());
        company.setCompanyAddress(companyRequest.getCompanyAddress());
        company.setOrganization(organizationService.getOrganization(companyRequest.getOrganizationId()));
        return companyService.createCompany(company);
    }


    @GetMapping("get/company/{id}")
    public Company getCompanyById (@PathVariable Long id){
        return companyService.getCompany(id);
    }


    @PutMapping("update/company/{id}")
    public Company updateCompany(@PathVariable Long id, @RequestBody CompanyRequest companyRequest){
        return companyService.updateCompany(id, companyRequest);

    }

    @DeleteMapping("delete/company/{id}")
    public String deleteCompany(@PathVariable Long id){
        return companyService.deleteCompany(id);
    }

}
