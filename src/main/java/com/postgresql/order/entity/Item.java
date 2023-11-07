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
        name = "Item"
)
public class Item {

    @Id
    @GeneratedValue()
    @Column(name = "itemId", updatable = false)
    private Long id;

    @Column(name = "itemName")
    private String name;

    @Column(name = "itemPrice")
    private Double price;

    @Column(name = "itemQuantity")
    private Integer quantity;


}
