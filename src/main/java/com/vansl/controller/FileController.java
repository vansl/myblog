package com.vansl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
 * @create: 18-4-27 下午11:44
 */

@Controller
public class FileController {

    //添加日志器
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    //action映射
    @GetMapping("/select")
    public  String select(HttpServletRequest request){
        //输出日志文件
        logger.info("Build Success");
        //返回select.jsp
        return "select";
    }

    @GetMapping("/files/{begindir}")
    public  String listFiles(HttpServletRequest request, @PathVariable String begindir) {
        System.out.println(begindir);
        //设置文件遍历起始目录
        request.setAttribute("beginDir",begindir);

        return "files";
    }

    @PostMapping("/fileUpload")
    public ModelAndView upload(HttpServletRequest request) throws IOException {
        long startTime = System.currentTimeMillis();
        ModelAndView result = new ModelAndView("result");
        double totalSize=0;
        try {
            //将当前上下文初始化给 CommonsMutipartResolver (多部分解析器)
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            //检查form中是否有enctype="multipart/form-data"
            if(multipartResolver.isMultipart(request)) {
                //将request变成多request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
                //获取multiRequest中所有的文件名
                Iterator iter = multiRequest.getFileNames();

                while(iter.hasNext()){
                    //一次遍历所有的文件
                    MultipartFile file = multiRequest.getFile(iter.next().toString());
                    if(file!=null&&file.getOriginalFilename()!=""){
                        String path = "/home/vansl/share/"+file.getOriginalFilename();
                        //上传
                        file.transferTo(new File(path));
                        totalSize+=file.getSize()/1048576.0;
                    }
                }
            }
            String uploadTime=String.valueOf((System.currentTimeMillis()-startTime)/1000.0);
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
