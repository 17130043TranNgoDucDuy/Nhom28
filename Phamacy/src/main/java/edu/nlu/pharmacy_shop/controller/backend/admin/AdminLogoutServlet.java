package edu.nlu.pharmacy_shop.controller.backend.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminLogoutServlet", value = "/backend/logout")
public class AdminLogoutServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7975010655683432840L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("id");
        session.removeAttribute("email");
        session.removeAttribute("firstName");
        session.removeAttribute("lastName");
        session.removeAttribute("imagePath");

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
