package com.rimunandabootcamp.restApi.controller;

import com.rimunandabootcamp.restApi.dto.CategoriesDto;
import com.rimunandabootcamp.restApi.dto.ProductsDto;
import com.rimunandabootcamp.restApi.entity.Categories;
import com.rimunandabootcamp.restApi.entity.Products;
import com.rimunandabootcamp.restApi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping
    public ResponseEntity<List<Products>> findAll(){
        List<Products> products = this.service.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> findById(
            @PathVariable(name = "id") Integer id
    ){
        Products products = this.service.findById(id);
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<String> save(
            @RequestBody ProductsDto.Save data
            ){
        this.service.save(data);
        return ResponseEntity.ok("Data berhasil di tambahkan");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(
            @PathVariable Integer id,
            @RequestBody ProductsDto.Save data
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
