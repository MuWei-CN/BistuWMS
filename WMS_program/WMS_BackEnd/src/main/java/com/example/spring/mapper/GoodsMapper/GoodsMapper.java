package com.example.spring.mapper.GoodsMapper;

import com.example.spring.bean.GoodsBean;
import com.example.spring.model.Goods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsMapper {
    @Select("SELECT id, name, quantity, price, description FROM goods WHERE inventory_id=#{inventory_id}")
    List<Goods> SelectGoodsByInventory(@Param("inventory_id") int inventory_id);

    @Select("SELECT inventory_id, id, name, quantity, price, description FROM goods WHERE id=#{id}")
    List<GoodsBean> SelectGoodsById(@Param("id") int id);

    @Select("SELECT inventory_id, id, name, quantity, price, description FROM goods")
    List<GoodsBean> SelectGoods();

    @Select("SELECT MAX(id) FROM goods")
    int goodsMaxId();

    @Update("UPDATE goods SET " +
            "inventory_id=IF(#{goodsbean.inventory_id}>=0,#{goodsbean.inventory_id},inventory_id)," +
            "id=IF(#{goodsbean.id}>0,#{goodsbean.id},id)," +
            "name=IF(#{goodsbean.name} is not null,#{goodsbean.name},name)," +
            "quantity=IF(#{goodsbean.quantity}!=0,#{goodsbean.quantity},quantity)," +
            "price=IF(#{goodsbean.price}!=0.0,#{goodsbean.price},price)," +
            "description=IF(#{goodsbean.description} is not null,#{goodsbean.description},description)" +
            "WHERE id=#{id}")
    int UpdateGoods(@Param("id") int id, @Param("goodsbean") GoodsBean goodsbean);

    @Update("UPDATE goods SET inventory_id=#{new_inventory_id} WHERE inventory_id = #{old_inventory_id}")
    int UpdateGoodsInventory(@Param("old_inventory_id") int old_inventory_id,@Param("new_inventory_id") int new_inventory_id);

    @Insert("INSERT into goods VALUES (0,#{good.id},#{good.name},#{good.quantity},#{good.price},#{good.description})")
    boolean insertGood(@Param("good") Goods goods);

    @Delete("DELETE from goods WHERE id=#{id}")
    int deleteGood(@Param("id") int id);
}
