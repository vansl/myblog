package com.vansl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author: vansl
 * @create: 18-3-19 下午4:15
 */

@Controller
public class FileUploadController  {

    @RequestMapping("/fileUpload")
    public ModelAndView upload(HttpServletRequest request) throws IOException{
        long startTime = System.currentTimeMillis();
        ModelAndView result = new ModelAndView("result");
        double totalSize=0;
        try {
            //将当前上下文初始化给 CommonsMutipartResolver (多部分解析器)
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            //检查form中是否有enctype="multipart/form-data"
            if(multipartResolver.isMultipart(request))
            {
                //将request变成多request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
                //获取multiRequest中所有的文件名
                Iterator iter = multiRequest.getFileNames();

                while(iter.hasNext()){
                    //一次遍历所有的文件
                    MultipartFile file = multiRequest.getFile(iter.next().toString());
                    if(file!=null){

                        String path = request.getServletContext().getRealPath("/WEB-INF/statics/share/")+file.getOriginalFilename();
                        //上传
                        file.transferTo(new File(path));
                        totalSize+=file.getSize()/1048576.0;
                    }
                }
            }
            String uploadTime=String.valueOf((System.currentTimeMillis()-startTime)/1000.0);
            System.out.print(String.format("%.2f",totalSize));
            result.addObject("size", String.format("%.2f",totalSize));
            result.addObject("uploadTime", uploadTime);
        }catch (MaxUploadSizeExceededException e){
            result.addObject("error", "上传文件大于5MB");
        }catch (Exception e){
            result.addObject("error", e.getMessage());
        }finally {
            return result;
        }

    }
}
