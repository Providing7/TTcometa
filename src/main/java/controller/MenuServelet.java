package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/menu/navegar")
public class MenuServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
		
		request.getRequestDispatcher("/menu.jsp").forward(request, response);
	}
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String destino = request.getParameter("destino");

        if (destino != null && !destino.trim().isEmpty()) {
            
        	request.getRequestDispatcher("/" + destino).forward(request, response);
            
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}
