package com.rimunandabootcamp.restApi.service;

import com.rimunandabootcamp.restApi.dao.UserDao;
import com.rimunandabootcamp.restApi.dto.UsersDto;
import com.rimunandabootcamp.restApi.entity.Users;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao dao;

    public void save(UsersDto.Save data){
        this.dao.save(data);
    }

    public void update(Integer id, UsersDto.Save data){
        findById(id);
        this.dao.update(id,data);
    }


    public List<Users> findAll(){
        return this.dao.findAll();
    }

    public Users findById(Integer id){
        return this.dao.findById(id)
                .orElseThrow( () -> new RuntimeException("user dengan id"+ id + "tidak ditemukan"));
    }
    public void delete(Integer id){
        findById(id);
        this.dao.delete(id);
    }

}
