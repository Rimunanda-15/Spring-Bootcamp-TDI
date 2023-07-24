package com.rimunandabootcamp.restApi.service;

import com.rimunandabootcamp.restApi.dao.UserProductDao;
import com.rimunandabootcamp.restApi.dto.UserProductDto;
import com.rimunandabootcamp.restApi.entity.UserProduct;
import com.rimunandabootcamp.restApi.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProductService {
    private final UserProductDao dao;
    public void save(UserProductDto.Save data ){this.dao.save(data);}
    public void update(Integer id, UserProductDto.Save data){findById(id); this.dao.update(id,data);}
    public List<UserProduct> findAll(){
        return this.dao.findAll();
    }
    public UserProduct findById(Integer id){
        return this.dao.findById(id)
                .orElseThrow( () -> new RuntimeException("data dengan id"+ id + "tidak ditemukan"));
    }
    public void delete(Integer id){
        findById(id);
        this.dao.delete(id);
    }
}
