package com.postgresql.order.controller;

import com.postgresql.order.entity.Users;
import com.postgresql.order.exception.DuplicateResourceException;
import com.postgresql.order.exception.MainServiceBusinessException;
import com.postgresql.order.request.UserRequest;
import com.postgresql.order.service.CompanyService;
import com.postgresql.order.service.DepartmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.postgresql.order.service.UserService;

@RestController
//@RequestMapping("api/v1")
@Tag(name = "User Controller", description = "User Controller")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CompanyService companyService;

    private String SUCCESS = "success";


    @PostMapping("/create/user")
    public Users createUser(@RequestBody UserRequest userRequest) {
            Users user = new Users();
            user.setUserName(userRequest.getUserName());
            user.setAddress(userRequest.getAddress());
            user.setEmail(userRequest.getEmail());
            user.setDepartment(departmentService.getDepartment(userRequest.getDepartmentId()));
            return userService.createUser(user);
    }

    @PutMapping("/update/user/{userId}")
    public Users updateUser(@PathVariable Long userId, @RequestBody UserRequest userRequest) {

        return userService.updateUser(userId, userRequest);
    }

    @GetMapping("/get/user/{userId}")
    public Users getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/delete/user/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);

    }

}
