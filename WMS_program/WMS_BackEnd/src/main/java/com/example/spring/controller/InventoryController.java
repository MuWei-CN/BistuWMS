package com.example.spring.controller;

import com.example.spring.bean.InventoryBean;
import com.example.spring.returnstatus.ResultData;
import com.example.spring.returnstatus.ReturnCode;
import com.example.spring.service.InventoryService.InventoryService;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.spring.returnstatus.ReturnCode.*;
import static com.example.spring.returnstatus.ReturnCode.INPUT_INVALID;

@RestController
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @RequestMapping("/inventory")
    public ResultData<List<InventoryBean>> getAllInventory(){
        try {
            return ResultData.success(inventoryService.allInventory());
        }catch (MyBatisSystemException e){
            if(e.getCause().getCause() instanceof org.springframework.jdbc.CannotGetJdbcConnectionException){
                return ResultData.fail(DATASOURCE_CONNECT_ERROR.getCode(), DATASOURCE_CONNECT_ERROR.getMessage());
            }else {
                return ResultData.fail(RC998.getCode(), RC998.getMessage());
            }
        }catch (Exception e){
            return ResultData.fail(ReturnCode.RC999.getCode(), e.getMessage());
        }
    }

    @RequestMapping("/inventory/api/add")
    public ResultData<Boolean> addInventory(@RequestParam(required = true) String creator_name, @RequestParam(required = true) int id, @Param("message") String message){
        try {
            return ResultData.success(inventoryService.addInventory(creator_name,id,message));
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
            return ResultData.fail(ReturnCode.RC999.getCode(), e.getMessage());
        }
    }

    @RequestMapping("/inventory/api/remove")
    public ResultData<Integer> removeInventory(@RequestParam(required = true) int id){
        try {
            return ResultData.success(inventoryService.removeInventory(id));
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
            return ResultData.fail(ReturnCode.RC999.getCode(), e.getMessage());
        }
    }
}
