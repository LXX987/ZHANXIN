package com.huaqi.zhanxin.controller;

import com.huaqi.zhanxin.entity.FileProperties;
import com.huaqi.zhanxin.entity.Picture;
import com.huaqi.zhanxin.entity.RestControllerHelper;
import com.huaqi.zhanxin.service.PictureService;
import com.huaqi.zhanxin.tools.GetInformationFromRequest;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("picture")
public class PictureController {
    private static final Logger LOGGER= LoggerFactory.getLogger(PictureController.class);
    @Autowired
    private PictureService pictureService;
    RestControllerHelper helper = new RestControllerHelper();

    @Autowired
    private FileProperties fileProperties;

    @ApiOperation(value="上传房产证")
    @ResponseBody
    @PostMapping("uploadHouse")
    public Map<String, Object> uploadHouse(HttpServletRequest request,
                                            @RequestParam("file") MultipartFile file){

        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();

        if(file.isEmpty()){
            map.put("success","0");
            map.put("file","上传文件为空！");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        }
        try {
            String result = pictureService.uploadFile(userID,file,"house");
            LOGGER.info(result);
            map.put("success","1");
            map.put("file","上传文件成功！");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("文件上传失败！");
            map.put("success","0");
            map.put("file","上传文件失败！");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        }
    }

    @ApiOperation(value="上传车产证明")
    @ResponseBody
    @PostMapping("uploadCar")
    public Map<String, Object> uploadCar(HttpServletRequest request,
                                            @RequestParam("file") MultipartFile file){

        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();

        if(file.isEmpty()){
            map.put("success","0");
            map.put("file","上传文件为空！");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        }
        try {
            String result = pictureService.uploadFile(userID,file,"car");
            LOGGER.info(result);
            map.put("success","1");
            map.put("file","上传文件成功！");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("文件上传失败！");
            map.put("success","0");
            map.put("file","上传文件失败！");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        }
    }

    @ApiOperation(value="上传银行流水")
    @ResponseBody
    @PostMapping("uploadBank")
    public Map<String, Object> uploadBank(HttpServletRequest request,
                                         @RequestParam("file") MultipartFile file){

        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();

        if(file.isEmpty()){
            map.put("success","0");
            map.put("file","上传文件为空！");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        }
        try {
            String result = pictureService.uploadFile(userID,file,"bank");
            LOGGER.info(result);
            map.put("success","1");
            map.put("file","上传文件成功！");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("文件上传失败！");
            map.put("success","0");
            map.put("file","上传文件失败！");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        }
    }

    @ApiOperation(value="上传无犯罪记录证明")
    @ResponseBody
    @PostMapping("uploadCrime")
    public Map<String, Object> uploadCrime(HttpServletRequest request,
                                          @RequestParam("file") MultipartFile file){

        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();

        if(file.isEmpty()){
            map.put("success","0");
            map.put("file","上传文件为空！");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        }
        try {
            String result = pictureService.uploadFile(userID,file,"crime");
            LOGGER.info(result);
            map.put("success","1");
            map.put("file","上传文件成功！");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("文件上传失败！");
            map.put("success","0");
            map.put("file","上传文件失败！");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        }
    }

    @ApiOperation(value="上传献血证明")
    @ResponseBody
    @PostMapping("uploadBlood")
    public Map<String, Object> uploadBlood(HttpServletRequest request,
                                           @RequestParam("file") MultipartFile file){

        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();

        if(file.isEmpty()){
            map.put("success","0");
            map.put("file","上传文件为空！");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        }
        try {
            String result = pictureService.uploadFile(userID,file,"blood");
            LOGGER.info(result);
            map.put("success","1");
            map.put("file","上传文件成功！");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("文件上传失败！");
            map.put("success","0");
            map.put("file","上传文件失败！");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        }
    }

    @ApiOperation(value="上传志愿服务证明")
    @ResponseBody
    @PostMapping("uploadVolunteer")
    public Map<String, Object> uploadVolunteer(HttpServletRequest request,
                                           @RequestParam("file") MultipartFile file){

        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();

        if(file.isEmpty()){
            map.put("success","0");
            map.put("file","上传文件为空！");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        }
        try {
            String result = pictureService.uploadFile(userID,file,"volunteer");
            LOGGER.info(result);
            map.put("success","1");
            map.put("file","上传文件成功！");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("文件上传失败！");
            map.put("success","0");
            map.put("file","上传文件失败！");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        }
    }

    @ApiOperation(value="上传捐款证明")
    @ResponseBody
    @PostMapping("uploadDonation")
    public Map<String, Object> uploadDonation(HttpServletRequest request,
                                               @RequestParam("file") MultipartFile file){

        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();

        if(file.isEmpty()){
            map.put("success","0");
            map.put("file","上传文件为空！");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        }
        try {
            String result = pictureService.uploadFile(userID,file,"donation");
            LOGGER.info(result);
            map.put("success","1");
            map.put("file","上传文件成功！");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("文件上传失败！");
            map.put("success","0");
            map.put("file","上传文件失败！");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        }
    }

    @ApiOperation(value="上传其他公益证明")
    @ResponseBody
    @PostMapping("uploadBenefit")
    public Map<String, Object> uploadBenefit(HttpServletRequest request,
                                              @RequestParam("file") MultipartFile file){

        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();

        if(file.isEmpty()){
            map.put("success","0");
            map.put("file","上传文件为空！");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        }
        try {
            String result = pictureService.uploadFile(userID,file,"otherBenefit");
            LOGGER.info(result);
            map.put("success","1");
            map.put("file","上传文件成功！");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("文件上传失败！");
            map.put("success","0");
            map.put("file","上传文件失败！");
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        }
    }

    @ApiOperation(value="获取房产证审核信息")
    @PostMapping("getHouseState")
    @ResponseBody
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataTypeClass = String.class, name = "token", value = "token标记", required = true)})
    public Map<String, Object> getAvatarImage(HttpServletRequest request)
    {
        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userId = getInfo.getUserId();
        RestControllerHelper helper = new RestControllerHelper();
//        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
//        Path avatarPath = Paths.get(fileProperties.getDocDir()).toAbsolutePath().normalize();
//        Path path = avatarPath.resolve(avatarName).normalize();
        Picture picture =pictureService.getHouse(userId);
        String picName=picture.getPicType();
        if(picName == null)
        {
            return null;
        }
        Path avatarPath = Paths.get(fileProperties.getDocDir()).toAbsolutePath().normalize();
        Path path = avatarPath.resolve(picName).normalize();
        File file = new File(path.toString());
//        String path = "file:/root/usr/java/file";
//        File file = new File(path.toString());
//        File file = new File(path+"/"+avatarName);
        try {
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            //return bytes;
            map.put("bytes",bytes);
            map.put("state",picture.getState());
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value="获取未审核信息")
    @GetMapping("getPictureList")
    @ResponseBody
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataTypeClass = String.class, name = "token", value = "token标记", required = true)})
    public Map<String, Object> getPictureList(HttpServletRequest request)
    {
        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userId = getInfo.getUserId();
        RestControllerHelper helper = new RestControllerHelper();
//        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
//        Path avatarPath = Paths.get(fileProperties.getDocDir()).toAbsolutePath().normalize();
//        Path path = avatarPath.resolve(avatarName).normalize();
        Picture picture =pictureService.getHouse(userId);
        String picName=picture.getPicType();
        if(picName == null)
        {
            return null;
        }
        Path avatarPath = Paths.get(fileProperties.getDocDir()).toAbsolutePath().normalize();
        Path path = avatarPath.resolve(picName).normalize();
        File file = new File(path.toString());
//        String path = "file:/root/usr/java/file";
//        File file = new File(path.toString());
//        File file = new File(path+"/"+avatarName);
        try {
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            //return bytes;
            map.put("bytes",bytes);
            map.put("state",picture.getState());
            helper.setMsg("Success");
            helper.setData(map);
            return helper.toJsonMap();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
