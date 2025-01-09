package com.example.spring.controller;

import com.example.spring.mapper.UserMapper.AdministratorMapper;
import com.example.spring.returnstatus.ResultData;
import com.example.spring.util.SpringUtil;
import com.example.spring.mapper.UserMapper.CommonMapper;
import com.example.spring.service.UserService.LogRegService;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

import static com.example.spring.returnstatus.ReturnCode.*;

//此处作为实验，用户的信息很简单，只有名称和密码
//此处作为实验，没有考虑用户信息的加密

@RestController
public class LogRegController {

    @Autowired
    LogRegService userservice;

    @RequestMapping("/login")
    public ResultData<Boolean> login(@RequestParam(value = "level",required = false,defaultValue = "999") int level,@Param("name") String name,@Param("password") String password){
        if(name.isBlank() || password.isBlank()){
            return ResultData.fail(INPUT_INVALID.getCode(), INPUT_INVALID.getMessage());
        }
        try {
            switch (level){
                case 1:
                    userservice.setMapper((AdministratorMapper)SpringUtil.getAppContext().getBean(AdministratorMapper.class));
                    return ResultData.success(userservice.LoginService(name, password));
                default:
                    userservice.setMapper((CommonMapper)SpringUtil.getAppContext().getBean(CommonMapper.class));
                    return ResultData.success(userservice.LoginService(name, password));
            }
        }catch (MyBatisSystemException e){
            if(e.getCause().getCause() instanceof org.springframework.jdbc.CannotGetJdbcConnectionException){
                return ResultData.fail(DATASOURCE_CONNECT_ERROR.getCode(), DATASOURCE_CONNECT_ERROR.getMessage());
            }else {
                return ResultData.fail(RC998.getCode(), RC998.getMessage());
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultData.fail(RC999.getCode(), RC999.getMessage());
        }
    }

    @RequestMapping("/register")
    public ResultData<Boolean> register(@RequestParam(value = "level",required = false,defaultValue = "999") int level, @Param("name") String name, @Param("password") String password){
        if(name.isBlank() || password.isBlank()){
            return ResultData.fail(INPUT_INVALID.getCode(), INPUT_INVALID.getMessage());
        }
        try {
            switch (level){
                case 1:
                    userservice.setMapper((AdministratorMapper)SpringUtil.getAppContext().getBean(AdministratorMapper.class));
                    return ResultData.success(userservice.RegisterService(name, password));
                default:
                    userservice.setMapper((CommonMapper)SpringUtil.getAppContext().getBean(CommonMapper.class));
                    return ResultData.success(userservice.RegisterService(name, password));
            }
        }catch (MyBatisSystemException e){
            if(e.getCause().getCause() instanceof org.springframework.jdbc.CannotGetJdbcConnectionException){
                return ResultData.fail(DATASOURCE_CONNECT_ERROR.getCode(), DATASOURCE_CONNECT_ERROR.getMessage());
            }else {
                return ResultData.fail(RC998.getCode(), RC998.getMessage());
            }
        }catch (DataIntegrityViolationException e){
            return ResultData.fail(USER_EXIST_ERROR.getCode(), USER_EXIST_ERROR.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            return ResultData.fail(RC999.getCode(), RC999.getMessage());
        }
    }

    @RequestMapping("/change")
    public ResultData<Boolean> changepassword(@RequestParam(value = "level",required = false,defaultValue = "999") int level,@Param("name") String name,@Param("password") String password,@Param("newpassword") String newpassword){
        if(name.isBlank() || password.isBlank() || newpassword.isBlank()){
            return ResultData.fail(INPUT_INVALID.getCode(), INPUT_INVALID.getMessage());
        }
        try {
            switch (level){
                case 1:
                    userservice.setMapper((AdministratorMapper)SpringUtil.getAppContext().getBean(AdministratorMapper.class));
                    return ResultData.success(userservice.ChangePasswordService(name, password, newpassword));
                default:
                    userservice.setMapper((CommonMapper)SpringUtil.getAppContext().getBean(CommonMapper.class));
                    return ResultData.success(userservice.ChangePasswordService(name, password, newpassword));
            }
        }catch (MyBatisSystemException e){
            if(e.getCause().getCause() instanceof org.springframework.jdbc.CannotGetJdbcConnectionException ){
                return ResultData.fail(DATASOURCE_CONNECT_ERROR.getCode(), DATASOURCE_CONNECT_ERROR.getMessage());
            }else {
                return ResultData.fail(RC998.getCode(), RC998.getMessage());
            }
        }catch (DataIntegrityViolationException e){
            if(e.getCause() instanceof com.mysql.cj.jdbc.exceptions.MysqlDataTruncation)
                return ResultData.fail(INPUT_INVALID.getCode(), INPUT_INVALID.getMessage());
            if(e.getCause() instanceof SQLIntegrityConstraintViolationException)
                return ResultData.fail(USERNAME_OR_PASSWORD_ERROR.getCode(), USERNAME_OR_PASSWORD_ERROR.getMessage());
            return ResultData.fail(RC998.getCode(), RC998.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            return ResultData.fail(RC999.getCode(), RC999.getMessage());
        }
    }
}
