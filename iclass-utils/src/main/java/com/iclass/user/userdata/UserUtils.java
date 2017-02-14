package com.iclass.user.userdata;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/14 11:06.
 */
public class UserUtils {

    /**
     * 通过jsonp来获取usercode
     * @param jsonp
     * @return
     */
    public static String getUsercodeByJsonpData(String jsonp) {
        String usercode = null;
        if(jsonp != null) {
            String usercodedata = jsonp.split(",")[1];
            usercode = usercodedata.split(":")[1].replaceAll("\"", "");
            System.out.println("UserUtils.getUsercodeByJsonpData : " + usercode);
        }
        return usercode;
    }

    /**
     * 根据username usercode userrole 生成 json数据,当然这里也可以将它封装为一个对象
     * 使用spring boot 去自动生成json 或者 jsonp数据
     * @param username
     * @param usercode
     * @param userrole
     * @return
     */
    public static String getUserJsonData(String username, String usercode, String userrole) {
        StringBuilder nameAndCodeAndRole = new StringBuilder("{\"username\":");
        nameAndCodeAndRole.append("\""+username+"\"").append(",\"usercode\":").append("\""+usercode+"\"");
        nameAndCodeAndRole.append(",\"userrole\":").append("\""+userrole+"\"").append("}");
        System.out.println("UserUtils.getUserJsonData :" +nameAndCodeAndRole.toString());
        return nameAndCodeAndRole.toString();
    }

    /**
     * 生成jsonp数据
     * @param jsondata :原始json数据
     * @param callback :callback对应的方法名
     * @return jsonp数据
     */
    public static String getUserJsonpData(String jsondata, String callback) {
        StringBuilder jsonp = new StringBuilder(callback + "(");
        jsonp.append(jsondata);
        jsonp.append(");");
        System.out.println("UserUtils.getUserJsonpData : " + jsonp.toString());
        return jsonp.toString();
    }
}
