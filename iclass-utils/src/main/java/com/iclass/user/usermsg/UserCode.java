package com.iclass.user.usermsg;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/10 19:03.
 */
public enum UserCode {

    USERNAMEEXISTED("1001"),USERCODEEXISTED("1002"),USERNAMECANUSE("1003"),USERCODECANUSE("1004"),
    LOGINSUCCESS("2001"),LOGINFAILED("2002"),
    SIGNUPSUCCESS("2003"),SIGNUPFAILED("2004"),
    CODECORRECT("3001"),CODENOTNULL("3002"),CODEERROR("3003");

    String value;
    String desc;

    UserCode(String value) {
        this.value = value;
    }

    UserCode(String value, String desc) {
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
