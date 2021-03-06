package com.iclass.user.component.service.impl;

import com.iclass.mybatis.dao.UserMapper;
import com.iclass.mybatis.po.User;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.md5.MD5;
import com.iclass.user.component.msg.Msg;
import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.user.component.service.api.LoginService;
import com.iclass.mybatis.dto.SessionUser;
import com.iclass.user.verificationcode.service.imp.VerificationCodeImpl;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VerificationCodeImpl verificationCode;

    @Override
    public String getMD5Password(String password) {
        return MD5.getPwd(password);
    }

    @Override
    public ServiceResult<ResponseMsg> login(HttpServletRequest request, String userrole, 
											String username, String password, String code, 
											String remember) {

        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
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
        Pattern p2 = Pattern.compile("[\\d]{11}");
        Matcher m = p.matcher(username);
        Matcher m2 = p2.matcher(username);

        if(codeCorrect) {
            //将用户输入的密码进行加密
            String newPassword = getMD5Password(password);

            HttpSession session = request.getSession();

            String sessionId = session.getId();

            User user;

            SessionUser sessionUser = new SessionUser();

            // 以工号的方式登录
            if(m.matches() || m2.matches()) {
                //参数username是usercode
                String usercode = username;
                user = userMapper.findByUsercodeAndPassword(usercode, newPassword, userrole);
            } else {
                //使用用户名的方式去登录
                user = userMapper.findByUsernameAndPassword(username, newPassword, userrole);
            }
            if(user != null) {
                logger.info("欢迎 " + user.getUsername() + " " + user.getUserrole() + " 登录");
                sessionUser.setUser(user);
                //保存json数据(用户登录信息)
                if(StringUtils.isNotBlank(remember)) {
                    logger.info("用户选择了记住密码, remember: " + remember);
                    session.setMaxInactiveInterval(3600 * 12 * 7);
                }
                session.setAttribute(sessionId, sessionUser);
                //查找到，就显示登录成功的信息，返回给前端
                serviceResult.setMessage(user.getUsercode());
                responseMsg.setMsg(user.getUserrole());
                serviceResult.setSuccess(true);
            } else {
                //user为空，表示没有查找到用户信息
                responseMsg.setMsg(Msg.LOGIN_FAILED);
            }
        } else {
            responseMsg.setMsg(Msg.CODE_ERROR);
        }
        serviceResult.setData(responseMsg);
        return serviceResult;
    }

    /**
     * 校验验证码，通过注入VerificationCodeImpl 来得到服务器生成的验证码数据
     * @param code 用户提交过来的验证码
     * @return true: 正确， false: 失败
     */
    @Override
    public boolean validateVerificationCode(HttpServletRequest request, String code) {
        if(StringUtils.isNotBlank(code)) {
            logger.info("请求的sessionId:" + request.getSession().getId());
            String stringCode = verificationCode.getVerificationCode(request);
            if(stringCode != null) {
                return stringCode.equalsIgnoreCase(code.trim());
            }
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
    public ServiceResult<SessionUser> getLoginedUserInfo(HttpServletRequest request) {
        ServiceResult<SessionUser> serviceResult = new ServiceResult<>();
            HttpSession session = request.getSession();
            //从session中获取session中的user数据
            SessionUser user = (SessionUser)session.getAttribute(session.getId());
            if(user == null) {
                serviceResult.setMessage("用户未登录");
            } else {
                serviceResult.setSuccess(true);
                serviceResult.setData(user);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<ResponseMsg> logout(HttpServletRequest request) {
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        ResponseMsg responseMsg = new ResponseMsg();
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        session.removeAttribute(sessionId);
        responseMsg.setMsg(Msg.LOGOUT_SUCCESS);
        serviceResult.setSuccess(true);
        serviceResult.setData(responseMsg);
        return serviceResult;
    }
}
