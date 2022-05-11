package com.huaqi.zhanxin.controller;

import com.huaqi.zhanxin.common.Result;
import com.huaqi.zhanxin.entity.*;
import com.huaqi.zhanxin.service.PictureService;
import com.huaqi.zhanxin.tools.GetInformationFromRequest;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
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
import java.util.List;
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


    @ApiOperation(value="上传无犯罪记录证明")
    @ResponseBody
    @PostMapping("uploadCrime")
    public Map<String, Object> uploadCrime(HttpServletRequest request, @RequestParam("file") MultipartFile file){

        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();

        if(file.isEmpty()){
            map.put("success","0");
            map.put("file","上传文件为空！");
            helper.setMsg("Failed");
            helper.setData(map);
            return helper.toJsonMap();
        }
        try {
            String result = pictureService.uploadFile(userID,file,"crime",request);
            LOGGER.info(result);
            if (result.equals("-1")) {
                map.put("success","0");
                map.put("file","上传失败！");
                helper.setMsg("Failed");
                helper.setData(map);
                return helper.toJsonMap();
            } else if (result.equals("-2")) {
                map.put("success","0");
                map.put("file","文件类型错误！");
                helper.setMsg("Failed");
                helper.setData(map);
                return helper.toJsonMap();
            } else {
                map.put("success","1");
                map.put("file","上传文件成功！");
                helper.setMsg("Success");
                helper.setData(map);
                return helper.toJsonMap();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("文件上传失败！");
            map.put("success","0");
            map.put("file","上传文件失败！");
            helper.setMsg("Failed");
            helper.setData(map);
            return helper.toJsonMap();
        }
    }

    @ApiOperation(value="上传献血证明")
    @ResponseBody
    @PostMapping("uploadBlood")
    public Map<String, Object> uploadBlood(HttpServletRequest request, @RequestParam("file") MultipartFile file){

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
            String result = pictureService.uploadFile(userID,file,"blood",request);
            LOGGER.info(result);
            if (result.equals("-1")) {
                map.put("success","0");
                map.put("file","上传失败！");
                helper.setMsg("Failed");
                helper.setData(map);
                return helper.toJsonMap();
            } else if (result.equals("-2")) {
                map.put("success","0");
                map.put("file","文件类型错误！");
                helper.setMsg("Failed");
                helper.setData(map);
                return helper.toJsonMap();
            } else {
                map.put("success","1");
                map.put("file","上传文件成功！");
                helper.setMsg("Success");
                helper.setData(map);
                return helper.toJsonMap();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("文件上传失败！");
            map.put("success","0");
            map.put("file","上传文件失败！");
            helper.setMsg("Failed");
            helper.setData(map);
            return helper.toJsonMap();
        }
    }

    @ApiOperation(value="上传志愿服务证明")
    @ResponseBody
    @PostMapping("uploadVolunteer")
    public Map<String, Object> uploadVolunteer(HttpServletRequest request, @RequestParam("file") MultipartFile file){

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
            String result = pictureService.uploadFile(userID,file,"volunteer",request);
            LOGGER.info(result);
            if (result.equals("-1")) {
                map.put("success","0");
                map.put("file","上传失败！");
                helper.setMsg("Failed");
                helper.setData(map);
                return helper.toJsonMap();
            } else if (result.equals("-2")) {
                map.put("success","0");
                map.put("file","文件类型错误！");
                helper.setMsg("Failed");
                helper.setData(map);
                return helper.toJsonMap();
            } else {
                map.put("success","1");
                map.put("file","上传文件成功！");
                helper.setMsg("Success");
                helper.setData(map);
                return helper.toJsonMap();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("文件上传失败！");
            map.put("success","0");
            map.put("file","上传文件失败！");
            helper.setMsg("Failed");
            helper.setData(map);
            return helper.toJsonMap();
        }
    }

    @ApiOperation(value="上传捐款证明")
    @ResponseBody
    @PostMapping("uploadDonation")
    public Map<String, Object> uploadDonation(HttpServletRequest request, @RequestParam("file") MultipartFile file){

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
            String result = pictureService.uploadFile(userID,file,"donation",request);
            LOGGER.info(result);
            if (result.equals("-1")) {
                map.put("success","0");
                map.put("file","上传失败！");
                helper.setMsg("Failed");
                helper.setData(map);
                return helper.toJsonMap();
            } else if (result.equals("-2")) {
                map.put("success","0");
                map.put("file","文件类型错误！");
                helper.setMsg("Failed");
                helper.setData(map);
                return helper.toJsonMap();
            } else {
                map.put("success","1");
                map.put("file","上传文件成功！");
                helper.setMsg("Success");
                helper.setData(map);
                return helper.toJsonMap();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("文件上传失败！");
            map.put("success","0");
            map.put("file","上传文件失败！");
            helper.setMsg("Failed");
            helper.setData(map);
            return helper.toJsonMap();
        }
    }

    @ApiOperation(value="上传话费证明")
    @ResponseBody
    @PostMapping("uploadPhoneCost")
    public Map<String, Object> uploadPhoneCost(HttpServletRequest request, @RequestParam("file") MultipartFile file){

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
            String result = pictureService.uploadFile(userID,file,"phoneCost",request);
            LOGGER.info(result);
            if (result.equals("-1")) {
                map.put("success","0");
                map.put("file","上传失败！");
                helper.setMsg("Failed");
                helper.setData(map);
                return helper.toJsonMap();
            } else if (result.equals("-2")) {
                map.put("success","0");
                map.put("file","文件类型错误！");
                helper.setMsg("Failed");
                helper.setData(map);
                return helper.toJsonMap();
            } else {
                map.put("success","1");
                map.put("file","上传文件成功！");
                helper.setMsg("Success");
                helper.setData(map);
                return helper.toJsonMap();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("文件上传失败！");
            map.put("success","0");
            map.put("file","上传文件失败！");
            helper.setMsg("Failed");
            helper.setData(map);
            return helper.toJsonMap();
        }
    }

    @ApiOperation(value = "获取犯罪记录审核情况")
    @GetMapping("getCrime")
    public Map<String, Object> getCrime(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();
        //int userID =1;
        List<Picture> crimePicList = pictureService.getCrime(userID);
        if(CollectionUtils.isEmpty(crimePicList)) {
            map.put("picState", "暂无数据");
        } else {
            int t=0;
            int t1=0;
            int max=0;
            for(int i = 0; i < crimePicList.size(); i++) {
                t1=crimePicList.get(i).getPicID();
                if(t<=t1) {
                    t=t1;
                    max=i;
                }
            }
            Picture crimePic=crimePicList.get(max);
            map.put("picState", crimePic.getState());
        }
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    @ApiOperation(value = "获取献血记录审核情况")
    @GetMapping("getBlood")
    public Map<String, Object> getBlood(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();
        //int userID =1;
        List<Picture> bloodPicList = pictureService.getBlood(userID);
        if(CollectionUtils.isEmpty(bloodPicList)) {
            map.put("picState", "暂无数据");
        } else {
            int t=0;
            int t1=0;
            int max=0;
            for(int i = 0; i < bloodPicList.size(); i++) {
                t1=bloodPicList.get(i).getPicID();
                if(t<=t1) {
                    t=t1;
                    max=i;
                }
            }
            Picture bloodPic=bloodPicList.get(max);
            map.put("picState", bloodPic.getState());
        }
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    @ApiOperation(value = "获取志愿服务记录审核情况")
    @GetMapping("getVolunteer")
    public Map<String, Object> getVolunteer(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();
        //int userID =1;
        List<Picture> volunteerPicList = pictureService.getVolunteer(userID);
        if(CollectionUtils.isEmpty(volunteerPicList)) {
            map.put("picState", "暂无数据");
        } else {
            int t=0;
            int t1=0;
            int max=0;
            for(int i = 0; i < volunteerPicList.size(); i++) {
                t1=volunteerPicList.get(i).getPicID();
                if(t<=t1) {
                    t=t1;
                    max=i;
                }
            }
            Picture volunteerPic=volunteerPicList.get(max);
            map.put("picState", volunteerPic.getState());
        }
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    @ApiOperation(value = "获取捐款记录审核情况")
    @GetMapping("getDonation")
    public Map<String, Object> getDonation(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();
        //int userID =1;
        List<Picture> donationPicList = pictureService.getDonation(userID);
        if(CollectionUtils.isEmpty(donationPicList)) {
            map.put("picState", "暂无数据");
        } else {
            int t=0;
            int t1=0;
            int max=0;
            for(int i = 0; i < donationPicList.size(); i++) {
                t1=donationPicList.get(i).getPicID();
                if(t<=t1) {
                    t=t1;
                    max=i;
                }
            }
            Picture donationPic=donationPicList.get(max);
            map.put("picState", donationPic.getState());
        }
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    @ApiOperation(value = "获取话费记录审核情况")
    @GetMapping("getPhoneCost")
    public Map<String, Object> getPhoneCost(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();
        //int userID =1;
        List<Picture> phoneCostPicList = pictureService.getPhoneCost(userID);
        if(CollectionUtils.isEmpty(phoneCostPicList)) {
            map.put("picState", "暂无数据");
        } else {
            int t=0;
            int t1=0;
            int max=0;
            for(int i = 0; i < phoneCostPicList.size(); i++) {
                t1=phoneCostPicList.get(i).getPicID();
                if(t<=t1) {
                    t=t1;
                    max=i;
                }
            }
            Picture phoneCostPic=phoneCostPicList.get(max);
            map.put("picState", phoneCostPic.getState());
        }
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
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
