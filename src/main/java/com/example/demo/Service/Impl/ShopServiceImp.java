package com.example.demo.Service.Impl;

import com.example.demo.Service.ShopService;
import com.example.demo.common.ServiceResponse;
import com.example.demo.dao.*;
import com.example.demo.pojo.*;
import com.example.demo.pojo.vo.OrderVo;
import com.example.demo.util.BigDecimalUtil;
import com.example.demo.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Override
    public ServiceResponse addShop(Shop shop) {

        int k= shopMapper.insert(shop);
        if (k>=0) return ServiceResponse.createBysuccessMessage("ok");
        else  return ServiceResponse.createByErrorMessage("error");
    }

    @Override
    public ServiceResponse addOrder(List<Integer> list, Integer userid,String remark) {
        //先创建Order对象
        Order order=new Order();
        order.setPrice(getPrice(list));
        order.setRemark(remark);
        order.setStatus(0);
        order.setCreateTime(DateTimeUtil.strToDate(DateTimeUtil.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
        order.setUserId(userid);
        //添加 order
        orderMapper.insert(order);
        //获取 order的id
        int id=orderMapper.selectOrderid(order);
        //添加ordertoproduct
        for (Integer productid: list) {
            OrderToProduct orderToProduct=new OrderToProduct();
            orderToProduct.setOrderId(id);
            orderToProduct.setProductid(productid);
            orderToProductMapper.insert(orderToProduct);
        }

        return ServiceResponse.createBysuccessMessage("ok");
    }

    @Override
    public ServiceResponse<List<OrderVo>> getorders(Integer shopid) {
        List<Order> list = orderMapper.selectOrderbyshopid(shopid);
        List<OrderVo> voList=makeOrderVo(list);
        return ServiceResponse.createBysuccessMessage("ok",voList);
    }

    @Override
    public ServiceResponse<List<Shop>> getshops() {
        List<Shop> list =  shopMapper.selectAll();
        return ServiceResponse.createBysuccessMessage("ok",list);
    }

    @Override
    public ServiceResponse addproduct(Integer shopid, Product product) {
        product.setShopId(shopid);
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

    private List<OrderVo> makeOrderVo(List<Order> list) {
        List<OrderVo> listvo=new ArrayList<>();
        for (Order order:list) {
            OrderVo orderVo=new OrderVo();
            orderVo.setCreateTime(order.getCreateTime());
            orderVo.setOrderId(order.getOrderId());
            orderVo.setPrice(order.getPrice());
            orderVo.setStatus(order.getStatus());
            orderVo.setRemark(order.getRemark());
            User user = userMapper.selectByPrimaryKey(order.getUserId());
            orderVo.setNickname(user.getNickname());
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
