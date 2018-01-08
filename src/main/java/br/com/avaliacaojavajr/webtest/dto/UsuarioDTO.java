package br.com.avaliacaojavajr.webtest.dto;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {
	
	private static final long serialVersionUID = 1399606181910882625L;
	
	private Long id;
	private String nome;
	private String email;
	private String senha;
	private TipoUsuarioDTO tipo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public TipoUsuarioDTO getTipo() {
		if ( tipo == null) {
			tipo = new TipoUsuarioDTO();
		}
		
		return tipo;
	}
	public void setTipo(TipoUsuarioDTO tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", tipo=" + tipo
				+ "]";
	}
	
	

}
