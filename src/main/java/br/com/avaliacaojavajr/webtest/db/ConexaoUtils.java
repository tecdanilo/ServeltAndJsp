package br.com.avaliacaojavajr.webtest.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import br.com.avaliacaojavajr.webtest.util.SistemaUtil;

public class ConexaoUtils {
	
	private static Logger logger = Logger.getLogger(ConexaoUtils.class);
	
	private static void closeConnection(Connection conn){
		try{
			if(SistemaUtil.isNotNull(conn)){
				conn.close();
			}
		}catch(Exception e){
			logger.error("contexto:",e);
		}
	}
	
	private static void closeConnection(Connection conn, PreparedStatement pstm){
		try{
			if(SistemaUtil.isNotNull(pstm)){
				pstm.close();
			}
				closeConnection(conn);
		}catch(Exception e){
			logger.error("contexto:",e);
		}
	}
	
	public static void closeConnection(Connection conn, PreparedStatement pstm, ResultSet rs){
		try{
			if(SistemaUtil.isNotNull(rs)){
				rs.close();
			}
			closeConnection(conn, pstm);
		}catch(Exception e){
			logger.error("contexto:",e);
		}
	}
	
	
	

}
