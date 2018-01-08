package br.com.avaliacaojavajr.webtest.dto;

public class TipoUsuarioDTO {
	
	private Long id;
	private String Descricao;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return Descricao;
	}
	public void setDescricao(String descricao) {
		Descricao = descricao;
	}
	@Override
	public String toString() {
		return "TipoUsuarioDTO [id=" + id + ", Descricao=" + Descricao + "]";
	}
	
	
	
	
	

}
