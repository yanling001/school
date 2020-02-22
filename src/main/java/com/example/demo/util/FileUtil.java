package com.example.demo.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

public class FileUtil {

    public static String uplod(MultipartFile file) {
        String fileName=file.getOriginalFilename();
        //获取文件扩展名fileExtensionName
        System.out.println(fileName);
        String list =fileName.substring(fileName.lastIndexOf("."),fileName.length());
        System.out.println(list);
        // int index =list.length-2;
        String fileExtensionName=list;
        System.out.println(fileExtensionName);
        String uplodFileName = UUID.randomUUID().toString()+fileExtensionName;
        System.out.println(uplodFileName);
        Configuration cfg = new Configuration(Zone.zone0());//华东
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String ak="VhF41Iek6pcuuXb-k2z1u5OrYsqdllF9LPC_wGX1";
        String sk="0CdtBZy5tWQZuz0cNSZkHS3C_9zzwEdAkuB9J8FB";
        String bucket = "hongyaoz";
        String localFilePath = "D:\\image\\5a27d7b1ad6e8.jpg";     //上传图片路径
        String key = uplodFileName;
        Auth auth = Auth.create(ak, sk);
        String upToken = auth.uploadToken(bucket);
        byte[] uploadBytes = new byte[0];
        try {
            uploadBytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayInputStream byteInputStream=new ByteArrayInputStream(uploadBytes);

        try {
            Response response = uploadManager.put(byteInputStream,key,upToken,null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        }
        catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
        }
        String ans="http://q5tn126d8.bkt.clouddn.com/"+uplodFileName;
        System.out.println(ans);
        return ans;
    }
}
