package com.rimunandabootcamp.restApi.dto;

import lombok.Data;

public class UsersDto {
    @Data
    public static class Save{
        private String name;
        private String email;
        private  String phone;
    }
}
