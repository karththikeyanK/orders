package com.postgresql.order.controller;

import com.postgresql.order.entity.Company;
import com.postgresql.order.entity.Department;
import com.postgresql.order.request.CompanyRequest;
import com.postgresql.order.request.DepartmentRequest;
import com.postgresql.order.service.CompanyService;
import com.postgresql.order.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("create/company")
    public Company createCompany(@RequestBody CompanyRequest companyRequest) {
        Company company = new Company();
        company.setCompanyName(companyRequest.getCompanyName());
        company.setCompanyRegNo(companyRequest.getCompanyRegNo());
        company.setCompanyAddress(companyRequest.getCompanyAddress());
        company.setOrganization(companyService.getOrganization(companyRequest.getOrganizationId()));
        return companyService.createCompany(company);
    }


    @PostMapping("create/departments")
    public Department createDepartment(@RequestBody DepartmentRequest departmentRequest) {
        Department department = new Department();
        department.setDepartmentName(departmentRequest.getDepartmentName());
        department.setDepartmentHead(departmentRequest.getDepartmentHead());
        department.setCompany(companyService.getCompany(departmentRequest.getCompanyId()));
        return departmentService.createDepartment(department);
    }






}
