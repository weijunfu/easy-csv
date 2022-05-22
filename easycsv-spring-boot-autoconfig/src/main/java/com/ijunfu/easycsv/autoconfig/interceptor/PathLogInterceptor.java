package com.ijunfu.easycsv.autoconfig.interceptor;

import com.ijunfu.easycsv.autoconfig.annotation.PathLog;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.logging.Logger;

/**
 * @Title :
 * @Remarks:
 * @Author : Weizhiguo
 * @Version: 1.0.0
 * @Date : 2022-05-22
 */
public class PathLogInterceptor implements HandlerInterceptor {

    private final static Logger LOGGER = Logger.getLogger(PathLogInterceptor.class.getSimpleName());

    private static final ThreadLocal<LocalDateTime> threadLocal = new ThreadLocal();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       if(handler instanceof  HandlerMethod) {
           HandlerMethod handlerMethod = (HandlerMethod) handler;

           PathLog pathLog = handlerMethod.getMethodAnnotation(PathLog.class);
           if(null != pathLog) {
               LocalDateTime start = LocalDateTime.now();
               threadLocal.set(start);
           }
       }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            PathLog pathLog = handlerMethod.getMethodAnnotation(PathLog.class);

            if(pathLog != null) {
                String uri = request.getRequestURI();

                Method method = handlerMethod.getMethod();
                String globalMethodName = method.getDeclaringClass().getName() + "#" + method.getName();

                LocalDateTime end = LocalDateTime.now();
                LocalDateTime start = threadLocal.get();

                Duration duration = Duration.between(start, end);
                StringBuffer sb = new StringBuffer();
                sb.append("Path: ")
                        .append(uri)
                        .append("\tMethod: ")
                        .append(globalMethodName)
                        .append("\tTitle: ")
                        .append(pathLog.name())
                        .append("\tNotes: ")
                        .append(pathLog.notes())
                        .append("\tTimeConsuming: ")
                        .append(duration.toMillis())
                        .append("ms");
                LOGGER.info(sb.toString());
            }
        }

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);

    }
}
