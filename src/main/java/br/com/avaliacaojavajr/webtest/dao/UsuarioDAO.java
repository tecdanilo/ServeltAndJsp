package br.com.avaliacaojavajr.webtest.dao;

import java.util.List;

import br.com.avaliacaojavajr.webtest.dto.UsuarioDTO;

public interface UsuarioDAO {

	boolean inserir(UsuarioDTO user);

	boolean atualizar(UsuarioDTO user);
	
	boolean deletarPorId(Long id);
	
	UsuarioDTO login(UsuarioDTO user);

	UsuarioDTO consultaPorId(Long id);

	List<UsuarioDTO> listaTudo();

}