package com.example.spring.mapper.UserManagerMapper;

import com.example.spring.bean.UserBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface AdminManagerMapper {
    @Insert("INSERT INTO commonuser VALUES (#{userbean.name},#{userbean.password})")
    public boolean addCommon(@Param("userbean") UserBean userBean);

    @Delete("DELETE FROM commonuser WHERE name=#{name}")
    public boolean delCommon(@Param("name") String name);

    @Update("UPDATE commonuser SET name=#{userbean.name},password=#{userbean.password} WHERE name=#{name}")
    public int updCommon(@Param("name") String name,@Param("userbean") UserBean newuserbean);

    @Select("SELECT name,password FROM commonuser")
    @MapKey("name")
    public Map<String,UserBean> selAllCommon();
}
