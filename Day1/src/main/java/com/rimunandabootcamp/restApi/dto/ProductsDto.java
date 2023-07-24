package com.rimunandabootcamp.restApi.dto;

import lombok.Data;

public class ProductsDto {

    @Data
    public static class Save{
        private String name;
        private Integer categoryId;
        private String stok;
    }
}
