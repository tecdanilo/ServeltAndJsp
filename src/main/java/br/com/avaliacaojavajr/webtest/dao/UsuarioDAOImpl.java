package br.com.avaliacaojavajr.webtest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.avaliacaojavajr.webtest.db.Conexao;
import br.com.avaliacaojavajr.webtest.db.ConexaoUtils;
import br.com.avaliacaojavajr.webtest.dto.UsuarioDTO;

public class UsuarioDAOImpl implements UsuarioDAO {
	
	private static Logger logger = LoggerFactory.getLogger(UsuarioDAOImpl.class);
	
	private Conexao con = null;
	
	public UsuarioDAOImpl() {
	}
	
	public boolean inserir(UsuarioDTO user){
		Boolean retorno = false;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder("INSERT INTO usuario (nome, email, senha, idtipousuario) VALUES ( ? , ? , ? , ? )");

		try{
			
			conn = new Conexao().getCon();
			
			pstm = conn.prepareStatement(sql.toString());
			
			////implementar
			pstm.setObject(1, user.getNome());
			pstm.setObject(2, user.getEmail());
			pstm.setObject(3, user.getSenha());
			pstm.setObject(4, user.getTipo().getId());
			
			
			retorno = pstm.executeUpdate() > 0;
			
			return retorno;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally {
			ConexaoUtils.closeConnection(conn, pstm, rs);
		}
		
	}
	
	public boolean atualizar(UsuarioDTO user){
		Boolean retorno = false;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder()
		.append("UPDATE usuario SET nome = ? , email = ?, senha = ?, idtipousuario = ? WHERE id = ? ");

		try{
			
			conn = new Conexao().getCon();
			
			pstm = conn.prepareStatement(sql.toString());
			
			//implmentar
			pstm.setObject(1, user.getNome());
			pstm.setObject(2, user.getEmail());
			pstm.setObject(3, user.getSenha());
			pstm.setObject(4, user.getTipo().getId());
			pstm.setObject(5, user.getId());
			
			retorno = pstm.executeUpdate() > 0;
			
			return retorno;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally {
			ConexaoUtils.closeConnection(conn, pstm, rs);
		}
	}
	
	public UsuarioDTO consultaPorId(Long id){
		UsuarioDTO retorno = new UsuarioDTO();
		StringBuilder sql = new StringBuilder();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try{
			con = new Conexao();
			sql.append("SELECT u.id, u.nome, u.email, u.senha, u.idtipousuario, tu.descricao FROM usuario as u INNER JOIN tipo_usuario as tu ON tu.id = u.idtipousuario WHERE u.id = ?");
			
			pst = con.getCon().prepareStatement(sql.toString());
			pst.setObject(1, id);
			
			rs = pst.executeQuery();
			
			/* 
			 * verifica se há um pelo menos
			 */
			if ( rs.next() ) {
				retorno.setId( rs.getLong("id") );
				retorno.setNome( rs.getString( "nome" ) );
				retorno.setEmail( rs.getString( "email" ));
				retorno.setSenha( rs.getString( "senha") );
				retorno.getTipo().setId( rs.getLong("idtipousuario") );
				retorno.getTipo().setDescricao( rs.getString( "descricao" ) );
			}
			
			return retorno;
			
		}catch(Exception e){
			logger.error("Erro ao consultar usuario",e);
			return null;
		}finally {
			ConexaoUtils.closeConnection(con.getCon(), pst, rs);
		}
	}
	
	public List<UsuarioDTO> listaTudo(){
		List<UsuarioDTO> retorno = new ArrayList<UsuarioDTO>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder("SELECT u.id, u.nome, u.email, u.senha, u.idtipousuario, tu.descricao FROM usuario as u INNER JOIN tipo_usuario as tu ON tu.id = u.idtipousuario");
		
		try{
			conn = new Conexao().getCon();
			pstm = conn.prepareStatement(sql.toString());
			rs = pstm.executeQuery();
			
			while (rs.next()){
				UsuarioDTO user = new UsuarioDTO();
				user.setId(rs.getLong("id"));
				user.setNome(rs.getString("nome"));
				user.setSenha(rs.getString("senha"));
				user.setEmail(rs.getString("email"));
				//user.setTipo(new TipoUsuarioDTO()); //T ODO Trazer o tipo na consulta FEITO
				user.getTipo().setId( rs.getLong("idtipousuario") );
				user.getTipo().setDescricao( rs.getString("descricao") );
				
				retorno.add(user);
			}
			return retorno;
		}catch(Exception e){
			logger.error("Erro ao consultar todos usuario ",e);
			return null;
		}finally {
			ConexaoUtils.closeConnection(conn, pstm, rs);
		}
		
	}

	public boolean deletarPorId(Long id) {
		Boolean retorno = false;
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = new Conexao().getCon();
			pstm = conn.prepareStatement("DELETE FROM usuario WHERE id = ?");
			
			pstm.setObject(1, id );
			
			retorno = pstm.executeUpdate() > 0;
			
			return retorno;
			
		} catch (SQLException e) {
			logger.error("Erro ao excluir usuario com id " + id ,e);
			return false;
		}finally {
			ConexaoUtils.closeConnection(conn, pstm, null);
		}
	}

	public UsuarioDTO login(UsuarioDTO user) {
		UsuarioDTO retorno = null;
		StringBuilder sql = new StringBuilder();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try{
			con = new Conexao();
			sql.append("SELECT u.id, u.nome, u.email, u.senha, u.idtipousuario, tu.descricao FROM usuario as u INNER JOIN tipo_usuario as tu ON tu.id = u.idtipousuario WHERE u.email = ? AND u.senha = ? ");
			
			pst = con.getCon().prepareStatement(sql.toString());
			pst.setObject(1, user.getEmail());
			pst.setObject(2, user.getSenha());
			
			
			rs = pst.executeQuery();
			
			/* 
			 * verifica se há um pelo menos
			 */
			if ( rs.next() ) {
				retorno = new UsuarioDTO();
				
				retorno.setId( rs.getLong("id") );
				retorno.setNome( rs.getString( "nome" ) );
				retorno.setEmail( rs.getString( "email" ));
				retorno.setSenha( rs.getString( "senha") );
				retorno.getTipo().setId( rs.getLong("idtipousuario") );
				retorno.getTipo().setDescricao( rs.getString( "descricao" ) );
			}
			
			return retorno;
			
		}catch(Exception e){
			logger.error("Erro ao verificar login usuario",e);
			return null;
		}finally {
			ConexaoUtils.closeConnection(con.getCon(), pst, rs);
		}
	}

	
	
}
