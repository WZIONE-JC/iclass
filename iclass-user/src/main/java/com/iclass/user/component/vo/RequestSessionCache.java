package com.iclass.user.component.vo;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/14 14:51.
 *
 * 用户请求缓存
 */
public class RequestSessionCache {

    private SessionUser sessionUser;

    private String requestUrl;

    public RequestSessionCache(){}

    public RequestSessionCache(SessionUser user, String requestUrl) {
        this.sessionUser = user;
        this.requestUrl = requestUrl;
    }

    public SessionUser getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(SessionUser sessionUser) {
        this.sessionUser = sessionUser;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public String toString() {
        return "RequestSessionCache{" +
                "sessionUser=" + sessionUser +
                ", requestUrl='" + requestUrl + '\'' +
                '}';
    }
}
