package com.linkinone.Lio.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonObject;

@Controller
public class CkeditorFileUploadController {

    @RequestMapping(value="/fileupload", method=RequestMethod.POST)
    @ResponseBody
    public String fileUpload(HttpServletRequest req, HttpServletResponse resp,
                             MultipartHttpServletRequest multiFile) throws Exception {
        JsonObject json = new JsonObject();
        PrintWriter printWriter = null;
        OutputStream out = null;
        MultipartFile file = multiFile.getFile("upload");
        if(file != null){
            if(file.getSize() > 0 && StringUtils.isNotBlank(file.getName())){
                if(file.getContentType().toLowerCase().startsWith("image/")){
                    try{
                        String fileName = file.getName();
                        byte[] bytes = file.getBytes();
                        String uploadPath = req.getServletContext().getRealPath("/img");
                        File uploadFile = new File(uploadPath);
                        if(!uploadFile.exists()) {
                            uploadFile.mkdirs();
                        }
                        fileName = UUID.randomUUID().toString();
                        uploadPath = uploadPath + "/" + fileName;
                        out = new FileOutputStream(new File(uploadPath));
                        out.write(bytes);

                        printWriter = resp.getWriter();
                        resp.setContentType("text/html");
                        String fileUrl = req.getContextPath() + "/img/" + fileName;

                        //json데이터
                        json.addProperty("uploaded", 1);
                        json.addProperty("fileName", fileName);
                        json.addProperty("url", fileUrl);

                        printWriter.println(json);
                    }catch(IOException e){
                        e.printStackTrace();
                    }finally {
                        if(out != null){
                            out.close();
                        }
                        if(printWriter != null) {
                            printWriter.close();
                        }
                    }
                }
            }
        }
        return null;
    }
}
