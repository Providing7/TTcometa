package acao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Acao {
    
    // O método executa DEVE lançar as exceções
    public String Executa(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException;
}