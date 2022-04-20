package com.huaqi.zhanxin.entity;

import java.util.HashMap;
import java.util.Map;

public class RestControllerHelper {
    private static final String RESULT_CODE = "code";
    private static final String RESULT_MSG = "msg";
    private static final String RESULT_DATA = "data";

    /**
     * 200: 成功。
     * 401: 当前请求需要用户验证。
     * 403：权限错误。
     * 404: 请求的资源未找到。
     * 408：请求超时。
     */
    public static final int SUCCESS = 200;
    public static final int UNLOGIN = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int TIMEOUT = 408;

    /**
     *  code: 状态码
     *  msg: 状态码消息
     *  data: 数据
     */
    private Integer code;
    private String msg;
    private Object data;

    public RestControllerHelper() {
        this.code = SUCCESS;
    }

    public RestControllerHelper(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public RestControllerHelper(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    /**
     * toJsonMap
     * @return
     */
    public Map<String,Object> toJsonMap() {
        Map<String,Object> map = new HashMap<>(3);
        map.put(RESULT_CODE,this.code);
        map.put(RESULT_MSG,this.msg);
        map.put(RESULT_DATA,this.data);
        return map;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
