package br.com.avaliacaojavajr.webtest.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.avaliacaojavajr.webtest.dto.UsuarioDTO;


public class UsuarioLogadoFilter implements Filter {
	
	@SuppressWarnings("unused")
	private String contextPath = null;

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
 
        UsuarioDTO u = (UsuarioDTO) session.getAttribute("usuarioLogado");
        if (u == null) {
            //session.invalidate();
            session.setAttribute("mensagem","Você não está logado no sistema!");
            //res.sendRedirect(contextPath + "/login");
            RequestDispatcher view = request.getRequestDispatcher("/login");
            view.forward(request, response);
            
        } else {
            res.setHeader("Cache-control", "no-cache, no-store");
            res.setHeader("Pragma", "no-cache");
            res.setHeader("Expires", "-1");
            filterChain.doFilter(request, response);
        }
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.contextPath = filterConfig.getServletContext().getContextPath();
	}

}
