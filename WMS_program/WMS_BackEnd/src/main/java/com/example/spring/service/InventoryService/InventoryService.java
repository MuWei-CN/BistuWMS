package com.example.spring.service.InventoryService;

import com.example.spring.bean.InventoryBean;
import com.example.spring.mapper.GoodsMapper.GoodsMapper;
import com.example.spring.mapper.InventoryMapper.InventoryMapper;
import com.example.spring.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryMapper inventoryMapper;

    public List<InventoryBean> allInventory() throws Exception{
        return inventoryMapper.SelectInventory();
    }

    public boolean addInventory(String creator_name,int id,String message) throws Exception{
        if(creator_name.isBlank())
            creator_name = null;

        return inventoryMapper.insertInventory(new InventoryBean(creator_name,id,message));
    }

    public int removeInventory(int id) throws Exception{
        try {
            GoodsMapper goodsMapper = SpringUtil.getAppContext().getBean(GoodsMapper.class);
            goodsMapper.UpdateGoodsInventory(id,0);
            return inventoryMapper.deleteInventory(id);
        }catch (Exception e){
            throw e;
        }
    }
}
