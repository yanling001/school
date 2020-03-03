package com.example.demo.Service;

import com.example.demo.common.ServiceResponse;
import com.example.demo.pojo.Order;
import com.example.demo.pojo.Product;
import com.example.demo.pojo.Shop;
import com.example.demo.pojo.vo.OrderNumber;
import com.example.demo.pojo.vo.OrderVo;
import com.example.demo.pojo.vo.ShopVo;

import java.util.List;

public interface ShopService {
    ServiceResponse addShop(Shop shop,List<String> list);

    ServiceResponse addOrder(List<OrderNumber> list, Integer userid, String remark, Integer shopid);

    ServiceResponse<List<OrderVo>> getorders(Integer shopid);

    ServiceResponse<List<ShopVo>> getshops();

    ServiceResponse addproduct(Integer shopid, Product product);

    ServiceResponse update(Integer shopid, Product product);

    ServiceResponse changeorderstatus(Integer shopid, Integer orderid);

    ServiceResponse collectshop(Integer userid, Integer shopid);

    ServiceResponse<ShopVo> getshopmsg(Integer shopid);

    ServiceResponse<List<OrderVo>> getmyorder(Integer userid);
}
