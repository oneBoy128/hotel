//package com.example.filter;
//
//import com.example.utils.Res;
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//
//@WebFilter("/*")
//public class LoginFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        //不必拦截的url (登录注册) (静态资源)
//        String url1 = "http://localhost:8080/hotel/users";
//        String url2 = "http://localhost:8080/hotel/users/login";
//        String url3 = "/static/";
//        //获取当前请求路径 进行判断
//        String url = req.getRequestURL().toString();
//        System.out.println(url);
//
//        //路径等于登录注册路径 或者 包含静态资源访问->放行
//
//        if (url.equals(url1) || url.contains(url2) || url.contains(url3)){
//            chain.doFilter(request,response);
//            return;
//        }
//
//        //判断 session中是否带有userId
//        HttpSession session = req.getSession();
//        Object userId = session.getAttribute("userId");
//        System.out.println(userId);
//        //判断userId是否为空
//        if (null != userId){
//            //登录过 放行
//            chain.doFilter(request,response);
//        }else {
//            //存储提示信息，跳转登录页面
//            req.setAttribute("loginMsg",new Res("您尚未登录！"));
//            //跳转登录页面
//            req.getRequestDispatcher("/login.html").forward(req,response);
//        }
//
//    }
//
//    public void init(FilterConfig config) throws ServletException {
//    }
//
//    public void destroy() {
//    }
//
//
//}
