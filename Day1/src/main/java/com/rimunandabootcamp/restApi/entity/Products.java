package com.rimunandabootcamp.restApi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    private Integer id;
    private String name;
    private Integer categoryId;
    private String stok;
}
