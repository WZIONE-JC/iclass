package com.iclass.user.service.impl;

import com.iclass.user.usermsg.UserCode;
import com.iclass.user.usermsg.UserException;
import com.iclass.user.usermsg.UserMsg;
import com.iclass.user.md5.MD5;
import com.iclass.user.mybatis.dao.UserMapper;
import com.iclass.user.service.api.LoginService;
import com.iclass.user.userdata.UserUtils;
import com.iclass.verificationcode.service.imp.VerificationCodeImpl;
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
@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VerificationCodeImpl verificationCode;

    @Override
    public String getMD5Password(String password) {
        return MD5.getPwd(password);
    }

    @Override
    public UserMsg login(HttpServletRequest request, String userrole, String username, String password, String code) {

        UserMsg userMsg;

        /**
         * 首先验证验证码，通过validateVerificationCode 方法去验证
         * 这里我考虑是从前端去验证
         */
        boolean codeCorrect = validateVerificationCode(code);
        System.out.println("LoginServiceImpl.login : " +userrole);
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

            //json数据
            String nameAndCodeAndRoleJson;

            String result;

            // 以工号的方式登录
            if(m.matches()) {
                System.out.println("使用工号登录");
                //返回的result是username, 而参数username是usercode
                result = userMapper.findByUsercodeAndPassword(userrole, username, newPassword);
                nameAndCodeAndRoleJson = UserUtils.getUserJsonData(result, username, userrole);
            } else {
                //使用用户名的方式去登录
                System.out.println("使用用户名登录");
                //返回的result是usercode
                result = userMapper.findByUsernameAndPassword(userrole, username, newPassword);
                nameAndCodeAndRoleJson = UserUtils.getUserJsonData(username, result, userrole);
            }

            System.out.println("欢迎 " + username + " " + result + " 登录");
            if(result != null) {
                //查找到，就显示登录成功的信息，返回给前端
                userMsg = new UserMsg(UserCode.LOGINSUCCESS, UserException.LOGINSUCCESS);
                //保存json数据(用户登录信息)
                session.setAttribute(sessionId, nameAndCodeAndRoleJson);
            } else {
                //result为空，表示没有查找到用户信息
                userMsg = new UserMsg(UserCode.LOGINFAILED, UserException.LOGINFAILED);
            }
        } else {
            userMsg = new UserMsg(UserCode.CODEERROR, UserException.CODEERROR);
        }
        return userMsg;
    }

    /**
     * 校验验证码，通过注入VerificationCodeImpl 来得到服务器生成的验证码数据
     * @param code 用户提交过来的验证码
     * @return true: 正确， false: 失败
     */
    @Override
    public boolean validateVerificationCode(String code) {
        System.out.println("LoginServiceImpl.validateVerificationCode");
        if(code != null && !code.equals("")) {
            String stringCode = verificationCode.getVerificationCode();
            if(stringCode != null) {
                System.out.println("获取到的stringCode:" + stringCode + ":" + code.trim());
                System.out.println("stringCode.equalsIgnoreCase(code.trim()):"+stringCode.equalsIgnoreCase(code.trim()));
                return stringCode.equalsIgnoreCase(code.trim());
            } else {
                System.out.println("获取到的stringCode:"+stringCode);
            }
        } else {
            System.out.println("验证码不能为空");
        }
        return false;
    }

    /**
     * 获取已登录用户的信息
     * 在用户登录时已经将如下json数据存入session中
     * {"username":"唐洋","usercode":"1308030331","userrole":"管理员"}
     * 在此方法中，通过sessionId 获取session 中存放的json数据
     * 然后结合callback 来生成jsonp数据
     * @param request 获取session
     * @param callback jsonp参数
     * @return 如果从session 中获取的jsondata存在，就返回jsonp 数据，否则返回null
     */
    @Override
    public String getLoginedUserInfo(HttpServletRequest request, String callback) {

        HttpSession session = request.getSession();
        System.out.println("LoginServiceImpl.getLoginedUserInfo sessionID: " + session.getId());
        //从session中获取session中的json数据
        String jsondata = (String)session.getAttribute(session.getId());
        if(jsondata != null) {
            //通过json数据生成jsonp数据
            String jsonp = UserUtils.getUserJsonpData(jsondata, callback);
            return jsonp.toString();
        }
        return null;
    }
}
