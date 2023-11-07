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
        name = "Organization"
)
public class Organization {
    @Id
    @GeneratedValue()
    @Column(name = "orgId", updatable = false)
    private Long orgId;

    private String name;
    private Long regNo;

}
