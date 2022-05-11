package edu.aynu.common.utils;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

public class UploadUtils {

    //定义一个上传文件的路径
    private static String BASE_PATH = null;//"src\\main\\resources\\static\\pictures\\";

    static {
        try {
            BASE_PATH = ResourceUtils.getURL("classpath:").getPath() + "static/pictures";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //定义文件服务器的访问地址
    //private static  final String SERVER_PATH="http://localhost:8080/pictures/";
    public static String upload(MultipartFile file){
        //获得上传文件的名称
        String filename = file.getOriginalFilename();
        //创建UUID，用来保持文件名字的唯一性
        String uuid = UUID.randomUUID().toString().replace("-","");
        //进行文件名称的拼接
        String newFileName = uuid+"-"+filename;
        //创建文件实例对象
        File uploadFile = new File(BASE_PATH,newFileName);
        //判断当前文件是否存在
        if (!uploadFile.exists()){
            //如果不存在就创建一个文件夹
            uploadFile.mkdirs();
        }
        //执行文件上传的命令
        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            return null;
        }
        //将上传的文件名称返回，注意，这里我们返回一个 服务器地址
        return newFileName; //不要server-path
    }

    public static void delete(String filename){
        File file = new File("src\\main\\resources\\static\\pictures\\", filename);
        System.out.println("+++++++++++"+filename);
        if (file.exists()){
            file.delete();
        }
    }
}
