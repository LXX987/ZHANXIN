package com.huaqi.zhanxin.service.impl;

import com.huaqi.zhanxin.entity.*;
import com.huaqi.zhanxin.mapper.PictureMapper;
import com.huaqi.zhanxin.mapper.UserMapper;
import com.huaqi.zhanxin.service.PictureService;
import com.huaqi.zhanxin.service.UserService;
import com.huaqi.zhanxin.tools.UploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    PictureMapper pictureMapper;
    @Value("${path.picture-upload-path}")
    private String PIC_UPLOAD_PATH;
    //private static final Logger LOGGER= LoggerFactory.getLogger(PictureService.class);
    @Override
    public String uploadFile(int userID, MultipartFile file, String picType, HttpServletRequest request) throws Exception {
        String pic_url;
        try {
            pic_url = UploadUtil.upload(file, PIC_UPLOAD_PATH, request);
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
        if(pic_url == null)
            return "-2";
        pictureMapper.fileInsert(userID, 0,picType, pic_url);
        return pic_url;
//        String os = System.getProperty("os.name");
//        File fileRealPath;   //文件存放地址
//        if (os.toLowerCase().startsWith("win")) {  //windows系统
//            String path = System.getProperty("user.dir");  //获取项目相对路径
//
//            fileRealPath = new File(path+"/src//main/resources/file");
//        }else{//linux系统
//            //获取根目录
//            //如果是在本地windows环境下，目录为项目的target\classes下
//            //如果是linux环境下，目录为jar包同级目录
//            File rootPath = new File(ResourceUtils.getURL("classpath:").getPath());
//            if(!rootPath.exists()){
//                rootPath = new File("");
//            }
//            fileRealPath = new File(rootPath.getAbsolutePath()+"/file/");
//        }
//        //判断文件夹是否存在
//        if(!fileRealPath.exists()){
//            //不存在，创建
//            fileRealPath.mkdirs();
//        }
////        获取文件名称
//        String Name = file.getOriginalFilename();
//        String prefix = Name.substring(Name.lastIndexOf(".")+1);
//
//        int num = getCurNumber()+1;
//        //文件名命名
//        String fileName = String.valueOf(userID)+ String.valueOf(num) +"." +prefix;
//
//        //创建文件存放地址
//        File resultPath = new File(fileRealPath+"/"+fileName);
//        if (resultPath.exists()){
//            LOGGER.warn("文件已经存在！");
//            return "false！";
//        }
////        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        file.transferTo(resultPath);
////        fileMapper.insert(title, sdf.format(new Date()), imageName, fileName);
//
//        pictureMapper.fileInsert(userID,0,picType,fileName);
//
//        System.out.println("absolutePath:"+fileRealPath.getCanonicalPath());
//        System.out.println("resultPath:"+resultPath.getCanonicalPath());
//        return "true！";
    }
    @Override
    public int getCurNumber() {
        if(pictureMapper.getCurNumber()==null) {
            return 0;
        } else {
            return pictureMapper.getCurNumber();
        }

    }

    @Override
    public Picture getHouse(int userId) {
        return pictureMapper.getHouse(userId);
    }

    @Override
    public List<Picture> getCrime(int userId) {
        return pictureMapper.getCrime(userId);
    }

    @Override
    public List<Picture> getBlood(int userId) {
        return pictureMapper.getBlood(userId);
    }

    @Override
    public List<Picture> getVolunteer(int userId) {
        return pictureMapper.getVolunteer(userId);
    }

    @Override
    public List<Picture> getDonation(int userId) {
        return pictureMapper.getDonation(userId);
    }

    @Override
    public List<Picture> getPhoneCost(int userId) {
        return pictureMapper.getPhoneCost(userId);
    }

    @Override
    public List<Picture> pictureList() {
        return pictureMapper.pictureList();
    }
}
