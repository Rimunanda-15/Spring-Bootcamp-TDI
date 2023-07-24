package com.rimunandabootcamp.restApi.dao;

import com.rimunandabootcamp.restApi.dto.ProductsDto;
import com.rimunandabootcamp.restApi.entity.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void save(ProductsDto.Save inputData){
        String query = "INSERT INTO public.product(\n" +
                "\tname, category_id, stok)\n" +
                "\tVALUES (:name, :categoryId, :stok);";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("name", inputData.getName());
        map.addValue("categoryId",inputData.getCategoryId());
        map.addValue("stok",inputData.getStok());

        this.jdbcTemplate.update(query,map);

    }

    public void update(Integer id , ProductsDto.Save inputData){
        String query="UPDATE public.product\n" +
                "\tSET name=:name, category_id=:categoryId, stok=:stok\n" +
                "\tWHERE id=:id ;";


        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id",id);
        map.addValue("name", inputData.getName());
        map.addValue("categoryId",inputData.getCategoryId());
        map.addValue("stok",inputData.getStok());

        this.jdbcTemplate.update(query,map);
    }

    public List<Products>  findAll(){
        String query = "SELECT id, name, category_id, stok\n" +
                "\tFROM public.product;";

        return this.jdbcTemplate.query(query, new RowMapper<Products>() {
            @Override
            public Products mapRow(ResultSet rs, int rowNum) throws SQLException {
                Products products = new Products();
                products.setId(rs.getInt("id"));
                products.setName(rs.getString("name"));
                products.setCategoryId(rs.getInt("category_id"));
                products.setStok(rs.getString("stok"));
                return products;
            }
        });
    }

    public Optional<Products> findById(Integer id){
        String query = "SELECT id, name, category_id, stok\n" +
                "\tFROM public.product WHERE id=:id;";
        MapSqlParameterSource map = new MapSqlParameterSource("id",id);
        try{
            return this.jdbcTemplate.queryForObject(query, map, new RowMapper<Optional<Products>>() {
                @Override
                public Optional<Products> mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Products products = new Products();
                    products.setId(rs.getInt("id"));
                    products.setName(rs.getString("name"));
                    products.setCategoryId(rs.getInt("category_id"));
                    products.setStok(rs.getString("stok"));
                    return Optional.of(products);
                }
            });
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    public void delete(Integer id){
        String query = "DELETE FROM public.product WHERE id=:id ;";
        MapSqlParameterSource map = new MapSqlParameterSource("id",id);
        this.jdbcTemplate.update(query,map);
    }
}
