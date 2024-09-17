package com.app.interceptor;

//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//@Slf4j
//@Component
//public class Interceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
////        String headerName = "Role";
////        String headerValue = "admin";
////        log.info("Request URL: {}", request.getRequestURL());
////        log.info("Header - {}: {}", headerName, headerValue);
////        return true;
////
////    }
//
//        String headerName = "Role";
//        String headerValue = request.getHeader(headerName);
//        log.info("Request URL: {}", request.getRequestURL());
//        log.info("Header - {}: {}", headerName, headerValue);
//
//        if ("admin".equalsIgnoreCase(headerValue)) {
//            return true;
//        } else {
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            response.getWriter().write("Access Denied: Admins only");
//            return false;
//        }
//
//    }
//
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        //Do Nothing
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        //Do Nothing
//    }
//}






import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class Interceptor implements HandlerInterceptor {

    private boolean isAdminEndpoint(String url) {

        return url.startsWith("/api/techTeam");

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
//        String headerName = "Role";
//        String headerValue = request.getHeader(headerName);
        //        log.info("Request URL: {}", request.getRequestURL());
//        log.info("Header - {}: {}", headerName, headerValue);
        String usernameHeader = "Username";
        String roleHeader = "Role";
        String username = request.getHeader(usernameHeader);
        String role = request.getHeader(roleHeader);

//        log.info("Request URL: {}", request.getRequestURL());
//        log.info("Header - {}: {}", usernameHeader, username);
//        log.info("Header - {}: {}", roleHeader, role);

        if (username == null || role == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Username and Role headers are required");
            return false;
        }

        if (isAdminEndpoint(request.getRequestURI())) {
            if ("admin".equalsIgnoreCase(role) ) {
                return true;
            } else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Access Denied: Admins only");
                return false;
            }
        } else {

            return true;
        }


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

//        log.info("Post handling function");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

//        log.info("After Completion function");

    }
}

