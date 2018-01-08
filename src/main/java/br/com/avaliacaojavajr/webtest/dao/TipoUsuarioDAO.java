package br.com.avaliacaojavajr.webtest.dao;

import java.util.List;

import br.com.avaliacaojavajr.webtest.dto.TipoUsuarioDTO;

public interface TipoUsuarioDAO {

	boolean inserir(TipoUsuarioDTO user);

	boolean atualizar(TipoUsuarioDTO user);

	TipoUsuarioDTO consultaPorId(Long id);

	List<TipoUsuarioDTO> listaTudo();

}