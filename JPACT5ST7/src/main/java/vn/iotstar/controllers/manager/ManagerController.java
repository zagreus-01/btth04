package vn.iotstar.controllers.manager;

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

@WebServlet(urlPatterns = { "/manager/home", "/manager/category/add", "/manager/category/edit", "/manager/category/delete" })
public class ManagerController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user == null || user.getRoleid() != 2) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getServletPath();
        if ("/manager/home".equals(action)) {
            List<Category> categories = categoryDao.findByUserId(user.getId());
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("/views/manager/home.jsp").forward(request, response);
        } else if ("/manager/category/edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Category category = categoryDao.findById(id);
            if (category != null && category.getUser().getId() == user.getId()) {
                request.setAttribute("category", category);
                request.getRequestDispatcher("/views/manager/edit.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user == null || user.getRoleid() != 2) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getServletPath();
        if ("/manager/category/add".equals(action)) {
            String name = request.getParameter("name");
            Category category = new Category(name, user);
            categoryDao.create(category);
        } else if ("/manager/category/edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            Category category = categoryDao.findById(id);
            if (category != null && category.getUser().getId() == user.getId()) {
                category.setName(name);
                categoryDao.update(category);
            }
        } else if ("/manager/category/delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Category category = categoryDao.findById(id);
            if (category != null && category.getUser().getId() == user.getId()) {
                categoryDao.delete(id);
            }
        }

        response.sendRedirect(request.getContextPath() + "/manager/home");
    }
}
