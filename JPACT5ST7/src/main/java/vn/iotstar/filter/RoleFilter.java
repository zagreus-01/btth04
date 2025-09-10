package vn.iotstar.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.User;

import java.io.IOException;

@WebFilter(urlPatterns = { "/user/*", "/manager/*", "/admin/*" })
public class RoleFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		User user = (User) session.getAttribute("user");
		String path = request.getServletPath();
		int roleid = user.getRoleid();

		boolean allowed = false;
		if (path.startsWith("/user/") && roleid == 0) {
		    allowed = true;
		} else if (path.startsWith("/admin/") && roleid == 1) { // Admin đúng với role=1
		    allowed = true;
		} else if (path.startsWith("/manager/") && roleid == 2) { // Manager đúng với role=2
		    allowed = true;
		}


		if (allowed) {
			chain.doFilter(req, res);
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
		}
	}

	@Override
	public void destroy() {
	}
}