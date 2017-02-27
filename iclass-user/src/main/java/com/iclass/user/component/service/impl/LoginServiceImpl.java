package com.iclass.user.component.service.impl;

import com.iclass.user.component.vo.SessionUser;
import com.iclass.user.component.exception.UserException;
import com.iclass.user.component.md5.MD5;
import com.iclass.user.component.msg.CodeMsg;
import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.user.component.service.api.LoginService;
import com.iclass.user.mybatis.dao.UserMapper;
import com.iclass.user.mybatis.model.User;
import com.iclass.user.verificationcode.service.imp.VerificationCodeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/10 20:46.
 */
@Service("LoginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VerificationCodeImpl verificationCode;

    @Override
    public String getMD5Password(String password) {
        return MD5.getPwd(password);
    }

    @Override
    public ResponseMsg login(HttpServletRequest request, String userrole, String username, String password, String code) {

        ResponseMsg responseMsg = new ResponseMsg();

        /**
         * 首先验证验证码，通过validateVerificationCode 方法去验证
         * 这里我考虑是从前端去验证
         */
        boolean codeCorrect = validateVerificationCode(request, code);

        /**
         * 如果登录方式是工号
         */
        Pattern p = Pattern.compile("[\\d]{10}");
        Matcher m = p.matcher(username);

        if(codeCorrect) {
            //将用户输入的密码进行加密
            String newPassword = getMD5Password(password);

            HttpSession session = request.getSession();

            String sessionId = session.getId();

            User user;

            SessionUser sessionUser = new SessionUser();

            // 以工号的方式登录
            if(m.matches()) {
                System.out.println("使用工号登录");
                //参数username是usercode
                String usercode = username;
                user = userMapper.findByUsercodeAndPassword(usercode, newPassword, userrole);
            } else {
                //使用用户名的方式去登录
                System.out.println("使用用户名登录");
                user = userMapper.findByUsernameAndPassword(username, newPassword, userrole);
            }
            if(user != null) {
                System.out.println("欢迎 " + user.getUsername() + " " + user.getUserrole() + " 登录");
                sessionUser.setUser(user);
                //保存json数据(用户登录信息)
                session.setAttribute(sessionId, sessionUser);
                //查找到，就显示登录成功的信息，返回给前端
                responseMsg.setCodeMsg(CodeMsg.LOGIN_SUCCESS);
            } else {
                //user为空，表示没有查找到用户信息
                responseMsg.setCodeMsg(CodeMsg.LOGIN_FAILED);
                System.out.println("LoginServiceImpl.login");
                System.out.println("登录信息错误:");
                System.out.println("request = [" + request + "], userrole = [" + userrole + "], username = [" + username + "], password = [" + password + "], code = [" + code + "]");
            }
        } else {
            responseMsg.setCodeMsg(CodeMsg.CODE_ERROR);
        }
        return responseMsg;
    }

    /**
     * 校验验证码，通过注入VerificationCodeImpl 来得到服务器生成的验证码数据
     * @param code 用户提交过来的验证码
     * @return true: 正确， false: 失败
     */
    @Override
    public boolean validateVerificationCode(HttpServletRequest request, String code) {
        System.out.println("LoginServiceImpl.validateVerificationCode");
        if(code != null && !code.equals("")) {
            String stringCode = verificationCode.getVerificationCode(request);
            if(stringCode != null) {
                System.out.println("获取到的stringCode:" + stringCode + ":" + code.trim());
                System.out.println("stringCode.equalsIgnoreCase(code.trim()):"+stringCode.equalsIgnoreCase(code.trim()));
                return stringCode.equalsIgnoreCase(code.trim());
            } else {
                System.out.println("获取到的stringCode:"+stringCode);
            }
        } else {
            throw new UserException("验证码不能为空", "用户输入的验证码:" + code);
        }
        return false;
    }

    /**
     * 获取已登录用户的信息
     * SessionUser
     * 在此方法中，通过sessionId 获取session 中存放的json数据
     * 然后结合callback 来生成jsonp数据
     * @param request 获取session
     * @return 如果从session 中获取的jsondata存在，就返回jsonp 数据，否则返回null
     */
    @Override
    public SessionUser getLoginedUserInfo(HttpServletRequest request) {
        SessionUser user;
        HttpSession session = request.getSession();
        System.out.println("LoginServiceImpl.getLoginedUserInfo sessionID: " + session.getId());
        //从session中获取session中的user数据
        user = (SessionUser)session.getAttribute(session.getId());
        if(user == null) {
            throw new UserException("用户未登录", session.getId()+"此ID对应的用户没有登录");
        }
        return user;
    }
}
