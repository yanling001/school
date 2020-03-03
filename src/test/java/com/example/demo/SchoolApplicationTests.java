package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.*;

import com.example.demo.pojo.*;
import com.example.demo.pojo.vo.SecondHandProductVo;
import com.example.demo.util.BigDecimalUtil;
import com.example.demo.util.DateTimeUtil;
import com.example.demo.util.HttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;


@SpringBootTest(classes = SchoolApplication.class)
@RunWith(SpringRunner.class)
public class  SchoolApplicationTests {
   @Autowired
    InvitationMapper invitationMapper;
    @Autowired
    SecondHandProductMapper secondHandProductMapper;
    @Autowired
    ImageMapper imageMapper;
    @Autowired
    ProductCommentMapper productCommentMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ShopMapper shopMapper;
    @Test
    public  void testDao() {
//        SecondHandProduct secondHandProduct =new SecondHandProduct();
//        secondHandProduct.setCategory("数码产品");
//        secondHandProduct.setContent("9成新");
//        secondHandProduct.setTitle("笔记本--华为");
//        secondHandProduct.setUserId(1);
//        secondHandProduct.setVideoAddress("https://www.bilibili.com/video/av87096463?from=search&seid=9430981759363756771");
//        secondHandProduct.setCreateTime(DateTimeUtil.strToDate(DateTimeUtil.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
//        ProductComment productComment=new ProductComment();
//        productComment.setContent("质量很不错，服务很好");
//        productComment.setProductId(1);
//        productComment.setStar(5);
//        productComment.setUserId(2);
//        productComment.setCreateTime(DateTimeUtil.strToDate(DateTimeUtil.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
//         Image image=new Image();
//         image.setProductId(2);
//        image.setImgAddress("https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJEVmpaEF9QiayJdicaBGwxUJl8XfS8Lpw6VDuDc2kJvG1NjFxZDUg1ICMRFXzmBFVOiagALMu0I4oSQ/132");
//       System.out.println(secondHandProductMapper);
//        System.out.println(userMapper);
   //     productCommentMapper.insertSelective(productComment);
        // String punchTime = simpleDateFormat.format(date);   //格式化后的时间
        //System.out.println(punchTime);
      //  System.out.println(DateTimeUtil.strToDate(DateTimeUtil.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));

//        Invitation invitation=new Invitation();
//        invitation.setContent("帮取快递-西区水房-妈妈驿站-货号:3901");
//        invitation.setUesrId(1);
//        invitation.setInvitationStatus(0);
//        invitation.setCreateTime(DateTimeUtil.strToDate(DateTimeUtil.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
//        invitation.setUpdateTime(DateTimeUtil.strToDate(DateTimeUtil.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
//         invitationMapper.insertSelective(invitation);
//
//        User user=new User();
//        user.setCreateTime(DateTimeUtil.strToDate(DateTimeUtil.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
//        user.setUpdateTime(DateTimeUtil.strToDate(DateTimeUtil.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
//        user.setName("傅一航");
//        user.setPhone("13245677898");
//        user.setEmail("1604267198@qq.com");
//        userMapper.insertSelective(user);
        Map<String,Object> map=new HashMap<>();
     //   User user=userMapper.selectByPrimaryKey(1);
        List<Integer> list=new ArrayList<>();
        list.add(1);
        list.add(2);
        map.put("remark","少放辣");
        map.put("user",7);
        map.put("list",list);
        System.out.println(JSON.toJSON(map));
    }
    @Autowired
    private RedisTemplate redisTemplate;
  @Test
    public  void redis(){
      String key = "productcategory"+1;
      System.out.println(redisTemplate.hasKey(key));
//      Map<String,Object> map=new HashMap<>();
//      map.put("p",1);
//      map.put("content","tset");
//      map.put("time",DateTimeUtil.dateToStr(new Date()));
//      map.put("price", new BigDecimal(123.4));
//      System.out.println(JSON.toJSONString(map));
//      Shop shop=new Shop();
//      shop.setShopname("饺子");
//      shop.setLocation("西安");
//      shop.setUserId(1);
//      shop.setTel("1234567");
//      System.out.println(JSON.toJSONString(shop));
//      Product product=new Product();
//      product.setProductName("羊肉饺子");
//      product.setPrice(new BigDecimal(123.4));
//      product.setProductImg("url");
//      product.setShopId(1);
//      product.setProductDescrible("xxxx");
//      System.out.println(JSON.toJSONString(product));
//      SecondHandProductVo secondHandProductVo=new SecondHandProductVo();
//      secondHandProductVo.setTitle("课桌");
//      secondHandProductVo.setNewDegree("八成");
//      List<String> list=new ArrayList<>();
//      list.add("https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJ132");
//      list.add("https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJ13dsf");
//      secondHandProductVo.setImages(list);
//      System.out.println(JSON.toJSONString(secondHandProductVo));
//      System.out.println(secondHandProductMapper.selectByPrimaryKey(24).getCreateTime());
//      ProductComment productComment=new ProductComment();
//      productComment.setUserId(1);
//      productComment.setStar(4);
//      productComment.setContent("不错");
//      productComment.setProductId(2);
//      System.out.println(JSON.toJSONString(productComment));
//      Product product=new Product();
//      product.setShopId(1);
//      product.setProductId(1);
//      product.setPrice(BigDecimal.valueOf(144.0));
//      product.setProductDescrible("2333");
//      product.setProductName("羊肉饺子");
//      product.setProductImg("url..");
//      product.setCreateTime(DateTimeUtil.strToDate(DateTimeUtil.dateToStr(new Date())));
//      productMapper.updateByPrimaryKey(product);
//        Shop shop=new Shop();
//        shop.setImgstore("url");
//        shop.setIntro("********");
//        shop.setShopId(2);
//        shopMapper.updateByPrimaryKeySelective(shop);
//      Date date = DateTimeUtil.strToDate("2020-2-1 2:21:22");
//      System.out.println(DateTimeUtil.dateToStr(date));
  }
}
