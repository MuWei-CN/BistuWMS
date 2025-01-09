package com.example.spring.service.GoodsService;

import com.example.spring.bean.GoodsBean;
import com.example.spring.mapper.GoodsMapper.GoodsMapper;
import com.example.spring.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    public List<Goods> searchGoodsOfInventory(int inventory_id) throws Exception{
        return goodsMapper.SelectGoodsByInventory(inventory_id);
    }

    public List<GoodsBean> listGoods() throws Exception{
        return goodsMapper.SelectGoods();
    }

    public int GoodBoundOut(int id,int inventoryId){
        return goodsMapper.UpdateGoods(id,new GoodsBean(-1,null,0,0.0,null,inventoryId));
    }

    public int BulkOutBound(int oldInventoryId,int newInventoryId) throws Exception{
        return goodsMapper.UpdateGoodsInventory(oldInventoryId,newInventoryId);
    }

    public int updateGood(int id,GoodsBean goodsBean) throws Exception{
        return goodsMapper.UpdateGoods(id, goodsBean);
    }

    public boolean addGood(String name,int quantity,double price,String message) throws Exception{
        if(name.isBlank())
            name = null;
        int id = goodsMapper.goodsMaxId() + 1;
        return goodsMapper.insertGood(new Goods(id,name,quantity,price,message));
    }

    public int removeGood(int id) throws Exception{
        return goodsMapper.deleteGood(id);
    }
}
