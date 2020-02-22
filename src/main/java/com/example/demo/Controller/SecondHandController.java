package com.example.demo.Controller;

import com.example.demo.Service.SecondHandService;
import com.example.demo.common.ServiceResponse;
import com.example.demo.pojo.ProductComment;
import com.example.demo.pojo.SecondHandProduct;
import com.example.demo.pojo.vo.ProductVo;
import com.example.demo.pojo.vo.SecondHandProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/second")
public class SecondHandController {
    @Autowired
    SecondHandService secondHandService;
    //获取二手手信息
    @RequestMapping("merchandiseList")
     public ServiceResponse<List<SecondHandProductVo>> getproductinfo(){
         return secondHandService.getproductinfo();
     }
    //当前用户获取二手产品信息看当前用户有没有收藏
    @RequestMapping("/product")
    public  ServiceResponse<List<ProductVo>> getproduct(Integer userId){
       return secondHandService.getproduct(userId);
    }
    //添加二手商品
    @RequestMapping("/addproduct")
    public  ServiceResponse addproduct(@RequestBody Map<String,Object> product ){
        SecondHandProduct secondHandProduct=new SecondHandProduct();
        secondHandProduct.setStatus(0);
        secondHandProduct.setUserId((Integer) product.get("pu"));
        secondHandProduct.setContent((String) product.get("content"));
        secondHandProduct.setPrice((BigDecimal) product.get("price"));
        List<String> imge = (List<String>) product.get("pic");
        return secondHandService.addproduct(secondHandProduct,imge);
    }
   //收藏二手产品
    @RequestMapping("/collect")
    public  ServiceResponse collect(Integer userid,Integer productid){
       return  secondHandService.collect(userid,productid);

    }

    //评价二手产品
    @RequestMapping("/comments")
    public  ServiceResponse comments(ProductComment productComment, MultipartFile file[]){
        return  secondHandService.comments(productComment,file);

    }
}
