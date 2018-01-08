package br.com.avaliacaojavajr.webtest.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.avaliacaojavajr.webtest.dao.TipoUsuarioDAO;
import br.com.avaliacaojavajr.webtest.dao.TipoUsuarioDAOImpl;
import br.com.avaliacaojavajr.webtest.dao.UsuarioDAO;
import br.com.avaliacaojavajr.webtest.dao.UsuarioDAOImpl;
import br.com.avaliacaojavajr.webtest.dto.TipoUsuarioDTO;
import br.com.avaliacaojavajr.webtest.dto.UsuarioDTO;

/**
 * Servlet implementation class AreaUsuarioSerlvet
 */
public class UsuarioSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String INSERT_OR_EDIT = "/cadUsuario.jsp";
    private static final String LIST_USER = "/listUsuario.jsp";
    private static final String LOGIN = "/login.jsp";
    private static final String USUARIO_LOGADO_SESSION = "usuarioLogado";
    private UsuarioDAO dao;
    private TipoUsuarioDAO tpUserDao;
       
    public UsuarioSerlvet() {
        super();
    }
    
    public UsuarioDAO getDaoInstance(){
    	if (dao == null){
    		dao = new UsuarioDAOImpl();
    	}
    	return dao;
    }
    
    public TipoUsuarioDAO getTipoUsuarioDaoInstance(){
    	if (tpUserDao == null){
    		tpUserDao = new TipoUsuarioDAOImpl();
    	}
    	return tpUserDao;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward="";
		String mensagem = "";
        String action = request.getParameter("action");
        Long userId = null;
       
        
        HttpSession session = request.getSession();
        UsuarioDTO logado = (UsuarioDTO) session.getAttribute(USUARIO_LOGADO_SESSION);
        
        if ( logado != null) {
            
        	if (action.equalsIgnoreCase("delete")){
            	userId = Long.parseLong(request.getParameter("id"));
            	
            	
                boolean alterouBanco;

                // Implementatar
            	alterouBanco = getDaoInstance().deletarPorId(userId);
                
                forward = LIST_USER;
                request.setAttribute("users", getDaoInstance().listaTudo());
                
                if (alterouBanco) {
					mensagem = "Usuário deletado com sucesso";
				} else {
					mensagem = "Erro ao deletar uruário";
				}
                request.setAttribute("mensagem", mensagem);
                
            } else if (action.equalsIgnoreCase("edit")){
                forward = INSERT_OR_EDIT;
                if (request.getParameter("id") != null){
    	            
                	userId = Long.parseLong(request.getParameter("id"));
    	            
                	UsuarioDTO user = getDaoInstance().consultaPorId(userId);
    	            // implementar
    	            request.setAttribute("user", user);
                }
                List<TipoUsuarioDTO> tipos = getTipoUsuarioDaoInstance().listaTudo();
                request.setAttribute("tipos", tipos);
                
            } else if (action.equalsIgnoreCase("listUsr")){
                forward = LIST_USER;
                request.setAttribute("users", getDaoInstance().listaTudo());
            } else {
                forward = INSERT_OR_EDIT;
            }
        } else {
        	forward = LOGIN;
        	mensagem = "Você não está logado no sistema!";
        }
        request.setAttribute("mensagem", mensagem);

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
	}
	
	private UsuarioDTO getUsuarioByFormuario(HttpServletRequest request) {
		UsuarioDTO user = new UsuarioDTO();
		
		String idString = request.getParameter("id");
		
		if ( idString != null && !"".equals(idString) && !"null".equals(idString) ) {
			user.setId( Long.valueOf(idString) );
		}
		
		user.setEmail( request.getParameter("email") );
		user.setSenha( request.getParameter("senha") );
		user.setNome( request.getParameter("nome") );
		
		String idTipoStr = request.getParameter("tipoUsuario"); 
		
		
		user.getTipo().setId( Long.valueOf( idTipoStr) );
		
		return user;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		String forward = "";
		String mensagem = null;
		
		UsuarioDTO user = null;
		boolean alterouBanco = false;
		
		HttpSession session = request.getSession();
        UsuarioDTO logado = (UsuarioDTO) session.getAttribute(USUARIO_LOGADO_SESSION);
        
        if ( logado != null) {
        	
        	if (request.getParameter("action").equals("insert")){
        		
        		//implementar
        		user = getUsuarioByFormuario(request);
        		
        		alterouBanco = getDaoInstance().inserir(user);
        		
        		forward = LIST_USER;
        		request.setAttribute("users", getDaoInstance().listaTudo());
        		
        		if (alterouBanco) {
					mensagem = "Usuário cadastrado com sucesso";
				} else {
					mensagem = "Erro ao cadastrar uruário";
				}
        		request.setAttribute("mensagem", mensagem);
        		
        	} else if(request.getParameter("action").equals("update")){
        		
        		//implementar
        		user = getUsuarioByFormuario(request);
        		
        		alterouBanco = getDaoInstance().atualizar(user);
        		
        		if (alterouBanco) {
					mensagem = "Usuário editado com sucesso";
				} else {
					mensagem = "Erro editar usuário";
				}
        		
        		forward = LIST_USER;
        		request.setAttribute("users", getDaoInstance().listaTudo());
        		
        	}
        	
        } else {
        	forward = LOGIN;
        }
        
        request.setAttribute("mensagem", mensagem);
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
	}
	
	
	
	

}
