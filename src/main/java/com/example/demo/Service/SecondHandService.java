package com.example.demo.Service;

import com.example.demo.common.ServiceResponse;
import com.example.demo.pojo.ProductComment;
import com.example.demo.pojo.SecondHandProduct;
import com.example.demo.pojo.vo.ProductCommentVo;
import com.example.demo.pojo.vo.ProductVo;
import com.example.demo.pojo.vo.SecondHandProductVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SecondHandService {
    ServiceResponse<List<SecondHandProductVo>> getproductinfo();

    ServiceResponse<List<ProductVo>> getproduct(Integer userId);

    ServiceResponse addproduct(SecondHandProduct product,List<String> image);

    ServiceResponse collect(Integer userid, Integer productid);

    ServiceResponse comments(ProductComment productComment,List<String> images);

    ServiceResponse<SecondHandProductVo> getproductinfobyid(Integer id);

    ServiceResponse<List<SecondHandProductVo>> getcollectproduct(Integer userId);
}
