package vn.iotstar.controllers.admin;

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

@WebServlet(urlPatterns = { "/admin/home", "/admin/category/add", "/admin/category/edit", "/admin/category/delete" })
public class AdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user == null || user.getRoleid() != 1) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getServletPath();
        if ("/admin/home".equals(action)) {
            List<Category> categories = categoryDao.findAll();
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("/views/admin/home.jsp").forward(request, response);
        } else if ("/admin/category/edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Category category = categoryDao.findById(id);
            request.setAttribute("category", category);
            request.getRequestDispatcher("/views/admin/edit.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user == null || user.getRoleid() != 1) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getServletPath();
        if ("/admin/category/add".equals(action)) {
            String name = request.getParameter("name");
            Category category = new Category(name, user);
            categoryDao.create(category);
        } else if ("/admin/category/edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            Category category = categoryDao.findById(id);
            if (category != null) {
                category.setName(name);
                categoryDao.update(category);
            }
        } else if ("/admin/category/delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            categoryDao.delete(id);
        }

        response.sendRedirect(request.getContextPath() + "/admin/home");
    }
}
