package rest.api.restApi.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import rest.api.restApi.mapper.Mapper;
import rest.api.restApi.model.MyUser;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    Mapper mapper;

    Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("======Login PreHandle Interceptor to do authentication logic ======");
        if (request.getAttribute("username") == null) {
            logger.error("======No username ======");
            return false;
        }
        String username = String.valueOf(request.getAttribute("username"));
        String password = String.valueOf(request.getAttribute("password"));
        MyUser user = mapper.getUserByUsername(username);
        if (user == null) {
            logger.error("======Wrong username ======");
            return false;
        }
        if (!user.password.equals(password)) {
            logger.error("======Wrong password ======");
            return false;
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        logger.info("======Login PostHandle Interceptor ======");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        logger.info("======Login AfterCompletion Interceptor ======");
    }
}