package com.vansl.dto;


import java.util.List;
import java.util.Map;

/**
 * @author: vansl
 * @create: 18-4-22 下午2:31
 */
// layui表格数据对象
public class TableData {

    private Integer code;       //成功的状态码，默认：0
    private String msg;        //状态信息
    private Long count;     //数据总数
    private List<?> data;    //数据

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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
