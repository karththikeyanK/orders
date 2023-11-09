package com.postgresql.order.controller;

import com.postgresql.order.entity.Department;
import com.postgresql.order.request.DepartmentRequest;
import com.postgresql.order.service.CompanyService;
import com.postgresql.order.service.DepartmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("api/v1")
@Tag(name = "Department Controller", description = "Department Controller")
public class DepartmentController {


    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CompanyService companyService;

    @PostMapping("create/departments")
    public Department createDepartment(@RequestBody DepartmentRequest departmentRequest) {
        Department department = new Department();
        department.setDepartmentName(departmentRequest.getDepartmentName());
        department.setDepartmentHead(departmentRequest.getDepartmentHead());
        department.setCompany(companyService.getCompany(departmentRequest.getCompanyId()));
        return departmentService.createDepartment(department);
    }

    @GetMapping("get/departments/{id}")
    public Department getDepartmentById(@PathVariable Long id){
        return departmentService.getDepartment(id);
    }

    @PutMapping("update/departments/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody DepartmentRequest departmentRequest){
        return departmentService.updateDepartment(id, departmentRequest);
    }

    @DeleteMapping("delete/departments/{id}")
    public void deleteDepartment(@PathVariable Long id){
        departmentService.deleteDepartment(id);
    }
}
