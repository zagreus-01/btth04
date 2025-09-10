package vn.iotstar.controllers.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.dao.CategoryDao;
import vn.iotstar.dao.impl.CategoryDaoImpl;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.User;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/user/home" })
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user == null || user.getRoleid() != 0) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        List<Category> categories = categoryDao.findAll();
        request.setAttribute("categories", categories);

        request.getRequestDispatcher("/views/user/home.jsp").forward(request, response);
    }
}
