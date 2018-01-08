package br.com.avaliacaojavajr.webtest.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.avaliacaojavajr.webtest.dao.UsuarioDAO;
import br.com.avaliacaojavajr.webtest.dao.UsuarioDAOImpl;
import br.com.avaliacaojavajr.webtest.dto.UsuarioDTO;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO dao;
	private static final String INDEX = "/index.jsp";
	private static final String LOGIN = "/login.jsp";
	private static final String USUARIO_LOGADO_SESSION = "usuarioLogado";
       
    public LoginServlet() {
        super();
    }
    
    public UsuarioDAO getDaoInstance(){
    	if (dao == null){
    		dao = new UsuarioDAOImpl();
    	}
    	return dao;
    }
    

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute(USUARIO_LOGADO_SESSION, null);
		
		String forward = LOGIN;
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* implementar autenticao aqui e redirecionar para a index caso o login seja valido, caso contrario, 
		 * manter na tela de login com mensagem de erro ao usuario
		 */
		
		String email = request.getParameter("txtEmail");
		String senha = request.getParameter("txtSenha");
		
		String forward = null;
		
		UsuarioDTO user = new UsuarioDTO();
		
		user.setEmail(email);
		user.setSenha(senha);
		
		user = getDaoInstance().login(user);
		HttpSession session = request.getSession();
		
		if ( user != null ) {
			forward = INDEX;
			session.setAttribute(USUARIO_LOGADO_SESSION, user);
		} else {
			forward = LOGIN;
			request.setAttribute("mensagem","Usuário e senha não conferem");
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
	}

}
