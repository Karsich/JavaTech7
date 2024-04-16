package com.web.files.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.web.files.Service.CookieService.GetCookie;
import static com.web.files.Service.DBService.read;

@WebServlet(urlPatterns = {"/auth"})
public class SessionsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = GetCookie(request,"Auth");
        if (!cookie.getValue().equals("")) {
            String currentURL = request.getRequestURL().toString();
            response.sendRedirect(currentURL.substring(0, currentURL.lastIndexOf("/")) + "/file-browser");
            return;
        }
        else request.getRequestDispatcher("auth.jsp").forward(request, response);
    }

    //Вход в систему
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");

        if (login.isEmpty() || pass.isEmpty()) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Отсутсвует логин или пароль");
            return;
        }

        if (read(login,pass)==null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Неправильный логин или пароль");
            return;
        }

        Cookie cookie = new Cookie("Auth",login);
        cookie.setMaxAge(1000000);
        response.addCookie(cookie);

        String currentURL = request.getRequestURL().toString();
        response.sendRedirect(currentURL.substring(0, currentURL.lastIndexOf("/")) + "/file-browser");
    }

}
