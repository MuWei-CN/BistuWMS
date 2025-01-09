package com.example.spring.mapper.UserMapper;

import com.example.spring.bean.UserBean;
import com.example.spring.mapper.UserMapper.UserMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdministratorMapper extends UserMapper {
    @Select("select count(*)=1 from administratoruser where name=#{userbean.name} AND password=#{userbean.password}")
    boolean findUserBy(@Param("userbean") UserBean userBean);

    @Insert("INSERT into administratoruser VALUES (#{userbean.name},#{userbean.password})")
    boolean addUser(@Param("userbean") UserBean userBean);

    @Update("UPDATE administratoruser SET password=IF(password=#{userbean.password},#{newpassword},null) WHERE name=#{userbean.name}")
    int updatePasswordBy(@Param("userbean") UserBean userBean,@Param("newpassword") String newpassword);
}
