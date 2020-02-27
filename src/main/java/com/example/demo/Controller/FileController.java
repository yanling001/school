package com.example.demo.Controller;

import com.example.demo.common.ServiceResponse;
import com.example.demo.util.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {
    @RequestMapping("/upload")
    @ResponseBody
    public ServiceResponse<List<String>> upload( MultipartFile[] file){
        List<String> list=new ArrayList<>();
        for (MultipartFile multipartFile:file){
            String uplod = FileUtil.uplod(multipartFile);
            list.add(uplod);
        }
        return ServiceResponse.createBysuccessMessage("ok",list);

    }
}
