package com.iclass.utils;

import com.iclass.user.mybatis.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/14 12:39.
 */
public class UserInfoHandler {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoHandler.class);

    /**
     * 去掉日期后面的 毫秒数 .0
     * @param user
     * @return
     */
    public static User userRegisterDateHandler(User user) {
        if(user != null) {
            String oldUserRegisterDate = user.getUserregisterdate();
            if(oldUserRegisterDate != null) {
                String userRegisterDate = oldUserRegisterDate.split("\\.")[0];
                if(logger.isDebugEnabled()) {
                    logger.debug("UserInfoHandler.userRegisterDateHandler:" + userRegisterDate);
                }
                user.setUserregisterdate(userRegisterDate);
                if(logger.isDebugEnabled()) {
                    logger.info("UserInfoHandler.userRegisterDataHandler: 已处理registerData信息：" + user);
                }
            }
        } else {
            logger.warn("UserInfoHandler.userRegisterDateHandler: user不能为空：" + user);
        }
        return user;
    }

    /**
     * 去掉用户信息中的password信息
     * @param user
     * @return
     */
    public static User userpasswordHandler(User user) {
        if(user != null) {
            user.setUserpassword(null);
            if(logger.isDebugEnabled()) {
                logger.debug("UserInfoHandler.userpasswordHandler: 已处理password信息 : " + user);
            }
        } else {
            logger.warn("UserInfoHandler.userpasswordHandler: user不能为空: "+user);
        }
        return user;
    }

    /**
     * 通过jsonp来获取usercode
     * @param jsonp
     * @return
     */
//    public static String getUsercodeByJsonpData(String jsonp) {
//        String usercode = null;
//        if(jsonp != null) {
//            String usercodedata = jsonp.split(",")[1];
//            usercode = usercodedata.split(":")[1].replaceAll("\"", "");
//            logger.info("UserUtils.getUsercodeByJsonpData : " + usercode);
//        }
//        return usercode;
//    }

    /**
     * 根据username usercode userrole 生成 json数据,当然这里也可以将它封装为一个对象
     * 使用spring boot 去自动生成json 或者 jsonp数据
     * @param username
     * @param usercode
     * @param userrole
     * @return
     */
//    public static String getUserJsonData(String username, String usercode, String userrole) {
//        StringBuilder nameAndCodeAndRole = new StringBuilder("{\"username\":");
//        nameAndCodeAndRole.append("\""+username+"\"").append(",\"usercode\":").append("\""+usercode+"\"");
//        nameAndCodeAndRole.append(",\"userrole\":").append("\""+userrole+"\"").append("}");
//        System.out.println("UserUtils.getUserJsonData :" +nameAndCodeAndRole.toString());
//        return nameAndCodeAndRole.toString();
//    }

    /**
     * 生成jsonp数据
     * @param jsondata :原始json数据
     * @param callback :callback对应的方法名
     * @return jsonp数据
     */
//    public static String getUserJsonpData(String jsondata, String callback) {
//        StringBuilder jsonp = new StringBuilder(callback + "(");
//        jsonp.append(jsondata);
//        jsonp.append(");");
//        System.out.println("UserUtils.getUserJsonpData : " + jsonp.toString());
//        return jsonp.toString();
//    }
}
