package com.rimunandabootcamp.restApi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProduct {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private String quantity;
}
