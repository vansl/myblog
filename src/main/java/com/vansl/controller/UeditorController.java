package com.vansl.controller;

import com.baidu.ueditor.ActionEnter;
import com.vansl.utils.AliyunOSSUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: vansl
 * @create: 18-4-10 下午10:23
 */

@Controller
@RequestMapping("/ued")
public class UeditorController {

    @GetMapping(value="")
    public void config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext().getRealPath("/");

        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/image")
    @ResponseBody
    public Map upload(String userId,/*String blogId,*/MultipartFile upfile) throws IOException {
        //返回上传结果（url等）
        Map<String,Object> result = new HashMap<>();

        try {
            if(upfile!=null&&upfile.getOriginalFilename()!=""){
                //获取文件名
                String fileName=upfile.getOriginalFilename();
                //上传到阿里云oss
                String url=AliyunOSSUtil.upload(upfile.getInputStream(),fileName,"vanslblog",userId);
                //返回上传结果
                result.put("state","SUCCESS");
                result.put("url",url);
            }
        }catch (Exception e){
            result.put("state","FAILED");
            e.printStackTrace();
        }finally {
            return result;
        }
    }
}
