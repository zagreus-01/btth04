package vn.iotstar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.entity.User;

import java.io.IOException;

@WebServlet(urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/web/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String roleParam = request.getParameter("role");

        // Check null để tránh lỗi NumberFormatException
        int roleSelected = -1;
        if (roleParam != null && !roleParam.isEmpty()) {
            roleSelected = Integer.parseInt(roleParam);
        }

        User user = userDao.findByUsernameAndPassword(username, password);

        if (user != null) {
            int roleid = user.getRoleid();

            // So sánh role DB với role chọn từ form
            if (roleid == roleSelected) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                if (roleid == 0) {
                    response.sendRedirect(request.getContextPath() + "/user/home");
                } else if (roleid == 1) {
                    response.sendRedirect(request.getContextPath() + "/admin/home");
                } else if (roleid == 2) {
                    response.sendRedirect(request.getContextPath() + "/manager/home");
                } else {
                    request.setAttribute("error", "Invalid role in database!");
                    request.getRequestDispatcher("/views/web/login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Role does not match with your account!");
                request.getRequestDispatcher("/views/web/login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Invalid username or password!");
            request.getRequestDispatcher("/views/web/login.jsp").forward(request, response);
        }
    }
}
