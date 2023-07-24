package com.rimunandabootcamp.restApi.dto;

import lombok.Data;

public class UserProductDto {
    @Data
    public  static class Save{
        private Integer userId;
        private Integer productId;
        private String quantity;
    }
}
