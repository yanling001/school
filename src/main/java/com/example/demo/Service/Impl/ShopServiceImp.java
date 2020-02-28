package com.example.demo.Service.Impl;

import com.example.demo.Controller.ShopController;
import com.example.demo.Service.ShopService;
import com.example.demo.common.ServiceResponse;
import com.example.demo.dao.*;
import com.example.demo.pojo.*;
import com.example.demo.pojo.vo.OrderVo;
import com.example.demo.pojo.vo.ProductVo;
import com.example.demo.pojo.vo.ShopProductVo;
import com.example.demo.pojo.vo.ShopVo;
import com.example.demo.util.BigDecimalUtil;
import com.example.demo.util.DateTimeUtil;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ShopServiceImp implements ShopService {
    @Autowired
    ShopMapper shopMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderToProductMapper orderToProductMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    WeChatMapper weChatMapper;
    @Autowired
    CollectShopMapper collectShopMapper;
    @Autowired
    RedisTemplate redisTemplate;
    @Override
    public ServiceResponse addShop(Shop shop) {
        String key = "shops";
       if (redisTemplate.hasKey(key)) redisTemplate.delete(key);
        User user=userMapper.selectByPrimaryKey(shop.getUserId());
        shop.setTel(user.getPhone());
        int k= shopMapper.insert(shop);
        user.setRole(1);
        user.setUserId(shop.getUserId());
        userMapper.updateByPrimaryKeySelective(user);
        if (k>=0) return ServiceResponse.createBysuccessMessage("ok");
        else  return ServiceResponse.createByErrorMessage("error");
    }

    @Override
    public ServiceResponse addOrder(List<Integer> list, Integer userid,String remark,Integer shopid) {
        String key = "shoporders"+shopid;
        if (redisTemplate.hasKey(key)) redisTemplate.delete(key);
        String key1 = "myorders"+userid;
        if (redisTemplate.hasKey(key1)) redisTemplate.delete(key1);
        //先创建Order对象
        Order order=new Order();
        order.setPrice(getPrice(list));
        order.setRemark(remark);
        order.setStatus(0);
        order.setCreateTime(DateTimeUtil.strToDate(DateTimeUtil.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
        order.setUserId(userid);
        order.setShopId(shopid);
        //添加 order
        orderMapper.insert(order);
        //获取 order的id
        int id=orderMapper.selectOrderid(order);
        //添加ordertoproduct
        for (Integer productid: list) {
            OrderToProduct orderToProduct=new OrderToProduct();
            orderToProduct.setOrderId(id);
            orderToProduct.setProductid(productid);
            orderToProductMapper.insertSelective(orderToProduct);
        }

        return ServiceResponse.createBysuccessMessage("ok");
    }

    @Override
    public ServiceResponse<List<OrderVo>> getorders(Integer shopid) {
        String key = "shoporders"+shopid;
        ValueOperations<String,List<OrderVo>> valueOperations = redisTemplate.opsForValue();
        if (redisTemplate.hasKey(key)){
            List<OrderVo> voList = valueOperations.get(key);
           return  ServiceResponse.createBysuccessMessage("ok",voList);
        }
        List<Order> list = orderMapper.selectOrderbyshopid(shopid);
        List<OrderVo> voList=makeOrderVo(list);
        valueOperations.set(key,voList,5, TimeUnit.HOURS);
        return ServiceResponse.createBysuccessMessage("ok",voList);
    }

    @Override
    public ServiceResponse<List<ShopVo>> getshops() {
        String key = "shops";
        ValueOperations<String,List<ShopVo>> valueOperations = redisTemplate.opsForValue();
        if (redisTemplate.hasKey(key)){
            List<ShopVo> voList = valueOperations.get(key);
            return  ServiceResponse.createBysuccessMessage("ok",voList);
        }
        List<Shop> list =  shopMapper.selectAll();
        List<ShopVo> shopVoList=new ArrayList<>();
        for (Shop shop:list){
            ShopVo shopVo=new ShopVo();
            shopVo.setShopId(shop.getShopId());
            shopVo.setIntro(shop.getIntro());
            shopVo.setLocation(shop.getLocation());
            shopVo.setShopname(shop.getShopname());
            shopVo.setNickname(userMapper.selectByPrimaryKey(shop.getUserId()).getNickname());
            shopVoList.add(shopVo);
        }
        valueOperations.set(key,shopVoList,5,TimeUnit.HOURS);
        return ServiceResponse.createBysuccessMessage("ok",shopVoList);
    }

    @Override
    public ServiceResponse addproduct(Integer shopid, Product product) {
        String key="shopmsg"+shopid;
        if (redisTemplate.hasKey(key)) redisTemplate.delete(key);
        product.setShopId(shopid);
        product.setCreateTime(DateTimeUtil.strToDate(DateTimeUtil.dateToStr(new Date())));
        int i=productMapper.insert(product);
        if (i>0) return  ServiceResponse.createBysuccessMessage("ok");
        return ServiceResponse.createByErrorMessage("error");
    }

    @Override
    public ServiceResponse update(Integer shopid, Product product) {
        if(shopid!=productMapper.selectShopid(product.getProductId()))
            return ServiceResponse.createByErrorMessage("当前shop没有权限");
        productMapper.updateByPrimaryKey(product);
        return ServiceResponse.createBysuccessMessage("ok");
    }

    @Override
    public ServiceResponse changeorderstatus(Integer shopid, Integer orderid) {
        String key = "shoporders"+shopid;
        //先校验orderid对不对
        Order order=orderMapper.selectByPrimaryKey(orderid);
        String key1 = "myorders"+order.getUserId();
        if (redisTemplate.hasKey(key)) redisTemplate.delete(key);
        if (redisTemplate.hasKey(key1)) redisTemplate.delete(key1);
        if(!order.getShopId().equals(shopid)){
            return ServiceResponse.createByErrorMessage("id错误");
        }
        order.setStatus(1);
        orderMapper.updateByPrimaryKeySelective(order);
        return ServiceResponse.createBysuccessMessage("ok");
    }

    @Override
    public ServiceResponse collectshop(Integer userid, Integer shopid) {
        CollectShop collectShop=new CollectShop();
        collectShop.setShopId(shopid);
        collectShop.setUserId(userid);
        collectShopMapper.insertSelective(collectShop);
        return ServiceResponse.createBysuccessMessage("ok");
    }

    @Override
    public ServiceResponse<ShopVo> getshopmsg(Integer shopid) {
        String key="shopmsg"+shopid;
        ValueOperations<String,ShopVo> valueOperations = redisTemplate.opsForValue();
        if (redisTemplate.hasKey(key)){
            ShopVo shopVo = valueOperations.get(key);
            return ServiceResponse.createBysuccessMessage("ok",shopVo);
        }
        Shop shop=shopMapper.selectByPrimaryKey(shopid);
        ShopVo shopVo=new ShopVo();
        shopVo.setShopname(shop.getShopname());
        shopVo.setIntro(shop.getIntro());
        shopVo.setLocation(shop.getLocation());
        User user=userMapper.selectByPrimaryKey(shop.getUserId());
        shopVo.setTel(user.getPhone());
        shopVo.setNickname(user.getNickname());
        List<Product> products=productMapper.selectByshopid(shop.getShopId());
        List<ShopProductVo> productVos=new ArrayList<>();
        for (Product product:products){
            ShopProductVo shopProductVo=new ShopProductVo();
            shopProductVo.setPrice(product.getPrice());
            shopProductVo.setProductId(product.getProductId());
            shopProductVo.setProductName(product.getProductName());
            productVos.add(shopProductVo);
        }
        shopVo.setMeals(productVos);
        valueOperations.set(key,shopVo,5,TimeUnit.HOURS);
        return ServiceResponse.createBysuccessMessage("ok",shopVo);
    }

    @Override
    public ServiceResponse<List<OrderVo>> getmyorder(Integer userid) {
        String key="myorders"+userid;
        ValueOperations<String,List<OrderVo>> valueOperations = redisTemplate.opsForValue();
        if (redisTemplate.hasKey(key)){
            List<OrderVo> voList = valueOperations.get(key);
            return ServiceResponse.createBysuccessMessage("ok",voList);
        }
        List<Order> list = orderMapper.selectOrderbyuserid(userid);
       List<OrderVo> voList =new ArrayList<>();
       for(Order order:list){
           OrderVo orderVo=new OrderVo();
           orderVo.setStatus(order.getStatus());
           Shop shop = shopMapper.selectByPrimaryKey(order.getShopId());
           orderVo.setShopname(shop.getShopname());
           orderVo.setCreateTime(order.getCreateTime());
           orderVo.setShoptel(shop.getTel());
           voList.add(orderVo);
       }
       valueOperations.set(key,voList,5,TimeUnit.HOURS);
        return ServiceResponse.createBysuccessMessage("ok",voList);
    }

    private List<OrderVo> makeOrderVo(List<Order> list) {
        List<OrderVo> listvo=new ArrayList<>();
        for (Order order:list) {
            OrderVo orderVo=new OrderVo();
            orderVo.setCreateTime(order.getCreateTime());
            orderVo.setOrderId(order.getOrderId());
            orderVo.setPrice(order.getPrice());
            orderVo.setStatus(order.getStatus());
            orderVo.setRemark(order.getRemark());
            User user=userMapper.selectByPrimaryKey(order.getUserId());
            orderVo.setNickname(user.getNickname());
            orderVo.setPhone(user.getPhone());
            List<Integer> productids=orderToProductMapper.selectproductbyshopid(order.getOrderId());
            List<String> productname=new ArrayList<>();
            for (Product product:productMapper.selectByids(productids)) {
                productname.add(product.getProductName());
            }
            orderVo.setProductname(productname);
            listvo.add(orderVo);
        }
        return listvo;
    }

    private BigDecimal getPrice(List<Integer> list) {
           List<Product>  lists=  productMapper.selectByids(list);
           BigDecimal price =new BigDecimal(0);
        for (Product product : lists) {
            price= BigDecimalUtil.add(price.doubleValue(),product.getPrice().doubleValue());
        }
        return price;
    }
}
