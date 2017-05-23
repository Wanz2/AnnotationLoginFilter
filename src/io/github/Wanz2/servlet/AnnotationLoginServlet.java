package io.github.Wanz2.servlet;

import javafx.print.Printer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by wuwenan on 10/05/2017.
 */
@WebServlet(name = "AnnotationLoginServlet",
        //访问servlet的url
        urlPatterns = "/servlet/AnnotationLoginServlet"
)
public class AnnotationLoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("正在登录的用户为：" + username);
        //若用户名和密码正确，则重定向至成功页面，并将用户名放入session
        if ("admin".equals(username)||"admin".equals(password)) {
            response.sendRedirect("/success.jsp");
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
        } else {
            //若用户名和密码不正确，则重定向至失败页面
            response.sendRedirect("/failure.jsp");
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
