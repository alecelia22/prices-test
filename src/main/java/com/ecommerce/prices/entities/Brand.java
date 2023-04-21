package com.ecommerce.prices.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Brand {

    @Id
    private long id;

    private String name;
}
