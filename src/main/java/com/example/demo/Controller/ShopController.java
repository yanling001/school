package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.ShopService;
import com.example.demo.common.ServiceResponse;
import com.example.demo.pojo.Product;
import com.example.demo.pojo.Shop;
import com.example.demo.pojo.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    ShopService shopService;
    @RequestMapping("/addshop")//商铺权限
    public ServiceResponse addshop(@RequestBody String info){
       Shop shop =   JSONObject.parseObject(info,Shop.class);
        return shopService.addShop(shop);
    }
    @RequestMapping("/getorders")//商铺权限
    public ServiceResponse<List<OrderVo>> getorders(Integer shopid){
        return shopService.getorders(shopid);
    }
    @RequestMapping("/addorder")//用户权限
    public  ServiceResponse addOrder(@RequestParam("list")List<Integer> list, @RequestParam("userid")Integer userid,@RequestParam("remark") String remark){
      return shopService.addOrder(list,userid,remark);
    }
    @RequestMapping("/getshops")//用户权限
    public  ServiceResponse<List<Shop>> getshops(){
        return shopService.getshops();
    }
    //商铺添加商品
    @RequestMapping("/addproduct")//商铺权限
    public  ServiceResponse addproduct(@RequestBody String info){
       Product product = JSONObject.parseObject(info,Product.class);
        Integer shopid = JSON.parseObject(info).getInteger("shopid");
        return shopService.addproduct(shopid,product);
    }
    //商铺修改商品
    @RequestMapping("/updateproduct")//商铺权限
    public  ServiceResponse update(Integer shopid,Product product){

        return shopService.update(shopid,product);
    }
}
