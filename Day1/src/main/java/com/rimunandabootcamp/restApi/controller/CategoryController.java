package com.rimunandabootcamp.restApi.controller;

import com.rimunandabootcamp.restApi.dto.CategoriesDto;
import com.rimunandabootcamp.restApi.dto.UsersDto;
import com.rimunandabootcamp.restApi.entity.Categories;
import com.rimunandabootcamp.restApi.entity.Users;
import com.rimunandabootcamp.restApi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Categories>> findAll(){
        List<Categories> categories = this.categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categories> findById(
            @PathVariable(name = "id") Integer id
    ){
        Categories categories = this.categoryService.findById(id);
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<String> save(
            @RequestBody CategoriesDto.Save data
    ){
        this.categoryService.save(data);
        return ResponseEntity.ok("Data berhasil di tambahkan");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(
            @PathVariable Integer id,
            @RequestBody CategoriesDto.Save data
    ){
        try {
            this.categoryService.update(id,data);
            return  ResponseEntity.ok("Data berhasil diubah");
        }catch (RuntimeException e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data dengan "+ id +" tidak ditemukan");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Integer id
    ){
        this.categoryService.delete(id);
        return ResponseEntity.ok("data berhasil dihapus");

    }
}
