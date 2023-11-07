package com.postgresql.order.controller;

import com.postgresql.order.entity.Users;
import com.postgresql.order.exception.DuplicateResourceException;
import com.postgresql.order.exception.MainServiceBusinessException;
import com.postgresql.order.request.UserRequest;
import com.postgresql.order.service.CompanyService;
import com.postgresql.order.service.DepartmentService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.postgresql.order.service.UserService;

@RestController
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

//    @PostMapping("/create/user")
//    public Users createUser(@RequestBody Users users) {
//        try {
//            return userService.createUser(users);
//        } catch (DuplicateResourceException e) {
//            throw new DuplicateResourceException("User already exists");
//        }
//    }
//
//    @PostMapping("/create/user")
//    public ResponseEntity<ApiResponse> Users createUser(@RequestBody Users users) {
//        try{
//            userService.createUser(users);
//            ApiResponse<Users> usersApiResponse = ApiResponse
//                    .builder()
//                    .status(SUCCESS)
//                    .results(users)
//                    .build();
//        }
//    }


}
