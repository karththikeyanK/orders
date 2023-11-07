package com.postgresql.order.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "Orders"
)
public class Orders {

    @Id
    @GeneratedValue()
    private Long orderId;

    @Column(name = "quantity")
    private Integer quantity;

    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "itemId")
    private Item item;

    @PrePersist
    protected void onCreate() {
        this.orderDate = LocalDate.now();
    }
}
