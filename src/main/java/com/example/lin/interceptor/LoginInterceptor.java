package com.example.lin.interceptor;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 时光之里山南水北,你我之间人山人海
 * <p>
 *
 * @author 折骨为刀
 * @create 2019-03-07 下午 02:32
 **/
@Component
@RequiredArgsConstructor
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

//    private final IAuthService iauthService;

    @Override
    public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if(handlerMethod.getMethodAnnotation(ClearInterceptor.class) != null){
                return true;
            }
        }

        String token = HttpServletUtils.getReqParam(request, Constant.P_TOKEN);
        int resultCode = ResultCode.SUCC;

        Object user = request.getSession().getAttribute("currentUser");
        if (null == user) {
            resultCode = ResultCode.TOKEN_EXPIRED;
            logger.info("currentuser error: resultCode=" + resultCode);
            HttpServletUtils.responseJson(response, new APIRespJson(resultCode).setMessage("当前用户未登入"));
            return false;
        }

        if (token == null) {
            resultCode = ResultCode.TOKEN_NOEXIST;
            logger.info("Token error: resultCode=" + resultCode);
            HttpServletUtils.responseJson(response, new APIRespJson (resultCode).setMessage("token参数缺失"));
            return false;
        } else {
            AccountStatusVo accountStatusVo;
            try {

                accountStatusVo = iauthService.getAccountStatus(token);
//                CurrentUser user = (CurrentUser) RedisUtil.getInstance ().get (token);
                if (null == accountStatusVo) {
                    resultCode = ResultCode.TOKEN_EXPIRED;
                    logger.info("account error: resultCode=" + resultCode);
                    HttpServletUtils.responseJson(response, new APIRespJson(resultCode).setMessage("token已失效,请重新登入"));
                    return false;
                }
            } catch (BusinessException e) {
                String msg = e.getCode() == ResultCode.TOKEN_NOEXIST ? "用户在其他客户端下线":e.getMessage();

                resultCode = e.getCode();
                logger.info("error: resultCode=" + e.getMessage());
                HttpServletUtils.responseJson(response, new APIRespJson(resultCode).setMessage(msg));
                return false;
            }
        }*/
        return true;
    }

    @Override
    public void postHandle (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
