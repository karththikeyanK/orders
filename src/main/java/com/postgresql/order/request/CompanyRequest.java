package com.postgresql.order.request;

public class CompanyRequest {
    private String companyName;
    private Long companyRegNo;
    private String companyAddress;

    private Long organizationId;


    public String getCompanyName() {
        return companyName;
    }

    public Long getCompanyRegNo() {
        return companyRegNo;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public Long getOrganizationId() {
        return organizationId;
    }
}
