package com.example.spring.controller;

import com.example.spring.bean.UserBean;
import com.example.spring.mapper.UserManagerMapper.RootManagerMapper;
import com.example.spring.returnstatus.ResultData;
import com.example.spring.service.UserManagerService.AdminManagerService;
import com.example.spring.service.UserManagerService.RootManagerService;
import com.example.spring.service.UserManagerService.UserManagerService;
import com.example.spring.util.SpringUtil;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.example.spring.returnstatus.ReturnCode.*;
import static com.example.spring.returnstatus.ReturnCode.RC998;

//此处作为实验，用户的信息很简单，只有名称和密码
//此处作为实验，没有考虑用户信息的加密

@RestController()
@RequestMapping("/user")
public class UserManagerController {

    private UserManagerService userManagerService;

    private void selectService(UserManagerService service){
        userManagerService = service;
    }

    @RequestMapping("/admin/add")
    public ResultData<Boolean> addAdmin(@Param("name") String name, @Param("password") String password){
        if(name.isBlank() || password.isBlank())
            return ResultData.fail(INPUT_INVALID.getCode(), INPUT_INVALID.getMessage());

        selectService(SpringUtil.getAppContext().getBean(RootManagerService.class));

        try{
            return ResultData.success(userManagerService.addUser(name, password));
        }catch (MyBatisSystemException e){
            if(e.getCause().getCause() instanceof org.springframework.jdbc.CannotGetJdbcConnectionException){
                return ResultData.fail(DATASOURCE_CONNECT_ERROR.getCode(), DATASOURCE_CONNECT_ERROR.getMessage());
            }else {
                return ResultData.fail(RC998.getCode(), RC998.getMessage());
            }
        }catch (DuplicateKeyException e){
            return ResultData.fail(USER_EXIST_ERROR.getCode(), USER_EXIST_ERROR.getMessage());
        }catch (DataIntegrityViolationException e){
            return ResultData.fail(INPUT_INVALID.getCode(), INPUT_INVALID.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            return ResultData.fail(RC999.getCode(), RC999.getMessage());
        }
    }

    @RequestMapping("/admin/delete")
    public ResultData<Boolean> deleteAdmin(@Param("name") String name){
        if(name.isBlank())
            return ResultData.fail(INPUT_INVALID.getCode(), INPUT_INVALID.getMessage());

        selectService(SpringUtil.getAppContext().getBean(RootManagerService.class));

        try{
            return ResultData.success(userManagerService.deleteUser(name));
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

    @RequestMapping("/admin/update")
    public ResultData<Boolean> updateAdmin(@Param("oldname") String oldname,@Param("newname") String newname,@Param("newpassword") String newpassword){
        if(oldname.isBlank() || newname.isBlank() || newpassword.isBlank())
            return ResultData.fail(INPUT_INVALID.getCode(), INPUT_INVALID.getMessage());

        selectService(SpringUtil.getAppContext().getBean(RootManagerService.class));

        try{
            return ResultData.success(userManagerService.updateUser(oldname,newname,newpassword) > 0);
        }catch (MyBatisSystemException e){
            if(e.getCause().getCause() instanceof org.springframework.jdbc.CannotGetJdbcConnectionException){
                return ResultData.fail(DATASOURCE_CONNECT_ERROR.getCode(), DATASOURCE_CONNECT_ERROR.getMessage());
            }else {
                return ResultData.fail(RC998.getCode(), RC998.getMessage());
            }
        }catch (DuplicateKeyException e){
            return ResultData.fail(USER_EXIST_ERROR.getCode(), USER_EXIST_ERROR.getMessage());
        }catch (DataIntegrityViolationException e){
            return ResultData.fail(INPUT_INVALID.getCode(), INPUT_INVALID.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            return ResultData.fail(RC999.getCode(), RC999.getMessage());
        }
    }

    @RequestMapping("/admin/search")
    public ResultData<UserBean> searchAdmin(@Param("name") String name){
        if(name.isBlank())
            return ResultData.fail(INPUT_INVALID.getCode(), INPUT_INVALID.getMessage());

        selectService(SpringUtil.getAppContext().getBean(RootManagerService.class));

        try{
            return ResultData.success(userManagerService.selectUserBy(name));
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

    @RequestMapping("/admin/list")
    public ResultData<Map<String, UserBean>> allAdmin(){
        selectService(SpringUtil.getAppContext().getBean(RootManagerService.class));

        try {
            return ResultData.success(userManagerService.selectAllUser());
        }catch (MyBatisSystemException e){
            if(e.getCause().getCause() instanceof org.springframework.jdbc.CannotGetJdbcConnectionException){
                return ResultData.fail(DATASOURCE_CONNECT_ERROR.getCode(), DATASOURCE_CONNECT_ERROR.getMessage());
            }else {
                return ResultData.fail(RC998.getCode(), RC998.getMessage());
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultData.fail(RC999.getCode(),RC999.getMessage());
        }
    }

    @RequestMapping("/common/add")
    public ResultData<Boolean> addCommon(@Param("name") String name, @Param("password") String password){
        if(name.isBlank() || password.isBlank())
            return ResultData.fail(INPUT_INVALID.getCode(), INPUT_INVALID.getMessage());

        selectService(SpringUtil.getAppContext().getBean(AdminManagerService.class));

        try{
            return ResultData.success(userManagerService.addUser(name, password));
        }catch (MyBatisSystemException e){
            if(e.getCause().getCause() instanceof org.springframework.jdbc.CannotGetJdbcConnectionException){
                return ResultData.fail(DATASOURCE_CONNECT_ERROR.getCode(), DATASOURCE_CONNECT_ERROR.getMessage());
            }else {
                return ResultData.fail(RC998.getCode(), RC998.getMessage());
            }
        }catch (DuplicateKeyException e){
            return ResultData.fail(USER_EXIST_ERROR.getCode(), USER_EXIST_ERROR.getMessage());
        }catch (DataIntegrityViolationException e){
            return ResultData.fail(INPUT_INVALID.getCode(), INPUT_INVALID.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            return ResultData.fail(RC999.getCode(), RC999.getMessage());
        }
    }

    @RequestMapping("/common/delete")
    public ResultData<Boolean> deleteCommon(@Param("name") String name){
        if(name.isBlank())
            return ResultData.fail(INPUT_INVALID.getCode(), INPUT_INVALID.getMessage());

        selectService(SpringUtil.getAppContext().getBean(AdminManagerService.class));

        try{
            return ResultData.success(userManagerService.deleteUser(name));
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

    @RequestMapping("/common/update")
    public ResultData<Boolean> updateCommon(@Param("oldname") String oldname,@Param("newname") String newname,@Param("newpassword") String newpassword){
        if(oldname.isBlank() || newname.isBlank() || newpassword.isBlank())
            return ResultData.fail(INPUT_INVALID.getCode(), INPUT_INVALID.getMessage());

        selectService(SpringUtil.getAppContext().getBean(AdminManagerService.class));

        try{
            return ResultData.success(userManagerService.updateUser(oldname,newname,newpassword) > 0);
        }catch (MyBatisSystemException e){
            if(e.getCause().getCause() instanceof org.springframework.jdbc.CannotGetJdbcConnectionException){
                return ResultData.fail(DATASOURCE_CONNECT_ERROR.getCode(), DATASOURCE_CONNECT_ERROR.getMessage());
            }else {
                return ResultData.fail(RC998.getCode(), RC998.getMessage());
            }
        }catch (DuplicateKeyException e){
            return ResultData.fail(USER_EXIST_ERROR.getCode(), USER_EXIST_ERROR.getMessage());
        }catch (DataIntegrityViolationException e){
            return ResultData.fail(INPUT_INVALID.getCode(), INPUT_INVALID.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            return ResultData.fail(RC999.getCode(), RC999.getMessage());
        }
    }

    @RequestMapping("/common/search")
    public ResultData<UserBean> searchCommon(@Param("name") String name){
        if(name.isBlank())
            return ResultData.fail(INPUT_INVALID.getCode(), INPUT_INVALID.getMessage());

        selectService(SpringUtil.getAppContext().getBean(AdminManagerService.class));

        try{
            return ResultData.success(userManagerService.selectUserBy(name));
        }catch (MyBatisSystemException e){
            if(e.getCause().getCause() instanceof org.springframework.jdbc.CannotGetJdbcConnectionException){
                return ResultData.fail(DATASOURCE_CONNECT_ERROR.getCode(), DATASOURCE_CONNECT_ERROR.getMessage());
            }else {
                return ResultData.fail(RC998.getCode(), RC998.getMessage());
            }
        }catch (Exception e){
            // e.printStackTrace();
            return ResultData.fail(RC999.getCode(), RC999.getMessage());
        }
    }

    @RequestMapping("/common/list")
    public ResultData<Map<String, UserBean>> allCommon(){
        selectService(SpringUtil.getAppContext().getBean(AdminManagerService.class));

        try {
            return ResultData.success(userManagerService.selectAllUser());
        }catch (MyBatisSystemException e){
            if(e.getCause().getCause() instanceof org.springframework.jdbc.CannotGetJdbcConnectionException){
                return ResultData.fail(DATASOURCE_CONNECT_ERROR.getCode(), DATASOURCE_CONNECT_ERROR.getMessage());
            }else {
                return ResultData.fail(RC998.getCode(), RC998.getMessage());
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultData.fail(RC999.getCode(),RC999.getMessage());
        }
    }
}
