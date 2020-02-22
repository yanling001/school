package com.example.demo.Service.Impl;

import com.example.demo.Service.SecondHandService;
import com.example.demo.common.ServiceResponse;
import com.example.demo.dao.*;
import com.example.demo.pojo.Collect;
import com.example.demo.pojo.Image;
import com.example.demo.pojo.ProductComment;
import com.example.demo.pojo.SecondHandProduct;
import com.example.demo.pojo.vo.ProductCommentVo;
import com.example.demo.pojo.vo.ProductVo;
import com.example.demo.pojo.vo.SecondHandProductVo;
import com.example.demo.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SecondHandServiceImp implements SecondHandService {
    @Autowired
    SecondHandProductMapper secondHandProductMapper;
    @Autowired
    ImageMapper imageMapper;
    @Autowired
    ProductCommentMapper productCommentMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    CollectMapper collectMapper;
    @Override
    public ServiceResponse<List<SecondHandProductVo>> getproductinfo() {
       List<SecondHandProduct> list=secondHandProductMapper.selectAll();
       List<SecondHandProductVo> relist=makevo(list);
        return ServiceResponse.createBysuccessMessage("ok",relist);
    }

    @Override
    public ServiceResponse<List<ProductVo>> getproduct(Integer userId) {
        List<SecondHandProduct> list=secondHandProductMapper.selectAll();
        List<ProductVo> relist=makeProductvo(list,userId);
        return ServiceResponse.createBysuccessMessage("ok",relist);
    }

    @Override
    public ServiceResponse addproduct(SecondHandProduct product,List<String> image) {
        product.setCreateTime(new Date());
        product.setStatus(0);
        secondHandProductMapper.insert(product);
        //获取id
        int id=secondHandProductMapper.selectid(product);
        for (String url:image) {
            Image images=new Image();
            images.setProductId(id);
            images.setImgAddress(url);
            imageMapper.insert(images);
        }
        return ServiceResponse.createBysuccessMessage("ok");
    }

    @Override
    public ServiceResponse collect(Integer userid, Integer productid) {
        Collect collect=new Collect();
        collect.setProductId(productid);
        collect.setUserId(userid);
        collectMapper.insert(collect);
        return ServiceResponse.createBysuccessMessage("ok");
    }

    @Override
    public ServiceResponse comments(ProductComment productComment, MultipartFile[] files) {
        productComment.setCreateTime(new Date());
        productCommentMapper.insert(productComment);
        int id=productCommentMapper.getcommentid(productComment);
        for (MultipartFile file:files) {
           String url = FileUtil.uplod(file);
           Image image=new Image();
           image.setCommentId(id);
           image.setImgAddress(url);
           image.setProductId(productComment.getProductId());
        }


        return ServiceResponse.createBysuccessMessage("ok");
    }

    private List<ProductVo> makeProductvo(List<SecondHandProduct> list,Integer userId) {
        List<ProductVo> relist=new ArrayList<>();
        for (SecondHandProduct secondHandProduct: list){
            ProductVo secondHandProductVo=new ProductVo();
            secondHandProductVo.setCategory(secondHandProduct.getCategory());
            secondHandProductVo.setContent(secondHandProduct.getContent());
            secondHandProductVo.setCreateTime(secondHandProduct.getCreateTime());
            secondHandProductVo.setProductId(secondHandProduct.getProductId());
            secondHandProductVo.setName(secondHandProduct.getName());
            secondHandProductVo.setPrice(secondHandProduct.getPrice());
            secondHandProductVo.setStatus(secondHandProduct.getStatus());
            secondHandProductVo.setVideoAddress(secondHandProduct.getVideoAddress());
            secondHandProductVo.setUser(userMapper.selectByPrimaryKey(secondHandProduct.getUserId()));
            secondHandProductVo.setImages(imageMapper.selectImageByproductid(secondHandProduct.getProductId()));
            int count= collectMapper.selectCount(userId,secondHandProduct.getProductId());
            if (count>0){
                secondHandProductVo.setCollectStatus(1);//表示该用户以经收藏
            } else    secondHandProductVo.setCollectStatus(0);//表示该用户还没收藏
            relist.add(secondHandProductVo);
        }
        return  relist;
    }

    private List<SecondHandProductVo> makevo(List<SecondHandProduct> list) {
        List<SecondHandProductVo> relist=new ArrayList<>();
        for (SecondHandProduct secondHandProduct: list){
            SecondHandProductVo secondHandProductVo=new SecondHandProductVo();
            secondHandProductVo.setCategory(secondHandProduct.getCategory());
            secondHandProductVo.setContent(secondHandProduct.getContent());
            secondHandProductVo.setCreateTime(secondHandProduct.getCreateTime());
            secondHandProductVo.setProductId(secondHandProduct.getProductId());
            secondHandProductVo.setName(secondHandProduct.getName());
            secondHandProductVo.setPrice(secondHandProduct.getPrice());
            secondHandProductVo.setStatus(secondHandProduct.getStatus());
            secondHandProductVo.setVideoAddress(secondHandProduct.getVideoAddress());
            secondHandProductVo.setUser(userMapper.selectByPrimaryKey(secondHandProduct.getUserId()));
            secondHandProductVo.setComments(makecommentvo(productCommentMapper.selectByproductid(secondHandProduct.getProductId())));
            secondHandProductVo.setImages(imageMapper.selectImageByproductid(secondHandProduct.getProductId()));
           relist.add(secondHandProductVo);
        }
        return  relist;
    }

    private List<ProductCommentVo> makecommentvo(List<ProductComment> list) {
        List<ProductCommentVo> relist=new ArrayList<>();
        for (ProductComment productComment: list){
            ProductCommentVo productCommentVo=new ProductCommentVo();
            productCommentVo.setCommentId(productComment.getCommentId());
            productCommentVo.setContent(productComment.getContent());
            productCommentVo.setCreateTime(productComment.getCreateTime());
            productCommentVo.setProductId(productComment.getProductId());
            productCommentVo.setStar(productComment.getStar());
            productCommentVo.setImages(imageMapper.selectImageBycommentid(productComment.getCommentId()));
            productCommentVo.setUser(userMapper.selectByPrimaryKey(productComment.getUserId()));
            relist.add(productCommentVo);
        }
        return  relist;
    }
}
