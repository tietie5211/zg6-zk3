package com.wjs.util;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author wangjunshan
 * @date 2023/11/29 9:20
 */
@Data
public class R implements Serializable {

    private String message;

    private boolean flag;

    private int code;

    private Map<String, Object> data = new HashMap<>();

    public R() {
    }

    public static R ok() {
        R r = new R();
        r.setMessage("操作成功");
        r.setFlag(true);
        r.setCode(CodeStatus.SUCCESS);
        return r;
    }

    public static R error() {
        R r = new R();
        r.setMessage("操作失败");
        r.setFlag(false);
        r.setCode(CodeStatus.ERROR);
        return r;
    }

    public R message(String message) {
        this.message = message;
        return this;
    }

    public R flag(boolean flag) {
        this.flag = flag;
        return this;
    }

    public R code(int code) {
        this.code = code;
        return this;
    }

    public R data(Map<String, Object> data) {
        this.data.putAll(data);
        return this;
    }

    public R data(String key,Object value) {
        this.data.put(key,value);
        return this;
    }
}
