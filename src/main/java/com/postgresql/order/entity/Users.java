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
        name = "Users"
)
public class Users {

    @Id
    @GeneratedValue()
    private Long userId;

    private String userName;
    private String email;
    private String address;

    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;

}
