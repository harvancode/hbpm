/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrv.core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author harvan
 */
public class DefaultInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
//        System.out.println(this.getClass().getSimpleName() + ": REQUEST Intercepted for URI: "
//                + request.getRequestURI() + (!request.getParameterMap().isEmpty() ? "?".concat(request.getQueryString()) : ""));
        return true;
    }
}
