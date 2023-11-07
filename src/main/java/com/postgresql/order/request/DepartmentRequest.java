package com.postgresql.order.request;

public class DepartmentRequest {
    private String departmentName;
    private String departmentHead;
    private Long companyId;

    public String getDepartmentName() {
        return departmentName;
    }

    public String getDepartmentHead() {
        return departmentHead;
    }

    public Long getCompanyId() {
        return companyId;
    }
}
