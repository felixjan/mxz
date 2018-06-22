package com.comm.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadUtils {
	/**
	 * @param request
	 * @param file 	  上传的文件 
	 * @param fileUrl 文件上传路径  例子/upload/images/
	 * @return
	 * @throws IOException
	 */
	public static String upload(HttpServletRequest request,MultipartFile file,String fileUrl) throws IOException{
	   //如果文件不为空，写入上传路径
       if(!file.isEmpty()) {
    	   //上传文件名
    	   String fileName = file.getOriginalFilename();
    	   String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
           String newFileName=DateUtils.currentTimeMillis()+"."+suffix;
           System.out.println(HttpUtils.getUrl(request)+fileUrl+newFileName);
    	   //上传文件路径
    	   String path = request.getServletContext().getRealPath(fileUrl);
    	   //获取指定文件或文件夹在工程中真实路径，getRequest()这个方法是返回一个HttpServletRequest，封装这个方法为了处理编码问题
    	   FileOutputStream fos = FileUtils.openOutputStream(new File(path+"/" +newFileName));//打开FileOutStrean流
    	   IOUtils.copy(file.getInputStream(),fos);//将MultipartFile file转成二进制流并输入到FileOutStrean
    	   fos.close();
           return HttpUtils.getUrl(request)+fileUrl+newFileName;
       } else {
           return "";
       }
	}
}
