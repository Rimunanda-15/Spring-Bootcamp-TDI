package com.rimunandabootcamp.restApi.controller;

import com.rimunandabootcamp.restApi.dto.ProductsDto;
import com.rimunandabootcamp.restApi.dto.UserProductDto;
import com.rimunandabootcamp.restApi.entity.Products;
import com.rimunandabootcamp.restApi.entity.UserProduct;
import com.rimunandabootcamp.restApi.service.UserProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detailproduct")
@RequiredArgsConstructor
public class UserProductController {
    private final UserProductService service;
    @GetMapping
    public ResponseEntity<List<UserProduct>> findAll(){
        List<UserProduct> userProducts = this.service.findAll();
        return ResponseEntity.ok(userProducts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProduct> findById(
            @PathVariable(name = "id") Integer id
    ){
        UserProduct userProduct = this.service.findById(id);
        return ResponseEntity.ok(userProduct);
    }

    @PostMapping
    public ResponseEntity<String> save(
            @RequestBody UserProductDto.Save data
            ){
        this.service.save(data);
        return ResponseEntity.ok("Data berhasil di tambahkan");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(
            @PathVariable Integer id,
            @RequestBody UserProductDto.Save data
    ){
        try {
            this.service.update(id,data);
            return  ResponseEntity.ok("Data berhasil diubah");
        }catch (RuntimeException e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data dengan "+ id +" tidak ditemukan");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Integer id
    ){
        this.service.delete(id);
        return ResponseEntity.ok("data berhasil dihapus");

    }
}
