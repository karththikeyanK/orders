package com.postgresql.order.service;

import com.postgresql.order.entity.Department;
import com.postgresql.order.exception.MainServiceBusinessException;
import com.postgresql.order.repo.DepartmentRepository;
import com.postgresql.order.request.DepartmentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department createDepartment(Department department) {
        log.info("DepartmentService:createDepartment execution started.");
        try{
            if(departmentRepository.existsDepartmentByDepartmentName(department.getDepartmentName())){
                throw new MainServiceBusinessException("Department with name " + department.getDepartmentName() + " already exists");
            }
            log.info("DepartmentService:createDepartment saving department to the database: {}", department);
            return departmentRepository.save(department);
        }catch (MainServiceBusinessException e){
            log.error("DepartmentService:createDepartment Exception occurred while persisting new department entry to the database, Exception message {}", e.getMessage());
            throw e;
        }
        catch (Exception e){
            log.error("DepartmentService:createDepartment Exception occurred while persisting new department entry to the database, Exception message {}", e.getMessage());
            throw new RuntimeException("Error creating department");
        }

    }

    public Department getDepartment(Long departmentId) throws MainServiceBusinessException {
        log.info("DepartmentService:getDepartment execution started.");
        try {
            return departmentRepository.findById(departmentId).orElseThrow(()->
                    new MainServiceBusinessException("Department with id " + departmentId + " does not exist"));
        }catch (MainServiceBusinessException e){
            // This catches the custom exception for non-existent departments
            log.error("Department Service: Department with id " + departmentId + " does not exist in the database.");
            throw e;
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            log.error("Department Service: Exception occurred while retrieving department by id from the database.", e);
            throw new RuntimeException("Error getting department");
        }

    }

    public Department updateDepartment(Long departmentId, DepartmentRequest department) throws MainServiceBusinessException {
        log.info("DepartmentService:updateDepartment execution started.");
        try {
            Department departmentToUpdate = departmentRepository.findById(departmentId).orElseThrow(()->
                    new MainServiceBusinessException("Department with id " + departmentId + " does not exist"));
            if(departmentRepository.existsDepartmentByDepartmentName(department.getDepartmentName())){
                throw new MainServiceBusinessException("Department with name " + department.getDepartmentName() + " already exists");
            }
            if (department.getDepartmentName() != null ) {
                departmentToUpdate.setDepartmentName(department.getDepartmentName());
            }
            return departmentRepository.save(departmentToUpdate);
        }catch (MainServiceBusinessException e){
            // This catches the custom exception for non-existent departments
            log.error("Department Service: Department with id " + departmentId + " does not exist in the database.");
            throw e;
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            log.error("Department Service: Exception occurred while updating department by id from the database.", e);
            throw new RuntimeException("Error updating department");
        }

    }


    public String deleteDepartment(Long id){
        try {
            log.info("DepartmentService:deleteDepartment execution started.");
            Department department = departmentRepository.findById(id).orElseThrow(()->
                    new MainServiceBusinessException("Department with id " + id + " does not exist"));
            departmentRepository.delete(department);
            log.info("DepartmentService:deleteDepartment execution ended.");
            return "Department Deleted Sucesfully";
        }catch (MainServiceBusinessException e){
            // This catches the custom exception for non-existent departments
            log.error("Department Service: Department with id " + id + " does not exist in the database.");
            throw e;
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            log.error("Department Service: Exception occurred while deleting department by id from the database.", e);
            throw new RuntimeException("Error deleting department");
        }
    }

}
