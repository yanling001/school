package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.SecondHandService;
import com.example.demo.common.ServiceResponse;
import com.example.demo.pojo.ProductComment;
import com.example.demo.pojo.SecondHandProduct;
import com.example.demo.pojo.vo.ProductVo;
import com.example.demo.pojo.vo.SecondHandProductVo;
import com.example.demo.pojo.vo.ShopVo;
import com.example.demo.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/second")
public class SecondHandController {
    @Autowired
    SecondHandService secondHandService;
    //获取二手手信息
    @RequestMapping("/msg")
    public ServiceResponse<SecondHandProductVo> getproductinfobyid(Integer id){
        if (id==null){
            return  ServiceResponse.createByErrorMessage("参数错误");
        }
        return secondHandService.getproductinfobyid(id);
    }
    @RequestMapping("/merchandiseList")
    public ServiceResponse<List<SecondHandProductVo>> getproductinfo(){
        return secondHandService.getproductinfo();
    }

    //获取用户收藏的二手商品
    @RequestMapping("/getcollectproduct")
    public  ServiceResponse<List<SecondHandProductVo>> getcollectproduct(Integer userId){
        if (userId==null){
            return ServiceResponse.createByErrorMessage("参数错误");
        }
        return secondHandService.getcollectproduct(userId);
    }
    //当前用户获取二手产品信息看当前用户有没有收藏
    @RequestMapping("/product")
    public  ServiceResponse<List<ProductVo>> getproduct(Integer userId){
       if (userId==null) return ServiceResponse.createByErrorMessage("参数错误");
        return secondHandService.getproduct(userId);
    }
    //添加二手商品
    @RequestMapping("/addproduct")
    public  ServiceResponse addproduct(@RequestBody String info){
        SecondHandProduct secondHandProduct=JSON.parseObject(info,SecondHandProduct.class);
        System.out.println(JSONObject.parseObject(info).getString("createTime"));
        secondHandProduct.setCreateTime(DateTimeUtil.strToDate(DateTimeUtil.dateToStr(new Date())));
        if(secondHandProduct.getUserId()==null){
            return ServiceResponse.createByErrorMessage("参数错误");
        }
        //        JSONObject jsonObject= JSONObject.parseObject(info);
//        SecondHandProduct secondHandProduct=new SecondHandProduct();
//        secondHandProduct.setPrice(jsonObject.getBigDecimal("price"));
//        secondHandProduct.setContent(jsonObject.getString("content"));
//        secondHandProduct.setUserId(jsonObject.getInteger("userId"));
//        secondHandProduct.setCreateTime(jsonObject.getDate("createTime"));
//        secondHandProduct.setStatus(0);
//        secondHandProduct.setName(jsonObject.getString("title"));
//        secondHandProduct.setVideoAddress(jsonObject.getString("videoAddress"));
//        secondHandProduct.setLocation(jsonObject.getString("location"));
//        secondHandProduct.setNewDegree(jsonObject.getString("newDegree"));

      //  SecondHandProduct secondHandProduct = JSON.parseObject(info, SecondHandProduct.class);
        List<String> imge = (List<String>) JSONObject.parseObject(info).get("images");
        return secondHandService.addproduct(secondHandProduct,imge);
    }
   //收藏二手产品
    @RequestMapping("/collect")
    public  ServiceResponse collect(Integer userid,Integer productid){
        if (userid==null||productid==null){
            return ServiceResponse.createByErrorMessage("参数错误");
        }
       return  secondHandService.collect(userid,productid);

    }

    //评价二手产品
    @RequestMapping("/comments")
    public  ServiceResponse comments(@RequestBody String info){
        ProductComment productComment = JSON.parseObject(info,ProductComment.class);
        if (productComment.getUserId()==null||productComment.getProductId()==null){
            return ServiceResponse.createByErrorMessage("参数错误");
        }
        List<String> list = (List<String>) JSONObject.parseObject(info).get("images");
        return  secondHandService.comments(productComment,list);

    }
}
