package com.iclass.cache.userrequest;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/14 14:51.
 *
 * 用户请求缓存
 */
public class UserRequestCache {

    private String usercode;

    private String username;

    private String userrole;

    private String sessionid;

    private String requesturl;

    public UserRequestCache(){}

    public UserRequestCache(String usercode, String username, String userrole, String sessionid, String requesturl) {
        if(usercode == null) {
            usercode = "";
        }
        if(username == null) {
            username = "";
        }
        if(userrole == null) {
            userrole = "";
        }
        if(sessionid == null) {
            sessionid = "";
        }
        if(requesturl == null) {
            requesturl = "";
        }
        this.usercode = usercode;
        this.username = username;
        this.userrole = userrole;
        this.sessionid = sessionid;
        this.requesturl = requesturl;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserrole() {
        return userrole;
    }

    public void setUserrole(String userrole) {
        this.userrole = userrole;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getRequesturl() {
        return requesturl;
    }

    public void setRequesturl(String requesturl) {
        this.requesturl = requesturl;
    }

    @Override
    public String toString() {
        return "UserRequestCache{" +
                "usercode='" + usercode + '\'' +
                ", username='" + username + '\'' +
                ", userrole='" + userrole + '\'' +
                ", sessionid='" + sessionid + '\'' +
                ", requesturl='" + requesturl + '\'' +
                '}';
    }
}
