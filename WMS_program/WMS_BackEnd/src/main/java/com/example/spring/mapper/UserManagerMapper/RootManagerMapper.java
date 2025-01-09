package com.example.spring.mapper.UserManagerMapper;

import com.example.spring.bean.UserBean;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface RootManagerMapper {

    @Insert("INSERT INTO administratoruser VALUES (#{userbean.name},#{userbean.password})")
    public boolean addAdmin(@Param("userbean") UserBean userBean);

    @Delete("DELETE FROM administratoruser WHERE name=#{name}")
    public boolean delAdmin(@Param("name") String name);

    @Update("UPDATE administratoruser SET name=#{userbean.name},password=#{userbean.password} WHERE name=#{name}")
    public int updAdmin(@Param("name") String name,@Param("userbean") UserBean newuserbean);

    @Select("SELECT name,password FROM administratoruser")
    @MapKey("name")
    public Map<String,UserBean> selAllAdmin();

}
