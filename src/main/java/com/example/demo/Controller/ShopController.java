package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.Claim;
import com.example.demo.Service.ShopService;
import com.example.demo.common.ServiceResponse;
import com.example.demo.pojo.Product;
import com.example.demo.pojo.Shop;
import com.example.demo.pojo.vo.OrderNumber;
import com.example.demo.pojo.vo.OrderVo;
import com.example.demo.pojo.vo.ShopVo;
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
       List<String> images=(List<String>)JSONObject.parseObject(info).get("imgstore");
        return shopService.addShop(shop,images);
    }

    //根据用户的id获取shop信息
    @RequestMapping("getshopinfo")
    public  ServiceResponse< List<Shop>> getshopinfo(Integer userId){
        if (userId==null){
            return  ServiceResponse.createByErrorMessage("参数错误");
        }
        return  shopService.getshopinfo(userId);
    }

    //获取用户收藏的shop
    @RequestMapping("getcollectshop")
    public  ServiceResponse<List<ShopVo>> getcollectshop(Integer userId){
        if (userId==null){
            return  ServiceResponse.createByErrorMessage("参数错误");
        }
      return   shopService.getcollectshop(userId);
    }
    @RequestMapping("/getorders")//商铺权限
    public ServiceResponse<List<OrderVo>> getorders(Integer shopid,Integer status, HttpServletRequest request){
        Map<String, Claim> map=(Map<String, Claim>) request.getAttribute("claim");
       //int role= map.get("role").asInt();
      // if (role!=1) return ServiceResponse.createByErrorMessage("无权限");
        if (shopid==null||status==null){
            return ServiceResponse.createByErrorMessage("参数错误");
        }
        return shopService.getorders(shopid,status);
    }
    @RequestMapping("/changeorderstatus")//商铺权限
    public ServiceResponse changeorderstatus(Integer shopid,Integer orderid, HttpServletRequest request){
        Map<String, Claim> map=(Map<String, Claim>) request.getAttribute("claim");
        //int role= map.get("role").asInt();
        // if (role!=1) return ServiceResponse.createByErrorMessage("无权限");
        if (shopid==null||orderid==null){
            return  ServiceResponse.createByErrorMessage("参数错误");
        }
        return shopService.changeorderstatus(shopid,orderid);
    }
    @RequestMapping("/addorder")//用户权限
    public  ServiceResponse addOrder(@RequestBody String info){
        JSONObject jsonObject = JSONObject.parseObject(info);
       JSONArray jsonArray = (JSONArray) jsonObject.get("list");
        List<OrderNumber> list=JSONArray.parseArray(jsonArray.toString(),OrderNumber.class);
        for (OrderNumber orderNumber:list){
            System.out.println(orderNumber.getNum());
        }
//        List<OrderNumber> orders=new ArrayList<>();
//        for (String order:list){
//            OrderNumber temp = JSON.parseObject(order, OrderNumber.class);
//            orders.add(temp);
//        }
        Integer userid = jsonObject.getInteger("userid");
        Integer shopid = jsonObject.getInteger("shopid");
        String remark =  jsonObject.getString("remark");
        return shopService.addOrder(list,userid,remark,shopid);
    }
    @RequestMapping("/collectshop")//用户权限
    public  ServiceResponse collectshop(Integer userid,Integer shopid){

        return shopService.collectshop(userid,shopid);
    }

    @RequestMapping("/getshops")//用户权限
    public  ServiceResponse<List<ShopVo>> getshops(){
        return shopService.getshops();
    }
    @RequestMapping("/getshopmsg")//用户权限
    public  ServiceResponse<ShopVo> getshopmsg(Integer shopid){
        if (shopid==null){
            return ServiceResponse.createByErrorMessage("参数错误");
        }
        return shopService.getshopmsg(shopid);
    }
    @RequestMapping("/getmyorder")//用户权限
    public  ServiceResponse<List<OrderVo>> getmyorder(Integer userid){
        if (userid==null){
            return  ServiceResponse.createByErrorMessage("参数错误");
        }
        return shopService.getmyorder(userid);
    }
    //商铺添加商品
    @RequestMapping("/addproduct")//商铺权限
    public  ServiceResponse addproduct(@RequestBody String info,HttpServletRequest request){
        Map<String, Claim> map=(Map<String, Claim>) request.getAttribute("claim");
       // int role= map.get("role").asInt();
      //  if (role!=1) return ServiceResponse.createByErrorMessage("无权限");
       Product product = JSONObject.parseObject(info,Product.class);
        Integer shopid = JSON.parseObject(info).getInteger("shopid");
        if (shopid==null){
            return  ServiceResponse.createByErrorMessage("参数错误");
        }
        return shopService.addproduct(shopid,product);
    }
    //商铺修改商品
    @RequestMapping("/updateproduct")//商铺权限
    public  ServiceResponse update(Integer shopid, Product product,HttpServletRequest request){
        Map<String, Claim> map=(Map<String, Claim>) request.getAttribute("claim");
       // int role= map.get("role").asInt();
      //  if (role!=1) return ServiceResponse.createByErrorMessage("无权限");
        return shopService.update(shopid,product);
    }

}
