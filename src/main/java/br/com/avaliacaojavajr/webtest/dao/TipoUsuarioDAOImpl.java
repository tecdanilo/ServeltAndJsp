package br.com.avaliacaojavajr.webtest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.avaliacaojavajr.webtest.db.Conexao;
import br.com.avaliacaojavajr.webtest.db.ConexaoUtils;
import br.com.avaliacaojavajr.webtest.dto.TipoUsuarioDTO;

public class TipoUsuarioDAOImpl implements TipoUsuarioDAO {
	
	public TipoUsuarioDAOImpl() {
	}
	
	public boolean inserir(TipoUsuarioDTO user){
		return true;
	}

	public boolean atualizar(TipoUsuarioDTO user){
		return true;
	}

	public TipoUsuarioDTO consultaPorId(Long id){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		/*
		 * Alterei aqui a query substitui * por id, descricao
		 */
		StringBuilder sql = new StringBuilder("Select id, descricao from tipo_usuario where id = ? order by descricao");
		
		try{
			conn = new Conexao().getCon();
			pstm = conn.prepareStatement(sql.toString());
			pstm.setLong(1, id);
			
			rs = pstm.executeQuery();
			
			
			if (rs.next()){
				TipoUsuarioDTO user = new TipoUsuarioDTO();
				user.setId(rs.getLong("id"));
				user.setDescricao(rs.getString("descricao"));
				return user;
			}
			return null;
		}catch(Exception e){
			return null;
		}finally {
			ConexaoUtils.closeConnection(conn, pstm, rs);
		}
		
	}

	public List<TipoUsuarioDTO> listaTudo(){
		
		List<TipoUsuarioDTO> retorno = new ArrayList<TipoUsuarioDTO>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder("Select id, descricao from tipo_usuario");
		
		try{
			conn = new Conexao().getCon();
			pstm = conn.prepareStatement(sql.toString());
			rs = pstm.executeQuery();
			
			while (rs.next()){
				TipoUsuarioDTO tpUser = new TipoUsuarioDTO();
				tpUser.setId(rs.getLong("id"));
				tpUser.setDescricao(rs.getString("descricao"));
				retorno.add(tpUser);
			}
			return retorno;
		}catch(Exception e){
			return null;
		}finally {
			ConexaoUtils.closeConnection(conn, pstm, rs);
		}
		
	}

}
