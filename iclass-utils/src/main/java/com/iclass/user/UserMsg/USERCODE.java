package com.iclass.user.UserMsg;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/10 19:03.
 */
public enum USERCODE {

    EXIST("1001"),NOEXIST("1002"),LOGINSUCCESS("1003"),LOGINFAILED("1004");

    String value;
    String desc;

    USERCODE(String value) {
        this.value = value;
    }

    USERCODE(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    public String getValue() {
        return value;
    }
    public String getDesc() {
        return desc;
    }
}
