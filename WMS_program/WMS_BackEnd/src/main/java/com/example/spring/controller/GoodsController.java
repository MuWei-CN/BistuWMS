package com.example.spring.controller;

import com.example.spring.bean.GoodsBean;
import com.example.spring.model.Goods;
import com.example.spring.returnstatus.ResultData;
import com.example.spring.returnstatus.ReturnCode;
import com.example.spring.service.GoodsService.GoodsService;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.spring.returnstatus.ReturnCode.*;
import static com.example.spring.returnstatus.ReturnCode.INPUT_INVALID;

@RestController
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/inventory/{inventory_id}")
    public ResultData<List<Goods>> GetGoodsOfInventory(@PathVariable int inventory_id){
        try {
            return ResultData.success(goodsService.searchGoodsOfInventory(inventory_id));
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

    @RequestMapping("/goods/api/goodout")
    public ResultData<Integer> GoodBoundIn(@RequestParam(required = true,name = "id") int id,@RequestParam(required = true,name = "inventory_id") int inventoryId){
        try {
            return ResultData.success(goodsService.GoodBoundOut(id,inventoryId));
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

    @RequestMapping("/goods")
    public ResultData<List<GoodsBean>> listGoods(){
        try {
            return ResultData.success(goodsService.listGoods());
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

    @RequestMapping("/goods/api/update")
    public ResultData<Integer> updateGood(@RequestParam(required = true) int id, @RequestBody GoodsBean good){
        if (good.getName().isBlank())
            good.setName(null);
        if (good.getDescription().isBlank())
            good.setDescription(null);

        try {
            return ResultData.success(goodsService.updateGood(id,good));
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

    @RequestMapping("/goods/api/add")
    public ResultData<Boolean> addGood(@RequestParam(required = true) String name, @RequestParam(required = true) int quantity,@RequestParam(required = true) double price,@Param("description") String description){
        try {
            return ResultData.success(goodsService.addGood(name,quantity,price,description));
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

    @RequestMapping("/goods/api/remove")
    public ResultData<Integer> removeGood(@RequestParam(required = true) int id){
        try {
            return ResultData.success(goodsService.removeGood(id));
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
}
