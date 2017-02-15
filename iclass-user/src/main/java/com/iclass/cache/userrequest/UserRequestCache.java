package com.iclass.cache.userrequest;

import com.iclass.user.mybatis.model.User;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/14 14:51.
 *
 * 用户请求缓存
 */
public class UserRequestCache {

    private User user;

    private String requestUrl;

    public UserRequestCache(){}

    public UserRequestCache(User user, String requestUrl) {
        this.user = user;
        this.requestUrl = requestUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public String toString() {
        return "UserRequestCache{" +
                "user=" + user +
                ", requestUrl='" + requestUrl + '\'' +
                '}';
    }
}
