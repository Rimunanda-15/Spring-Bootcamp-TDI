package com.rimunandabootcamp.restApi.dao;

import com.rimunandabootcamp.restApi.dto.UsersDto;
import com.rimunandabootcamp.restApi.entity.Users;
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
public class UserDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

//   Insert data to database
    public void save(UsersDto.Save inputData){
        String query = "INSERT INTO public.\"user\"(\n" +
                "\t nama, email, phone)\n" +
                "\tVALUES (:name, :email, :phone);" ;

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("name",inputData.getName());
        map.addValue("email",inputData.getEmail());
        map.addValue("phone",inputData.getPhone());


        this.jdbcTemplate.update(query,map);
    }

    public void update(int id , UsersDto.Save inputData){
        String query = "UPDATE public.\"user\"\n" +
                "\tSET nama=:name, email=:email, phone=:phone\n" +
                "\tWHERE id=:id ;";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        map.addValue("name", inputData.getName());
        map.addValue("email", inputData.getEmail());
        map.addValue("phone", inputData.getPhone());

        this.jdbcTemplate.update(query,map);
    }


    /*
    * Get All data
    * */
    public List<Users> findAll(){
        String query = "SELECT id, nama, email, phone\n" +
                "\tFROM public.\"user\";";

        return this.jdbcTemplate.query(query, new RowMapper<Users>() {
            @Override
            public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("nama"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                return user;
            }
        });
    }

    public Optional<Users> findById(Integer id){
        String query = "SELECT id, nama, email, phone\n" +
                "\tFROM public.user where id=:id ;";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id",id);

        try {
            return this.jdbcTemplate.queryForObject(query, map, new RowMapper<Optional<Users>>() {
                @Override
                public Optional<Users> mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Users user = new Users();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("nama"));
                    user.setEmail(rs.getString("email"));
                    user.setPhone(rs.getString("phone"));
                    return Optional.of(user);
                }
            });
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    public void delete(Integer id){
        String query = "DELETE FROM public.user WHERE id=:id";
        MapSqlParameterSource map = new MapSqlParameterSource("id",id);
        this.jdbcTemplate.update(query,map);
    }
}
