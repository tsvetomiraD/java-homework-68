package rest.api.restApi.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import rest.api.restApi.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class Interceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(Interceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getHeader("Authorization") == null) {
            logger.error("Authorization not sent.");
            logger.error("Validation NOK.");
            return false;
        }

        HandlerMethod method = (HandlerMethod) handler;
        Role methodAnnotation = method.getMethodAnnotation(Role.class);

        if (methodAnnotation == null) {
            return true;
        }

        String role = String.valueOf(request.getAttribute("role"));
        if (!role.equals(methodAnnotation.role())) {
            logger.error("role different from required");
            return false;
        }

        logger.info("Validation OK.");
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
    }
}
