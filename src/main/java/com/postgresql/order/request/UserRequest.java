package com.postgresql.order.request;

public class UserRequest {

    private String userName;
    private String email;
    private String address;

    private Long departmentId;

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public Long getDepartmentId() {
        return departmentId;
    }
}
