package acao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NavegaMenu implements Acao {
    
    @Override
    public String Executa(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        return "menu.jsp";
    }
}