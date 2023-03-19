package org.chobit.tiger.spring.ext;

import org.chobit.tiger.web.admin.AbstractAdminPageController;
import org.chobit.tiger.web.front.FrontPageController;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.chobit.tiger.constants.Config.PATH_ADMIN;
import static org.chobit.tiger.constants.Constants.REDIRECT_PREFIX;
import static org.chobit.tiger.utils.Strings.isBlank;

/**
 * @author robin
 */
public class CalfThemeInterceptor implements HandlerInterceptor {


    private String themePath;


    public CalfThemeInterceptor(String themePath) {
        this.themePath = themePath;
    }


    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           @Nullable ModelAndView modelAndView) {

        if (null == modelAndView
                || null == handler
                || isBlank(modelAndView.getViewName())
                || !(handler instanceof HandlerMethod)) {
            return;
        }
        if (modelAndView.getViewName().startsWith(REDIRECT_PREFIX)) {
            return;
        }

        Object m = ((HandlerMethod) handler).getBean();

        if (m instanceof FrontPageController) {
            modelAndView.setViewName(themePath + modelAndView.getViewName());
        }

        if (m instanceof AbstractAdminPageController) {
            modelAndView.setViewName(PATH_ADMIN + modelAndView.getViewName());
        }
    }


}
