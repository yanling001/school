package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.Claim;
import com.example.demo.Service.ShopService;
import com.example.demo.common.ServiceResponse;
import com.example.demo.pojo.Product;
import com.example.demo.pojo.Shop;
import com.example.demo.pojo.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
    public ServiceResponse<List<OrderVo>> getorders(Integer shopid, HttpServletRequest request){
        Map<String, Claim> map=(Map<String, Claim>) request.getAttribute("claim");
       //int role= map.get("role").asInt();
      // if (role!=1) return ServiceResponse.createByErrorMessage("无权限");
        return shopService.getorders(shopid);
    }
    @RequestMapping("/addorder")//用户权限
    public  ServiceResponse addOrder(@RequestBody String info){
        JSONObject jsonObject = JSONObject.parseObject(info);
        List<Integer> list=(List<Integer>) jsonObject.get("list");
        Integer userid = jsonObject.getInteger("userid");
        String remark =  jsonObject.getString("remark");
        return shopService.addOrder(list,userid,remark);
    }
    @RequestMapping("/getshops")//用户权限
    public  ServiceResponse<List<Shop>> getshops(){
        return shopService.getshops();
    }
    //商铺添加商品
    @RequestMapping("/addproduct")//商铺权限
    public  ServiceResponse addproduct(@RequestBody String info,HttpServletRequest request){
        Map<String, Claim> map=(Map<String, Claim>) request.getAttribute("claim");
       // int role= map.get("role").asInt();
      //  if (role!=1) return ServiceResponse.createByErrorMessage("无权限");
       Product product = JSONObject.parseObject(info,Product.class);
        Integer shopid = JSON.parseObject(info).getInteger("shopid");
        return shopService.addproduct(shopid,product);
    }
    //商铺修改商品
    @RequestMapping("/updateproduct")//商铺权限
    public  ServiceResponse update(Integer shopid,Product product,HttpServletRequest request){
        Map<String, Claim> map=(Map<String, Claim>) request.getAttribute("claim");
       // int role= map.get("role").asInt();
      //  if (role!=1) return ServiceResponse.createByErrorMessage("无权限");
        return shopService.update(shopid,product);
    }
}
