package com.example.spring.mapper.InventoryMapper;

import com.example.spring.bean.InventoryBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InventoryMapper {
    @Select("SELECT creator_name,id,message FROM inventory")
    public List<InventoryBean> SelectInventory();

    @Insert("INSERT into inventory VALUES (#{inventory.creator_name},#{inventory.id},#{inventory.message})")
    public boolean insertInventory(@Param("inventory") InventoryBean inventoryBean);

    @Delete("DELETE from inventory WHERE id=#{id}")
    public int deleteInventory(@Param("id") int id);
}
