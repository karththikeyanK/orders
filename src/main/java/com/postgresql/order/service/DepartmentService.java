package com.postgresql.order.service;

import com.postgresql.order.entity.Department;
import com.postgresql.order.exception.MainServiceBusinessException;
import com.postgresql.order.repo.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department getDepartment(Long departmentId) throws MainServiceBusinessException {

        try {
            if(!departmentRepository.existsById(departmentId)){
                throw new MainServiceBusinessException("Department with id " + departmentId + " does not exist");
            }
            return departmentRepository.findById(departmentId).orElse(null);
        }catch (Exception e){
            throw new MainServiceBusinessException("Error getting department");
        }
    }
}
