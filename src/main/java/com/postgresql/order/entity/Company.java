package com.postgresql.order.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "Company"
)
public class Company {

    @Id
    @GeneratedValue()
    @Column(name = "companyId", updatable = false)
    private Long companyId;
    private String companyName;
    private Long companyRegNo;
    private String companyAddress;

    @ManyToOne
    private Organization organization;

}
