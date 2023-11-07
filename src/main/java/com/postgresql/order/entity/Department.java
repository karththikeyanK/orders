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
        name = "Department"
)
public class Department {

    @Id
    @GeneratedValue()
    private Long departmentId;

    private String departmentName;
    private String departmentHead;

    @ManyToOne
    private Company company;

}
